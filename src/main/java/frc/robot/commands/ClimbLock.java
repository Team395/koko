// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.enums.LocationPositions;
import frc.robot.enums.LockPositions;
import frc.robot.subsystems.Climber;

public class ClimbLock extends CommandBase {
  private final Climber m_climber;
  private final LockPositions requestedLockPosition;
  private final LocationPositions requestedLocationPosition;

  /** Creates a new ClimbLock. */
  public ClimbLock(Climber climber, IO io, LockPositions requestedLockPosition, LocationPositions requestedLocationPosition) {
    m_climber = climber;
    addRequirements(m_climber);

    this.requestedLockPosition = requestedLockPosition;
    this.requestedLocationPosition = requestedLocationPosition;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public void climbLockUnlock(LockPositions lockPosition, LocationPositions locationPosition) {
    switch(lockPosition) {
      case LOCK: 
        climbLock(locationPosition);
        break;
      case UNLOCK:
        climbUnlock(locationPosition);
        break;  
    } 
  }

  public void climbLock(LocationPositions locationPosition) {
    switch(locationPosition) {
      case FRONT:
        m_climber.climberFrontLock.set(Value.kForward);
        break;
      case BACK:
        m_climber.climberBackLock.set(Value.kForward); 
        break; 
    }
  }

  public void climbUnlock(LocationPositions locationPosition) {
    switch(locationPosition) {
      case FRONT:
        m_climber.climberFrontLock.set(Value.kReverse);
        break;

      case BACK:
        m_climber.climberBackLock.set(Value.kReverse);
        break;
    }
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbLockUnlock(requestedLockPosition, requestedLocationPosition);
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
