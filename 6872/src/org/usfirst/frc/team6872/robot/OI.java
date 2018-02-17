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
	
	public Joystick joy0 = new Joystick(0); // Gamepad F310
	public Joystick joy1 = new Joystick(1); // Extreme 3D Pro
	public SendableChooser<Command> chooser = new SendableChooser<>();
	
	public OI() {
		chooser.addDefault("Recorded Auto", new Autonomous(0, -2));
		chooser.addObject("Auto Left", new Autonomous(0, -1));
		chooser.addObject("Auto Middle", new Autonomous(1, -1));
		chooser.addObject("Auto Right", new Autonomous(2, -1));
		chooser.addObject("Auto Left to Left", new Autonomous(0, 0));
		chooser.addObject("Auto Middle to Left", new Autonomous(1, 0));
		chooser.addObject("Auto Right to Left", new Autonomous(2, 0));
		chooser.addObject("Auto Left to Right", new Autonomous(0, 1));
		chooser.addObject("Auto Middle to Right", new Autonomous(1, 1));
		chooser.addObject("Auto Right to Right", new Autonomous(2, 1));
		
		SmartDashboard.putData("Auto Mode", chooser);
		SmartDashboard.putBoolean("Tank Drive", false);
		SmartDashboard.putNumber("Joystick Sensibility", 0.6);
		
		bindButton(5, new RecordAuto());
		// Logitech Gamepad F310 XInput
		//bindButton(12, new ExtendArm()); // Arrow Up
		//bindButton(13, new RetractArm()); // Arrow Down
		//bindButton(9, new ContractWinch()); // Start
		//bindButton(4, new OpenClaw()); // Y
		//bindButton(1, new CloseClaw()); // A
	}
	
	@SuppressWarnings("unused")
	private JoystickButton bindButton(int buttonNumber, Command command) {
		JoystickButton button = new JoystickButton(joy0, buttonNumber);
		button.whileHeld(command);
		return button;
	}
}
