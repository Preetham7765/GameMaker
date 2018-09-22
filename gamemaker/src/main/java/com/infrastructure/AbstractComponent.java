package com.infrastructure;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.view.ObjectProperties;

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
		return this.objectProperties.x;
	}


	public void setX(int x) {
		this.objectProperties.x = x;
	}


	public int getY() {
		return this.objectProperties.y;
	}


	public void setY(int y) {
		this.objectProperties.y = y;
	}


	public int getVelX() {
		return this.objectProperties.velX;
	}


	public void setVelX(int velX) {
		this.objectProperties.velX = velX;
	}


	public int getVelY() {
		return this.objectProperties.velY;
	}


	public void setVelY(int velY) {
		this.objectProperties.velY = velY;
	}


	public int getWidth() {
		return this.objectProperties.width;
	}


	public void setWidth(int width) {
		this.objectProperties.width = width;
	}


	public int getHeight() {
		return this.objectProperties.height;
	}


	public void setHeight(int height) {
		this.objectProperties.height = height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}	
	
//	public void draw(Graphics g) {}

}
