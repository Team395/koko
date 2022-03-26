package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

    //Intake Testing
    public double getIntakeLeftTrigger() {
        return solenoidController.getLeftTriggerAxis();
    }

    public double getIntakeRightTrigger() {
        return solenoidController.getRightTriggerAxis();
    }

    public double getIntakeControllerRoller() {
        return solenoidController.getLeftX();
    }

    //Climber Testing

    XboxController solenoidController = new XboxController(1);
    public JoystickButton solenoidXboxAButton = new JoystickButton(solenoidController, 1);
    JoystickButton solenoidXboxBButton = new JoystickButton(solenoidController, 2);
    JoystickButton solenoidXboxXButton = new JoystickButton(solenoidController, 3);
    JoystickButton solenoidXboxYButton = new JoystickButton(solenoidController, 4);

    JoystickButton driverXboxAButton = new JoystickButton(driverController, 1);
    JoystickButton driverXboxBButton = new JoystickButton(driverController, 2); 
    JoystickButton driverXboxXButton = new JoystickButton(driverController, 3); 
    JoystickButton driverXboxYButton = new JoystickButton(driverController, 4); 

    JoystickButton intakeXboxCButton = new JoystickButton(driverController, 5);

    JoystickButton solenoidStartButton = new JoystickButton(solenoidController, 1);
    JoystickButton solenoidBackButton = new JoystickButton(solenoidController, 2);

    JoystickButton solenoidRightTriggerButton = new JoystickButton(solenoidController, 4);
    JoystickButton solenoidLeftTriggerButton = new JoystickButton(solenoidController, 4);

    JoystickButton driverRightTriggerButton = new JoystickButton(driverController, 3);
    JoystickButton driverLeftTriggerButton = new JoystickButton(driverController, 4);


}