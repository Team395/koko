// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Drivetrain Falcon 500s 
    public static final int driveLeftLeaderFalconID = 1;
    public static final int driveLeftFollowerFalconID = 3;
    public static final int driveRightLeaderFalconID = 2;
    public static final int driveRightFollowerFalconID = 4;

    public static final double kTurnClamp = 0.5;
    public static final int kDriveMinimumSpeed = 0;
    public static final double kJoystickTurnDeadzone = 0.15;

    //Intake 
    public static final int intakeRollerSPXID = 5;
    public static final int intakeArmSparkMaxID = 8;
    public static final double intakeArmSpeed = 0.8;

    public static final double kJoystickRollerDeadzone = 0.2;
    public static final double kJoystickArmDeadzone = 0.2;

    public static final double kMaxRotateSpeed = 0.5;

    //Climber 7Solenoid, 2Sparks
    public static final int ClimberRotate1SparkMaxID = 5;
    public static final int ClimberRotate2SparkMaxID = 6;

     //PCM 0
    public static final int ClimbHook1OpenSolenoidID = 0;
    public static final int ClimbHook1CloseSolenoidID = 1;
    public static final int ClimbHook2OpenSolenoidID = 2;
    public static final int ClimbHook2CloseSolenoidID = 3;
    public static final int ClimbHook3OpenSolenoidID = 4;
    public static final int ClimbHook3CloseSolenoidID = 5;
    public static final int ClimbHook4OpenSolenoidID = 6;
    public static final int ClimbHook4CloseSolenoidID = 7;

     //PCM 1
    public static final int ClimbLock1SolenoidID = 0;
    public static final int ClimbUnlock1SolenoidID = 1;
    public static final int ClimbLock2SolenoidID = 2;
    public static final int ClimbUnlock2SolenoidID = 3; 

    public static final int ClimbRaiseSolenoidID = 4;
    public static final int ClimbLowerSolenoidID = 5;
}
