// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climberRotate = new CANSparkMax(Constants.ClimberRotateSparkMaxID, MotorType.kBrushless);
  public CANSparkMax climberLift = new CANSparkMax(Constants.ClimberLiftSparkMaxID, MotorType.kBrushless);
  
  public DoubleSolenoid climberLock = new DoubleSolenoid(1,2,3); 
  public DoubleSolenoid climberValve = new DoubleSolenoid(Constants.ClimberOpenSolenoidID, 0, Constants.ClimberCloseSolenoidID);  
  
  public void rotateClimber(final ClimberAngles angle) {
    
  }

}
