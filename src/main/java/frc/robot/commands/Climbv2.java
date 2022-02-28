// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Climber;

/** Add your docs here. */
public class Climbv2 extends edu.wpi.first.wpilibj2.command.InstantCommand {
  /** Add your docs here. */

  private final Climber m_climber; 

  public Climbv2(Climber climber) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_climber = climber; 
    addRequirements(m_climber);
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    m_climber.ClimberOpenF();
  }
}

