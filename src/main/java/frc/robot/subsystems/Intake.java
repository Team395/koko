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

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.IntakePositions;

/** Add your docs here. */
public class Intake extends SubsystemBase {
  public VictorSPX intakeRoller;
  public CANSparkMax intakeArm;
  public RelativeEncoder armEncoder;
  public IO m_io;

  public IntakePositions currentPosition = IntakePositions.UP;

  public Intake() {
    intakeRoller = new VictorSPX(Constants.Intake.kRollerSpxID);
    intakeRoller.setNeutralMode(NeutralMode.Brake);

    intakeArm = new CANSparkMax(Constants.Intake.kArmSparkMaxID, MotorType.kBrushless);
    intakeArm.restoreFactoryDefaults();
    intakeArm.setIdleMode(IdleMode.kBrake);

    armEncoder = intakeArm.getEncoder();
    armEncoder.setPosition(0);
    // TODO: this was failing...why?
    // Set position units to degrees
    // armEncoder.setPositionConversionFactor(1/360);

    intakeArm.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    intakeArm.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    // figure these out
    intakeArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 2f);
    intakeArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -18f);

    intakeArm.burnFlash();

    intakeRoller.set(ControlMode.PercentOutput, 0);
    intakeArm.set(0);
  }

  public void roll(double speed) {
    if (Math.abs(speed) < Constants.IO.kJoystickDeadzone) {
      speed = 0;
    }
    intakeRoller.set(ControlMode.PercentOutput, speed);
  }

  public void move(double armSpeed) {
    if (Math.abs(armSpeed) < Constants.IO.kJoystickDeadzone) {
      armSpeed = 0;
    }

    double sign = Math.signum(armSpeed);
    armSpeed = sign * Math.pow(armSpeed, 4);
    armSpeed = Math.min(armSpeed, 0.5);
    intakeArm.set(armSpeed);
  }

  public void periodic() {
    SmartDashboard.putBoolean("Intake enabled", Constants.Intake.Enabled);
    SmartDashboard.putNumber("arm encoder", armEncoder.getPosition());
    SmartDashboard.putNumber("roller speed",
        intakeRoller.getMotorOutputPercent());
    SmartDashboard.putNumber("arm speed", intakeArm.get());
  }
}
