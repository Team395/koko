package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IO {

    XboxController driverController = new XboxController(0);
    XboxController operatorController = new XboxController(1);

    public double getControllerLeftTrigger() {
        // TODO: why are we inverting the trigger value? they're bound to [0,+1] already
        return -1 * driverController.getLeftTriggerAxis();
    }

    public double getControlleRightTrigger() {
        // TODO: why are we inverting the trigger values? they're bound to [0,+1]
        // already
        return -1 * driverController.getRightTriggerAxis();
    }

    public double getControllerTurn() {
        return -1 * driverController.getLeftX();
    }

    // Intake Testing
    public double getIntakeLeftTrigger() {
        return operatorController.getLeftTriggerAxis();
    }

    public double getIntakeRightTrigger() {
        return operatorController.getRightTriggerAxis();
    }

    public double getIntakeControllerRoller() {
        return -1 * operatorController.getLeftY();
    }

    public double getIntake() {
        return 0;
        // return -1 * solenoidController.getRightY();
    }

    // TODO: set to appropriate input
    public double getClimberRotate() {
        // return 0;
        return -1 * operatorController.getRightY();
    }

    public boolean getButton(JoystickButton button) {
        return button.get();
    }

    JoystickButton intakeXboxCButton = new JoystickButton(driverController, 5);

    // Climber Testing
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
}
