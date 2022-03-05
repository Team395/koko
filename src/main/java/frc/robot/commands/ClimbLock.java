// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.concurrent.locks.Lock;

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

  public LockPositions LockPosition = LockPositions.LOCK;
  public LocationPositions LocationPosition = LocationPositions.FRONT;
  
  
  //lock, unlock, front, back

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

  // public void lockClimber(LocationPositions location) {
  //   switch(location) {
  //     case FRONT:
  //       LocationPosition = LocationPositions.FRONT;
  //       break;

  //     case BACK:
  //       LocationPosition = LocationPositions.BACK;
  //       break;
  //   }

  public void lockClimber(LockPositions position, climberLock location) {
    switch(position) {
      case LOCK:
        climberLock(location);
        m_climber.climberFrontLock.set(Value.kForward);
        LockPosition = LockPositions.LOCK;
        break;

      case UNLOCK:
        m_climber.climberFrontLock.set(Value.kReverse);
        LockPosition = LockPositions.UNLOCK;
        break;
        
    }
  }





  public void climberLock(LocationPositions location) {
    if (lockClimberFRONT) {
      switch(position) {
        case LOCK:
          // lockClimber(location);
          m_climber.climberFrontLock.set(Value.kForward);
          LockPosition = LockPositions.LOCK;
          break;

        case UNLOCK:
          m_climber.climberFrontLock.set(Value.kReverse);
          LockPosition = LockPositions.UNLOCK;
          break;
      }
    }

    else {
      switch(position) {
        case LOCK:
          m_climber.climberBackLock.set(Value.kForward);
          LockPosition = LockPositions.LOCK;
          break;

        case UNLOCK:
          m_climber.climberBackLock.set(Value.kReverse);
          LockPosition = LockPositions.UNLOCK;
          break;
      }

    }
  }


  


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climberLock(requestedLockPosition);
    

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
