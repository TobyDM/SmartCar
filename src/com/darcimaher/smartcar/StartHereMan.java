package com.darcimaher.smartcar;

import lejos.nxt.Motor;

public class StartHereMan {

	public static void main(String[] args) {

	CarController cc = new CarController(new Car(Motor.A, Motor.B), new FakeRemoteControlListener());
	cc.loop();
	
	}

	private static void sleepAWhile(int numSeconds) {
		try {
			Thread.sleep(numSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void doStupidStuff() {
		Car myCar = new Car(Motor.A,Motor.B);
		
		sleepAWhile(5);

		myCar.goFoward();
		sleepAWhile(2);
		
		myCar.stop();
		sleepAWhile(1);
		
		myCar.curve(false);
		sleepAWhile(8);
		
		myCar.curve(true);
		sleepAWhile(3);
		
		myCar.rotate(false);
		sleepAWhile(3);
		
		myCar.rotate(true);
		sleepAWhile(2);
		
		myCar.stop();
	}
	
}
