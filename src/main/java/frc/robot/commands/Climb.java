// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.enums.LockPositions;
import frc.robot.enums.ValvePositions;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {

  private final Climber m_climber;
  private final LockPositions requestedLockPosition;
  private final ValvePositions requestedValvePosition;

  public LockPositions LockPosition = LockPositions.LOCK;
  public ValvePositions ValvePosition = ValvePositions.OPEN;



  public Climb(Climber climber, IO io, LockPositions requestedLockPosition, ValvePositions requestedValvePosition) {
    m_climber = climber;
    addRequirements(m_climber);
    
    this.requestedLockPosition = requestedLockPosition;
    this.requestedValvePosition = requestedValvePosition;
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public void climberLock(LockPositions position) {
    switch(position) {
      case LOCK:
        m_climber.climberFrontLock.set(Value.kForward);
        LockPosition = LockPositions.LOCK;
        break;

      case UNLOCK:
        m_climber.climberFrontLock.set(Value.kReverse);
        LockPosition = LockPositions.UNLOCK;
        break;
    }
  }

  public void climberValve(ValvePositions position) {
    switch(position) {
      case OPEN:
        m_climber.climberFrontValve.set(Value.kForward);
        ValvePosition = ValvePositions.OPEN;
        break;

      case CLOSE:
        m_climber.climberFrontValve.set(Value.kReverse);
        ValvePosition = ValvePositions.CLOSE;
        break;
    }
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (requestedLockPosition != null) {
    climberLock(requestedLockPosition);
    }
    
    if (requestedValvePosition != null ) {
    climberValve(requestedValvePosition);
    } 
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
