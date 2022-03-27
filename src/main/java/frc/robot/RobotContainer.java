// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.Climb.ClimbLower;
import frc.robot.commands.Climb.ClimbRaise;
import frc.robot.commands.Climb.ClimbRotate;
import frc.robot.commands.Climb.CloseH1;
import frc.robot.commands.Climb.CloseH2;
import frc.robot.commands.Climb.CloseH3;
import frc.robot.commands.Climb.CloseH4;
import frc.robot.commands.Climb.Lock5;
import frc.robot.commands.Climb.Lock6;
import frc.robot.commands.Climb.OpenH1;
import frc.robot.commands.Climb.OpenH2;
import frc.robot.commands.Climb.OpenH3;
import frc.robot.commands.Climb.OpenH4;
import frc.robot.commands.Climb.Unlock5;
import frc.robot.commands.Climb.Unlock6;
import frc.robot.subsystems.Climber;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final IO io = new IO();
  private final Climber climber = new Climber();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // climber.setDefaultCommand(new ClimbRotate(climber, io));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    io.solenoidLeftShoulderButton.whenPressed(new InstantCommand(climber::toggleLock1, climber));
    io.solenoidXboxXButton.whenPressed(new InstantCommand(climber::toggleHook1, climber));
    io.solenoidXboxYButton.whenPressed(new InstantCommand(climber::toggleHook2, climber));

    io.solenoidRightShoulderButton.whenPressed(new InstantCommand(climber::toggleLock2, climber));
    io.solenoidXboxAButton.whenPressed(new InstantCommand(climber::toggleHook3, climber));
    io.solenoidXboxBButton.whenPressed(new InstantCommand(climber::toggleHook4, climber));

    io.solenoidLeftStick.whenPressed(new InstantCommand(climber::toggleRaise, climber));
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
