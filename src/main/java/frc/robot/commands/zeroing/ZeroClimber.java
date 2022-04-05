package frc.robot.commands.zeroing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ZeroClimber extends CommandBase {
    Climber climber;

    public ZeroClimber(Climber climber) {
        this.climber = climber;

        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.zeroEncoders();
        climber.rotateToDegrees(0.0);
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
