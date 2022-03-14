// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.enums.IntakePositions;
import frc.robot.subsystems.Intake;

public class StowIntake extends CommandBase {
  /** Creates a new StowIntake. */
  private IO m_io; 
  private Intake m_intake;

  public StowIntake(Intake m_intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = new Intake();
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.intakeLift(IntakePositions.UP);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

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
