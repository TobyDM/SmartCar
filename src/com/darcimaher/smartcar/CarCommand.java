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

	public CarCommand reversedCommand() {
		if (this == FORWARD) return REVERSE;
		if (this == REVERSE) return FORWARD;
		if (this == ROTATE_LEFT) return ROTATE_RIGHT;
		if (this == ROTATE_RIGHT) return ROTATE_LEFT;
		if (this == TURN_LEFT) return REV_TURN_LEFT;
		if (this == TURN_RIGHT) return REV_TURN_RIGHT;
		if (this == REV_TURN_LEFT) return TURN_LEFT;
		if (this == REV_TURN_RIGHT) return TURN_RIGHT;
		return this;
	}
}
