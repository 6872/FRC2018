package org.usfirst.frc.team6872.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

import org.usfirst.frc.team6872.robot.RobotMap;

/**
 *
 */
public class Tower extends Subsystem {
	
	private SpeedController tower = RobotMap.telescopicTowerMotor;
	private SpeedController winch = RobotMap.winchMotor;

	/**
	 * Extend / retract the telescopic tower
	 * 
	 * @param speed
	 *            Speed in range [-1,1]
	 */
    public void moveTower(double speed) {
        tower.set(speed);
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
    
    public void stopTower() {
    	tower.stopMotor();
    }
    
    public void stopWinch() {
    	winch.stopMotor();
    }

	@Override
	public void initDefaultCommand() {}
}

