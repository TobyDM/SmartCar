package com.darcimaher.smartcar;


public class RecordedCommand {
	private final CarCommand command;
	private final long startTimeMilliseconds;
	private long endTimeMilliseconds;
	
	public RecordedCommand(CarCommand command, long startTimeMilliseconds) {
		super();
		this.command = command;
		this.startTimeMilliseconds = startTimeMilliseconds;
	}

	public long getEndTimeMilliseconds() {
		return endTimeMilliseconds;
	}

	public void setEndTimeMilliseconds(long endTimeMilliseconds) {
		this.endTimeMilliseconds = endTimeMilliseconds;
	}

	public CarCommand getCommand() {
		return command;
	}

	public long getStartTimeMilliseconds() {
		return startTimeMilliseconds;
	}

	public long durationMilliseconds() {
		long difference = this.endTimeMilliseconds - this.startTimeMilliseconds;
		if (difference < 0) {
			difference = 0;
		}
		
		if (this.command == CarCommand.STOP && difference > 1000) {
			difference = 1000;
		}
		
		return difference;
	}
	
}
