// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class IO {
        public static final double kJoystickDeadzone = 0.15;
    }

    public static final class Drivetrain {
        public static final boolean Enabled = false;

        public static final int kLeftLeaderFalconID = 1;
        public static final int kLeftFollowerFalconID = 3;
        public static final int kRightLeaderFalconID = 2;
        public static final int kRightFollowerFalconID = 4;

        public static final int kMinimumSpeed = 0;
        public static final double kTurnClamp = 0.5;
    }

    public static final class Intake {
        public static final boolean Enabled = false;

        public static final int kRollerSpxID = 5;
        public static final int kArmSparkMaxID = 8;
        public static final double kArmMaxSpeed = 0.8;
        public static final double kRollerMaxSpeed = 0.5;
    }

    public static final class Climber {
        public static final boolean Enabled = true;

        public static final int kRotateLeaderSparkMaxID = 5;
        public static final int kRotateFollowerSparkMaxID = 6;
        public static final double kRotateMaxSpeed = 0.5;

        public static final SolenoidConfiguration kHook1 = new SolenoidConfiguration(0, 0, 1);
        public static final SolenoidConfiguration kHook2 = new SolenoidConfiguration(0, 2, 3);
        public static final SolenoidConfiguration kHook3 = new SolenoidConfiguration(0, 4, 5);
        public static final SolenoidConfiguration kHook4 = new SolenoidConfiguration(0, 6, 7);

        public static final SolenoidConfiguration kLock1 = new SolenoidConfiguration(1, 0, 1);
        public static final SolenoidConfiguration kLock2 = new SolenoidConfiguration(1, 2, 3);
        public static final SolenoidConfiguration kRaise = new SolenoidConfiguration(1, 4, 5);
    }
}
