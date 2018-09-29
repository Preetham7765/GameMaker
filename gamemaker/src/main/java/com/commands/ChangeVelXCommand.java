package com.commands;

import com.infrastructure.AbstractComponent;

public class ChangeVelXCommand implements Command{

	private AbstractComponent abstractComponent;
	
	public ChangeVelXCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setVelX(-abstractComponent.getVelX());
	}

	@Override
	public void undo() {
		abstractComponent.setVelX(-abstractComponent.getVelX());		
	}

}
