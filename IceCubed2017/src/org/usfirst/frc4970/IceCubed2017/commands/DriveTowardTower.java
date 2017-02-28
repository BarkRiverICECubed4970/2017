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

/**
 *
 */
public class DriveTowardTower extends Command {

	private double driveTowardTowerDutyCycle;
	private double storedDutyCycle;
	private double kP;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveTowardTower(double dutyCycle) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        storedDutyCycle = dutyCycle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	driveTowardTowerDutyCycle = SmartDashboard.getNumber("Tower Drive DutyCycle", storedDutyCycle);
    	kP = SmartDashboard.getNumber("Timed Drive DutyCycle", 0.03);
    	Robot.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       	Robot.driveTrain.drive(driveTowardTowerDutyCycle, 
    					   (-Robot.getGyroAngle()*kP), false);
       	SmartDashboard.putNumber("Raw Range Values", Robot.getSonarAverage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
