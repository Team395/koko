// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.enums.IntakePositions;
import frc.robot.subsystems.Intake;

public class DeployIntake extends CommandBase {
  /** Creates a new DeployIntake. */
  private final Intake m_intake;

  public DeployIntake(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = new Intake();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.intakeLift(IntakePositions.UP);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shoot out the balls
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
