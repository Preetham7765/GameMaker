package com.behavior;

import com.infrastructure.ActionBehavior;
import com.infrastructure.ObjectProperties;

public class Move implements ActionBehavior {
	
	private ObjectProperties objectProperties;
	
	public Move(ObjectProperties objectProperties)
	{
		this.objectProperties = objectProperties;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		objectProperties.setX(objectProperties.getX() + objectProperties.getVelX());
		objectProperties.setY(objectProperties.getY() + objectProperties.getVelY());
		
	}

	
}
