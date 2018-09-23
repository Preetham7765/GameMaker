package com.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

public class Paddle extends AbstractComponent {
	
	public Paddle(ObjectProperties objectProperties) {
		super(objectProperties);
	}

	public void draw(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
	}
}
