// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.IO;
import frc.robot.enums.Climb.HookPositions;
import frc.robot.enums.Climb.LockPositions;
import frc.robot.subsystems.Climber;


public class ClimbSequence extends SequentialCommandGroup {

  public ClimbSequence(Climber climber, IO io) {
    addCommands(
      new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kMidDegrees), climber),

      // <- Front of Robot

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

      new InstantCommand(() -> climber.setLock1(LockPositions.UNLOCK), climber),
      new InstantCommand(() -> climber.setHook2(HookPositions.OPEN), climber),

      // (1)  2
      //   \ /    U1
      //    |
      //   / \   (L2)
      // (3) (4)

      new WaitForButton(io.driverXboxAButton, io),

      new InstantCommand(() -> climber.setHook2(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setLock1(LockPositions.LOCK), climber),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

      new InstantCommand(() -> climber.setLock2(LockPositions.UNLOCK), climber),
      new InstantCommand(() -> climber.setHook4(HookPositions.OPEN), climber),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \    U2
      // (3)  4

      new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kHighDegrees), climber),

      //       4
      //        \ __ (3)   U2
      //        /
      // (2) ‾‾|          (L1)
      //      (1)

      new InstantCommand(() -> climber.setHook4(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setLock2(LockPositions.LOCK), climber),

      //      (4)
      //        \ __ (3)  (L2)
      //        /
      // (2) ‾‾|          (L1)
      //      (1)

      new InstantCommand(() -> climber.setLock1(LockPositions.UNLOCK), climber),
      new InstantCommand(() -> climber.setHook1(HookPositions.OPEN), climber),
      new InstantCommand(() -> climber.setHook2(HookPositions.OPEN), climber),

      //      (4)
      //        \ __ (3)  (L2)
      //        /
      //   2 ‾‾|           U1
      //       1

      new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kHalfRestDegrees), climber),

      // (4) (3)
      //   \ /   (L2)
      //    |
      //   / \    U1
      //  2  1

      new InstantCommand(() -> climber.setHook2(HookPositions.CLOSE), climber),

      // (4) (3)
      //   \ /   (L2)
      //    |
      //   / \    U1
      // (2)  1

      new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kTraversalDegrees), climber),

      //       1
      //        \ __ (2)   U1
      //        /
      // (3) ‾‾|          (L2)
      //      (4)

      new InstantCommand(() -> climber.setHook1(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setLock1(LockPositions.LOCK), climber),

      //      (1)
      //        \ __ (2)  (L1)
      //        /
      // (3) ‾‾|          (L2)
      //      (4)

      new InstantCommand(() -> climber.setLock2(LockPositions.UNLOCK), climber),
      new InstantCommand(() -> climber.setHook4(HookPositions.OPEN), climber),
      new InstantCommand(() -> climber.setHook3(HookPositions.OPEN), climber),

      //      (1)
      //        \ __ (2)  (L1)
      //        /
      //  3  ‾‾|           U2
      //       4

      new InstantCommand(() -> climber.rotateToDegrees(Constants.Climber.kRestDegrees), climber),

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \    U2
      //  3   4

      new InstantCommand(() -> climber.setHook4(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setHook3(HookPositions.CLOSE), climber),
      new InstantCommand(() -> climber.setLock2(LockPositions.LOCK), climber)

      // (1) (2)
      //   \ /   (L1)
      //    |
      //   / \   (L2)
      // (3) (4)

    );
  }
}
