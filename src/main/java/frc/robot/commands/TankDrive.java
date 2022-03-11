// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.subsystems.DriveTrain;


public class TankDrive extends CommandBase {
  private final DriveTrain m_drivetrain;
  private final IO m_io;

  /** Creates a new TankDrive. */
  public TankDrive(DriveTrain drivetrain, IO io) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    m_io = io;
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  
    public void GTADrive(double leftTrigger, double rightTrigger, double turn) {
        
      if ( Math.abs(turn) < Constants.kJoystickTurnDeadzone) {
          turn = 0.0;
        }

      turn = Math.abs(turn) < Constants.kJoystickTurnDeadzone
        ? 0.0
        : (turn - Math.signum(turn) * Constants.kJoystickTurnDeadzone) / (1.0 - Constants.kJoystickTurnDeadzone);
        

        double left = rightTrigger - leftTrigger;
        double right = rightTrigger - leftTrigger;

        turn = turn * turn * Math.signum(turn);

        // if (left < 0) { turn *= -1.0; }
        turn = turn * 2/3;

        left += turn;
        right -= turn;

        left = Math.min(1.0, Math.max(-1.0, left));
        right = Math.max(-1.0, Math.min(1.0, right));

        m_drivetrain.tankDrive(left, right);
    }

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    GTADrive(m_io.getControllerLeftTrigger(), m_io.getControlleRightTrigger(), m_io.getControllerTurn());
    // System.out.println(m_io.getControllerLeftTrigger());
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
