package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IO {

    XboxController driverController = new XboxController(0);
    XboxController operatorController = new XboxController(1);

    public JoystickButton operatorXboxAButton = new JoystickButton(operatorController, 1);
    public JoystickButton operatorXboxBButton = new JoystickButton(operatorController, 2);
    public JoystickButton operatorXboxXButton = new JoystickButton(operatorController, 3);
    public JoystickButton operatorXboxYButton = new JoystickButton(operatorController, 4);

    public JoystickButton driverXboxAButton = new JoystickButton(driverController, 1);
    public JoystickButton driverXboxBButton = new JoystickButton(driverController, 2);
    public JoystickButton driverXboxXButton = new JoystickButton(driverController, 3);
    public JoystickButton driverXboxYButton = new JoystickButton(driverController, 4);

    public JoystickButton operatorLeftStick = new JoystickButton(operatorController, 9);

    public JoystickButton operatorRightShoulderButton = new JoystickButton(operatorController, 6);
    public JoystickButton operatorLeftShoulderButton = new JoystickButton(operatorController, 5);

    public JoystickButton driverRightShoulderButton = new JoystickButton(driverController, 6);
    public JoystickButton driverLeftShoulderButton = new JoystickButton(driverController, 5);
    

    public double getDriverLeftTrigger() {
        // TODO: why are we inverting the trigger value? they're bound to [0,+1] already
        return -1 * driverController.getLeftTriggerAxis();
    }

    public double getDriverRightTrigger() {
        // TODO: why are we inverting the trigger values? they're bound to [0,+1]
        // already
        return -1 * driverController.getRightTriggerAxis();
    }

    public double getDriverLeftX() {
        return -1 * driverController.getLeftX();
    }

    // Intake Testing
    public double getOperatorLeftTrigger() {
        return operatorController.getLeftTriggerAxis();
    }

    public double getOperatorRightTrigger() {
        return operatorController.getRightTriggerAxis();
    }

    public double getOperatorControllerRoller() {
        return -1 * operatorController.getLeftY();
    }

    public boolean getIntakeButton() {
        return getButton(driverXboxBButton);
    }

    public boolean getButton(JoystickButton button) {
        return button.get();
    }
}
