package com.behavior;

import com.infrastructure.AbstractComponent;
import com.infrastructure.ActionBehavior;

public class Move implements ActionBehavior {
	
	private AbstractComponent abstractComponent;
	
	public Move(AbstractComponent absComponent)
	{
		this.abstractComponent = absComponent;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		abstractComponent.setX(abstractComponent.getX() + abstractComponent.getVelX());
		abstractComponent.setY(abstractComponent.getY() + abstractComponent.getVelY());
		
	}

	
}
