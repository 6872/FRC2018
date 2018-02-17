package org.usfirst.frc.team6872.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

import org.usfirst.frc.team6872.robot.RobotMap;
import org.usfirst.frc.team6872.robot.commands.ClawJoystick;

/**
 *
 */
public class Claw extends Subsystem {

    private SpeedController claw = RobotMap.clawMotor;
    
    public void move(double speed) {
    	claw.set(-speed);
    }
    
    public void stop() {
    	claw.stopMotor();
    }

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ClawJoystick());
	}
}

