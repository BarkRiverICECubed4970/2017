// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4970.IceCubed2017;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainSpeedController1;
    public static SpeedController driveTrainSpeedController2;
    public static SpeedController driveTrainSpeedController3;
    public static SpeedController driveTrainSpeedController4;
    public static RobotDrive driveTrainRobotDrive41;

    public static SpeedController gearMotorGearMotorDriver;
    public static SpeedController liftMotorGearMotorDriver;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainSpeedController1 = new Spark(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 1", (Spark) driveTrainSpeedController1);
        
        driveTrainSpeedController2 = new Spark(1);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 2", (Spark) driveTrainSpeedController2);
        
        driveTrainSpeedController3 = new Spark(3);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 3", (Spark) driveTrainSpeedController3);
        
        driveTrainSpeedController4 = new Spark(4);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 4", (Spark) driveTrainSpeedController4);
        
        driveTrainRobotDrive41 = new RobotDrive(driveTrainSpeedController1, driveTrainSpeedController3,
              driveTrainSpeedController2, driveTrainSpeedController4);
        
        driveTrainRobotDrive41.setSafetyEnabled(true);
        driveTrainRobotDrive41.setExpiration(0.1);
        driveTrainRobotDrive41.setSensitivity(0.5);
        driveTrainRobotDrive41.setMaxOutput(1.0);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainRobotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        liftMotorGearMotorDriver = new Spark(5);
        LiveWindow.addActuator("LiftMotor", "LiftMotorDriver", (Spark) liftMotorGearMotorDriver); 
        gearMotorGearMotorDriver = new Spark(6);
        LiveWindow.addActuator("GearMotor", "GearMotorDriver", (Spark) gearMotorGearMotorDriver); 
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
