package com.controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import com.commands.ClockTickCommand;
import com.commands.Command;
import com.commands.MoveCommand;
import com.components.Clock;
import com.infrastructure.AbstractComponent;
import com.infrastructure.ComponentType;
import com.infrastructure.ObjectListType;
import com.infrastructure.Observer;
import com.observable.GameTimer;
import com.view.WindowFrame;

import com.infrastructure.Constants;

public class GamePlayController implements Observer, KeyListener, ActionListener {
	
	private ArrayList<AbstractComponent> actionList;
	private AbstractComponent gameCharacter;
	private ArrayList<AbstractComponent> collectibleList;
	private ArrayList<AbstractComponent> compositeList;
	private WindowFrame windowFrame;
	private Deque<Command> commandQueue;
	private Clock clock;
	private int collectiblesCollected = 0;
	private GameTimer gameTimer;
	
	public GamePlayController(WindowFrame windowFrame, GameTimer gameTimer) {
		this.gameTimer = gameTimer;
		
		this.windowFrame = windowFrame;
		loadComponentList();
		
	}
	
	public void loadComponentList() {

		
		actionList = new ArrayList<>();
		collectibleList = new ArrayList<>();
		compositeList = windowFrame.getGamePanel().getComponentList();
//		clock = new Clock();
		commandQueue = new LinkedList<>();
		
		for(AbstractComponent abstractComponent : compositeList) {
			
			ObjectListType objectListType = abstractComponent.getObjectProperties().getObjectListType();	
			if(objectListType == ObjectListType.ACTION) {
				actionList.add(abstractComponent);
			} else if(objectListType.equals(ObjectListType.EVENT)) {
				gameCharacter = abstractComponent;
			} else if(objectListType == ObjectListType.COLLECTIBLE) {
				collectibleList.add(abstractComponent);
			}		
		}
		System.out.println("gameCharacter " + gameCharacter);
	}

	public void save() {
//		pause();
		try {
			String fileName = windowFrame.showSaveDialog();
			if(!fileName.isEmpty()) {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			windowFrame.save(out);
//			out.writeObject(commandQueue);
			out.close();
			fileOut.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void update() {
//		commandQueue.addFirst(new ClockTickCommand(this.clock));
//		System.out.print(actionList.size() + " Size");
		for(AbstractComponent abstractComponent : actionList) {
			int x = abstractComponent.getVelY();
			int y = abstractComponent.getVelX();
			commandQueue.add(new MoveCommand(abstractComponent, x, y));
		}	
		checkCollisionDetection();
		this.windowFrame.draw(null);
	}
	
	
	public void load() {
//		pause();
//		commandQueue.clear();
		try {
			int brickNum = 0;
			String fileName = windowFrame.showOpenDialog();
			if(!fileName.isEmpty()) {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			windowFrame.load(in);
			
//			commandQueue.clear();
//			Deque<Command> loadCmdQueue = (Deque<Command>) in.readObject();
//			commandQueue.addAll(loadCmdQueue);
//			initCommands();
			in.close();
			fileIn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		windowFrame.draw(null);
	}

	private void checkCollisionDetection() {
		
//		Collision with action objects
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
						if(collectiblesCollected == collectibleList.size()-1)
						{}
//							Game Ends
						else
							collectiblesCollected++;
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
				actionComponent.setVelY(-actionComponent.getVelY());
			}
			
//			Collision action component with bottom wall
			
			if(actionComponent.getBottomCoordinates() >= Constants.GAME_PANEL_HEIGHT)
			{
				actionComponent.setVelY(-actionComponent.getVelY());
			}
		}
		
//		Collision of gameCharacter with collectible list
		
		for(AbstractComponent collectibleComponent : collectibleList)
		{
			if(collectibleComponent.getBounds().intersects(gameCharacter.getBounds()))
				collectibleComponent.performAction();
			if(collectiblesCollected == collectibleList.size()-1)
			{}
//				Game Ends
			else
				collectiblesCollected++;
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
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText = e.getActionCommand();
		if(commandText.equals("Play")) {
			gameTimer.registerObserver(this);
		}
		else if(commandText.equals("Save")) {
			save();
		}
		else if(commandText.equals("Load")) {
			load();
			loadComponentList();
		}
	}
}

