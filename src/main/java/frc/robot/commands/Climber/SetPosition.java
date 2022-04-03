// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetPosition<P> extends CommandBase {
  private final SetPositionCallable<P> setCallable;
  private final P position;

  public SetPosition(SetPositionCallable<P> setCallable, P position) {
    this.setCallable = setCallable;
    this.position = position;
  }

  @Override
  public void execute() {
    setCallable.set(position);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
