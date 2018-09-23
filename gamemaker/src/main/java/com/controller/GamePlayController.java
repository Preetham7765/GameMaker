package com.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
		
		loadComponentList();
		this.windowFrame = windowFrame;
		
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
