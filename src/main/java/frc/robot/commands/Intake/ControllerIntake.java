// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.Intake.IntakePositions;
import frc.robot.subsystems.Intake;

public class ControllerIntake extends CommandBase {
  public Intake intake;
  private JoystickButton intakeButton;
  private JoystickButton outtakeButton;
  private IO io;

  public ControllerIntake(IO io, Intake intake) {
    this.intake = intake;
    this.io = io;
    this.intakeButton = io.getIntakeButton();
    this.outtakeButton = io.getOuttakeButton();

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(0);

    System.out.println("INTAKE SETUP");

    intakeButton.whenHeld(new IntakeCargo(intake), true);
    outtakeButton.whenHeld(new OuttakeCargo(intake), true);

    io.driverXBoxLinesButton.whenHeld(new InstantCommand(() -> intake.setRollSpeed(Constants.Intake.kRollerIntakeSpeed)));
    io.driverXBoxLinesButton.whenReleased(new InstantCommand(() -> intake.setRollSpeed(0)));
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(0);

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
