// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.commands.Climber.SetPosition;
import frc.robot.enums.Climb.HookPositions;
import frc.robot.enums.Climb.LockPositions;
import frc.robot.enums.Climb.ExtendPositions;

class ClimberState implements Sendable { 
  public HookPositions Hook1State = HookPositions.UNSET;
  public HookPositions Hook2State = HookPositions.UNSET;
  public HookPositions Hook3State = HookPositions.UNSET;
  public HookPositions Hook4State = HookPositions.UNSET;
  public LockPositions Lock1State = LockPositions.UNSET;
  public LockPositions Lock2State = LockPositions.UNSET;
  public ExtendPositions ExtendState = ExtendPositions.UNSET;

  Climber climber;

  ClimberState(Climber climber) {
    this.climber = climber;
  }

  public void initSendable(SendableBuilder builder) {
    builder.addStringProperty("Hook 1", () -> Hook1State.toString(), (s) -> {});
    builder.addStringProperty("Hook 2", () -> Hook2State.toString(), (s) -> {});
    builder.addStringProperty("Hook 3", () -> Hook3State.toString(), (s) -> {});
    builder.addStringProperty("Hook 4", () -> Hook4State.toString(), (s) -> {});
    builder.addStringProperty("Lock 1", () -> Lock1State.toString(), (s) -> {});
    builder.addStringProperty("Lock 2", () -> Lock2State.toString(), (s) -> {});
    builder.addStringProperty("Extend", () -> ExtendState.toString(), (s) -> {});
  }
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

  ClimberState state = new ClimberState(this);

  IO io;

  public Climber(IO io) {
    this.io = io;

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
        Constants.Climber.kLock1.reverseChannel);
    lock2 = new DoubleSolenoid(
        Constants.Climber.kLock2.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kLock2.forwardChannel,
        Constants.Climber.kLock2.reverseChannel);
    extend = new DoubleSolenoid(
        Constants.Climber.kExtend.pcmId,
        PneumaticsModuleType.CTREPCM,
        Constants.Climber.kExtend.forwardChannel,
        Constants.Climber.kExtend.reverseChannel);

    climbRotate1 = new CANSparkMax(Constants.Climber.kRotateLeaderSparkMaxID, MotorType.kBrushless);
    climbRotate2 = new CANSparkMax(Constants.Climber.kRotateFollowerSparkMaxID, MotorType.kBrushless);

    climbRotate1.restoreFactoryDefaults();
    climbRotate2.restoreFactoryDefaults();

    climbRotate1.setInverted(true);
    var err = climbRotate2.follow(climbRotate1, true);
    if (err != REVLibError.kOk) {
      System.out.println(err);
    }

    climbRotate1.setIdleMode(IdleMode.kBrake);
    climbRotate2.setIdleMode(IdleMode.kBrake);

    climbRotate1.set(0);

    rotate1encoder = climbRotate1.getEncoder();
    rotate2encoder = climbRotate2.getEncoder();
    pidController = climbRotate1.getPIDController();

    rotate1encoder.setPosition(0);
    rotate2encoder.setPosition(0);

    pidController.setP(Constants.Climber.kGainsLoaded.kP);
    pidController.setI(Constants.Climber.kGainsLoaded.kI);
    pidController.setD(Constants.Climber.kGainsLoaded.kD);
    pidController.setIZone(Constants.Climber.kGainsLoaded.kIzone);
    pidController.setFF(Constants.Climber.kGainsLoaded.kF);
    pidController.setOutputRange(-1 * Constants.Climber.kGainsLoaded.kPeakOutput,
        Constants.Climber.kGainsLoaded.kPeakOutput);

    setHook1(HookPositions.CLOSE);
    setHook2(HookPositions.CLOSE);
    setHook3(HookPositions.CLOSE);
    setHook4(HookPositions.CLOSE);

    ScheduledThreadPoolExecutor lockAfterDelay = new ScheduledThreadPoolExecutor(1);
    lockAfterDelay.schedule(() -> {
      setLock1(LockPositions.LOCK);
      setLock2(LockPositions.LOCK);
    }, 2, TimeUnit.SECONDS);

    setExtend(ExtendPositions.LOWER);

    climbRotate1.burnFlash();
    climbRotate2.burnFlash();

    SmartDashboard.putData("Climber State", state);

    SmartDashboard.putData("Lock L1", new SetPosition<LockPositions>(this::setLock1, LockPositions.LOCK));
    SmartDashboard.putData("Unlock L1", new SetPosition<LockPositions>(this::setLock1, LockPositions.UNLOCK));
    SmartDashboard.putData("Lock L2", new SetPosition<LockPositions>(this::setLock2, LockPositions.LOCK));
    SmartDashboard.putData("Unlock L2", new SetPosition<LockPositions>(this::setLock2, LockPositions.UNLOCK));

    SmartDashboard.putData("Open H1", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock1, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook1, HookPositions.OPEN)
    ));
    SmartDashboard.putData("Close H1", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock1, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook1, HookPositions.CLOSE)
    ));
    SmartDashboard.putData("Open H2", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock1, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook2, HookPositions.OPEN)
    ));
    SmartDashboard.putData("Close H2", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock1, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook2, HookPositions.CLOSE)
    ));
    SmartDashboard.putData("Open H3", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock2, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook3, HookPositions.OPEN)
    ));
    SmartDashboard.putData("Close H3", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock2, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook3, HookPositions.CLOSE)
    ));
    SmartDashboard.putData("Open H4", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock2, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook4, HookPositions.OPEN)
    ));
    SmartDashboard.putData("Close H4", new SequentialCommandGroup(
      new SetPosition<LockPositions>(this::setLock2, LockPositions.UNLOCK),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new SetPosition<HookPositions>(this::setHook4, HookPositions.CLOSE)
    ));

    SmartDashboard.putData("Raise Climber", new SetPosition<ExtendPositions>(this::setExtend, ExtendPositions.RAISE));
    SmartDashboard.putData("Lower Climber", new SetPosition<ExtendPositions>(this::setExtend, ExtendPositions.LOWER));

    SmartDashboard.putData("Rotate To 0", new InstantCommand(() -> rotateToDegrees(0.0), this));
  }

  public void zeroEncoders() {
    rotate1encoder.setPosition(0);
    rotate2encoder.setPosition(0);
  }

  public double getPositionDegrees() {
    return Constants.Climber.rotationsToDegrees(rotate1encoder.getPosition());
  }

  public void rotateToDegrees(Double degrees) {
    double setpoint = Constants.Climber.degreesToRotations(degrees);
    pidController.setReference(setpoint, ControlType.kPosition);
    SmartDashboard.putNumber("Rotate Setpoint", degrees);
  }

  public void setRotateSpeed(double speed) {
    double sign = Math.signum(speed);
    speed = sign * Math.pow(speed, 4);

    if (Math.abs(speed) < Constants.IO.kJoystickDeadzone) {
      speed = 0.0;
    }

    speed = sign * Math.min(Math.abs(speed), Constants.Climber.kRotateMaxSpeed);

    climbRotate1.set(speed);
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
    SmartDashboard.putNumber("Rotate Speed", climbRotate1.getAppliedOutput());
    SmartDashboard.putNumber("Rotate Position", Constants.Climber.rotationsToDegrees(rotate1encoder.getPosition()));

    SmartDashboard.putString("Extend Position", state.ExtendState.toString());
    SmartDashboard.putString("Hook1 Position", state.Hook1State.toString());
    SmartDashboard.putString("Hook2 Position", state.Hook2State.toString());
    SmartDashboard.putString("Hook3 Position", state.Hook3State.toString());
    SmartDashboard.putString("Hook4 Position", state.Hook4State.toString());
    SmartDashboard.putString("Lock1 Position", state.Lock1State.toString());
    SmartDashboard.putString("Lock2 Position", state.Lock2State.toString());
  }

  public void teleopPeriodic() {}
}
