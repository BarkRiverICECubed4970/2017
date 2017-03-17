// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4970.IceCubed2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4970.IceCubed2017.Robot;
import org.usfirst.frc4970.IceCubed2017.subsystems.DriveTrain;

/**
 *
 */
public class ReverseDrive extends Command {

    public ReverseDrive() {

        requires(Robot.driveTrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.timedDriveDutyCycle = SmartDashboard.getNumber("Timed Drive DutyCycle", Robot.timedDriveDutyCycle);
    	Robot.reverseDriveTimeout = SmartDashboard.getNumber("Reverse Drive Timeout", Robot.reverseDriveTimeout);
    	setTimeout(Robot.reverseDriveTimeout);
    	Robot.driveTrain.controlLoop(DriveTrain.REVERSE_DRIVE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// don't reverse if we're in auto mode and the target hasn't been found
    	if ((Robot.inAuto == false) || (Robot.targetFound == true))
    	{
    		Robot.driveTrain.controlLoop(DriveTrain.REVERSE_DRIVE);
    	}
    }

    protected boolean isFinished() {
    	// exit if we're in auto and didn't find the target, or if the function times out
    	if (((Robot.inAuto == true) && (Robot.targetFound == false)) || (isTimedOut() == true))
    	{
    		return true;
    	} else
    	{
    		return false;
    	}
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.controlLoop(DriveTrain.STOP_MOTOR);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
