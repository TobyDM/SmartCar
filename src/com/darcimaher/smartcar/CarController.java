package com.darcimaher.smartcar;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class CarController {
	
	private final Car realCar;
	private final RemoteControlListener rC;
	private boolean keepLooping;

	public CarController(Car realCar, RemoteControlListener rC) {
		super();
		this.realCar = realCar;
		this.rC = rC;

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			
			@Override
			public void buttonReleased(Button b) {
			}
			
			@Override
			public void buttonPressed(Button b) {
				keepLooping = false;			}
		});
}
	
	public void loop (){
		
		keepLooping = true;

		while (keepLooping) {
			CarCommand nextCommand = this.rC.giveMeTheNextCommand();
			sendCommandToCar(nextCommand);
		}
		
		
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
