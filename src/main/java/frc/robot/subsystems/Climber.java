// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
// import frc.robot.commands.Climb.ClimbLock;
// import frc.robot.commands.Climb.ClimbValve;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public CANSparkMax climbRotate = new CANSparkMax(Constants.ClimberRotateSparkMaxID, MotorType.kBrushless);
  public DoubleSolenoid climbLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberLiftUPSolenoidID, Constants.ClimberLiftDOWNSolenoidID);
  public DoubleSolenoid climbFApproach = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbFApproachGO, Constants.ClimberFApproachLEAVE); 
  public DoubleSolenoid climbFClose = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbFCloseGO, Constants.ClimbFCloseLEAVE); 
  public DoubleSolenoid climbFLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbFLock, Constants.climbFUnlock);   
  public DoubleSolenoid climbBApproach = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBApproachGO, Constants.climbBApproachLEAVE); 
  public DoubleSolenoid climbBClose = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBCloseGO, Constants.climbBCloseLEAVE); 
  public DoubleSolenoid climbBLock = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBLock, Constants.climbBUnlock); 

  public void setRotateSpeed(double speed) {
    if (Math.abs(speed) < Constants.kJoystickRollerDeadzone) {
      speed = 0.0; 
    }
    climbRotate.set(ControlMode.PercentOutput, speed); 
  }


  // public ClimbLock m_climblock;
  // public ClimbValve m_climbvalve;
  public IO io; 

  public Climber() {

  }

}

