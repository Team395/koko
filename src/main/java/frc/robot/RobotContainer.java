// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Climb.ClimbRotate;
import frc.robot.commands.Intake.LiftIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
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
  public DriveTrain drivetrain;
  public Climber climber;
  public Intake intake;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    if (Constants.Drivetrain.Enabled) {
      drivetrain = new DriveTrain(io);
      drivetrain.setDefaultCommand(new TankDrive(drivetrain, io));
    }

    if (Constants.Climber.Enabled) {
      climber = new Climber();
      climber.setDefaultCommand(new ClimbRotate(climber, io));
      configureClimberBindings();
    }

    if (Constants.Intake.Enabled) {
      intake = new Intake();
      // TODO: create `IntakeJoystick` command and set as default for Intake
      intake.setDefaultCommand(new LiftIntake(io, intake));
      configureIntakeBindings();
    }
  }

  private void configureClimberBindings() {
    io.solenoidLeftShoulderButton.whenPressed(new InstantCommand(climber::toggleLock1, climber));
    io.solenoidXboxXButton.whenPressed(new InstantCommand(climber::toggleHook1,
        climber));
    io.solenoidXboxYButton.whenPressed(new InstantCommand(climber::toggleHook2,
        climber));

    io.solenoidRightShoulderButton.whenPressed(new InstantCommand(climber::toggleLock2, climber));
    io.solenoidXboxAButton.whenPressed(new InstantCommand(climber::toggleHook3,
        climber));
    io.solenoidXboxBButton.whenPressed(new InstantCommand(climber::toggleHook4,
        climber));

    io.solenoidLeftStick.whenPressed(new InstantCommand(climber::toggleRaise,
        climber));
  }

  private void configureIntakeBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
