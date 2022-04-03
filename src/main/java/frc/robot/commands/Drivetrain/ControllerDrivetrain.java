// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.subsystems.Drivetrain;

public class ControllerDrivetrain extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final IO m_io;

  public ControllerDrivetrain(Drivetrain drivetrain, IO io) {
    m_drivetrain = drivetrain;
    m_io = io;

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_drivetrain.GTADrive(
      m_io.getDriverLeftTrigger(), 
      m_io.getDriverRightTrigger(), 
      m_io.getDriverLeftX()
    );
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
