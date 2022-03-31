package frc.robot.commands.zeroing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ZeroIntake extends CommandBase {
    Intake intake;

    public ZeroIntake(Intake intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.zeroEncoders();
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
