// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.concurrent.locks.Lock;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.enums.ClimberLock;
import frc.robot.enums.ClimberValve;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {

  private final Climber m_climber;
  private final IO m_io;

  public ClimberLock LockPosition = ClimberLock.LOCK;
  public ClimberValve ValvePosition = ClimberValve.OPEN;



  public Climb(Climber climber, IO io) {
    m_climber = climber;
    m_io = io;
    addRequirements(m_climber);
    

    // Use addRequirements() here to declare subsystem dependencies.
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
        switch(LockPosition) {
      case LOCK:
        m_climber.ClimberLockF();
        break;
      
      case UNLOCK:
        m_climber.ClimberUnlockF();
        break;
    }

    switch(ValvePosition) {
      case OPEN:
        m_climber.ClimberOpenF();
        break;

      case CLOSE:
        m_climber.ClimberCloseF();
        break;
    }

  }
// put valve position down here as well
  public void climberLock(ClimberLock LockPosition) {
    switch(LockPosition) {
      case LOCK:
        m_climber.climberLockFront.set(Value.kForward);
        LockPosition = ClimberLock.LOCK;
        break;

      case UNLOCK:
        m_climber.climberLockFront.set(Value.kReverse);
        LockPosition = ClimberLock.UNLOCK;
        break;
    }
  }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
