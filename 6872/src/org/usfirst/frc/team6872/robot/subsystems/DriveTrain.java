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
	 * PS3 joystick.
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
	 *            Scale applied to joystick position
	 */
	public void drive(Joystick joy, double scale, Boolean tankDrive) {
		if (tankDrive) {
			double left = 0;
			double right = 0;
			if (joy.getRawAxis(2) > 0.1) {
				left = joy.getRawAxis(2);
			}
			else {
				left = -joy.getRawAxis(1);
			}
			if (joy.getRawAxis(3) > 0.1) {
				right = joy.getRawAxis(3);
			}
			else {
				right = joy.getRawAxis(5);
			}
			drive.tankDrive(left * scale, right * scale);
		}
		else {
			double power = 0;
			if (joy.getRawAxis(3) > 0.1) {
				power = joy.getRawAxis(3);
			}
			else {
				power = f(-joy.getRawAxis(1)) * scale;
			}
			drive.arcadeDrive(power, f(joy.getRawAxis(4)) * scale);
		}
	}
	
	private double f(double x) {
		return x;
		//return (x + Math.signum(x) * 0.4) / 1.4;
	}
}
