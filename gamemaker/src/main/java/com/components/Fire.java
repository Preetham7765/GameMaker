package com.components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.constants.Constants;

public class Fire implements IComponent, Serializable {

	private static final long serialVersionUID = 12L;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	private BufferedImage image; 
	
	
	public Fire(int x, int y, int velX, int velY, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
		
		try {
			this.image = ImageIO.read(new File(Constants.fireImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
			this.image = image;
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

	public void drawGraphic(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

}
