// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.Climb.ExtendPositions;
import frc.robot.enums.Climb.HookPositions;
import frc.robot.enums.Climb.LockPositions;
import frc.robot.subsystems.Climber;


public class ClimbSequence extends SequentialCommandGroup {

  public ClimbSequence(Climber climber, IO io) {
    addCommands(
      new RotateToDegrees(climber, Constants.Climber.kMidDegrees),
      new InstantCommand(() -> climber.setExtend(ExtendPositions.RAISE), climber),

      //      Front of Robot -->

      //    3           1
      //     >---------<
      //    4     |     2
      //          |      
      //          |______________
      //        __|__________\
      //        [ O   O   O ] \__

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

      new InstantCommand(() -> climber.setLock1(LockPositions.UNLOCK), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setHook1(HookPositions.OPEN), climber),

      //  1  (2)
      //   \ /    U1
      //    |
      //   / \   (L2)
      // (3) (4)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setHook1(HookPositions.CLOSE), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setLock1(LockPositions.LOCK), climber),

      new WaitForButton(io.driverXboxAButton, io, climber),
      new InstantCommand(() -> climber.setExtend(ExtendPositions.LOWER), climber),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setLock2(LockPositions.UNLOCK), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setHook3(HookPositions.OPEN), climber),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \    U2
      //  3  (4)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new RotateToDegrees(climber, Constants.Climber.kHighDegrees),

      //          3
      //  (4) __ /          U2
      //         \
      //         / ‾‾ (1)  (L1)
      //      (2)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setHook3(HookPositions.CLOSE), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setLock2(LockPositions.LOCK), climber),

      //         (3)
      //  (4) __ /         (L2)
      //         \
      //         / ‾‾ (1)  (L1)
      //      (2)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setLock1(LockPositions.UNLOCK), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setHook1(HookPositions.OPEN), climber),
      new InstantCommand(() -> climber.setHook2(HookPositions.OPEN), climber),

      //         (3)
      //  (4) __ /         (L2)
      //         \
      //         / ‾‾ 1     U1
      //       2

      new WaitForButton(io.driverXboxAButton, io, climber),

      new RotateToDegrees(climber, Constants.Climber.kHalfRestDegrees),

      // (4) (3)
      //   \ /   (L2)
      //    |
      //   / \    U1
      //  2   1

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setHook1(HookPositions.CLOSE), climber),

      // (4) (3)
      //   \ /   (L2)
      //    |
      //   / \    U1
      //  2  (1)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new RotateToDegrees(climber, Constants.Climber.kTraversalDegrees),

      //          2
      //  (1) __ /          U1
      //         \
      //         / ‾‾ (4)  (L2)
      //      (3)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setHook2(HookPositions.CLOSE), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setLock1(LockPositions.LOCK), climber),

      //         (2)
      //  (1) __ /         (L1)
      //         \
      //         / ‾‾ (4)  (L2)
      //      (3)

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setLock2(LockPositions.UNLOCK), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setHook4(HookPositions.OPEN), climber),
      new InstantCommand(() -> climber.setHook3(HookPositions.OPEN), climber),

      //         (2)
      //  (1) __ /         (L1)
      //         \
      //         / ‾‾ 4     U2
      //       3

      new WaitForButton(io.driverXboxAButton, io, climber),

      new RotateToDegrees(climber, Constants.Climber.kRestDegrees),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \    U2
      //  3   4

      new WaitForButton(io.driverXboxAButton, io, climber),

      new InstantCommand(() -> climber.setHook4(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setHook3(HookPositions.CLOSE), climber),
      new WaitCommand(Constants.Climber.kLockWaitSeconds),
      new InstantCommand(() -> climber.setLock2(LockPositions.LOCK), climber)

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

    );
  }
}
