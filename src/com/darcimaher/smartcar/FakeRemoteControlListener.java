package com.darcimaher.smartcar;

import java.util.Random;

public class FakeRemoteControlListener implements RemoteControlListener {
	
	private static final int MAX_SECONDS = 4;
	private static final int MIN_SECONDS = 1;

	private CarCommand lastCommandSent = CarCommand.STOP;
	
	public CarCommand giveMeTheNextCommand() {
		Random randomNumberGenerator = new Random();
		int secondsToWait = randomNumberGenerator.nextInt(MAX_SECONDS - MIN_SECONDS + 1) + MIN_SECONDS;
		
		CarCommand commandToSend;
		
		do {
			commandToSend = randomCarCommand();	
		} while (commandToSend == lastCommandSent);
		lastCommandSent = commandToSend;

		try {
			Thread.sleep(secondsToWait * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return commandToSend;
	}
	
	private CarCommand randomCarCommand() {
		Random randomNumberGenerator = new Random();
		int numberOfCarCommandValues = CarCommand.values().length;
		int randomCarCommandNumber = randomNumberGenerator.nextInt(numberOfCarCommandValues);
		CarCommand commandToSend = CarCommand.commandFromNumber(randomCarCommandNumber);
		return commandToSend;
	}

}
