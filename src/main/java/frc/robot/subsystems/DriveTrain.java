// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Add your docs here. */
public class DriveTrain extends SubsystemBase {
  public TalonFX leftLeader = new TalonFX(Constants.driveLeftLeaderFalconID);
  public TalonFX leftFollower = new TalonFX(Constants.driveLeftFollowerFlaconID);
  public TalonFX rightLeader = new TalonFX(Constants.driveRightLeaderFalconID);
  public TalonFX rightFollower = new TalonFX(Constants.driveRightFollowerFalconID);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DriveTrain() {
    leftLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightLeader.configFactoryDefault();
    rightFollower.configFactoryDefault();

    leftFollower.setInverted(false);
    rightFollower.setInverted(true);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

  }

  public void tankDrive(double leftSpeed, double rightSpeed) {

    leftLeader.set(TalonFXControlMode.PercentOutput, leftSpeed);
    rightLeader.set(TalonFXControlMode.PercentOutput, rightSpeed);    
  }

  public void arcadeDrive(double speed, double turn) {
    turn = MathUtil.clamp(turn, -1* Constants.kTurnClamp, Constants.kTurnClamp);
    double sign = Math.signum(turn);

    if(sign > 0) {turn = Math.max(Constants.kDriveMinimumSpeed, turn); }
    else if (sign < 0) {turn = Math.min(-1 * Constants.kDriveMinimumSpeed, turn); }

    sign = Math.signum(speed);
    if(sign > 0) { speed = Math.max(Constants.kDriveMinimumSpeed, speed); }
    else if (sign < 0) {speed = Math.min(-1 * Constants.kDriveMinimumSPeed, speed);}

    leftLeader.set(ControlMode.PercentOutput, speed - turn);
    rightLeader.set(ControlMode.PercentOutput, speed + turn);
  } 
}
