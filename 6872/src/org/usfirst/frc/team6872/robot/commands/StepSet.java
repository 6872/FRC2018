package org.usfirst.frc.team6872.robot.commands;
import java.util.Iterator;
import java.util.LinkedList;

public class StepSet {
	public LinkedList<Step> steps = new LinkedList<>();
	
	public StepSet() {}
	
	public StepSet(String str) {
		String[] s = str.split(",");
		for (int i = 0; i < s.length; i++) {
			steps.add(new Step(s[i]));
		}
	}
	
	public boolean add(Step step) {
		return steps.add(step);
	}
	
	public String toString() {
		LinkedList<String> s = new LinkedList<>();
		for (Iterator<Step> i = steps.iterator(); i.hasNext();) {
			s.add(i.next().toString());
		}
		return String.join(",", s);
	}
}