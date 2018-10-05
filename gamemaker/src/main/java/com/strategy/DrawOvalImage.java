package com.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawOvalImage implements Drawable, Serializable{

	private static final long serialVersionUID = 3L;

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Image dimg = component.getImage().getScaledInstance(component.getWidth(), component.getHeight(),
		        Image.SCALE_SMOOTH);
		g2d.setClip(new Ellipse2D.Float(component.getX(), component.getY(), component.getWidth(), component.getWidth()));
		
		g2d.drawImage(dimg, component.getX(), component.getY(),component.getWidth(), component.getHeight(), null); 
		g2d.dispose();
	}

}
