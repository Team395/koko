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
import frc.robot.enums.ClimberLock;
import frc.robot.enums.ClimberValve;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climberRotate = new CANSparkMax(Constants.ClimberRotateSparkMaxID, MotorType.kBrushless);
  public CANSparkMax climberLift = new CANSparkMax(Constants.ClimberLiftSparkMaxID, MotorType.kBrushless);
  
  public DoubleSolenoid climberLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberUnlockSolenoidId, Constants.ClimberLockSolenoidId); 
  public DoubleSolenoid climberValve = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberOpenSolenoidId, Constants.ClimberCloseSolenoidId);  
  
  public ClimberValve currentValve = ClimberValve.CLOSE;
  public ClimberLock currentAngle = ClimberLock.LOCK;
  //TODO set the rotation angle and lift angle. 


  //TODO sequence of climber 

  // public void rotateClimber(final ClimberAngles angle) {
    
  // }

}
