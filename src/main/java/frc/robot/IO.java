package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IO {

    XboxController driverController = new XboxController(0);
    XboxController solenoidController = new XboxController(1);

    public double getControllerLeftTrigger() {
        // TODO: why are we inverting the trigger value? they're bound to [0,+1] already
        return -1 * driverController.getLeftTriggerAxis();
    }

    public double getControlleRightTrigger() {
        // TODO: why are we inverting the trigger values? they're bound to [0,+1] already
        return -1 * driverController.getRightTriggerAxis();
    }

    public double getControllerTurn() {
        return -1 * driverController.getLeftX();
    }
    
     //Intake Testing
     public double getIntakeLeftTrigger() {
        return solenoidController.getLeftTriggerAxis();
    }

    public double getIntakeRightTrigger() {
        return solenoidController.getRightTriggerAxis();
    }

    public double getIntakeControllerRoller() {
        return -1 * solenoidController.getLeftY();
    }

    public double getIntake() {
        return -1 * solenoidController.getRightY();
    }

    JoystickButton intakeXboxCButton = new JoystickButton(driverController, 5);

    //Climber Testing
    JoystickButton solenoidXboxAButton = new JoystickButton(solenoidController, 1);
    JoystickButton solenoidXboxBButton = new JoystickButton(solenoidController, 2);
    JoystickButton solenoidXboxXButton = new JoystickButton(solenoidController, 3);
    JoystickButton solenoidXboxYButton = new JoystickButton(solenoidController, 4);

    JoystickButton driverXboxAButton = new JoystickButton(driverController, 1);
    JoystickButton driverXboxBButton = new JoystickButton(driverController, 2); 
    JoystickButton driverXboxXButton = new JoystickButton(driverController, 3); 
    JoystickButton driverXboxYButton = new JoystickButton(driverController, 4); 

    JoystickButton solenoidLeftStick = new JoystickButton(solenoidController, 9);

    JoystickButton solenoidRightShoulderButton = new JoystickButton(solenoidController, 6);
    JoystickButton solenoidLeftShoulderButton = new JoystickButton(solenoidController, 5);

    JoystickButton driverRightShoulderButton = new JoystickButton(driverController, 6);
    JoystickButton driverLeftShoulderButton = new JoystickButton(driverController, 5);
}
