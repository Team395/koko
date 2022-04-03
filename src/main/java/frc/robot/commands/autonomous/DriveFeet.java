package frc.robot.commands.autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainConfigurators.DriveStraightConfigurator;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DriveFeet extends CommandBase {
  private Drivetrain drivetrain;
  private double distanceInFeet;
  private int _withinThresholdLoops;
  private double allowableClosedLoopErrorInches = 3
      * Constants.Drivetrain.kRotationsPerInch
      * Constants.Drivetrain.kMotorRotationsPerWheelRotation
      * Constants.Drivetrain.kSensorUnitsPerRotation;

  public DriveFeet(Drivetrain drivetrain, double distanceInFeet) {
    this.drivetrain = drivetrain;
    this.distanceInFeet = distanceInFeet;

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    _withinThresholdLoops = 0;

    var configurator = new DriveStraightConfigurator(drivetrain);
    configurator.configDrivetrainDriveStraight();

    drivetrain.zeroDistance();

    double target_sensorUnits = Constants.Drivetrain.drivetrainForward
        * distanceInFeet
        * Constants.Drivetrain.kSensorUnitsPerFoot;

    double target_turn = drivetrain.rightLeader.getSelectedSensorPosition(1);

    /*
     * Configured for Position Closed loop on Integrated Sensors' Sum and Auxiliary
     * PID on Pigeon's Yaw
     */
    drivetrain.driveStraight(target_sensorUnits, target_turn);
  }

  @Override
  public void execute() {
    /* Check if closed loop error is within the threshld */
    double drivetrainClosedLoopError = drivetrain.getClosedLoopError();
    if (drivetrainClosedLoopError < +allowableClosedLoopErrorInches &&
        drivetrainClosedLoopError > -allowableClosedLoopErrorInches) {

      ++_withinThresholdLoops;
    } else {
      _withinThresholdLoops = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.tankDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (_withinThresholdLoops > 10);
  }
}
