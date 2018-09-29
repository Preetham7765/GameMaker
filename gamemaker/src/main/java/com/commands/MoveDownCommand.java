package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveDownCommand implements Command{

	private AbstractComponent abstractComponent;
	
	public MoveDownCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setY(abstractComponent.getY() + abstractComponent.getVelY());
	}

	@Override
	public void undo() {
		abstractComponent.setY(abstractComponent.getY() - abstractComponent.getVelY());		
	}

}
