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

  @Override
  public void initialize() {
    intake.setRollSpeed(0);
  }

  @Override
  public void execute() {
    intake.setRollSpeed(io.getOperatorControllerRoller());
  }

  @Override
  public void end(boolean interrupted) {
    intake.setRollSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
