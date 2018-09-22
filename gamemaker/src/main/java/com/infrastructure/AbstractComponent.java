package com.infrastructure;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class AbstractComponent implements IComposite, Serializable {
	
	IActionBehavior ib;
	ObjectProperties objectProperties;
	
	public void setActionBehavior(IActionBehavior ib) {
		this.ib = ib;
	}
	
	public void performAction() {
		ib.action();
	}
	
	
	public AbstractComponent(ObjectProperties objectProperties)
	{
		this.objectProperties = objectProperties;
	}

	
	public int getX() {
		return this.objectProperties.getX();
	}


	public void setX(int x) {
		this.objectProperties.setX(x);
	}


	public int getY() {
		return this.objectProperties.getY();
	}


	public void setY(int y) {
		this.objectProperties.setY(y);
	}


	public int getVelX() {
		return this.objectProperties.getVelX();
	}

	public void setVelX(int velX) {
		this.objectProperties.setVelX(velX);
	}


	public int getVelY() {
		return this.objectProperties.getVelY();
	}


	public void setVelY(int velY) {
		this.objectProperties.setVelY(velY);
	}

	public int getWidth() {
		return this.objectProperties.getWidth();
	}


	public void setWidth(int width) {
		this.objectProperties.setWidth(width);
	}


	public int getHeight() {
		return this.objectProperties.getHeight();
	}


	public void setHeight(int height) {
		this.objectProperties.setHeight(height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}	
	
	public int getBottomCoordinates() {
		return getY() + getHeight();
	}

	public int getRightCoordinates() {
		return getX() + getWidth();
	}
	
//	public void draw(Graphics g) {}

	// public void serializable
}
