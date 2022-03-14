// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.enums.IntakePositions;
import frc.robot.subsystems.Intake;

public class StowIntake extends CommandBase {
  /** Creates a new StowIntake. */
  private Intake m_intake;
  private Timer m_timer = new Timer();

  private double timerStartTime;
  private boolean isCommandFinished = false;

  public StowIntake(Intake m_intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = new Intake();
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.intakeLift(IntakePositions.UP);
    m_timer.start();
    timerStartTime = m_timer.get();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_timer.get() > timerStartTime + 1.5) {
      isCommandFinished = true;
    }
    else {
      isCommandFinished = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isCommandFinished;
  }
}
