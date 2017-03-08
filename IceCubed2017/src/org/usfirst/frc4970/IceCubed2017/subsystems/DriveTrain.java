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

import org.usfirst.frc4970.IceCubed2017.Robot;
import org.usfirst.frc4970.IceCubed2017.RobotMap;
import org.usfirst.frc4970.IceCubed2017.commands.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem implements PIDOutput {

    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();        	
    public static AnalogInput sonar = new AnalogInput(3);;
    
    private final PIDController gyroPid = new PIDController(0.010, 0, 0, gyro, this);

    // control state values
    public static final int JOYSTICKS = 1;
    public static final int TOWER_DRIVE = 2;
    public static final int TURN_DEGREES = 3;
    public static final int TIMED_DRIVE = 4;
    public static final int REVERSE_DRIVE = 5;
    public static final int STOP_MOTOR = 6;
    
    private static double PID_rotateValue;
    private static double forward;
    private static double rotate;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    private static double dutyCycleLimit;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void setupGyroPID(double setPoint)
    {
    	Robot.gyroPidKp = SmartDashboard.getNumber("Gyro PID KP", Robot.gyroPidKp);
    	Robot.gyroPidKi = SmartDashboard.getNumber("Gyro PID KI", Robot.gyroPidKi);
    	Robot.gyroPidKd = SmartDashboard.getNumber("Gyro PID KD", Robot.gyroPidKd);
    	Robot.gyroPidMinIn = SmartDashboard.getNumber("Gyro PID Min Input", Robot.gyroPidMinIn);
    	Robot.gyroPidMaxIn = SmartDashboard.getNumber("Gyro PID Max Input", Robot.gyroPidMaxIn);
    	Robot.gyroPidMinOut = SmartDashboard.getNumber("Gyro PID Min Output", Robot.gyroPidMinOut);
    	Robot.gyroPidMaxOut = SmartDashboard.getNumber("Gyro PID Max Output", Robot.gyroPidMaxOut);
    	Robot.gyroPidTolerance = SmartDashboard.getNumber("Gyro PID Tolerance", Robot.gyroPidTolerance);

    	gyroPid.reset();
		gyroPid.setPID(Robot.gyroPidKp, Robot.gyroPidKi , Robot.gyroPidKd);
		gyroPid.setInputRange(Robot.gyroPidMinIn, Robot.gyroPidMaxIn);
		gyroPid.setOutputRange(Robot.gyroPidMinOut, Robot.gyroPidMaxOut);
		gyroPid.setAbsoluteTolerance(Robot.gyroPidTolerance);
		gyroPid.setSetpoint(setPoint);
		gyroPid.enable();
    }
    
    public void setGyroPidSetpoint(double setPoint)
    {
		gyroPid.setSetpoint(setPoint);    	
    }
    
    public boolean gyroPidOnTarget()
    {
    	return gyroPid.onTarget();
    }
    
    public void calibrateGyro()
    {
   		gyro.calibrate();
    }
    
    public void resetGyro()
    {
   		gyro.reset();
    }
    
    public double getGyroAngle()
    {
    	return gyro.getAngle();
    }    

    public void initSonar()
    {
        /* set up rangefinder */
        sonar.setOversampleBits(4);
        sonar.setAverageBits(2);
        AnalogInput.setGlobalSampleRate(500000);
    }
    
    public int getSonarAverage()
    {
    	return sonar.getAverageValue();
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void controlLoop(int commandInControl)
    {
    	switch (commandInControl) {

    	case JOYSTICKS:
    		forward = Robot.oi.getJoystick1().getRawAxis(1);
    		rotate = Robot.oi.getJoystick1().getRawAxis(0);
    		break;
    		
    	case TOWER_DRIVE:
    		forward = -Robot.towerDriveDutyCycle;
    		rotate = PID_rotateValue;
    		break;
    		
    	case TURN_DEGREES:
    		forward = 0.0;
    		rotate = PID_rotateValue;    		    		
    		break;
    		
    	case TIMED_DRIVE:
    		forward = -Robot.timedDriveDutyCycle;
    		rotate = PID_rotateValue;    		
    		break;
    		
    	case REVERSE_DRIVE:
    		forward = Robot.timedDriveDutyCycle;
    		rotate = 0.0;    		
    		break;
    		
    	case STOP_MOTOR:
    		forward = 0.0;
    		rotate = 0.0;
    		break;

    	default:
    		forward = 0.0;
    		rotate = 0.0;
    		break;

    	}
    	
    	dutyCycleLimit = SmartDashboard.getNumber("Max Drive DutyCycle",1.0);

    	if (dutyCycleLimit < 1.0)
    	{
    		if (forward < (-1.0*dutyCycleLimit))
    		{
    			forward = -1.0*dutyCycleLimit;
    		} else if (forward > dutyCycleLimit)
    		{
    			forward = dutyCycleLimit;
    		}
    		
    		if (rotate < (-1.0*dutyCycleLimit))
    		{
    			rotate = -1.0*dutyCycleLimit;
    		} else if (rotate > dutyCycleLimit)
    		{
    			rotate = dutyCycleLimit;
    		}
    	}
    	
    	robotDrive41.arcadeDrive(forward, rotate, false);
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		PID_rotateValue = output;
	}

}

