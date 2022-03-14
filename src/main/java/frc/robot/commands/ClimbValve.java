// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.enums.LocationPositions;
import frc.robot.enums.ValvePositions;
import frc.robot.subsystems.Climber;

public class ClimbValve extends CommandBase {
  private Climber m_climber; 
  private ValvePositions requestedValvePosition;
  private LocationPositions requestedLocationPosition; 

  public ValvePositions ValvePosition = ValvePositions.OPEN;
  public LocationPositions LocationPosition = LocationPositions.FRONT; 

  public ClimbValve(Climber climber, IO m_io, ValvePositions requestedValvePosition, LocationPositions requestedLocationPositon) {
    m_climber = climber;
    addRequirements(m_climber); 

    this.requestedValvePosition = requestedValvePosition;
    this.requestedLocationPosition = requestedLocationPosition;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public void climbOpenClose(ValvePositions valvePosition, LocationPositions locationPosition) {
    switch(valvePosition) {
      case OPEN:
        climbOpen(locationPosition); 
        break; 
      case CLOSE:
        climbClose(locationPosition); 
        break;  
    }
  }

  public void climbOpen(LocationPositions locationPosition) {
    switch(locationPosition) {
      case FRONT:
        m_climber.climberFrontValve.set(Value.kForward);
        break;
      case BACK:
        // m_climber.climberBackValve.set(Value.kForward);
        break;
    }
  }

  public void climbClose(LocationPositions LocationPosition) {
    switch(LocationPosition) {
      case FRONT:
        m_climber.climberFrontValve.set(Value.kReverse);
        break;

      case BACK:
        // m_climber.climberBackValve.set(Value.kReverse); 
        break; 
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbOpenClose(requestedValvePosition, requestedLocationPosition);
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
