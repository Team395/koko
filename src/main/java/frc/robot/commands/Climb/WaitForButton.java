// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.IO;

public class WaitForButton extends CommandBase {
  private final JoystickButton m_button;
  private final IO m_io;

  public WaitForButton(JoystickButton button, IO io) {
    m_button = button;
    m_io = io;
  }
  
  @Override
  public boolean isFinished() {
    return m_io.getButton(m_button);
  }
}
