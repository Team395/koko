// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Climb.ClimbLock;
import frc.robot.commands.Climb.ClimbValve;
import frc.robot.commands.Intake.DeployIntake;
import frc.robot.commands.Intake.StowIntake;
import frc.robot.enums.LocationPositions;
import frc.robot.enums.LockPositions;
import frc.robot.enums.ValvePositions;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final IO m_io = new IO();
  private final Climber m_climber = new Climber();
  private final Intake m_intake = new Intake();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_io.solenoidXboxAButton.whenPressed(new ClimbLock(m_climber, m_io, LockPositions.LOCK, LocationPositions.FRONT));
    m_io.solenoidXboxBButton.whenPressed(new ClimbLock(m_climber, m_io, LockPositions.UNLOCK, LocationPositions.FRONT));
    m_io.solenoidXboxXButton.whenPressed(new ClimbLock(m_climber, m_io, LockPositions.LOCK, LocationPositions.BACK));
    m_io.solenoidXboxYButton.whenPressed(new ClimbLock(m_climber, m_io, LockPositions.UNLOCK, LocationPositions.BACK)); 

    m_io.driverXboxAButton.whenPressed(new ClimbValve(m_climber, m_io, ValvePositions.OPEN, LocationPositions.FRONT)); 
    m_io.driverXboxBButton.whenPressed(new ClimbValve(m_climber, m_io, ValvePositions.CLOSE, LocationPositions.FRONT)); 
    m_io.driverXboxXButton.whenPressed(new ClimbValve(m_climber, m_io, ValvePositions.OPEN, LocationPositions.BACK));
    m_io.driverXboxYButton.whenPressed(new ClimbValve(m_climber, m_io, ValvePositions.CLOSE, LocationPositions.BACK));


  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
