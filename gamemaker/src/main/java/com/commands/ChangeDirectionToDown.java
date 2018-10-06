package com.commands;

import java.util.Random;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Direction;

public class ChangeDirectionToDown implements Command{

	
	private AbstractComponent abstractComponent;
	
	public ChangeDirectionToDown(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		int velX = abstractComponent.getVelX();
		int velY = abstractComponent.getVelY();
		
		if(velX != 0) {
			abstractComponent.setVelY(Math.abs(velX));
		}
		else {
			abstractComponent.setVelY(Math.abs(velY));
		}
		abstractComponent.setVelX(0);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	
	

}
