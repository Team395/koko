// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IO;

/** Add your docs here. */
public class Drivetrain extends SubsystemBase {
  public TalonFX leftLeader;
  public TalonFX leftFollower;
  public TalonFX rightLeader;
  public TalonFX rightFollower;

  public PigeonIMU pidgey = new PigeonIMU(Constants.Drivetrain.pidgeyCanId);
  public TalonFXConfiguration leftConfig = new TalonFXConfiguration();
  public TalonFXConfiguration rightConfig = new TalonFXConfiguration();

  public TalonFXInvertType leftInvert = TalonFXInvertType.CounterClockwise; // Same as invert = "false"
  public TalonFXInvertType rightInvert = TalonFXInvertType.Clockwise; // Same as invert = "true"

  public Drivetrain(IO io) {
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

  public void arcadeDrive(double speed, double turn) {
    SmartDashboard.putNumber("turn", turn);
    turn = MathUtil.clamp(turn, -1 * Constants.Drivetrain.kTurnClamp, Constants.Drivetrain.kTurnClamp);
    double sign = Math.signum(turn);

    if (sign > 0) {
      turn = Math.max(Constants.Drivetrain.kMinimumSpeed, turn);
    } else if (sign < 0) {
      turn = Math.min(-1 * Constants.Drivetrain.kMinimumSpeed, turn);
    }

    sign = Math.signum(speed);
    if (sign > 0) {
      speed = Math.max(Constants.Drivetrain.kMinimumSpeed, speed);
    } else if (sign < 0) {
      speed = Math.min(-1 * Constants.Drivetrain.kMinimumSpeed, speed);
    }

    SmartDashboard.putNumber("massagedTurn", turn);

    leftLeader.set(ControlMode.PercentOutput, speed + turn);
    rightLeader.set(ControlMode.PercentOutput, speed - turn);
  }

  public void configureDrivetrain(TalonFXConfiguration leftConfig, TalonFXConfiguration rightConfig) {
    leftLeader.configAllSettings(leftConfig);
    rightLeader.configAllSettings(rightConfig);

    leftLeader.setInverted(leftInvert);
    rightLeader.setInverted(rightInvert);
    leftFollower.setInverted(TalonFXInvertType.FollowMaster);
    rightFollower.setInverted(TalonFXInvertType.FollowMaster);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    rightLeader.configAuxPIDPolarity(true);

    /* Set status frame periods to ensure we don't have stale data */
    rightLeader.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, Constants.kTimeoutMs);
    rightLeader.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, Constants.kTimeoutMs);
    rightLeader.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, Constants.kTimeoutMs);
    leftLeader.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, Constants.kTimeoutMs);

    rightLeader.selectProfileSlot(Constants.CTRE.kSlot_Distance, Constants.CTRE.PID_PRIMARY);
    rightLeader.selectProfileSlot(Constants.CTRE.kSlot_Turning, Constants.CTRE.PID_TURN);
  }

  public double getClosedLoopError() {
    return rightLeader.getClosedLoopError();
  }

  // Zero all sensors, both Pigeon and Talons
  public void zeroSensors() {
    zeroDistance();

    pidgey.setYaw(0, Constants.kTimeoutMs);
    pidgey.setAccumZAngle(0, Constants.kTimeoutMs);
    System.out.println("[Integrated Sensors + Position] All sensors are zeroed.\n");
  }

  // Zero integrated sensors on Talons
  public void zeroDistance() {
    leftLeader.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
    rightLeader.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);

    leftLeader.setSelectedSensorPosition(0);
    rightLeader.setSelectedSensorPosition(0);

    System.out.println("[Integrated Sensors] All encoders are zeroed.\n");
  }

  public void driveStraight(double targetStraightUnits, double targetTurn) {
    targetTurn = rightLeader.getSelectedSensorPosition(1);
    rightLeader.set(TalonFXControlMode.Position, targetStraightUnits, DemandType.AuxPID, targetTurn);
    leftLeader.follow(rightLeader, FollowerType.AuxOutput1);
  }

  public double getHeading() {
    var pidgeyArray = new double[3];
    pidgey.getYawPitchRoll(pidgeyArray);
    SmartDashboard.putNumber("pidgey yaw", pidgeyArray[0]);
    return pidgeyArray[0] * (Constants.Drivetrain.kGyroReversed ? -1 : 1);
  }

  public void periodic() {
    SmartDashboard.putBoolean("Drivertrain enabled.", Constants.Drivetrain.Enabled);
    SmartDashboard.putNumber("left Leader speed", leftLeader.getMotorOutputPercent());
    SmartDashboard.putNumber("right Leader speed", rightLeader.getMotorOutputPercent());
    SmartDashboard.putNumber("leftSensor", leftLeader.getSelectedSensorPosition());
    SmartDashboard.putNumber("rightSensor", rightLeader.getSelectedSensorPosition());
    SmartDashboard.putNumber("rightPrimary", rightLeader.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("rightAux", rightLeader.getSelectedSensorPosition(1));
    SmartDashboard.putNumber("leftPrimary", leftLeader.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("leftAux", leftFollower.getSelectedSensorPosition(1));
    SmartDashboard.putNumber("closedLoopTarget", rightLeader.getClosedLoopTarget());
    SmartDashboard.putNumber("closedLoopError", rightLeader.getClosedLoopError());
    SmartDashboard.putNumber("pidgey", rightLeader.getSelectedSensorPosition(1));
    var pidgeyArray = new double[3];
    pidgey.getAccumGyro(pidgeyArray);
    SmartDashboard.putNumber("pidgeyDirect", pidgeyArray[0]);
  }
}
