/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveManuallyCommand extends Command {
  public DriveManuallyCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveSubsystem);
  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
// place code for joystick inputs here
//manualDrive (double move, double turn)
double move = RobotMap.invertDriveY * Robot.oi.controllerDrive.getY();
double turn = RobotMap.invertDriveX * Robot.oi.controllerDrive.getX();
/*    
    double move = invertDriveY * Robot.oi.controllerDrive.getY();
    double turn = invertDriveX * Robot.oi.controllerDrive.getX();
*/
    Robot.driveSubsystem.manualDrive(move, turn);
    //Robot.driveSubsystem.leftDriveMotor.set(move);

    double volts = Robot.driveSubsystem.analogDistance.getValue();
    
SmartDashboard.putNumber ("Analog", volts);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
