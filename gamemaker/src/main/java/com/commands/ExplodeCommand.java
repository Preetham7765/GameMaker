package com.commands;

import com.infrastructure.AbstractComponent;

public class ExplodeCommand implements Command{

	private AbstractComponent component;
	
	public ExplodeCommand(AbstractComponent component) {
		this.component = component;
	}
	
	@Override
	public void execute() {
		component.setVisbility(false);
	}

	@Override
	public void undo() {
		component.setVisbility(true);
	}

}
