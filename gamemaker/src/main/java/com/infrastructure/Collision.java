package com.infrastructure;

public class Collision {
	private AbstractComponent primaryComponent;
	private AbstractComponent secondaryComponent;
	private String primaryCompAction;
	private String secondaryCompAction;
	
	public Collision(AbstractComponent primaryComponent,AbstractComponent secondaryComponent,String primaryCompAction,String secondaryCompAction) {
		this.primaryComponent = primaryComponent;
		this.secondaryComponent = secondaryComponent;
		this.primaryCompAction = primaryCompAction;
		this.secondaryCompAction = secondaryCompAction;
	}
	
	public boolean checkIntersectionBetweenComponents(AbstractComponent firstComponent, AbstractComponent secondComponent) {
			if(firstComponent.getBounds().intersects(secondComponent.getBounds())){
				return true;
			} else {
				return false;
			}
	}
	
	public void checkCollisionBetweenGameElementAndBounds(AbstractComponent component) {   //change return type

		//Coordinate delta = element.getCoordinate();
		
 		//get current position of ball
 		int left =  component.getX();
 		int right = component.getX() + component.getWidth();
 		int top = component.getY();
 		int bottom = component.getY() + component.getHeight();
 		
 		/*
 		if((left <=0) && (delta.getX() < 0))
 		{
 		    return Direction.X;
 		}
 		if((right >= Constants.GAME_PANEL_WIDTH) && (delta.getX() > 0))
 		{
 			return Direction.X;
 		}
 		if((top <=0) && (delta.getY() < 0))
 		{
 			return Direction.Y;
 		}
 		if((bottom >= Constants.GAME_PANEL_HEIGHT) && (delta.getY() > 0))
 		{
 			return Direction.Y;
 		}
 	*/
		//return Direction.NONE;
 	
	}
	
	public AbstractComponent getPrimaryComponent() {
		return primaryComponent;
	}
	public void setPrimaryComponent(AbstractComponent primaryComponent) {
		this.primaryComponent = primaryComponent;
	}
	public AbstractComponent getSecondaryComponent() {
		return secondaryComponent;
	}
	public void setSecondaryComponent(AbstractComponent secondaryComponent) {
		this.secondaryComponent = secondaryComponent;
	}
	public String getPrimaryCompAction() {
		return primaryCompAction;
	}
	public void setPrimaryCompAction(String primaryCompAction) {
		this.primaryCompAction = primaryCompAction;
	}
	public String getSecondaryCompAction() {
		return secondaryCompAction;
	}
	public void setSecondaryCompAction(String secondaryCompAction) {
		this.secondaryCompAction = secondaryCompAction;
	}
	
}
