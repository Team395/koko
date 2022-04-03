// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.enums.Intake.IntakePositions;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class OuttakeCargo extends CommandBase {
  public Intake intake;
  private Timer timer;
  private double durationSeconds;
  private double startTime;

  public OuttakeCargo(Intake intake, double durationSeconds) {
    this.intake = intake;
    this.durationSeconds = durationSeconds;

    timer = new Timer();
    timer.start();
    startTime = timer.get();
  }

  public OuttakeCargo(Intake intake) {
    this.intake = intake;
  }

  @Override
  public void initialize() {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(Constants.Intake.kRollerOuttakeSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setPosition(IntakePositions.UP);
    intake.setRollSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return timer != null ? timer.get() - startTime >= durationSeconds : false;
  }
}
