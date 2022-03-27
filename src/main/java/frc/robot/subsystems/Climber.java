// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climbRotate1 = new CANSparkMax(Constants.ClimberRotate1SparkMaxID, MotorType.kBrushless);
  public CANSparkMax climbRotate2 = new CANSparkMax(Constants.ClimberRotate2SparkMaxID, MotorType.kBrushless); 

  public DoubleSolenoid Hook1 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook1OpenSolenoidID, Constants.ClimbHook1CloseSolenoidID); 
  public DoubleSolenoid Hook2 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook2OpenSolenoidID, Constants.ClimbHook2CloseSolenoidID); 
  public DoubleSolenoid Hook3 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook3OpenSolenoidID, Constants.ClimbHook3CloseSolenoidID);   
  public DoubleSolenoid Hook4 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Constants.ClimbHook4OpenSolenoidID, Constants.ClimbHook4CloseSolenoidID); 
  
  public DoubleSolenoid Lock5 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbLock5SolenoidID, Constants.ClimbUnlock5SolenoidID); 
  public DoubleSolenoid Lock6 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbLock6SolenoidID, Constants.ClimbUnlock6SolenoidID); 
  public DoubleSolenoid climbLift = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, Constants.ClimbRaiseSolenoidID, Constants.ClimbLowerSolenoidID);

  public Climber() {
    climbRotate1.setInverted(false);
    climbRotate2.setInverted(true);
    climbRotate2.follow(climbRotate1);
  }

  public void setRotateSpeed(double speed) {
    if (Math.abs(speed) < Constants.kJoystickRollerDeadzone) {
      speed = 0.0; 
    }
    climbRotate1.set(speed); 
  }

}
