// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
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

  DoubleSolenoid hook1;
  DoubleSolenoid hook2;
  DoubleSolenoid hook3;
  DoubleSolenoid hook4;

  DoubleSolenoid lock1;
  DoubleSolenoid lock2;
  DoubleSolenoid raise;

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
    climbRotate2.setInverted(false);
    // TODO: follow wasn't working...why??
    // climbRotate2.follow(climbRotate1, true);

    climbRotate1.burnFlash();
    climbRotate2.burnFlash();

    climbRotate1.set(0);

    climbRotate1.getEncoder().setPosition(0);
    climbRotate2.getEncoder().setPosition(0);

    // TODO: set default states for solenoids
    hook1.set(Value.kForward);
    hook2.set(Value.kForward);
    hook3.set(Value.kReverse);
    hook4.set(Value.kForward);

    lock1.set(Value.kReverse);
    lock2.set(Value.kForward);

    raise.set(Value.kReverse);
  }

  public void setRotateSpeed(double speed) {
    double sign = Math.signum(speed);
    speed = sign * Math.pow(speed, 4);

    if (Math.abs(speed) < Constants.IO.kJoystickDeadzone) {
      speed = 0.0;
    }

    speed = sign * Math.min(Math.abs(speed), Constants.Climber.kRotateMaxSpeed);

    climbRotate1.set(speed);
    climbRotate2.set(-1 * speed);
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
    SmartDashboard.putNumber("rotate 1 speed", climbRotate1.get());
    SmartDashboard.putNumber("rotate 2 speed", climbRotate2.get());
    SmartDashboard.putNumber("rotate 1 encoder", climbRotate1.getEncoder().getPosition());
    SmartDashboard.putNumber("rotate 2 encoder", climbRotate2.getEncoder().getPosition());
  }
}
