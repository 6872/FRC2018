package org.usfirst.frc.team6872.robot.commands;
public class Step {
	public double l;
	public double r;
	
	public Step(double left, double right) {
		l = left;
		r = right;
	}
	
	public Step(String str) {
		String[] s = str.split("\\s+");
		l = Double.parseDouble(s[0]);
		r = Double.parseDouble(s[1]);
	}
	
	public String toString() {
		return String.format("%.3f %.3f", l, r);
	}
}