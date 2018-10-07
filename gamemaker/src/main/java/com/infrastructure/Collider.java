package com.infrastructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.commands.ChangeDirection;
import com.commands.ChangeVelXCommand;
import com.commands.ChangeVelYCommand;
import com.commands.Command;
import com.commands.ExplodeCommand;
import com.commands.MoveCommand;
import com.commands.NullCommand;
import com.commands.ReappearLeftCommand;
import com.commands.ReappearRightCommand;

//import org.apache.log4j.Logger;

public class Collider implements Serializable {
	// protected static final Logger logger = Logger.getLogger(Collider.class);
	private AbstractComponent primaryComponent;
	private AbstractComponent secondaryComponent;
	private CollisionType primaryCollisionType;
	private CollisionType secondaryCollisionType;
	private Collision collision;
	private ArrayList<Command> eventList;
	private Random random;
	

	public Collider(AbstractComponent primaryComponent, AbstractComponent secondaryComponent,
			CollisionType primaryCollisionType, CollisionType secondaryCollisionType, Collision collision) {
		this.primaryComponent = primaryComponent;
		this.secondaryComponent = secondaryComponent;
		this.primaryCollisionType = primaryCollisionType;
		this.secondaryCollisionType = secondaryCollisionType;
		this.collision = collision;
		random = new Random();	
		// this.eventList = eventList;
	}

	public void execute() {
		
		if(primaryComponent.getVisibility() && secondaryComponent.getVisibility() && collision.checkIntersectionBetweenElements(primaryComponent, secondaryComponent)) {
			System.out.println("primary X and primary Y: " + primaryComponent.getX() + " " +  primaryComponent.getY());
			System.out.println("secondary X and primary Y: " + secondaryComponent.getX() + " " + secondaryComponent.getY());
			Command command = getCollisionAction(primaryComponent, primaryCollisionType);
			if (primaryCollisionType == CollisionType.BOUNCE) {
				Direction direction = collision.checkCollisionBetweenAbstractComponents(primaryComponent,
						secondaryComponent);
				changeDirectionsOnCollision(primaryComponent, direction);
			}
			else if(primaryCollisionType == CollisionType.CHANGE_DIRECTION) {
				changeDirectionRandom(primaryComponent);
			}
			command.execute();
			command = getCollisionAction(secondaryComponent, secondaryCollisionType);
			if (secondaryCollisionType == CollisionType.BOUNCE) {
				Direction direction = collision.checkCollisionBetweenAbstractComponents(secondaryComponent,
						primaryComponent);
				changeDirectionsOnCollision(secondaryComponent, direction);
			}
			else if(secondaryCollisionType == CollisionType.CHANGE_DIRECTION) {

				changeDirectionRandom(secondaryComponent);
			}
			command.execute();
		}
	}

	public Command getCollisionAction(AbstractComponent component, CollisionType collisionType) {
		if (collisionType == CollisionType.BOUNCE) {
			return new MoveCommand(component);
		}
		if (collisionType == CollisionType.EXPLODE) {
			System.out.println("explod return");
			return new ExplodeCommand(component);
		}
		if(collisionType == CollisionType.REAPPEAR_LEFT) {
			System.out.println("Reappear left");
			return new ReappearLeftCommand(component);
		}
		if(collisionType == CollisionType.REAPPEAR_RIGHT) {
			System.out.println("Reappear Right");
			return new ReappearRightCommand(component);
		}	
		return new NullCommand(component);
	}

	public void changeDirectionsOnCollision(AbstractComponent component, Direction direction) {
		Command changeVelXCommand = null;
		Command changeVelYCommand = null;
		if (direction == Direction.X) {
			changeVelXCommand = new ChangeVelXCommand(component);
		}
		if (direction == Direction.Y) {
			changeVelYCommand = new ChangeVelYCommand(component);
		}
		if (direction == Direction.BOTH) {
			changeVelXCommand = new ChangeVelXCommand(component);
			changeVelYCommand = new ChangeVelYCommand(component);
		}
		if (changeVelXCommand != null) {
			changeVelXCommand.execute();
		}
		if (changeVelYCommand != null) {
			changeVelYCommand.execute();
		}
	}
	
	private void changeDirectionRandom(AbstractComponent component) {
		new ChangeDirection(component).execute();
		
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (primaryComponent.getComponentName() + " " + secondaryComponent.getComponentName() + " "
				+ primaryCollisionType + " " + secondaryCollisionType);
	}

}