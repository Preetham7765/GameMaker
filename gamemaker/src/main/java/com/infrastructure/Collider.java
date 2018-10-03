package com.infrastructure;

import java.io.Serializable;
import java.util.ArrayList;

import com.commands.ChangeVelXCommand;
import com.commands.ChangeVelYCommand;
import com.commands.Command;
import com.commands.ExplodeCommand;
import com.commands.MoveCommand;
import com.commands.NullCommand;
import com.controller.GamePlayController;

//import org.apache.log4j.Logger;



public class Collider implements Serializable {
	//protected static final Logger logger = Logger.getLogger(Collider.class);
	private AbstractComponent primaryComponent;
	private AbstractComponent secondaryComponent;
	private CollisionType primaryCollisionType;
	private CollisionType secondaryCollisionType;
	private Collision collision;
	private ArrayList<Command> eventList;
	
	public Collider(AbstractComponent primaryComponent, AbstractComponent secondaryComponent,CollisionType primaryCollisionType,CollisionType secondaryCollisionType,Collision collision) {
		this.primaryComponent = primaryComponent;
		this.secondaryComponent = secondaryComponent;
		this.primaryCollisionType = primaryCollisionType;
		this.secondaryCollisionType = secondaryCollisionType;
		this.collision = collision;
		//this.eventList = eventList;
	}
	
	public void execute(GamePlayController controller) {
		
		if(primaryComponent.getVisibility() && secondaryComponent.getVisibility() && collision.checkIntersectionBetweenElements(primaryComponent, secondaryComponent)) {
		
			if(eventList != null) {
				for(Command eventCommand : eventList) {
					eventCommand.execute();
					controller.addCommand(eventCommand);
				}
			}
			Command command = getCollisionAction(primaryComponent, primaryCollisionType);
			if (primaryCollisionType == CollisionType.BOUNCE) {
				Direction direction = collision.checkIntersectionBetweenComponents(primaryComponent, secondaryComponent);
				changeDirectionsOnCollision(primaryComponent, direction,controller);
			}
			command.execute();
			controller.addCommand(command);
			command = getCollisionAction(secondaryComponent, secondaryCollisionType);
			if (secondaryCollisionType == CollisionType.BOUNCE) {
				Direction direction = collision.checkIntersectionBetweenComponents(secondaryComponent, primaryComponent);
				changeDirectionsOnCollision(secondaryComponent, direction,controller);
			}
			command.execute();
			controller.addCommand(command);
		}
	}
	
	public Command getCollisionAction(AbstractComponent component, CollisionType collisionType) {
		if(collisionType == CollisionType.BOUNCE) {
			return new MoveCommand(component);
		}
		if(collisionType == CollisionType.EXPLODE) {
			return new ExplodeCommand(component);
		}
		return new NullCommand(component);
	}
	
	public void changeDirectionsOnCollision(AbstractComponent component, Direction direction,GamePlayController controller) {
		Command changeVelXCommand = null;
		Command changeVelYCommand = null;
		if(direction == Direction.X) {
			 changeVelXCommand = new ChangeVelXCommand(component);
		}
		if(direction == Direction.Y) {
			changeVelYCommand = new ChangeVelYCommand(component);
		}
		if(direction == Direction.BOTH) {
			changeVelXCommand = new ChangeVelXCommand(component);
			changeVelYCommand = new ChangeVelYCommand(component);
		}
		if(changeVelXCommand != null) {
			changeVelXCommand.execute();
			controller.addCommand(changeVelXCommand);
		}
		if(changeVelYCommand != null) {
			changeVelYCommand.execute();
			controller.addCommand(changeVelYCommand);
		}
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

	public CollisionType getPrimaryCollisionType() {
		return primaryCollisionType;
	}

	public void setPrimaryCollisionType(CollisionType primaryCollisionType) {
		this.primaryCollisionType = primaryCollisionType;
	}

	public CollisionType getSecondaryCollisionType() {
		return secondaryCollisionType;
	}

	public void setSecondaryCollisionType(CollisionType secondaryCollisionType) {
		this.secondaryCollisionType = secondaryCollisionType;
	}
	
}