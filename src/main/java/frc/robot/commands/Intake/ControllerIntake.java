// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.Intake.IntakePositions;
import frc.robot.subsystems.Intake;

public class ControllerIntake extends CommandBase {
  public Intake intake;
  private IO io;

  public ControllerIntake(IO io, Intake intake) {
    this.io = io;
    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(0);
  }

  @Override
  public void execute() {
    intake.setPosition(io.getIntakeButton() ? IntakePositions.DOWN : IntakePositions.UP);
    intake.setRollSpeed(Constants.Intake.kRollerIntakeSpeed);
  }

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
