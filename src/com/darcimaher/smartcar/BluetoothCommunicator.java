//
// From https://github.com/rlunding/Lejos.git
//

package com.darcimaher.smartcar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

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
			System.out.println("failed to send");
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
			System.out.println("failed to send");
		}
	}

	public static String receiveData() {
		String s = " ";
		try {
			s = dis.readUTF();
			return s;
		} catch (IOException e) {
			
			System.out.println("failed to receive");
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
			
			System.out.println("failed to receive");
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
			
			System.out.println("failed to close");
		}
	}

	public static void connect(String name) {
		System.out.println("Connecting to bluetooth...");
		RemoteDevice btrd = Bluetooth.getKnownDevice(name);
		if (btrd == null) {
			
			System.out.println("No such device");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		btc = Bluetooth.connect(btrd);
		if (btc == null) {
			
			System.out.println("Connect fail");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			
			System.out.println("Connected");
			isConnected = true;
			dis = btc.openDataInputStream();
			dos = btc.openDataOutputStream();
		}
	}

	public static void connect() {
		
		System.out.println("Waiting for bluetooth...");
		btc = Bluetooth.waitForConnection();
		
		System.out.println("Connected to bluetooth.");
		isConnected = true;
		dis = btc.openDataInputStream();
		dos = btc.openDataOutputStream();
	}
}
