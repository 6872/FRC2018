package org.usfirst.frc.team6872.robot.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.usfirst.frc.team6872.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Autonomous extends Command {
	
	public int pos;
	public int target;
	
	protected Queue<TimedStep> steps = new LinkedList<>();
	protected TimedStep currentStep;
	protected double nextStep = 0;
	protected boolean finished = false;

    public Autonomous(int position, int target) {
    	pos = position;
    	this.target = target;
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double r =  0.6;
    	double rd = 1;
    	double s = 0.7;
    	if (target == -2) {
    		load();
    		return;
    	}
    	if (target == -1) {
    		if (Robot.gameData.length() == 0) {
    			target = 0;
    			return;
    		}
    		switch (Robot.gameData.charAt(0)) {
				case 'L':
					target = 1;
					break;
				case 'R':
					target = 0;
					break;
    		}
    	}
    	steps.add(new TimedStep(s, 1));
    	if (target == 0 && pos != 0) {
    		steps.add(new TimedStep(-r, r, rd));
    		steps.add(new TimedStep(s, pos * 2));
    		steps.add(new TimedStep(r, -r, rd));
    	}
    	if (target == 1 && pos != 2) {
    		steps.add(new TimedStep(r, -r, rd));
    		steps.add(new TimedStep(s, (2 - pos) * 2));
    		steps.add(new TimedStep(-r, r, rd));
    	}
    	steps.add(new TimedStep(s, 3));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timeSinceInitialized() < nextStep) {
    		Robot.driveTrain.drive(currentStep.left, currentStep.right);
    	}
    	else {
    		currentStep = steps.poll();
    		if (currentStep == null) {
    			finished = true;
    		}
    		else {
        		Robot.driveTrain.drive(currentStep.left, currentStep.right);
    			nextStep += currentStep.duration;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.drive(0, 0);
    }
    
    public void load() {
    	String str = "";
		try {
			str = new String(Files.readAllBytes(Paths.get("/home/lvuser/FRC2018Auto.txt")));
		} catch (IOException e) {
			System.out.println("IO Error");
			return;
		}
    	StepSet s = new StepSet(str);

		for (Iterator<Step> i = s.steps.iterator(); i.hasNext();) {
			Step step = i.next();
			steps.add(new TimedStep(step.l, step.r, 0));
		}
    }
    
    public class TimedStep {
    	public double left;
    	public double right;
    	public double duration;
    	
    	public TimedStep(double left, double right, double duration) {
    		this.left = left;
    		this.right = right;
    		this.duration = duration;
    	}
    	
    	public TimedStep(double speed, double duration) {
    		this.left = speed;
    		this.right = speed;
    		this.duration = duration;
    	}
    }
}
