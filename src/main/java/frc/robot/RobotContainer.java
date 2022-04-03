// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Intake.IntakeCargo;
import frc.robot.commands.Drivetrain.ControllerDrivetrain;
import frc.robot.commands.Intake.ControllerIntake;
import frc.robot.commands.Intake.OuttakeCargo;
import frc.robot.commands.autonomous.DriveFeet;
import frc.robot.commands.autonomous.TurnDegrees;
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
  public SendableChooser<Command> autoChooser;

  public boolean isDrivetrainEnabled = false;
  public boolean isIntakeEnabled = false;
  public boolean climberEnabled = false;


  public RobotContainer() {
    SmartDashboard.putData("Enable Drivetrain", new EnableDrivetrain(this));
    SmartDashboard.putData("Enable Intake", new EnableIntake(this));
    SmartDashboard.putData("Enable Climber", new EnableClimber(this));

    if (isDrivetrainEnabled) { new EnableDrivetrain(this); }
    if (climberEnabled) { new EnableClimber(this); }
    if (isIntakeEnabled) { new EnableIntake(this); }
  }

  public void enableDrivetrain() {
    drivetrain = new Drivetrain(io);
    drivetrain.setDefaultCommand(new ControllerDrivetrain(drivetrain, io));

    isDrivetrainEnabled = true;
  }

  public void enableIntake() {
    intake = new Intake();
    configureIntakeBindings();
    intake.setDefaultCommand(new ControllerIntake(io, intake));

    SmartDashboard.putData("Zero Intake", new ZeroIntake(intake));

    isIntakeEnabled = true;
  }

  public void enableClimber() {
    climber = new Climber();
    configureClimberBindings();

    SmartDashboard.putData("Zero Climber", new ZeroClimber(climber));

    climberEnabled = true;
  }

  public void addAutoChooser() {
    autoChooser = new SendableChooser<Command>();

    autoChooser.setDefaultOption("No Auto", new InstantCommand());

    autoChooser.addOption("Drive", new DriveFeet(drivetrain, -5));

    autoChooser.addOption("Drive +2 Balls", new SequentialCommandGroup(
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake),
      new DriveFeet(drivetrain, -3)
    ));

    autoChooser.addOption("Drive +3 Balls", new SequentialCommandGroup(
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake),
      new DriveFeet(drivetrain, -3),
      new TurnDegrees(drivetrain, 180),
      new ParallelCommandGroup(
        new DriveFeet(drivetrain, 2),
        new IntakeCargo(intake)
      ),
      new TurnDegrees(drivetrain, -180),
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake)
    ));
    
  }

  private void configureClimberBindings() {
    // io.operatorXboxYButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kMidDegrees), climber));
    // io.operatorXboxBButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kHighDegrees), climber));
    // io.operatorXboxAButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kTraversalDegrees), climber));
    // io.operatorXboxXButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kRestDegrees), climber));

    // io.driverXboxBButton.whenPressed(new ClimbSequence(climber, io));
  }

  private void configureIntakeBindings() {
    // io.operatorRightShoulderButton.whenHeld(new InstantCommand(() -> intake.setPosition(IntakePositions.DOWN), intake));
    // io.operatorRightShoulderButton.whenReleased(new InstantCommand(() -> intake.setPosition(IntakePositions.UP), intake));
  }


  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public void periodic() {
    SmartDashboard.putBoolean("Drivetrain Enabled", isDrivetrainEnabled);
    SmartDashboard.putBoolean("Intake Enabled", isIntakeEnabled);
    SmartDashboard.putBoolean("Climber Enabled", climberEnabled);
  }
}
