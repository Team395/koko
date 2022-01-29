package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

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



}
