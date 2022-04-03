// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.enums.Intake.IntakePositions;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class IntakeCargo extends CommandBase {
  public Intake intake;
  private Timer timer = new Timer();
  private double startTime;

  public IntakeCargo(Intake intake) {
    this.intake = intake;

    timer.start();
    startTime = timer.get();
  }

  @Override
  public void initialize() {
    intake.setPosition(IntakePositions.DOWN);
    intake.setRollSpeed(Constants.Intake.kRollerIntakeSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return timer.get() - startTime >= Constants.Intake.kIntakeSeconds;
  }
}
