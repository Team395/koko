package frc.robot.commands.enabling;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableClimber extends CommandBase {
    RobotContainer robotContainer;

    public EnableClimber(RobotContainer rc) {
        this.robotContainer = rc;
    }

    @Override
    public void execute() {
        robotContainer.enableClimber();
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
