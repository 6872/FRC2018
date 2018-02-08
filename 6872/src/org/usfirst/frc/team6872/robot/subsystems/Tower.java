package org.usfirst.frc.team6872.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

import org.usfirst.frc.team6872.robot.RobotMap;

/**
 *
 */
public class Tower extends Subsystem {
	
	private SpeedController arm = RobotMap.telescopicArmMotor;
	private SpeedController winch = RobotMap.winchMotor;

	/**
	 * Extend / retract the telescopic arm
	 * 
	 * @param speed
	 *            Speed in range [-1,1]
	 */
    public void moveArm(double speed) {
        arm.set(speed);
    }
    
    /**
	 * Contract the winch to climb
	 * 
	 * @param speed
	 *            Speed in range [0,1]
	 */
    public void contract(double speed) {
        winch.set(speed);
    }
    
    public void stopArm() {
    	arm.stopMotor();
    }
    
    public void stopWinch() {
    	winch.stopMotor();
    }

	@Override
	protected void initDefaultCommand() {}
}

