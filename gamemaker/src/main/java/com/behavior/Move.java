package com.behavior;

import com.infrastructure.ActionBehavior;
import com.infrastructure.Constants;
import com.infrastructure.ObjectProperties;

public class Move implements ActionBehavior {
	
	private ObjectProperties objectProperties;
	
	public Move(ObjectProperties objectProperties)
	{
		this.objectProperties = objectProperties;
	}

	@Override
	public void performAction() {
		
		int newX = objectProperties.getX() + objectProperties.getVelX();
		int newY = objectProperties.getY() + objectProperties.getVelY();
		
		if (newX < 0) objectProperties.setX(0);
		if (newY < 0) objectProperties.setY(0);
		
		if (newX > Constants.GAME_PANEL_WIDTH) objectProperties.setX(Constants.GAME_PANEL_WIDTH);
		if (newY > Constants.GAME_PANEL_HEIGHT) objectProperties.setY(Constants.GAME_PANEL_HEIGHT);
						
	}
	
}
