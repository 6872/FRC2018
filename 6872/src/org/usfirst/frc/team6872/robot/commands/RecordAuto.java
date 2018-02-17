package org.usfirst.frc.team6872.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.usfirst.frc.team6872.robot.Robot;

public class RecordAuto extends Command {
	
	public StepSet steps = new StepSet();

    public RecordAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double scale = -Robot.oi.joy1.getRawAxis(3) / 2 + 0.5;
    	steps.add(new Step(-Robot.oi.joy0.getRawAxis(1) * scale, -Robot.oi.joy0.getRawAxis(5) * scale));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	String s = steps.toString();
    	File f = new File("/home/lvuser/FRC2018Auto.txt");
    	try {
			f.createNewFile();
		} catch (IOException e1) {
			System.out.println("IO Error");
		}
    	try(PrintWriter writer = new PrintWriter(f);) {
    		writer.println(s);
    	} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
    }
}
