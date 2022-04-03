// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class RotateToDegrees extends CommandBase {
  private Climber climber;
  private double degrees;
  private double currentDegrees;

  public RotateToDegrees(Climber climber, double degrees) {
    this.climber = climber;
    this.degrees = degrees;
  }

  @Override
  public void initialize() {
    currentDegrees = climber.getPositionDegrees();
    climber.rotateToDegrees(degrees);
  }

  @Override
  public void execute() {
    currentDegrees = climber.getPositionDegrees();
  }
  
  @Override
  public boolean isFinished() {
    return Math.abs(degrees - currentDegrees) <= Constants.Climber.kRotateAcceptableErrorDegrees;
  }
}
