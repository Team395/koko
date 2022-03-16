// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  /** Creates a new IntakeBalls. */
  public Intake m_intake;
  private IO m_io;

  public IntakeBalls() {
    // Use addRequirements() here to declare subsystem dependencies.
    m_io = new IO();
    m_intake = new Intake();
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setIntakeRollSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.setIntakeRollSpeed(m_io.getIntakeControllerRoller());
    //right trigger, pull up, left trigger, pull down;
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
