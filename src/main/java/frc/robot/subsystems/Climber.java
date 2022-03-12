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
import frc.robot.IO;
import frc.robot.commands.ClimbLock;
import frc.robot.commands.ClimbValve;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climberRotate = new CANSparkMax(Constants.ClimberRotateSparkMaxID, MotorType.kBrushless);
  public CANSparkMax climberLift = new CANSparkMax(Constants.ClimberLiftSparkMaxID, MotorType.kBrushless);
  
  public DoubleSolenoid climberFrontLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberFrontUnlockSolenoidID, Constants.ClimberFrontLockSolenoidID); 
  public DoubleSolenoid climberFrontValve = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberFrontOpenSolenoidID, Constants.ClimberFrontCloseSolenoidID);  
  public DoubleSolenoid climberBackLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberBackUnlockSolenoidID, Constants.ClimberBackLockSolenoidID);
  public DoubleSolenoid climberBackValve = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberBackOpenSolenoidID, Constants.ClimberBackCloseSolenoidID);
  public DoubleSolenoid climberElevate = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberElevateUpSolenoidID, Constants.ClimberElevateDownSolenoidID);
  //5th elevate solenoid


  public ClimbLock m_climblock;
  public ClimbValve m_climbvalve;
  public IO io; 

  public Climber() {

  // // m_climb = new ClimbLock(this, io, null, null);
  // // this.setDefaultCommand(m_climb);

  }

}

