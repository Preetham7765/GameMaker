package com.behavior;

import com.infrastructure.AbstractComponent;
import com.infrastructure.ActionBehavior;

public class Visibility implements ActionBehavior {
	
	private AbstractComponent abstractComponent;
	
	public Visibility(AbstractComponent abstractComponent)
	{
		this.abstractComponent = abstractComponent;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
		abstractComponent.setVisbility(false);
	}
	
	

}
