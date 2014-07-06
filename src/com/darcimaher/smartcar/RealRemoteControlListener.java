package com.darcimaher.smartcar;

public class RealRemoteControlListener implements RemoteControlListener {

	@Override
	public CarCommand giveMeTheNextCommand() {
		// Get the next command from the stream
		int commandReceivedFromRemote = BluetoothCommunicator.receiveInt();
		
		// Turn it into a CarCommand
		return CarCommand.commandFromNumber(commandReceivedFromRemote);
	}

	public void connect(String relayName) {
		BluetoothCommunicator.connect(relayName);
	}

	public RealRemoteControlListener(String relayName) {
		super();
		this.connect(relayName);

	}

}
