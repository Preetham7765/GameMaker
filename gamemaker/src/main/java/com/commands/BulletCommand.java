package com.commands;

import com.infrastructure.AbstractComponent;

public class BulletCommand implements Command{

	private static final long serialVersionUID = 788831467916562104L;
	private AbstractComponent abstractComponent;
	private AbstractComponent bullet;
	
	public BulletCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
		bullet = new AbstractComponent();
	}
	
	@Override
	public void execute() {
		//bullet.setX(x);
		
		abstractComponent.setY(abstractComponent.getY() + abstractComponent.getVelY());
	}

	@Override
	public void undo() {
		abstractComponent.setY(abstractComponent.getY() - abstractComponent.getVelY());		
	}

}
