/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6872.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6872.robot.RobotMap;
import org.usfirst.frc.team6872.robot.commands.DriveWithJoysticks;

public class DriveTrain extends Subsystem {
	
	private SpeedController leftMotor = RobotMap.leftMotor;
	private SpeedController rightMotor = RobotMap.rightMotor;
	private DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick0.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 *
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}

	/**
	 * Arcade style driving for the DriveTrain.
	 * 
	 * @param scale
	 *            Scale applied to joystick0 position
	 */
	public void drive(Joystick joy0, Joystick joy1, double scale, Boolean tankDrive) {
		if (tankDrive) {
			double left = 0;
			double right = 0;
			if (joy0.getRawAxis(2) > 0.1) {
				left = joy0.getRawAxis(2);
			}
			else {
				left = -joy0.getRawAxis(1);
			}
			if (joy0.getRawAxis(3) > 0.1) {
				right = joy0.getRawAxis(3);
			}
			else {
				right = joy0.getRawAxis(5);
			}
			drive.tankDrive(left * scale, right * scale);
		}
		else {
			double turn = 0;
			if (joy1.getRawButton(2)) {
				turn = -1;
			}
			else if (joy1.getRawButton(1)) {
				turn = 1;
			}
			else {
				turn = f(joy1.getX());
			}
			drive.arcadeDrive(f(-joy1.getY()) * scale, turn * scale);
		}
	}
	
	private double f(double x) {
		return x;
		//return (x + Math.signum(x) * 0.4) / 1.4;
	}
}
