package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Climb;
import frc.robot.commands.Climbv2;
import frc.robot.enums.ClimberLock;
import frc.robot.subsystems.Climber;

public class IO {

    XboxController driverController = new XboxController(0);
    //TODO
    static final double joystickDeadzone = 0.15;

    public double getControllerLeftTrigger() {
        return -1 * driverController.getLeftTriggerAxis();
    }

    public double getControlleRightTrigger() {
        return -1 * driverController.getRightTriggerAxis();
    }

    public double getControllerTurn() {
        return -1 * driverController.getLeftX();
    }

    public boolean aSolenoidValveF() {
        return solenoidController.getAButtonPressed();
    }


    XboxController solenoidController = new XboxController(1);
    JoystickButton solenoidXboxAButton = new JoystickButton(solenoidController, 1);
    JoystickButton solenoidXboxBButton = new JoystickButton(solenoidController, 2);
    JoystickButton solenoidXboxXButton = new JoystickButton(solenoidController, 3);
    JoystickButton solenoidXboxYButton = new JoystickButton(solenoidController, 4);
    


    // solenoidXboxAButton.whenPressed(new Climbv2());

    

public void OI () {
    solenoidXboxAButton.whenPressed(new Climbv2(null) );

}

}