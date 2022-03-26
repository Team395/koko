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
    public static final int intakeArmSparkMaxID = 6;
    public static final double intakeArmSpeed = 0.8;
    public static final double kJoystickRollerDeadzone = 0.2;
    public static final double kJoystickArmDeadzone = 0.2;


    //Climber 7Solenoid, 1Sparks
    public static final int ClimberRotate1SparkMaxID = 5;
    public static final int ClimberRotate2SparkMaxID = 5;

    public static final int ClimberLiftUPSolenoidID = 0;
    public static final int ClimberLiftDOWNSolenoidID = 0;
    public static final int ClimbFApproachGO = 0;
    public static final int ClimberFApproachLEAVE = 0;
    public static final int ClimbFCloseGO = 0;
    public static final int ClimbFCloseLEAVE = 0;
    public static final int climbFLock = 0;
    public static final int climbFUnlock = 0;
    public static final int climbBApproachGO = 0;
    public static final int climbBApproachLEAVE = 0; 
    public static final int climbBCloseGO = 0; 
    public static final int climbBCloseLEAVE = 0;
    public static final int climbBlock = 0; 
    public static final int climbBLock = 0; 
    public static final int climbBUnlock = 0; 
}
