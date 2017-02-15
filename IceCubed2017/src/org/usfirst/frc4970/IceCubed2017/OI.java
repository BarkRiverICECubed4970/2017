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

import org.usfirst.frc4970.IceCubed2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick joystick1;
    public JoystickButton joystickButton1;
    public Joystick joystick2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {

    	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        joystick2 = new Joystick(1);
        
        joystickButton1 = new JoystickButton(joystick2, 1);
        joystickButton1.whileHeld(new DriveTowardTower(Robot.towerDriveDutyCycle));
 
        joystick1 = new Joystick(0);

        joystickButton1 = new JoystickButton(joystick2, 5);
        joystickButton1.whenPressed(new GrabGear(Robot.grabDutyCycle, Robot.grabMaxCurrent));
        
        joystickButton1 = new JoystickButton(joystick2, 4);
        joystickButton1.whileHeld(new ReleaseGear(Robot.releaseGearTimeout, Robot.releaseDutyCycle));
         
        // SmartDashboard Buttons
        SmartDashboard.putData("PlaceGear", new PlaceGear());
        SmartDashboard.putData("TimedDrive", new TimedDrive(Robot.timedDriveTimeout, Robot.timedDriveDutyCycle));
        SmartDashboard.putData("DriveTowardTower", new DriveTowardTower(Robot.towerDriveDutyCycle));
        SmartDashboard.putData("GrabGear", new GrabGear(Robot.grabDutyCycle, Robot.grabMaxCurrent));
        SmartDashboard.putData("ReleaseGear", new ReleaseGear(Robot.releaseGearTimeout, Robot.releaseDutyCycle));
        SmartDashboard.putData("LiftTray", new LiftTray(Robot.liftTrayDutyCycle));
        SmartDashboard.putData("LowerTray", new LowerTray(Robot.lowerTrayTimeout, Robot.lowerTrayDutyCycle));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoystick1() {
        return joystick1;
    }

    public Joystick getJoystick2() {
        return joystick2;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

