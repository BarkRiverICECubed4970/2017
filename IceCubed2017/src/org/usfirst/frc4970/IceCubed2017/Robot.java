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
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;

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

    public static ADXRS450_Gyro gyro;
    
    public static AnalogInput sonar;
    
    private static UsbCamera gearCamera;
    private static UsbCamera climbCamera;
    private static int cameraExposure;
    
    private static final int GEARCAM = 0;
    private static final int CLIMBCAM = 1;
    public static int cameraView = GEARCAM;
    
    public Preferences prefs;
    public static int timedDriveTimeout;
    public static int timedTurnTimeout;
    public static int releaseGearTimeout;
    public static int lowerTrayTimeout;
    public static double grabDutyCycle;
    public static double grabMaxDutyCycle;
    public static double grabMaxCurrent;
    public static double releaseDutyCycle;
    public static double timedDriveDutyCycle;
    public static double timedTurnRate;
    public static double towerDriveDutyCycle;
    public static double liftTrayDutyCycle;
    public static double lowerTrayDutyCycle;
    public static double climbRopeDutyCycle;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static GearMotor gearMotor;
    public static LiftMotor liftMotor;
    public static ClimbMotor climbMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

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
        
    	prefs = Preferences.getInstance();

        timedDriveTimeout = 4;
        timedTurnTimeout = 4;
        releaseGearTimeout = 4;
        lowerTrayTimeout = 4;
        grabDutyCycle = 0.3;
        grabMaxDutyCycle = 0.6;
        grabMaxCurrent = 8.0;
        releaseDutyCycle = 0.5;
        liftTrayDutyCycle = 0.25;
        lowerTrayDutyCycle = 0.25;
        timedDriveDutyCycle = 0.4;
        timedTurnRate = 0.4;
        towerDriveDutyCycle = 0.4;
        climbRopeDutyCycle = 1.0;
        
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        // instantiate the command used for the autonomous period
        autoChooser = new SendableChooser<Command>();
        autoChooser.addDefault("Place Gear", new PlaceGear());
        autoChooser.addObject("Timed Drive", new TimedDrive(timedDriveTimeout, timedDriveDutyCycle));
        autoChooser.addObject("Do Nothing", null);
        SmartDashboard.putData("Autonomous chooser", autoChooser);

        gyro = new ADXRS450_Gyro();        	
        
        /* set up rangefinder */
        sonar = new AnalogInput(3);
        sonar.setOversampleBits(4);
        sonar.setAverageBits(2);
        AnalogInput.setGlobalSampleRate(500000);
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


        new Thread(() -> {
        	cameraExposure = 3;

        	gearCamera = CameraServer.getInstance().startAutomaticCapture();
        	gearCamera.setResolution(320, 240);
        	gearCamera.setExposureManual(cameraExposure);

        	climbCamera = CameraServer.getInstance().startAutomaticCapture();
        	climbCamera.setResolution(320, 240);
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
        
        
        new Thread(() -> {
        	double counter = 0;
        	while (true) {
        		SmartDashboard.putNumber("Counter", counter++);
        		SmartDashboard.putNumber("Raw Range Values", Robot.getSonarAverage());
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
    
    public static void calibrateGyro()
    {
   		gyro.calibrate();
    }
    
    public static void resetGyro()
    {
   		gyro.reset();
    }
    
    public static double getGyroAngle()
    {
    	return gyro.getAngle();
    }
    
    public static int getSonarAverage()
    {
    	return sonar.getAverageValue();
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
        cameraExposure = 0;
        gearCamera.setExposureManual(cameraExposure);
        autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
        gyro.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
       	SmartDashboard.putNumber("gyro", gyro.getAngle());
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        cameraExposure = 3;
        gearCamera.setExposureManual(cameraExposure);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
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
