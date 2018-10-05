package com.strategy;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawRectImage implements Drawable, Serializable{
	
	private static final long serialVersionUID = 5L;

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Image dimg = component.getImage().getScaledInstance(component.getWidth(), component.getHeight(),
		        Image.SCALE_SMOOTH);
		g.drawImage(dimg, component.getX(), component.getY(), component.getWidth(), component.getHeight(), null);
	}

}
