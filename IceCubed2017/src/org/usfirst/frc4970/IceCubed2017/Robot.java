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

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;

import org.usfirst.frc4970.IceCubed2017.commands.PlaceGear;
import org.usfirst.frc4970.IceCubed2017.commands.TimedDrive;
import org.usfirst.frc4970.IceCubed2017.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser<Command> autoChooser;

    private static final int IMG_WIDTH = 320;
    private static final int IMG_HEIGHT = 240;
    public static final double IMG_CENTER = (double)IMG_WIDTH/2.0;
    
    private static UsbCamera gearCamera;
    private static UsbCamera climbCamera;
    private static int cameraExposure = 3;
    
    public static final int GEARCAM = 0;
    public static final int CLIMBCAM = 1;
    public static int cameraView = GEARCAM;

    public static double centerTimedDriveTimeout = 0.5;
    public static double sideTimedDriveTimeout = 1.7;
    public static double towerDriveTimeout = 3.5;
    public static double turnDegreesTimeout = 3.0;
    public static double reverseDriveTimeout = 2.0;
    public static double reverseDriveDutyCycle = 0.3;
    public static double releaseGearTimeout = 3.0;
    public static double lowerTrayTimeout = 4.0;
    public static double grabDutyCycle = 0.3;
    public static double grabMaxDutyCycle = 0.6;
    public static double releaseDutyCycle = 0.5;
    public static double timedDriveDutyCycle = 0.4;
    public static double towerDriveDutyCycle = 0.35;
    public static double liftTrayDutyCycle = 0.25;
    public static double lowerTrayDutyCycle = 0.25;
    public static double climbRopeDutyCycle = 1.0;
    public static double turnDegrees = -60.0;
    public static double pixelsToDegrees = 0.05;
    
    public static double gyroPidKp = 0.05;
    public static double gyroPidKi = 0.0;
    public static double gyroPidKd = 0.0;
    public static double gyroPidMinIn = -60.0;
    public static double gyroPidMaxIn = 60.0;
    public static double gyroPidMinOut = -1.0;
    public static double gyroPidMaxOut = 1.0;
    public static double gyroPidTolerance = 4.0;
    public static double gyroPidMaxSetpoint = 8;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static GearMotor gearMotor;
    public static LiftMotor liftMotor;
    public static ClimbMotor climbMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static double centerXArray[];
    private static final double[] defaultArray = {0.0};
    
    public static boolean targetFound = false;
    public static boolean inAuto = false;
    public static boolean gambleInAuto = false;
        
    private static NetworkTable table;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
   
    	RobotMap.init();
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        gearMotor = new GearMotor();
        liftMotor = new LiftMotor();
        climbMotor = new ClimbMotor();
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        table = NetworkTable.getTable("GRIP/myContoursReport");

        // instantiate the command used for the autonomous period
        autoChooser = new SendableChooser<Command>();
        autoChooser.addDefault("Timed Drive", new TimedDrive(false));
        autoChooser.addObject("Place Gear Left", new PlaceGear(60.0, false));
        autoChooser.addObject("Place Gear Center Feeling Lucky", new PlaceGear(0.0, true));
        autoChooser.addObject("Place Gear Center Safe", new PlaceGear(0.0, false));
        autoChooser.addObject("Place Gear Right", new PlaceGear(-60.0, false));
        autoChooser.addObject("Do Nothing", null);
        SmartDashboard.putData("Autonomous chooser", autoChooser);

        SmartDashboard.putNumber("Max Drive DutyCycle",1.0);
    	SmartDashboard.putNumber("Timed Drive DutyCycle", timedDriveDutyCycle);
    	SmartDashboard.putNumber("Turn Degrees", turnDegrees);
    	SmartDashboard.putNumber("Turn Degrees Override", 0.0);
    	SmartDashboard.putNumber("Tower Drive DutyCycle", towerDriveDutyCycle);
    	SmartDashboard.putNumber("Gyro PID KP", gyroPidKp);
    	SmartDashboard.putNumber("Gyro PID KI", gyroPidKi);
    	SmartDashboard.putNumber("Gyro PID KD", gyroPidKd);
    	SmartDashboard.putNumber("Gyro PID Min Input", gyroPidMinIn);
    	SmartDashboard.putNumber("Gyro PID Max Input", gyroPidMaxIn);
    	SmartDashboard.putNumber("Gyro PID Min Output", gyroPidMinOut);
    	SmartDashboard.putNumber("Gyro PID Max Output", gyroPidMaxOut);
    	SmartDashboard.putNumber("Gyro PID Tolerance", gyroPidTolerance);
    	SmartDashboard.putNumber("Gyro PID Max Setpoint", gyroPidMaxSetpoint);
    	SmartDashboard.putNumber("Center Timed Drive Timeout", centerTimedDriveTimeout);
    	SmartDashboard.putNumber("Side Timed Drive Timeout", sideTimedDriveTimeout);
    	SmartDashboard.putNumber("Tower Drive Timeout", towerDriveTimeout);
    	SmartDashboard.putNumber("Turn Degrees Timeout", turnDegreesTimeout);
    	SmartDashboard.putNumber("Reverse Drive Timeout", reverseDriveTimeout);
    	SmartDashboard.putNumber("Pixels To Degrees", pixelsToDegrees);
    	
    	driveTrain.setupGyroPID();
    	driveTrain.initSonar();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        new Thread(() -> {

        	gearCamera = CameraServer.getInstance().startAutomaticCapture(1);
        	gearCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        	gearCamera.setExposureManual(cameraExposure);
        	
        	climbCamera = CameraServer.getInstance().startAutomaticCapture(0);
        	climbCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        	climbCamera.setExposureManual(cameraExposure);
        	
        	CvSink cvSink1 = CameraServer.getInstance().getVideo(gearCamera);
        	CvSink cvSink2 = CameraServer.getInstance().getVideo(climbCamera);

        	CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);
        	
        	Mat source = new Mat();
        	
        	while (true) {
        		if (cameraView == GEARCAM)
        		{
                    cvSink2.setEnabled(false);
                    cvSink1.setEnabled(true);
                    cvSink1.grabFrame(source);
        			
        		} else
        		{
                    cvSink1.setEnabled(false);
                    cvSink2.setEnabled(true);
                    cvSink2.grabFrame(source);        			
        		}

        		outputStream.putFrame(source);
         	}
        	
        }).start();
      
    	SmartDashboard.putNumber("Camera Exposure", cameraExposure);

        new Thread(() -> {
        	double counter = 0;
        	while (true) {
        		SmartDashboard.putNumber("Counter", counter++);
        		SmartDashboard.putNumber("Raw Range Values", driveTrain.getSonarAverage());
        		Timer.delay(0.1);
        	}
        }).start();        
    }

    public static void toggleCamera()
    {
    	if (cameraView == GEARCAM)
    	{
    		cameraView = CLIMBCAM;
    	} else
    	{
    		cameraView = GEARCAM;
    	}
    }
     
    public static int getCameraView()
    {
    	return cameraView;
    }
     
    public static boolean updateCenterXArray()
    {
    	centerXArray = table.getNumberArray("centerX", defaultArray);
    	if (centerXArray.length == 2)
    	{
    		return true;
    	} else
    	{
    		return false;
    	}
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();      
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	inAuto = true;
        cameraExposure = 1;
        gearCamera.setExposureManual(cameraExposure);
        autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
        driveTrain.resetGyro();
    }

    public static void setExposure(int exposure)
    {
        cameraExposure = exposure;
        gearCamera.setExposureManual(cameraExposure);
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
       	SmartDashboard.putNumber("gyro", driveTrain.getGyroAngle());
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    	inAuto = false;
        cameraExposure = 3;
        gearCamera.setExposureManual(cameraExposure);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("Gyro Angle", Robot.driveTrain.getGyroAngle());

        if (cameraExposure != (int)SmartDashboard.getNumber("Camera Exposure", 3))
        {
        	cameraExposure = (int)SmartDashboard.getNumber("Camera Exposure", 3);
        	gearCamera.setExposureManual(cameraExposure);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
