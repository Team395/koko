// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
// import frc.robot.commands.Climb.ClimbLock;
// import frc.robot.commands.Climb.ClimbValve;

/** Add your docs here. */
public class Climber extends SubsystemBase {
  public static final String Unlock6 = null;
  public CANSparkMax climbRotate1 = new CANSparkMax(Constants.ClimberRotate1SparkMaxID, MotorType.kBrushless);
  public CANSparkMax climbRotate2 = new CANSparkMax(Constants.ClimberRotate2SparkMaxID, MotorType.kBrushless); 
  public DoubleSolenoid climbLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimberLiftUPSolenoidID, Constants.ClimberLiftDOWNSolenoidID);

  //fix ports
  public DoubleSolenoid Hook1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbFApproachGO, Constants.ClimberFApproachLEAVE); 
  public DoubleSolenoid Hook2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.ClimbFCloseGO, Constants.ClimbFCloseLEAVE); 
  public DoubleSolenoid Hook3 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbFLock, Constants.climbFUnlock);   
  public DoubleSolenoid Hook4 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBApproachGO, Constants.climbBApproachLEAVE); 
  public DoubleSolenoid Lock5 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBCloseGO, Constants.climbBCloseLEAVE); 
  public DoubleSolenoid Lock6 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.climbBLock, Constants.climbBUnlock); 

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

  public void openH1() {
    Hook1.set(Value.kReverse);
  }

}
