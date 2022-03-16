// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.IntakePositions;

/** Add your docs here. */
public class Intake extends SubsystemBase {
  //double check which is which for Intake
  public VictorSPX intakeRoller = new VictorSPX(Constants.intakeRollerSPXID);
  public CANSparkMax intakeArm = new CANSparkMax(Constants.intakeArmSparkMaxID, MotorType.kBrushless);
  public RelativeEncoder armEncoder;
  public IO m_io; 
  
  public IntakePositions currentPosition = IntakePositions.UP;
 
  public Intake() {
    intakeRoller.setNeutralMode(NeutralMode.Brake);
    intakeArm.setIdleMode(IdleMode.kBrake);
    
    armEncoder = intakeArm.getEncoder();
    armEncoder.setPosition(0); 

    // Set position units to degrees
    armEncoder.setPositionConversionFactor(1/360);

    intakeArm.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    intakeArm.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    intakeArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 0);
    intakeArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 90);

  }


  public void setIntakeRollSpeed(double speed) {
    if (Math.abs(speed) < Constants.kJoystickRollerDeadzone) {
      speed = 0.0;
    }
    intakeRoller.set(ControlMode.PercentOutput, speed);
  }

  public void setRaiseArmSpeed(double raiseArmSpeed) {
    if (Math.abs(raiseArmSpeed) < Constants.kJoystickArmDeadzone) {
      raiseArmSpeed = 0;
    }
    intakeArm.set(raiseArmSpeed);
  }

  public void setLowerArmSpeed(double lowerArmSpeed) {
    if (Math.abs(lowerArmSpeed) < Constants.kJoystickArmDeadzone) {
      lowerArmSpeed = 0;
    }
    intakeArm.set(lowerArmSpeed);
  }


  public void intakeLift(final IntakePositions position) {
    switch(position) {
      case UP:
        intakeArm.set(-Constants.intakeArmSpeed);
        intakeArm.setIdleMode(IdleMode.kCoast);
        currentPosition = IntakePositions.UP;
        break;

      case DOWN: 
        intakeArm.setIdleMode(IdleMode.kBrake);
        intakeArm.set(Constants.intakeArmSpeed);
        currentPosition = IntakePositions.DOWN;
        break;
    }
  }

}
