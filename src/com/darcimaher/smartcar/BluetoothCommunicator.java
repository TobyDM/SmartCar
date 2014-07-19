//
// From https://github.com/rlunding/Lejos.git
//

package com.darcimaher.smartcar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class BluetoothCommunicator {

	private static DataInputStream dis = null;
	private static DataOutputStream dos = null;
	private static BTConnection btc;
	private static boolean isConnected;

	private BluetoothCommunicator() {
		isConnected = false;
	}

	public static boolean getIsConnected() {
		return isConnected;
	}

	public static void sendData(String output) {
		try {
			dos.writeUTF(output);
			dos.flush();
		} catch (IOException ioe) {
			isConnected = false;
			stopConnection();
//			connect();
			LCD.drawString("failed to send", 0, 3);
		}
	}

	public static void sendInt(int output) {
		try {
			dos.writeInt(output);
			dos.flush();
		} catch (IOException ioe) {
			isConnected = false;
			stopConnection();
//			connect();
			LCD.drawString("failed to send", 0, 3);
		}
	}

	public static String receiveData() {
		String s = " ";
		try {
			s = dis.readUTF();
			return s;
		} catch (IOException e) {
			LCD.clear();
			LCD.drawString("failed to receive", 0, 3);
			isConnected = false;
			stopConnection();
//			connect();
			return "0";
		}
	}

	public static int receiveInt() {
		int i = 0;
		try {
			i = dis.readInt();
			return i;
		} catch (IOException e) {
			LCD.clear();
			LCD.drawString("failed to receive", 0, 3);
			isConnected = false;
			stopConnection();
//			connect();
			return 0;
		}
	}

	public static void stopConnection() {
		try {
			dos.close();
			dis.close();
			btc.close();
		} catch (IOException e) {
			LCD.clear();
			LCD.drawString("failed to close", 0, 3);
		}
	}

	public static void connect(String name) {
		LCD.drawString("Connecting...", 0, 0);
		LCD.refresh();
		RemoteDevice btrd = Bluetooth.getKnownDevice(name);
		if (btrd == null) {
			LCD.clear();
			LCD.drawString("No such device", 0, 3);
			LCD.refresh();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		btc = Bluetooth.connect(btrd);
		if (btc == null) {
			LCD.clear();
			LCD.drawString("Connect fail", 0, 3);
			LCD.refresh();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			LCD.clear();
			LCD.drawString("Connected", 0, 3);
			LCD.refresh();
			isConnected = true;
			dis = btc.openDataInputStream();
			dos = btc.openDataOutputStream();
		}
	}

	public static void connect() {
		LCD.clear();
		LCD.drawString("Waiting for bluetooth...", 0, 3);
		btc = Bluetooth.waitForConnection();
		LCD.clear();
		LCD.drawString("Connected to bluetooth.", 0, 3);
		isConnected = true;
		dis = btc.openDataInputStream();
		dos = btc.openDataOutputStream();
	}
}
