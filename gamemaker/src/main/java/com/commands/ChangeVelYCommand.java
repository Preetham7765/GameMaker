package com.commands;

import com.infrastructure.AbstractComponent;

public class ChangeVelYCommand implements Command{

	private AbstractComponent abstractComponent;
	
	public ChangeVelYCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setVelY(-abstractComponent.getVelY());
	}

	@Override
	public void undo() {
		abstractComponent.setVelY(-abstractComponent.getVelY());		
	}

}
