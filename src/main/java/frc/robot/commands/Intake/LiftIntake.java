// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.subsystems.Intake;

public class LiftIntake extends CommandBase {
  public Intake intake;
  private IO io;

  public LiftIntake(IO io, Intake intake) {
    this.io = io;
    this.intake = intake;

    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.move(0);
    intake.roll(0);
  }

  public void MoveIntake(double raiseArmSpeed, double lowerArmSpeed) {
    raiseArmSpeed = raiseArmSpeed - lowerArmSpeed;
    lowerArmSpeed = raiseArmSpeed - lowerArmSpeed;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = io.getIntake();
    intake.move(speed);
    intake.roll(io.getIntakeControllerRoller());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.move(0);
    intake.roll(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
