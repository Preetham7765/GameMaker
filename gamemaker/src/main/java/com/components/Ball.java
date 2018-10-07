package com.components;

import java.awt.Color;
import java.awt.Graphics;
import java.io.ObjectInputStream;
	
import com.infrastructure.AbstractComponent;
import com.infrastructure.ObjectProperties;

public class Ball extends AbstractComponent {

	public Ball(ObjectProperties objectProperties) {
		super(objectProperties);
	}

	public int getYRadiusCoordinates() {
		return getY() + (getHeight() / 2);
	}

	public int getXRadiusCoordinates() {
		return getX() + (getWidth() / 2);
	}

	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(this.getX(), this.getY(), this.getWidth() / 2, this.getWidth() / 2);
	}

	@Override
	public void load(ObjectInputStream ip) {
		// TODO Auto-generated method stub

	}

}
