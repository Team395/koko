// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.subsystems.Intake;

public class RollIntake extends CommandBase {
  public Intake intake;
  public IO io; 
  
  public RollIntake(IO io, Intake intake) {
    this.intake = intake;
    this.io = io;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.roll(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.roll(io.getIntakeControllerRoller());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.roll(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
