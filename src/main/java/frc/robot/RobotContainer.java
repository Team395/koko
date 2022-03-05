// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Climb;
import frc.robot.enums.LockPositions;
import frc.robot.enums.ValvePositions;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final IO m_io = new IO();
  private final DriveTrain m_driverain = new DriveTrain(m_io);
  private final Climber m_climber = new Climber();


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
    m_io.solenoidXboxAButton.whenPressed(new Climb(m_climber, m_io, LockPositions.LOCK, null));
    m_io.solenoidXboxBButton.whenPressed(new Climb(m_climber, m_io, LockPositions.UNLOCK, null));
    m_io.solenoidXboxXButton.whenPressed(new Climb(m_climber, m_io, null, ValvePositions.CLOSE));
    m_io.solenoidXboxYButton.whenPressed(new Climb(m_climber, m_io, null, ValvePositions.OPEN));




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
