package com.strategy;

import java.awt.Graphics;
import java.awt.Image;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawRectImage implements Drawable{

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Image dimg = component.getImage().getScaledInstance(component.getWidth(), component.getHeight(),
		        Image.SCALE_SMOOTH);
		g.drawImage(dimg, component.getX(), component.getY(), component.getWidth(), component.getHeight(), null);
	}

}
