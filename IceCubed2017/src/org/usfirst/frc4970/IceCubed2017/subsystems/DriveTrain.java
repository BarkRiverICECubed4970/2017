// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4970.IceCubed2017.subsystems;

import org.usfirst.frc4970.IceCubed2017.RobotMap;
import org.usfirst.frc4970.IceCubed2017.commands.*;
import org.usfirst.frc4970.IceCubed2017.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController speedController1 = RobotMap.driveTrainSpeedController1;
    private final SpeedController speedController2 = RobotMap.driveTrainSpeedController2;
    private final SpeedController speedController3 = RobotMap.driveTrainSpeedController3;
    private final SpeedController speedController4 = RobotMap.driveTrainSpeedController4;
    private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double forward, double rotate, boolean squaredInputs) {
//    	robotDrive41.tankDrive(left, right, squaredInputs);
//    	robotDrive41.arcadeDrive(Robot.oi.getJoystick1());
    	robotDrive41.arcadeDrive(forward, rotate, false);
       	SmartDashboard.putNumber("speedcontroller 1", Robot.driveTrain.speedController1.get());
       	SmartDashboard.putNumber("speedcontroller 2", Robot.driveTrain.speedController2.get());
       	SmartDashboard.putNumber("speedcontroller 3", Robot.driveTrain.speedController3.get());
       	SmartDashboard.putNumber("speedcontroller 4", Robot.driveTrain.speedController4.get());
    }
    
    public void stop() {
    	robotDrive41.drive(0,0);
    }

}

