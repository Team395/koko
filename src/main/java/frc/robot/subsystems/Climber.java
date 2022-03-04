// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.commands.Climb;
import frc.robot.enums.ClimberLock;
import frc.robot.enums.ClimberValve;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climberRotate = new CANSparkMax(Constants.ClimberRotateSparkMaxID, MotorType.kBrushless);
  public CANSparkMax climberLift = new CANSparkMax(Constants.ClimberLiftSparkMaxID, MotorType.kBrushless);
  
  public DoubleSolenoid climberLockFront = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberUnlockFrontSolenoidId, Constants.ClimberLockFrontSolenoidId); 
  public DoubleSolenoid climberValveFront = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberOpenFrontSolenoidId, Constants.ClimberCloseFrontSolenoidId);  
  public DoubleSolenoid climberLockBack = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberUnlockBackSolenoidId, Constants.ClimberLockBackSolenoidId);

  public Climb m_climb;
  public IO io; 

  public Climber() {

  m_climb = new Climb(this, io);
  this.setDefaultCommand(m_climb);

  }


  //Climber Front 
  public void ClimberOpenF() {
    climberValveFront.set(Value.kReverse);
  }

  public void ClimberCloseF() {
    climberValveFront.set(Value.kForward);
  }

  public void ClimberUnlockF() {
    climberLockFront.set(Value.kReverse);
  }
  public void ClimberLockF() {
    climberLockFront.set(Value.kForward);
  }


  //Climber Back

  public void ClimberUnlockB() {
    climberLockBack.set(Value.kReverse);
  }
  public void ClimberLockB() {
    climberLockBack.set(Value.kForward);
  }
 
}

