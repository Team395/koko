// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbRotate extends CommandBase {
  public Climber m_climber;
  public IO m_io; 

  public ClimbRotate() {
    m_io = new IO(); 
    m_climber = new Climber(); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.setRotateSpeed(0); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
