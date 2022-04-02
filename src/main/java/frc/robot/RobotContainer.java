// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TankDrive;
import frc.robot.commands.enabling.EnableClimber;
import frc.robot.commands.enabling.EnableDrivetrain;
import frc.robot.commands.enabling.EnableIntake;
import frc.robot.commands.zeroing.ZeroClimber;
import frc.robot.commands.zeroing.ZeroIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final IO io = new IO();
  public Drivetrain drivetrain;
  public Climber climber;
  public Intake intake;

  public boolean isDrivetrainEnabled = false;
  public boolean isIntakeEnabled = false;
  public boolean climberEnabled = false;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    if (isDrivetrainEnabled) {
      new EnableDrivetrain(this);
    }

    if (climberEnabled) {
      new EnableClimber(this);
    }

    if (isIntakeEnabled) {
      new EnableIntake(this);
    }

    SmartDashboard.putData("Enable Drivetrain", new EnableDrivetrain(this));
    SmartDashboard.putData("Enable Intake", new EnableIntake(this));
    SmartDashboard.putData("Enable Climber", new EnableClimber(this));
  }

  public void enableDrivetrain() {
    drivetrain = new Drivetrain(io);
    drivetrain.setDefaultCommand(new TankDrive(drivetrain, io));

    isDrivetrainEnabled = true;
  }

  public void enableIntake() {
    intake = new Intake();
    configureIntakeBindings();

    SmartDashboard.putData("Zero Intake", new ZeroIntake(intake));

    isIntakeEnabled = true;
  }

  public void enableClimber() {
    climber = new Climber();
    configureClimberBindings();

    SmartDashboard.putData("Zero Climber", new ZeroClimber(climber));

    climberEnabled = true;
  }

  private void configureClimberBindings() {
    io.operatorXboxYButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kMidDegrees), climber));
    io.operatorXboxBButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kHighDegrees), climber));
    io.operatorXboxAButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kTraversalDegrees), climber));
    io.operatorXboxXButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kRestDegrees), climber));
  }

  private void configureIntakeBindings() {
    io.operatorRightShoulderButton.whenHeld(new InstantCommand(intake::moveDown, intake));
    io.operatorRightShoulderButton.whenReleased(new InstantCommand(intake::moveUp, intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  public void periodic() {
    SmartDashboard.putBoolean("Drivetrain Enabled", isDrivetrainEnabled);
    SmartDashboard.putBoolean("Intake Enabled", isIntakeEnabled);
    SmartDashboard.putBoolean("Climber Enabled", climberEnabled);
  }
}
