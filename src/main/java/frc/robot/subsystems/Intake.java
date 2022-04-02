// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.Intake.IntakePositions;

/** Add your docs here. */
public class Intake extends SubsystemBase {
  public VictorSPX intakeRoller;
  public CANSparkMax intakeArm;
  public SparkMaxPIDController pidController;
  public RelativeEncoder armEncoder;
  public IO m_io;

  public IntakePositions currentPosition = IntakePositions.UP;

  public Intake() {
    intakeRoller = new VictorSPX(Constants.Intake.kRollerSpxID);
    intakeRoller.setNeutralMode(NeutralMode.Brake);

    intakeArm = new CANSparkMax(Constants.Intake.kArmSparkMaxID, MotorType.kBrushless);
    intakeArm.restoreFactoryDefaults();
    intakeArm.setIdleMode(IdleMode.kBrake);

    intakeArm.setClosedLoopRampRate(0.5);

    armEncoder = intakeArm.getEncoder();
    pidController = intakeArm.getPIDController();
    armEncoder.setPosition(0);

    pidController.setP(Constants.Intake.kGains.kP);
    pidController.setI(Constants.Intake.kGains.kI);
    pidController.setD(Constants.Intake.kGains.kD);
    pidController.setIZone(Constants.Intake.kGains.kIzone);
    pidController.setFF(Constants.Intake.kGains.kF);
    pidController.setOutputRange(-1 * Constants.Intake.kGains.kPeakOutput,
        Constants.Intake.kGains.kPeakOutput);

    pidController.setReference(0, ControlType.kPosition);

    intakeArm.burnFlash();
  }

  public void zeroEncoders() {
    armEncoder.setPosition(0);
    System.out.println("Zero'd intake encoders.");
  }

  public void roll(double speed) {
    if (Math.abs(speed) < Constants.IO.kJoystickDeadzone) {
      speed = 0;
    }
    intakeRoller.set(ControlMode.PercentOutput, speed);
  }

  public void moveUp() {
    pidController.setReference(Constants.Intake.kUp, ControlType.kPosition);
  }

  public void moveDown() {
    pidController.setReference(Constants.Intake.kDown, ControlType.kPosition);
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
    SmartDashboard.putNumber("roller speed", intakeRoller.getMotorOutputPercent());
    SmartDashboard.putNumber("arm speed", intakeArm.get());

    SmartDashboard.putNumber("Set P Gain", pidController.getP());
    SmartDashboard.putNumber("Set I Gain", pidController.getI());
    SmartDashboard.putNumber("Set D Gain", pidController.getD());
    SmartDashboard.putNumber("Set I Zone", pidController.getIZone());
    SmartDashboard.putNumber("Set Feed Forward", pidController.getFF());
    SmartDashboard.putNumber("Set Max Output", pidController.getOutputMax());
    SmartDashboard.putNumber("Set Min Output", pidController.getOutputMin());
  }

  public void teleopPeriodic() {
  }
}
