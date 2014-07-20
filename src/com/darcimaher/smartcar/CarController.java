package com.darcimaher.smartcar;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;

public class CarController {

	private static final String RELAY_NAME = "Nico R";

	private final Car realCar;
	private final RemoteControlListener rC;
	private boolean keepLooping;
	private final ArrayList<RecordedCommand> recordedCommands;

	// THIS IS THE ENTRY POINT FOR OUR PROGRAM...
	public static void main(String[] args) {

		System.out.println("Press any key to start.");
		Button.waitForAnyPress();

		RemoteControlListener listener = new RealRemoteControlListener(
				RELAY_NAME);

		CarController myCarControllerVariable = new CarController(new Car(
				Motor.A, Motor.B), listener);
		myCarControllerVariable.loop();

	}

	// ============================================================

	public CarController(Car realCar, final RemoteControlListener rControl) {
		super();
		this.realCar = realCar;
		this.rC = rControl;
		this.recordedCommands = new ArrayList<RecordedCommand>();

		Button.ESCAPE.addButtonListener(new ButtonListener() {

			@Override
			public void buttonReleased(Button b) {
			}

			@Override
			public void buttonPressed(Button b) {
				keepLooping = false;
				rC.shutDown();
			}
		});
	}

	public void loop() {

		keepLooping = true;

		while (keepLooping) {
			CarCommand nextCommand = this.rC.giveMeTheNextCommand();

			if (nextCommand == CarCommand.REPLAY) {
				// if this is REPLAY, then play the list
				this.playCommands(true);

			} else if (nextCommand == CarCommand.CLEAR) {
				// if this is CLEAR, then clear the list
				this.recordedCommands.clear();

			} else {
				// otherwise, record this and send it along to the car
				recordCommand(nextCommand);
				sendCommandToCar(nextCommand);
			}
		}

	}

	private void recordCommand(CarCommand nextCommand) {

		// stick the current time into a variable
		long currentTimeMilliseconds = System.currentTimeMillis();

		// set the time for the prior command
		setEndTimeForLastRecordedCommand(currentTimeMilliseconds);

		// stick it in a RecordedCommand object
		RecordedCommand c = new RecordedCommand(nextCommand,
				currentTimeMilliseconds);

		// put the object in the list
		this.recordedCommands.add(c);

	}

	private void setEndTimeForLastRecordedCommand(long currentTimeMilliseconds) {
		// set the end time for the prior item in the list.
		if (this.recordedCommands.size() > 0) {
			RecordedCommand lastCommand = this.recordedCommands
					.get(this.recordedCommands.size() - 1);
			if (lastCommand.getEndTimeMilliseconds() == 0) {
				lastCommand.setEndTimeMilliseconds(currentTimeMilliseconds);
			}
		}
	}

	private void playCommands(boolean reverse) {

		// set the end time for the last recorded command if it's not there
		setEndTimeForLastRecordedCommand(System.currentTimeMillis());

		// put our list into a variable
		List<RecordedCommand> myList;

		if (reverse) {
			myList = this.reverseCommands(this.recordedCommands);
		} else {
			myList = this.recordedCommands;
		}

		System.out.println("**REPLAYING " + myList.size() + " COMMANDS**");

		// play all the commands
		for (RecordedCommand currentRecordedCommand : myList) {

//			 System.out.println("*" + currentRecordedCommand.getCommand().name() + " " + currentRecordedCommand.getStartTimeMilliseconds() + "-" + currentRecordedCommand.getEndTimeMilliseconds());

			// send command to car
			sendCommandToCar(currentRecordedCommand.getCommand());

			// wait the difference in time between start and end
			try {
				Thread.sleep(currentRecordedCommand.durationMilliseconds());
			} catch (InterruptedException e) {
			}
		}
		
		// send a STOP!
		sendCommandToCar(CarCommand.STOP);

	}

	private List<RecordedCommand> reverseCommands(List<RecordedCommand> listOfCommands) {

		// make a new list variable
		ArrayList<RecordedCommand> reversedList = new ArrayList<RecordedCommand>();

		// Loop through the list we got, 
		// and add a reverso of each item to the START of our new list
		
		for (RecordedCommand fred : listOfCommands){
			CarCommand reversedCommand = fred.getCommand().reversedCommand();
			RecordedCommand reverso = new RecordedCommand(reversedCommand, fred.getStartTimeMilliseconds());
			reverso.setEndTimeMilliseconds(fred.getEndTimeMilliseconds());
			reversedList.add(0, reverso);
		}

		// give the list back to whomever called us
		return reversedList;

	}

	private void sendCommandToCar(CarCommand cmd) {

		System.out.println(cmd.name());

		switch (cmd) {
		case STOP:
			this.realCar.stop();
			break;

		case FORWARD:
			this.realCar.goFoward();
			break;

		case REVERSE:
			this.realCar.goBackward();
			break;

		case ROTATE_LEFT:
			this.realCar.rotate(false);
			break;

		case ROTATE_RIGHT:
			this.realCar.rotate(true);
			break;

		case TURN_LEFT:
			this.realCar.curve(false);
			break;

		case TURN_RIGHT:
			this.realCar.curve(true);
			break;

		case REV_TURN_LEFT:
			this.realCar.curveBackward(false);
			break;

		case REV_TURN_RIGHT:
			this.realCar.curveBackward(true);
			break;

		default:
			break;
		}
	}

}
