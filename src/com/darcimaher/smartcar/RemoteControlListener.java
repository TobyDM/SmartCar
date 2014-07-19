package com.darcimaher.smartcar;

public interface RemoteControlListener {
	CarCommand giveMeTheNextCommand();
	void shutDown();
} 
