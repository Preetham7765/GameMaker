package com.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.infrastructure.Constants;
import com.infrastructure.IComposite;

public class Brick implements IComposite, Serializable {
	
	private static final long serialVersionUID = 7L;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	
	public Brick(int x, int y, int velX, int velY, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
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


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
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
