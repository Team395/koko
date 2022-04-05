// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.IO;
import frc.robot.subsystems.Climber;

public class WaitForButton extends CommandBase {
  private final JoystickButton m_button;
  private final IO m_io;
  private final Climber m_climber;

  public WaitForButton(JoystickButton button, IO io, Climber climber) {
    m_button = button;
    m_io = io;
    m_climber = climber;
  }

  public void execute() {
    if (m_io.driverLeftShoulderButton.get()) {
      m_climber.rotateToDegrees(m_climber.getPositionDegrees() + 5);
    }

    if (m_io.driverRightShoulderButton.get()) {
      m_climber.rotateToDegrees(m_climber.getPositionDegrees() - 5);
    }
  }
  
  @Override
  public boolean isFinished() {
    return m_io.getButtonState(m_button);
  }
}
