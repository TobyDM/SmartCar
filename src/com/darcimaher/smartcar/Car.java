package com.darcimaher.smartcar;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class Car {
	
	private static final double WHEEL_DIAMETER_CM = 4.0;
	private static final double TRACK_WIDTH_CM = 11.0;
	private static final double CURVE_RADIUS_CM = 14.0;
	private final DifferentialPilot coolPilot;
	
	
	public Car(RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		coolPilot = new DifferentialPilot(WHEEL_DIAMETER_CM, TRACK_WIDTH_CM, leftMotor, rightMotor, true);
	}
	
	public void goFoward (){
		coolPilot.forward();
		
	}
	
	public void stop (){
		coolPilot.stop();		
	}

	public void goBackward (){
		coolPilot.backward();
	}

	public void curve (boolean turnToTheRight){
		if (turnToTheRight) {
			coolPilot.arcForward(-1 * CURVE_RADIUS_CM);
		} else {
			coolPilot.arcForward(CURVE_RADIUS_CM);
		}
	}

	public void rotate (boolean turnToTheRight){
		if (turnToTheRight) {
			coolPilot.rotateRight();
		} else {
			coolPilot.rotateLeft();
		}
	}
	public void curveBackward (boolean turnToTheRight){
		if (turnToTheRight) {
			coolPilot.arcBackward(-1 * CURVE_RADIUS_CM);
		} else {
			coolPilot.arcBackward(CURVE_RADIUS_CM);
		}
	}

}
