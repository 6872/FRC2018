package org.usfirst.frc.team6872.robot.commands;

import org.usfirst.frc.team6872.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawJoystick extends Command {

    public ClawJoystick() {
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.gamepad.getRawButton(6)) {
        	Robot.claw.move(0.25);
    	}
    	else {
        	Robot.claw.move(-Robot.oi.gamepad.getRawAxis(3) * 0.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.claw.stop();
    }
}
