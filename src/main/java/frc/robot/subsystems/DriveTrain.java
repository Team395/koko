// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;

/** Add your docs here. */
public class DriveTrain extends SubsystemBase {
  public TalonFX leftLeader;
  public TalonFX leftFollower;
  public TalonFX rightLeader;
  public TalonFX rightFollower;

  public DriveTrain(IO io) {
    leftLeader = new TalonFX(Constants.Drivetrain.kLeftLeaderFalconID);
    leftLeader.configFactoryDefault();
    leftLeader.setNeutralMode(NeutralMode.Brake);
    leftLeader.setInverted(true);

    leftFollower = new TalonFX(Constants.Drivetrain.kLeftFollowerFalconID);
    leftFollower.configFactoryDefault();
    leftFollower.setNeutralMode(NeutralMode.Brake);
    leftFollower.setInverted(true);
    leftFollower.follow(leftLeader);

    rightLeader = new TalonFX(Constants.Drivetrain.kRightLeaderFalconID);
    rightLeader.configFactoryDefault();
    rightLeader.setNeutralMode(NeutralMode.Brake);
    rightLeader.setInverted(false);

    rightFollower = new TalonFX(Constants.Drivetrain.kRightFollowerFalconID);
    rightFollower.configFactoryDefault();
    rightFollower.setNeutralMode(NeutralMode.Brake);
    rightFollower.setInverted(false);
    rightFollower.follow(rightLeader);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    leftLeader.set(TalonFXControlMode.PercentOutput, leftSpeed);
    rightLeader.set(TalonFXControlMode.PercentOutput, rightSpeed);
  }

  public void periodic() {
    SmartDashboard.putBoolean("Drivertrain enabled.", Constants.Drivetrain.Enabled);
    SmartDashboard.putNumber("left Leader speed", leftLeader.getMotorOutputPercent());
    SmartDashboard.putNumber("right Leader speed", rightLeader.getMotorOutputPercent());
  }
}
