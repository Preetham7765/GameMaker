package com.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.infrastructure.IComposite;

public class Ball implements IComposite, Serializable {
	
	private static final long serialVersionUID = 6L;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int radius;
	
	public Ball(int x, int y, int velX, int velY, int radius) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.radius = radius;
	}
	
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return radius;
	}

	public int getHeight() {
		return radius;
	}

	
	public int getBottomCoordinates() {
		return getY() + getHeight();
	}

	public int getRightCoordinates() {
		return getX() + getWidth();
	}

	public int getYRadiusCoordinates() {
		return getY() + (getHeight() / 2);
	}

	public int getXRadiusCoordinates() {
		return getX() + (getWidth() / 2);
	}


	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.fillOval(this.getX(), this.getY(), radius, radius);
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public void addComponent(IComposite composite) {
		// TODO Auto-generated method stub
		
	}

	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
