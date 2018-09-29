package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveUpCommand implements Command{
	
	private AbstractComponent abstractComponent;
	
	public MoveUpCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		abstractComponent.setY(abstractComponent.getY() - abstractComponent.getVelY());
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		abstractComponent.setY(abstractComponent.getY() + abstractComponent.getVelY());
	}
	
}
