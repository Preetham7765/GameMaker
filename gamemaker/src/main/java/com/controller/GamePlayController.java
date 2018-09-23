package com.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

import com.commands.ClockTickCommand;
import com.commands.Command;
import com.commands.MoveCommand;
import com.components.Clock;
import com.infrastructure.AbstractComponent;
import com.infrastructure.ComponentType;
import com.infrastructure.ObjectListType;
import com.infrastructure.Observer;
import com.view.WindowFrame;
import com.infrastructure.Constants;

public class GamePlayController implements Observer, KeyListener {
	
	private ArrayList<AbstractComponent> actionList;
	private AbstractComponent gameCharacter;
	private ArrayList<AbstractComponent> collectibleList;
	private ArrayList<AbstractComponent> compositeList;
	private WindowFrame windowFrame;
	private Deque<Command> commandQueue;
	private Clock clock;
	
	public GamePlayController(WindowFrame windowFrame) {
		
		this.windowFrame = windowFrame;
		loadComponentList();
		
	}
	
	public void loadComponentList() {
		
		actionList = new ArrayList<>();
		collectibleList = new ArrayList<>();
		compositeList = windowFrame.getGamePanel().getComponentList();
//		clock = new Clock();
		
		for(AbstractComponent abstractComponent : compositeList) {
			
			ObjectListType objectListType = abstractComponent.getObjectProperties().getObjectListType();	
			if(objectListType == ObjectListType.ACTION) {
				actionList.add(abstractComponent);
			} else if(objectListType == ObjectListType.EVENT) {
				gameCharacter = abstractComponent;
			} else if(objectListType == ObjectListType.COLLECTIBLE) {
				collectibleList.add(abstractComponent);
			}		
		}

	}
	
	public void update() {
		commandQueue.addFirst(new ClockTickCommand(this.clock));
		for(AbstractComponent abstractComponent : actionList) {
			int x = abstractComponent.getVelY();
			int y = abstractComponent.getVelX();
			commandQueue.add(new MoveCommand(abstractComponent, x, y));
		}	
		checkCollisionDetection();
		this.windowFrame.draw(null);
	}

	
	private void checkCollisionDetection() {
		
		for (AbstractComponent actionComponent: actionList)
		{
			for (AbstractComponent actionComponent2: actionList)
			{
//				Collision with other action component
				if(actionComponent.getBounds().intersects(actionComponent2.getBounds()))
				{
					actionComponent.setVelX(-actionComponent.getVelX());
					actionComponent.setVelY(-actionComponent.getVelY());
					actionComponent2.setVelX(-actionComponent2.getVelX());
					actionComponent2.setVelY(-actionComponent2.getVelY());
				}
			}
			for (AbstractComponent collectibleComponent: collectibleList)
			{
//				Collision with collectible component
				if(actionComponent.getBounds().intersects(collectibleComponent.getBounds()))
				{	
					if(actionComponent.getCanCollect()) {
						collectibleComponent.performAction();
					}
					actionComponent.setVelX(-actionComponent.getVelX());
					actionComponent.setVelY(-actionComponent.getVelY());
					}
			}
//			Collision with game character
			if(actionComponent.getBounds().intersects(gameCharacter.getBounds())) {
				actionComponent.setVelX(-actionComponent.getVelX());
				actionComponent.setVelY(-actionComponent.getVelY());
			}
			
//			Collision action component with right wall
			if(actionComponent.getRightCoordinates() >= Constants.GAME_PANEL_WIDTH)
			{
				actionComponent.setVelX(-actionComponent.getVelX());
			}
			
//			Collision action component with left wall
			if(actionComponent.getRightCoordinates() - actionComponent.getWidth() <= 0)
			{
				actionComponent.setVelX(-actionComponent.getVelX());
			}
			
//			Collision action component with up wall
			
			if(actionComponent.getBottomCoordinates() - actionComponent.getHeight() <= 0)
			{
				actionComponent.setVelX(-actionComponent.getVelY());
			}
			
//			Collision action component with bottom wall
			
			if(actionComponent.getBottomCoordinates() >= Constants.GAME_PANEL_HEIGHT)
			{
				actionComponent.setVelX(-actionComponent.getVelY());
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int curX = Math.abs(this.gameCharacter.getVelX());
		int curY = Math.abs(this.gameCharacter.getVelY());
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) // && canMoveLeft(this, Constants.getPaddleLeftOffset()
			commandQueue.addFirst(new MoveCommand(this.gameCharacter, -curX, 0));
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) //  && canMoveRight(this, Constants.getPaddleRightOffset())
			commandQueue.addFirst(new MoveCommand(this.gameCharacter, curX, 0));

		else if (e.getKeyCode() == KeyEvent.VK_UP) // && canMoveLeft(this, Constants.getPaddleLeftOffset()
			commandQueue.addFirst(new MoveCommand(this.gameCharacter, 0, curY));
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) //  && canMoveRight(this, Constants.getPaddleRightOffset())
			commandQueue.addFirst(new MoveCommand(this.gameCharacter, 0, -curY));

//		commandQueue.collisionResponse(e);			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
