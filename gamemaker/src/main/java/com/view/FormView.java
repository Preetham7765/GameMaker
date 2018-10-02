package com.view;

import java.util.ArrayList;
import java.util.Map;

import com.infrastructure.ElementType;

public class FormView {
	private ElementType elementType;
	private String elementName;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	private boolean collectible;
	private int actionType;
	private Map<Integer, String> keyActionMap;
	private ArrayList<String> timeActionArray;

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
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

	public boolean isCollectible() {
		return collectible;
	}

	public void setCollectible(boolean collectible) {
		this.collectible = collectible;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Map<Integer, String> getKeyActionMap() {
		return keyActionMap;
	}

	public void setKeyActionMap(Map<Integer, String> keyActionMap) {
		this.keyActionMap = keyActionMap;
	}

	public ArrayList<String> getTimeActionArray() {
		return timeActionArray;
	}

	public void setTimeActionArray(ArrayList<String> timeActionArray) {
		this.timeActionArray = timeActionArray;
	}

}
