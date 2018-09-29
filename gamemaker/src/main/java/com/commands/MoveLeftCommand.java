package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveLeftCommand implements Command{
	
	private AbstractComponent abstractComponent;
	
	public MoveLeftCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setX(abstractComponent.getX() - abstractComponent.getVelX());
	}

	@Override
	public void undo() {
		abstractComponent.setX(abstractComponent.getX() + abstractComponent.getVelX());		
	}

}
