package com.infrastructure;

public class ObjectProperties {
	
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	private String type;
	private boolean canCollect;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public boolean getCanCollect() {
		return canCollect;
	}

	public void setCanCollect(boolean canCollect) {
		this.canCollect = canCollect;
	}
	
	public ObjectProperties() {
		this.x = 5;
		this.y = 5;
		this.velX = 1;
		this.velY = 1;
		this.width = 10;
		this.height = 10;
		this.type = "";
		this.canCollect = false;
	}
	
	public ObjectProperties(int x, int y, int velX, int velY, int width, int height, String type, boolean canCollect) {
		super();
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
		this.type = type;
		this.canCollect = canCollect;
	}
}
