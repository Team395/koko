// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;

// public class Intakee extends CommandBase {

//   private final Intake m_intake;
//   private final IntakePositions requestedIntakePosition; 

//   public IntakePositions position = IntakePositions.UP;

//   // UP, DOWN
//   /** Creates a new Intakee. */
//   public Intakee() {
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

  

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}
  
//   public void intakeLift(final IntakePositions position) {
//     switch(positon) {
//       case UP: 
//         intakeArm.set(-Constants.intakeArmSpeed);
//         intakeArm.setIdleMode(IdleMode.kCoast); 
//         currentPosition = IntakePositions.UP; 
//         break; 

//       case DOWN: 
//         intakeArm.setIdleMode(IdleMode.kBrake); 
//         intakeArm.set(Constants.intakeArmSpeed); 
//         currentPosition = IntakePositions.DOWN) 
//         break; 
//     }
//   } 
//    // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     intakeLif(requestedIntakePosition); 

//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return true;
//   }
// }
