package com.infrastructure;

import java.io.Serializable;
import java.util.ArrayList;

//import org.apache.log4j.Logger;



public class Collider implements Serializable {
	//protected static final Logger logger = Logger.getLogger(Collider.class);
	private AbstractComponent primaryComponent;
	private AbstractComponent secondaryComponent;
	private CollisionType primaryCollisionType;
	private CollisionType secondaryCollisionType;
	private Collision collision;
	//private ArrayList<Command> eventList;
	
	public Collider(AbstractComponent primaryComponent, AbstractComponent secondaryComponent,CollisionType primaryCollisionType,CollisionType secondaryCollisionType,Collision collision) {
		this.primaryComponent = primaryComponent;
		this.secondaryComponent = secondaryComponent;
		//this.collisionType1 = collisionType1;
		//this.collisionType2 = collisionType2;
		this.collision = collision;
		//this.eventList = eventList;
	}
	
	public void execute(MainController controller) {
		
		if(element1.isVisible() && element2.isVisible() && collisionChecker.checkIntersectionBetweenElements(element1, element2)) {
		
			if(eventList != null) {
				for(Command eventCommand : eventList) {
					eventCommand.execute();
					controller.addCommand(eventCommand);
				}
			}
			Command command = getCollisionAction(element1, collisionType1);
			if (collisionType1 == CollisionType.BOUNCE) {
				Direction direction = collisionChecker.checkCollisionBetweenGameElements(element1, element2);
				changeDirectionsOnCollision(element1, direction,controller);
			}
			command.execute();
			controller.addCommand(command);
			command = getCollisionAction(element2, collisionType2);
			if (collisionType2 == CollisionType.BOUNCE) {
				Direction direction = collisionChecker.checkCollisionBetweenGameElements(element2, element1);
				changeDirectionsOnCollision(element2, direction,controller);
			}
			command.execute();
			controller.addCommand(command);
		}
	}
	
	public Command getCollisionAction(GameElement gameElement, CollisionType collisionType) {
		if(collisionType == CollisionType.BOUNCE) {
			return new MoveCommand(gameElement);
		}
		if(collisionType == CollisionType.EXPLODE) {
			return new ExplodeCommand(gameElement);
		}
		return new NullCommand(gameElement);
	}
	
	public void changeDirectionsOnCollision(GameElement element, Direction direction,MainController controller) {
		Command changeVelXCommand = null;
		Command changeVelYCommand = null;
		if(direction == Direction.X) {
			 changeVelXCommand = new ChangeVelXCommand(element);
		}
		if(direction == Direction.Y) {
			changeVelYCommand = new ChangeVelYCommand(element);
		}
		if(direction == Direction.BOTH) {
			changeVelXCommand = new ChangeVelXCommand(element);
			changeVelYCommand = new ChangeVelYCommand(element);
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
}