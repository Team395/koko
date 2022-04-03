package frc.robot.commands.enabling;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableAll extends CommandBase {
    RobotContainer robotContainer;

    public EnableAll(RobotContainer rc) {
        this.robotContainer = rc;
    }

    @Override
    public void execute() {
        robotContainer.enableDrivetrain();
        robotContainer.enableClimber();
        robotContainer.enableIntake();
        robotContainer.addAutoChooser();
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
