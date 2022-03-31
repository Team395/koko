package frc.robot.commands.enabling;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableIntake extends CommandBase {
    RobotContainer robotContainer;

    public EnableIntake(RobotContainer rc) {
        this.robotContainer = rc;
    }

    @Override
    public void execute() {
        robotContainer.enableIntake();
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
