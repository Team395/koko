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
import frc.robot.commands.Climber.ClimbSequence;
import frc.robot.commands.Drivetrain.ControllerDrivetrain;
import frc.robot.commands.Intake.ControllerIntake;
import frc.robot.commands.Intake.OuttakeCargo;
import frc.robot.commands.autonomous.DriveFeet;
import frc.robot.commands.autonomous.TurnDegrees;
import frc.robot.commands.enabling.EnableAll;
import frc.robot.commands.enabling.EnableClimber;
import frc.robot.commands.enabling.EnableDrivetrain;
import frc.robot.commands.enabling.EnableIntake;
import frc.robot.commands.zeroing.ZeroClimber;
import frc.robot.commands.zeroing.ZeroIntake;
import frc.robot.enums.Climb.ExtendPositions;
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
  public boolean isClimberEnabled = false;
  public boolean isAllEnabled = false;


  public RobotContainer() {
    SmartDashboard.putData("Enable Drivetrain", new EnableDrivetrain(this));
    SmartDashboard.putData("Enable Intake", new EnableIntake(this));
    SmartDashboard.putData("Enable Climber", new EnableClimber(this));
    SmartDashboard.putData("Enable All", new EnableAll(this));

    if (isDrivetrainEnabled) { new EnableDrivetrain(this); }
    if (isClimberEnabled) { new EnableClimber(this); }
    if (isIntakeEnabled) { new EnableIntake(this); }
    if (isAllEnabled) { new EnableAll(this); }
  }

  public void enableDrivetrain() {
    if (isDrivetrainEnabled) { return; }

    drivetrain = new Drivetrain(io);
    drivetrain.setDefaultCommand(new ControllerDrivetrain(drivetrain, io));

    isDrivetrainEnabled = true;
  }

  public void enableIntake() {
    if (isIntakeEnabled) { return; }

    intake = new Intake();
    configureIntakeBindings();
    intake.setDefaultCommand(new ControllerIntake(io, intake));

    SmartDashboard.putData("Zero Intake", new ZeroIntake(intake));

    isIntakeEnabled = true;
  }

  public void enableClimber() {
    if (isClimberEnabled) { return; }

    climber = new Climber();
    configureClimberBindings();

    SmartDashboard.putData("Zero Climber", new ZeroClimber(climber));

    isClimberEnabled = true;
  }

  public void addAutoChooser() {
    if (autoChooser != null) { return; }

    autoChooser = new SendableChooser<Command>();

    autoChooser.setDefaultOption("No Auto", new InstantCommand());

    autoChooser.addOption("Drive", new DriveFeet(drivetrain, -5));

    autoChooser.addOption("Drive +2 Balls", new SequentialCommandGroup(
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake, Constants.Intake.kOuttakeSeconds),
      new DriveFeet(drivetrain, -3)
    ));

    autoChooser.addOption("Drive +3 Balls", new SequentialCommandGroup(
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake, Constants.Intake.kOuttakeSeconds),
      new DriveFeet(drivetrain, -3),
      new TurnDegrees(drivetrain, 180),
      new ParallelCommandGroup(
        new DriveFeet(drivetrain, 2),
        new IntakeCargo(intake, Constants.Intake.kIntakeSeconds)
      ),
      new TurnDegrees(drivetrain, -180),
      new DriveFeet(drivetrain, 5),
      new OuttakeCargo(intake, Constants.Intake.kOuttakeSeconds)
    ));

    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureClimberBindings() {
    // io.driverXboxYButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kMidDegrees), climber));
    // io.driverXboxBButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kHighDegrees), climber));
    // io.driverXboxAButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kTraversalDegrees), climber));
    // io.driverXboxXButton.whenPressed(new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kRestDegrees), climber));

    io.driverXboxXButton.whenPressed(new ClimbSequence(climber, io));

    io.driverLeftShoulderButton.whenPressed(new InstantCommand(() -> climber.setExtend(ExtendPositions.RAISE), climber));
    io.driverLeftShoulderButton.whenReleased(new InstantCommand(() -> climber.setExtend(ExtendPositions.LOWER), climber));
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
    SmartDashboard.putBoolean("Climber Enabled", isClimberEnabled);
  }
}
