package com.darcimaher.smartcar;

public enum CarCommand {
	STOP, FORWARD, REVERSE, ROTATE_LEFT, ROTATE_RIGHT, TURN_LEFT, TURN_RIGHT, REV_TURN_LEFT, REV_TURN_RIGHT, REPLAY, CLEAR;
	
	public static CarCommand commandFromNumber(int numberValue) {
		CarCommand cmd = CarCommand.STOP;
		CarCommand[] values = CarCommand.values();
		if (numberValue >= 0 && numberValue < values.length) {
			cmd = CarCommand.values()[numberValue];
		}
		return cmd;
	}
}
