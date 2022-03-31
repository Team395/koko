/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class TurnDegrees extends PIDCommand {
    private Drivetrain drivetrain;

    public TurnDegrees(Drivetrain drivetrain, double degreesToTurn) {
        super(new PIDController(Constants.Drivetrain.kGains_Pigeon.kP, Constants.Drivetrain.kGains_Pigeon.kI,
                Constants.Drivetrain.kGains_Pigeon.kD),
                drivetrain::getHeading, degreesToTurn, output -> drivetrain.arcadeDrive(0d, output), drivetrain);

        getController()
                .setTolerance(Constants.Drivetrain.kTurnAcceptableErrorDegrees, 10);

        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();

        this.drivetrain.zeroSensors();
    }

    @Override
    public void execute() {
        super.execute();

        SmartDashboard.putNumber("closedLoopError", getController().getPositionError());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
