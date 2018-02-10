/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6872.robot;

import org.usfirst.frc.team6872.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick joystick = new Joystick(0);
	public SendableChooser<Command> chooser = new SendableChooser<>();
	
	public OI() {
		chooser.addObject("Auto Left", new Autonomous(0, -1));
		chooser.addObject("Auto Centre", new Autonomous(1, -1));
		chooser.addObject("Auto Right", new Autonomous(2, -1));
		chooser.addObject("Auto Left to Left", new Autonomous(0, 0));
		chooser.addObject("Auto Centre to Left", new Autonomous(1, 0));
		chooser.addObject("Auto Right to Left", new Autonomous(2, 0));
		chooser.addObject("Auto Left to Right", new Autonomous(0, 1));
		chooser.addObject("Auto Centre to Right", new Autonomous(1, 1));
		chooser.addObject("Auto Right to Right", new Autonomous(2, 1));
		SmartDashboard.putData("Auto Mode", chooser);
		
		SmartDashboard.putBoolean("Tank Drive", false);
		SmartDashboard.putNumber("Joystick Sensibility", 0.6);
		
		// Logitech Gamepad F310 XInput
		bindButton(12, new ExtendArm()); // Arrow Up
		bindButton(13, new RetractArm()); // Arrow Down
		bindButton(9, new ContractWinch()); // Start
		bindButton(3, new OpenClaw()); // Y
		bindButton(0, new CloseClaw()); // A
	}
	
	private JoystickButton bindButton(int buttonNumber, Command command) {
		JoystickButton button = new JoystickButton(joystick, buttonNumber);
		button.whileHeld(command);
		return button;
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
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

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
