package com.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

public class Brick extends AbstractComponent {
	
	public Brick(ObjectProperties objectProperties) {
		super(objectProperties);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
