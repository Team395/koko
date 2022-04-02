// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.enums.Climb.HookPositions;
import frc.robot.enums.Climb.LockPositions;
import frc.robot.enums.Climb.ExtendPositions;

class ClimberState { // TODO: implement Sendable
  public HookPositions Hook1State;
  public HookPositions Hook2State;
  public HookPositions Hook3State;
  public HookPositions Hook4State;
  public LockPositions Lock1State;
  public LockPositions Lock2State;
  public ExtendPositions ExtendState;
}

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climbRotate1;
  public CANSparkMax climbRotate2;

  public SparkMaxPIDController pidController;
  public RelativeEncoder rotate1encoder;
  public RelativeEncoder rotate2encoder;

  DoubleSolenoid hook1;
  DoubleSolenoid hook2;
  DoubleSolenoid hook3;
  DoubleSolenoid hook4;

  DoubleSolenoid lock1;
  DoubleSolenoid lock2;
  DoubleSolenoid extend;

  ClimberState state = new ClimberState();

  public Climber() {
    hook1 = new DoubleSolenoid(
        Constants.Climber.kHook1.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kHook1.forwardChannel,
        Constants.Climber.kHook1.reverseChannel);
    hook2 = new DoubleSolenoid(
        Constants.Climber.kHook2.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kHook2.forwardChannel,
        Constants.Climber.kHook2.reverseChannel);
    hook3 = new DoubleSolenoid(
        Constants.Climber.kHook3.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kHook3.forwardChannel,
        Constants.Climber.kHook3.reverseChannel);
    hook4 = new DoubleSolenoid(
        Constants.Climber.kHook4.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kHook4.forwardChannel,
        Constants.Climber.kHook4.reverseChannel);

    lock1 = new DoubleSolenoid(
        Constants.Climber.kLock1.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kLock1.forwardChannel,
        Constants.Climber.kLock1.forwardChannel);
    lock2 = new DoubleSolenoid(
        Constants.Climber.kLock2.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kLock2.forwardChannel,
        Constants.Climber.kLock2.forwardChannel);
    extend = new DoubleSolenoid(
        Constants.Climber.kRaise.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kRaise.forwardChannel,
        Constants.Climber.kRaise.reverseChannel);

    climbRotate1 = new CANSparkMax(Constants.Climber.kRotateLeaderSparkMaxID, MotorType.kBrushless);
    climbRotate2 = new CANSparkMax(Constants.Climber.kRotateFollowerSparkMaxID, MotorType.kBrushless);

    climbRotate1.restoreFactoryDefaults();
    climbRotate2.restoreFactoryDefaults();

    climbRotate1.setInverted(false);
    var err = climbRotate2.follow(climbRotate1, true);
    if (err != REVLibError.kOk) {
      System.out.println(err);
    }

    climbRotate1.set(0);

    rotate1encoder = climbRotate1.getEncoder();
    rotate2encoder = climbRotate2.getEncoder();
    pidController = climbRotate1.getPIDController();

    rotate1encoder.setPosition(0);
    rotate2encoder.setPosition(0);

    pidController.setP(Constants.Climber.kGainsUnloaded.kP);
    pidController.setI(Constants.Climber.kGainsUnloaded.kI);
    pidController.setD(Constants.Climber.kGainsUnloaded.kD);
    pidController.setIZone(Constants.Climber.kGainsUnloaded.kIzone);
    pidController.setFF(Constants.Climber.kGainsUnloaded.kF);
    pidController.setOutputRange(-1 * Constants.Climber.kGainsUnloaded.kPeakOutput,
        Constants.Climber.kGainsUnloaded.kPeakOutput);

    hook2.set(Constants.Climber.Hook.kClose);
    hook1.set(Constants.Climber.Hook.kClose);
    hook3.set(Constants.Climber.Hook.kClose);
    hook4.set(Constants.Climber.Hook.kClose);

    lock1.set(Constants.Climber.Lock.kLock);
    lock2.set(Constants.Climber.Lock.kLock);

    extend.set(Constants.Climber.Extend.kLower);

    climbRotate1.burnFlash();
    climbRotate2.burnFlash();
  }

  public void zeroEncoders() {
    rotate1encoder.setPosition(0);
    rotate2encoder.setPosition(0);
  }

  public void rotateToDegrees(Double degrees) {
    double setpoint = Constants.Climber.degreesToRotations(degrees);
    pidController.setReference(setpoint, ControlType.kPosition);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }

  public void setRotateSpeed(double speed) {
    double sign = Math.signum(speed);
    speed = sign * Math.pow(speed, 4);

    if (Math.abs(speed) < Constants.IO.kJoystickDeadzone) {
      speed = 0.0;
    }

    speed = sign * Math.min(Math.abs(speed), Constants.Climber.kRotateMaxSpeed);

    // climbRotate1.set(speed);
    // climbRotate2.set(-1 * speed);
  }

  public void setHook1(HookPositions position) {
    hook1.set(Constants.Climber.Hook.kMap.get(position));
    state.Hook1State = position;
  }

  public void setHook2(HookPositions position) {
    hook2.set(Constants.Climber.Hook.kMap.get(position));
    state.Hook2State = position;
  }

  public void setHook3(HookPositions position) {
    hook3.set(Constants.Climber.Hook.kMap.get(position));
    state.Hook3State = position;
  }

  public void setHook4(HookPositions position) {
    hook4.set(Constants.Climber.Hook.kMap.get(position));
    state.Hook4State = position;
  }

  public void setLock1(LockPositions position) {
    lock1.set(Constants.Climber.Lock.kMap.get(position));
    state.Lock1State = position;
  }

  public void setLock2(LockPositions position) {
    lock2.set(Constants.Climber.Lock.kMap.get(position));
    state.Lock2State = position;
  }

  public void setExtend(ExtendPositions position) {
    extend.set(Constants.Climber.Extend.kMap.get(position));
    state.ExtendState = position;
  }


  public void periodic() {
    SmartDashboard.putNumber("rotate 1 speed", climbRotate1.getAppliedOutput());
    SmartDashboard.putNumber("rotate 2 speed", climbRotate2.getAppliedOutput());
    SmartDashboard.putNumber("rotate 1 encoder", rotate1encoder.getPosition());
    SmartDashboard.putNumber("rotate 2 encoder", rotate2encoder.getPosition());

    SmartDashboard.putNumber("Set P Gain", pidController.getP());
    SmartDashboard.putNumber("Set I Gain", pidController.getI());
    SmartDashboard.putNumber("Set D Gain", pidController.getD());
    SmartDashboard.putNumber("Set I Zone", pidController.getIZone());
    SmartDashboard.putNumber("Set Feed Forward", pidController.getFF());
    SmartDashboard.putNumber("Set Max Output", pidController.getOutputMax());
    SmartDashboard.putNumber("Set Min Output", pidController.getOutputMin());
  }

  public void teleopPeriodic() {
  }
}
