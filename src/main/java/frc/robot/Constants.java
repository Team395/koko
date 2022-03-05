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
        //TODO_Label Ports
    public static final int driveLeftLeaderFalconID = 1;
    public static final int driveLeftFollowerFalconID = 3;
    public static final int driveRightLeaderFalconID = 2;
    public static final int driveRightFollowerFalconID = 4; 

    //Intake 
    public static final int intakeArmSPXID = 5;
    public static final int intakeRollerSparkMaxID = 6;
    
        //TODO
    public static final double intakeArmSpeed = 0.8;

        //TODO_Tune
    public static final double kTurnClamp = 0.5;
    public static final int kDriveMinimumSpeed = 0;

    public static final double kJoystickTurnDeadzone = 0.5;

    //Climber SparkMax and Solenoids TODO label ports
    public static final int ClimberRotateSparkMaxID = 5;
    public static final int ClimberLiftSparkMaxID = 0;

    public static final int ClimberUnlockFrontSolenoidId = 0;
    public static final int ClimberLockFrontSolenoidId = 1;
    public static final int ClimberOpenFrontSolenoidId = 2;
    public static final int ClimberCloseFrontSolenoidId = 3;
    public static final int ClimberUnlockBackSolenoidId = 4;
    public static final int ClimberLockBackSolenoidId = 5;
    

}
