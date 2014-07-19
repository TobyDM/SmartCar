package com.darcimaher.smartcar;

import lejos.nxt.Button;
import lejos.nxt.Motor;

public class StartHereMan {
	
	private static final String RELAY_NAME = "Nico R";

	public static void main(String[] args) {

		
		System.out.println("Press any key to start.");
		Button.waitForAnyPress();
		
		
//		doBluetoothThing();
		runCarController();
	}
	
	private static void runCarController() {
		RemoteControlListener listener = new RealRemoteControlListener(RELAY_NAME);
//		RemoteControlListener listener = new FakeRemoteControlListener();
		
		CarController cc = new CarController(new Car(Motor.A, Motor.B), listener);
		cc.loop();
		
	}
	
//	private static void doBluetoothThing() {
//		BluetoothCommunicator.connect(RELAY_NAME);
//		while(keepGoing) {
//			while(BluetoothCommunicator.getIsConnected()) {
//				// This will WAIT for there to be something to read.
//				int input = BluetoothCommunicator.receiveInt();
//				System.out.println("Received command:", 0, 4);
//				LCD.drawInt(input, 1, 5);
//			}
//		}
//	}
//
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
