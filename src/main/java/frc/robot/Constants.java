// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.enums.Climb.HookPositions;
import frc.robot.enums.Climb.LockPositions;
import frc.robot.enums.Intake.IntakePositions;
import frc.robot.enums.Climb.ExtendPositions;

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
    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;

    public static final class IO {
        public static final double kJoystickDeadzone = 0.15;
    }

    public static final class Drivetrain {
        // Direction
        public static final double drivetrainForward = -1;
        public static final double drivetrainBackward = 1;

        public static final TalonFXInvertType kLeftInvert = TalonFXInvertType.CounterClockwise; // Same as invert = "true"
        public static final TalonFXInvertType kRightInvert = TalonFXInvertType.Clockwise; // Same as invert = "false"

        public static final int kLeftLeaderFalconID = 1;
        public static final int kLeftFollowerFalconID = 3;
        public static final int kRightLeaderFalconID = 2;
        public static final int kRightFollowerFalconID = 4;

        public static final int kMinSpeed = 0;
        public static final double kMaxTurn = 0.5;
        public static final double kMaxSpeed = 0.5;
        public final static double kTurnAcceptableErrorDegrees = 0.5;

        /**
         * How many sensor units per rotation.
         * Using Talon FX Integrated Sensor.
         * 
         * @link https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
         */
        public final static int kSensorUnitsPerRotation = 2048;

        public static final int pidgeyCanId = 0; // TODO: fix
        // This is a property of the Pigeon IMU, and should not be changed.
        public final static int kPigeonUnitsPerRotation = 8192;
        public final static boolean kGyroReversed = false;

        // Motor neutral dead-band, set to the minimum 0.1%.
        public final static double kNeutralDeadband = 0.001;

        public final static double kMotorRotationsPerWheelRotation = 20.8333;
        public final static double kInchesPerFoot = 12.0;
        public final static double kRotationsPerInch = 1 / (2 * Math.PI * 3);
        public final static double kSensorUnitsPerFoot = Constants.Drivetrain.kInchesPerFoot
                * Constants.Drivetrain.kRotationsPerInch
                * Constants.Drivetrain.kMotorRotationsPerWheelRotation
                * Constants.Drivetrain.kSensorUnitsPerRotation;

        /**
         * PID Gains may have to be adjusted based on the responsiveness of control
         * loop.
         * kF: 1023 represents output value to Talon at 100%, 6800 represents Velocity
         * units at 100% output
         * Not all set of Gains are used in this project and may be removed as desired.
         * 
         * kP kI kD kF Iz PeakOut
         */
        public final static Gains kGains_Distance = new Gains(0.1, 0.0, 0.0, 0.0, 100, 0.50);
        public final static Gains kGains_Turning = new Gains(2.0, 0.0, 4.0, 0.0, 200, 1.00);
        private final static double kGains_Pigeon_kP = 0.025;
        public final static Gains kGains_Pigeon = new Gains(
                kGains_Pigeon_kP,
                0.0,
                kGains_Pigeon_kP / 10.0,
                0.0,
                200,
                1.00);
    }

    public static final class Intake {
        public static final boolean Enabled = true;

        public static final int kRollerSpxID = 5;
        public static final int kArmSparkMaxID = 8;
        public static final double kArmMaxSpeed = 0.8;
        public static final double kRollerMaxSpeed = 1.0;
        public static final double kRollerIntakeSpeed = Math.min(1.0, kRollerMaxSpeed);
        public static final double kRollerOuttakeSpeed = kRollerIntakeSpeed;
        public static final double kOuttakeSeconds = 5;
        public static final double kIntakeSeconds = 5;

        public final static Gains kGains = new Gains(0.1, 0.0, 10.0, 0.0, 0, 0.50);

        public final static double kUp = 0;
        public final static double kDown = 19;

        public final static Map<IntakePositions, Double> kMap  = new HashMap<IntakePositions, Double>() {{
            put(IntakePositions.UP, kUp);
            put(IntakePositions.DOWN, kDown);
        }};
    }

    public static final class Climber {
        public static final boolean Enabled = false;

        public static final int kRotateLeaderSparkMaxID = 5;
        public static final int kRotateFollowerSparkMaxID = 11;
        public static final double kRotateMaxSpeed = 0.5;

        public final static Gains kGainsLoaded = new Gains(0.9, 0.0, 0.0, 0.0, 0, 0.75);
        public final static Gains kGainsUnloaded = new Gains(0.4, 0.0, 0.0, 0.0, 0, 0.5);
        public final static double kDegreesToRotations = 150d / 360d; // rotations/degrees
        public final static double kRotationsToDegrees = 1 / kDegreesToRotations;
        // TODO: may need to increase threshold for climbing, had set to 5 when testing
        public final static double kRotateAcceptableErrorDegrees = 0.5;
        public final static double kLockWaitSeconds = 0.1;

        public final static double kMidDegrees = 0;
        public final static double kHighDegrees = kMidDegrees + 160;
        public final static double kHalfRestDegrees = kHighDegrees + (180 - kHighDegrees % 180);
        public final static double kTraversalDegrees = kHighDegrees + 180;
        // TODO: figure out why consistently undershooting full revolution
        public final static double kRestDegrees = kTraversalDegrees + (180 - kTraversalDegrees % 180) + 15;


        public final static double degreesToRotations(double degrees) {
            return degrees * kDegreesToRotations;
        }

        public final static double rotationsToDegrees(double rotations) {
            return rotations * kRotationsToDegrees;
        }

        public static final SolenoidConfiguration kHook1 = new SolenoidConfiguration(1, 4, 5);
        public static final SolenoidConfiguration kHook2 = new SolenoidConfiguration(0, 5, 4);
        public static final SolenoidConfiguration kHook3 = new SolenoidConfiguration(0, 7, 6);
        public static final SolenoidConfiguration kHook4 = new SolenoidConfiguration(1, 0, 1);

        public static final SolenoidConfiguration kLock1 = new SolenoidConfiguration(0, 0, 1);
        public static final SolenoidConfiguration kLock2 = new SolenoidConfiguration(0, 2, 3); 
        public static final SolenoidConfiguration kExtend = new SolenoidConfiguration(1, 2, 3);

        public static final class Hook {
            public final static DoubleSolenoid.Value kOpen = Value.kReverse;
            public final static DoubleSolenoid.Value kClose = Value.kForward;

            public final static Map<HookPositions, DoubleSolenoid.Value> kMap  = new HashMap<HookPositions, DoubleSolenoid.Value>() {{
                put(HookPositions.OPEN, kOpen);
                put(HookPositions.CLOSE, kClose);
            }};
        }

        public static final class Lock {
            public final static DoubleSolenoid.Value kLock = Value.kForward;
            public final static DoubleSolenoid.Value kUnlock = Value.kReverse;

            public final static Map<LockPositions, DoubleSolenoid.Value> kMap  = new HashMap<LockPositions, DoubleSolenoid.Value>() {{
                put(LockPositions.LOCK, kLock);
                put(LockPositions.UNLOCK, kUnlock);
            }};
        }

        public static final class Extend {
            public final static DoubleSolenoid.Value kRaise = Value.kForward;
            public final static DoubleSolenoid.Value kLower = Value.kReverse;

            public final static Map<ExtendPositions, DoubleSolenoid.Value> kMap  = new HashMap<ExtendPositions, DoubleSolenoid.Value>() {{
                put(ExtendPositions.RAISE, kRaise);
                put(ExtendPositions.LOWER, kLower);
            }};
        }
    }

    public static final class CTRE {
        /** ---- Flat constants, you should not need to change these ---- */
        /*
         * We allow either a 0 or 1 when selecting an ordinal for remote devices [You
         * can have up to 2 devices assigned remotely to a talon/victor]
         */
        public final static int REMOTE_0 = 0;
        public final static int REMOTE_1 = 1;
        /*
         * We allow either a 0 or 1 when selecting a PID Index, where 0 is primary and 1
         * is auxiliary
         */
        public final static int PID_PRIMARY = 0;
        public final static int PID_TURN = 1;
        /*
         * Firmware currently supports slots [0, 3] and can be used for either PID Set
         */
        public final static int SLOT_0 = 0;
        public final static int SLOT_1 = 1;
        public final static int SLOT_2 = 2;
        public final static int SLOT_3 = 3;
        /* ---- Named slots, used to clarify code ---- */
        public final static int kSlot_Distance = SLOT_0;
        public final static int kSlot_Turning = SLOT_1;
        public final static int kSlot_Velocity = SLOT_2;
        public final static int kSlot_MotionProfile = SLOT_3;
    }
}
