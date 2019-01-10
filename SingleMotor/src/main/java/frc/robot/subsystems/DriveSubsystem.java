/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

// local imports
import frc.robot.RobotMap;
import frc.robot.commands.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  // instantiate new motor controller objects
  public WPI_TalonSRX leftDriveMotor = new WPI_TalonSRX(RobotMap.leftTalonMotorPort);
  public WPI_TalonSRX rightDriveMotor = new WPI_TalonSRX(RobotMap.rightTalonMotorPort);
  public AnalogInput analogDistance = new AnalogInput(RobotMap.analogChan0);

// instantiate a new DifferentialDrive object
// assign motor controllers to differential drive
public DifferentialDrive drive = new DifferentialDrive(leftDriveMotor, rightDriveMotor);
public DigitalInput limitSwitch0 = new DigitalInput(RobotMap.addrLimitSwitch0);

// assign motor controllers to differential drive
//not needed at this time

// create constructor function

// point slaves to masters

// add teleopDrive() method
// not needed at this time


// add manualDrive() method
// throttle is how fast movement fwd/bkwd steer rate of change in direction
public void manualDrive (double move, double turn) {




  // null out joystick or create dead band
  


    if (Math.abs(move) < 0.1) {
       move = 0.00;
    }
    if (Math.abs(turn) < 0.1) {
        turn = 0.00;
    }

  // limit speed 
  if  (move > 0.1) move = move * RobotMap.maxMoveSpeed;
  if  (move < -0.1) move = move * RobotMap.maxMoveSpeed;
  if  (turn > 0.1) turn = turn * RobotMap.maxTurnSpeed;
  if  (turn < -0.1) turn = turn * RobotMap.maxTurnSpeed; 

  double volts = analogDistance.getVoltage();

  if (move < 0) {
    if (volts <= RobotMap.maxMoveSpeed) {
      move = move * volts;
      turn = turn * volts;
    }
    if (volts <= 0.5) {
      move = 0.5;
    }
  }

  System.out.println("AnalogValue = " + volts);
  System.out.println("MoveValue = " + move);
  System.out.println("TurnValue = " + turn);

  drive.arcadeDrive(move, turn);
}


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    setDefaultCommand(new DriveManuallyCommand());
  }
}
