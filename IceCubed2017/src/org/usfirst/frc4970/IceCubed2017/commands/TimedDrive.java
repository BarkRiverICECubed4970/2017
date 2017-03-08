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
public class TimedDrive extends Command {

	private static boolean centerPosition;
	private static double timeout;
	
	public TimedDrive(boolean isCenter) {

        requires(Robot.driveTrain);

        centerPosition = isCenter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.timedDriveDutyCycle = SmartDashboard.getNumber("Timed Drive DutyCycle", Robot.timedDriveDutyCycle);	
    	if (centerPosition)
    	{
        	timeout = SmartDashboard.getNumber("Center Timed Drive Timeout", Robot.centerTimedDriveTimeout);    		
    	} else
    	{
        	timeout = SmartDashboard.getNumber("Side Timed Drive Timeout", Robot.sideTimedDriveTimeout);    		    		
    	}
    	setTimeout(timeout);
    	Robot.driveTrain.resetGyro();
    	Robot.driveTrain.setupGyroPID(Robot.driveTrain.getGyroAngle());
    	Robot.driveTrain.controlLoop(DriveTrain.TIMED_DRIVE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.controlLoop(DriveTrain.TIMED_DRIVE);
    }

    protected boolean isFinished() {
        return isTimedOut();
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
