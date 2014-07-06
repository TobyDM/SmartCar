package com.darcimaher.smartcar;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class StartHereMan {
	
	private static Boolean keepGoing = true;

	public static void main(String[] args) {
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			@Override
			public void buttonReleased(Button b) {
			}
			@Override
			public void buttonPressed(Button b) {
				keepGoing = false;
			}
		});
		doBluetoothThing();
//		runCarController();
	}
	
	private static void runCarController() {
		CarController cc = new CarController(new Car(Motor.A, Motor.B), new FakeRemoteControlListener());
		cc.loop();
		
	}
	
	private static final String RELAY_NAME = "Nico R";
	private static void doBluetoothThing() {
		BTCom.connect(RELAY_NAME);
		LCD.drawString("wookie", 0, 1);
		while(keepGoing) {
			LCD.drawString("blah", 0, 2);
			while(BTCom.getIsConnected()) {
				LCD.drawString("I'm Working", 0, 3);
				BTCom.sendData("hello world");
				int input = BTCom.receiveInt();
				LCD.drawString("From relay", 0, 4);
				LCD.drawInt(input, 0, 5);
			}
		}
	}

//	private static void sleepAWhile(int numSeconds) {
//		try {
//			Thread.sleep(numSeconds * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	private static void doStupidStuff() {
//		Car myCar = new Car(Motor.A,Motor.B);
//		
//		sleepAWhile(5);
//
//		myCar.goFoward();
//		sleepAWhile(2);
//		
//		myCar.stop();
//		sleepAWhile(1);
//		
//		myCar.curve(false);
//		sleepAWhile(8);
//		
//		myCar.curve(true);
//		sleepAWhile(3);
//		
//		myCar.rotate(false);
//		sleepAWhile(3);
//		
//		myCar.rotate(true);
//		sleepAWhile(2);
//		
//		myCar.stop();
//	}
	
}
