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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

class ClimberState { // TODO: implement Sendable
  public DoubleSolenoid.Value Hook1State;
  public DoubleSolenoid.Value Hook2State;
  public DoubleSolenoid.Value Hook3State;
  public DoubleSolenoid.Value Hook4State;
  public DoubleSolenoid.Value Lock1State;
  public DoubleSolenoid.Value Lock2State;
  public DoubleSolenoid.Value RaiseState;
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
  DoubleSolenoid raise;

  ClimberState state = new ClimberState();

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

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
    raise = new DoubleSolenoid(
        Constants.Climber.kRaise.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kRaise.forwardChannel,
        Constants.Climber.kRaise.reverseChannel);

    climbRotate1 = new CANSparkMax(Constants.Climber.kRotateLeaderSparkMaxID, MotorType.kBrushless);
    climbRotate2 = new CANSparkMax(Constants.Climber.kRotateFollowerSparkMaxID, MotorType.kBrushless);

    climbRotate1.restoreFactoryDefaults();
    climbRotate2.restoreFactoryDefaults();

    climbRotate1.setInverted(false);
    // climbRotate2.setInverted(false);
    // TODO: follow wasn't working...why??
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

    // // PID coefficients
    // kP = 0.1;
    // kI = 1e-4;
    // kD = 1;
    // kIz = 0;
    // kFF = 0;
    // kMaxOutput = 0.5;
    // kMinOutput = -0.5;

    // set PID coefficients
    pidController.setP(Constants.Climber.kGainsUnloaded.kP);
    pidController.setI(Constants.Climber.kGainsUnloaded.kI);
    pidController.setD(Constants.Climber.kGainsUnloaded.kD);
    pidController.setIZone(Constants.Climber.kGainsUnloaded.kIzone);
    pidController.setFF(Constants.Climber.kGainsUnloaded.kF);
    pidController.setOutputRange(-1 * Constants.Climber.kGainsUnloaded.kPeakOutput,
        Constants.Climber.kGainsUnloaded.kPeakOutput);

    // // display PID coefficients on SmartDashboard
    // SmartDashboard.putNumber("P Gain", kP);
    // SmartDashboard.putNumber("I Gain", kI);
    // SmartDashboard.putNumber("D Gain", kD);
    // SmartDashboard.putNumber("I Zone", kIz);
    // SmartDashboard.putNumber("Feed Forward", kFF);
    // SmartDashboard.putNumber("Max Output", kMaxOutput);
    // SmartDashboard.putNumber("Min Output", kMinOutput);
    // SmartDashboard.putNumber("Set Rotations", 0);
    // pidController.setReference(0, ControlType.kPosition);

    // TODO: set default states for solenoids
    hook1.set(Value.kForward);
    hook2.set(Value.kForward);
    hook3.set(Value.kReverse);
    hook4.set(Value.kForward);

    lock1.set(Value.kReverse);
    lock2.set(Value.kForward);

    raise.set(Value.kReverse);

    climbRotate1.burnFlash();
    climbRotate2.burnFlash();
  }

  public void rotateToMid() {
    double setpoint = Constants.Climber.degreesToRotations(Constants.Climber.kMidDegrees);
    pidController.setReference(setpoint, ControlType.kPosition);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }

  public void rotateToHigh() {
    double setpoint = Constants.Climber.degreesToRotations(Constants.Climber.kHighDegrees);
    pidController.setReference(setpoint, ControlType.kPosition);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }

  public void rotateToTraversal() {
    double setpoint = Constants.Climber.degreesToRotations(Constants.Climber.kTraversalDegrees);
    pidController.setReference(Constants.Climber.kTraversalDegrees, ControlType.kPosition);
    pidController.setReference(setpoint, ControlType.kPosition);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }

  public void rotateToRest() {
    double setpoint = Constants.Climber.degreesToRotations(Constants.Climber.kRestDegrees);
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

  public void toggleHook1() {
    hook1.toggle();
    state.Hook1State = hook1.get();
  }

  public void toggleHook2() {
    hook2.toggle();
    state.Hook2State = hook2.get();
  }

  public void toggleHook3() {
    hook3.toggle();
    state.Hook3State = hook3.get();
  }

  public void toggleHook4() {
    hook4.toggle();
    state.Hook4State = hook4.get();
  }

  public void toggleLock1() {
    lock1.toggle();
    state.Lock1State = lock1.get();
  }

  public void toggleLock2() {
    lock2.toggle();
    state.Lock2State = lock2.get();
  }

  public void toggleRaise() {
    raise.toggle();
    state.RaiseState = raise.get();
  }

  // changes solenoid value to string
  public String doubleSolenoidValueToString(DoubleSolenoid.Value state) {
    switch (state) {
      case kForward:
        return "forward";
      case kReverse:
        return "reverse";
      case kOff:
        return "off";
      default:
        return "unknown";
    }
  }

  public void periodic() {
    SmartDashboard.putBoolean("climber enabled", Constants.Climber.Enabled);
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
    // read PID coefficients from SmartDashboard
    // double p = SmartDashboard.getNumber("P Gain", 0);
    // double i = SmartDashboard.getNumber("I Gain", 0);
    // double d = SmartDashboard.getNumber("D Gain", 0);
    // double iz = SmartDashboard.getNumber("I Zone", 0);
    // double ff = SmartDashboard.getNumber("Feed Forward", 0);
    // double max = SmartDashboard.getNumber("Max Output", 0);
    // double min = SmartDashboard.getNumber("Min Output", 0);
    // double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to
    // // controller
    // if ((p != kP)) {
    // pidController.setP(p);
    // kP = p;
    // }
    // if ((i != kI)) {
    // pidController.setI(i);
    // kI = i;
    // }
    // if ((d != kD)) {
    // pidController.setD(d);
    // kD = d;
    // }
    // if ((iz != kIz)) {
    // pidController.setIZone(iz);
    // kIz = iz;
    // }
    // if ((ff != kFF)) {
    // pidController.setFF(ff);
    // kFF = ff;
    // }
    // if ((max != kMaxOutput) || (min != kMinOutput)) {
    // pidController.setOutputRange(min, max);
    // kMinOutput = min;
    // kMaxOutput = max;
    // }

    // pidController.setReference(rotations, CANSparkMax.ControlType.kPosition);

    // SmartDashboard.putNumber("SetPoint", rotations);
    SmartDashboard.putNumber("ProcessVariable", rotate1encoder.getPosition());
  }
}
