package com.view;

import com.infrastructure.CollisionType;

public class ColliderData {
	private String primaryElement;
	private String secondaryElement;
	private CollisionType primaryAct;
	private CollisionType secondaryAct;
	
	public String getPrimaryElement() {
		return primaryElement;
	}
	public void setPrimaryElement(String primaryElement) {
		this.primaryElement = primaryElement;
	}
	public String getSecondaryElement() {
		return secondaryElement;
	}
	public void setSecondaryElement(String secondaryElement) {
		this.secondaryElement = secondaryElement;
	}
	public CollisionType getPrimaryAct() {
		return primaryAct;
	}
	public void setPrimaryAct(CollisionType primaryAct) {
		this.primaryAct = primaryAct;
	}
	public CollisionType getSecondaryAct() {
		return secondaryAct;
	}
	public void setSecondaryAct(CollisionType secondaryAct) {
		this.secondaryAct = secondaryAct;
	}
	
}
