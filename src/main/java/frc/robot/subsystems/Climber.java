// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.locks.Lock;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

class ClimberState {
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
  // public CANSparkMax climbRotate1 = new CANSparkMax(Constants.ClimberRotate1SparkMaxID, MotorType.kBrushless);
  // public CANSparkMax climbRotate2 = new CANSparkMax(Constants.ClimberRotate2SparkMaxID, MotorType.kBrushless); 

  DoubleSolenoid hook1 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook1OpenSolenoidID, Constants.ClimbHook1CloseSolenoidID); 
  DoubleSolenoid hook2 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook2OpenSolenoidID, Constants.ClimbHook2CloseSolenoidID); 
  DoubleSolenoid hook3 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook3OpenSolenoidID, Constants.ClimbHook3CloseSolenoidID);   
  DoubleSolenoid hook4 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook4OpenSolenoidID, Constants.ClimbHook4CloseSolenoidID); 

  DoubleSolenoid lock1 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbLock1SolenoidID, Constants.ClimbUnlock1SolenoidID); 
  DoubleSolenoid lock2 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbLock2SolenoidID, Constants.ClimbUnlock2SolenoidID); 
  DoubleSolenoid raise = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbRaiseSolenoidID, Constants.ClimbLowerSolenoidID);

  ClimberState state = new ClimberState();

  public Climber() {
    // climbRotate1.setInverted(false);
    // climbRotate2.setInverted(true);
    // climbRotate2.follow(climbRotate1);

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
    speed = speed*speed;

    if (Math.abs(speed) < Constants.kJoystickRollerDeadzone) {
      speed = 0.0;
    }

    speed = Math.min(speed, Constants.kMaxRotateSpeed);

    // climbRotate1.set(speed);
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
}
