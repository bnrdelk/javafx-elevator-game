package com.example.elevatorgame;

import java.lang.Math;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

public class ElevatorPerson {
	
	private int enterTime;
	private int initialPosition;
	private int target;
	public String name;
	
	public ElevatorPerson(String name, int ip, int t) {
		this.name = name;
		enterTime = Elevator.getTravelMeter();
		initialPosition = ip;
		target = t;
	}

	public int getTarget() {
		return target;
	}
	
	
	
	public String toString() {
		if( Math.abs(Elevator.getTravelMeter()-enterTime) <= (Math.abs(getTarget() - initialPosition) )) {
			return ("I am " + name + ". I traveled " + Math.abs(enterTime - Elevator.getTravelMeter()) + " floors. I am happy.");
		}
		else {
			return ("I am " + name + ". I traveled " + Math.abs(enterTime - Elevator.getTravelMeter()) + " floors. I am unhappy." );
		}
	}
}

