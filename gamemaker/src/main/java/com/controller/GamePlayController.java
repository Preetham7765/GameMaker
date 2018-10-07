package com.controller;

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
import java.util.Deque;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.commands.ChangeDirection;
import com.commands.ChangeVelXCommand;
import com.commands.ChangeVelYCommand;
import com.commands.Command;
import com.commands.MoveCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.Collision;
import com.infrastructure.Direction;
import com.infrastructure.Observer;
import com.observable.GameTimer;
import com.view.WindowFrame;

public class GamePlayController implements Observer, KeyListener, ActionListener {

	private ArrayList<AbstractComponent> actionList;
	private AbstractComponent gameCharacter;
	private ArrayList<AbstractComponent> collectibleList;
	private ArrayList<AbstractComponent> compositeList;
	private WindowFrame windowFrame;
	private Deque<Command> commandQueue;
	private int collectiblesCollected = 0;
	private GameTimer gameTimer;
	private GameMakerController gameMakerController;
	private Collision collisionChecker;
	private Random random;
	private Direction[] directions = { Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN };

	public GamePlayController(WindowFrame windowFrame, GameTimer gameTimer, GameMakerController gameMakerController) {
		this.gameTimer = gameTimer;

		this.windowFrame = windowFrame;
		this.gameMakerController = gameMakerController;
		collisionChecker = new Collision();
		random = new Random();
	}

	/*
	 * public void loadComponentList() { actionList = new ArrayList<>();
	 * compositeList = windowFrame.getGamePanel().getComponentList(); commandQueue =
	 * new LinkedList<>();
	 * 
	 * for (AbstractComponent abstractComponent : compositeList) {
	 * 
	 * ObjectListType objectListType =
	 * abstractComponent.getObjectProperties().getObjectListType(); if
	 * (objectListType == ObjectListType.ACTION) {
	 * actionList.add(abstractComponent); } else if
	 * (objectListType.equals(ObjectListType.EVENT)) { gameCharacter =
	 * abstractComponent; } else if (objectListType == ObjectListType.COLLECTIBLE) {
	 * collectibleList.add(abstractComponent); } } }
	 */

	public void save() {
		// pause();
		try {
			String fileName = windowFrame.showSaveDialog();

			if (!fileName.isEmpty()) {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				windowFrame.save(out);
				out.close();
				fileOut.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {

		for (Collider collider : gameMakerController.getColliders()) {
			collider.execute();
		}

		for (AbstractComponent component : gameMakerController.getRotatorList()) {
			new MoveCommand(component).execute();
		}

		if (gameMakerController.getBullets().size() != 0) {
			for (AbstractComponent bullet : gameMakerController.getBullets()) {
				if (bullet.getY() > 0)
					bullet.setY(bullet.getY() - bullet.getVelY());
				else
					bullet.setVisbility(false);
			}
		}

		List<AbstractComponent> timeComponents = gameMakerController.getTimeComponents();
		for (AbstractComponent component : timeComponents) {
			Direction changedDirection = collisionChecker.checkCollisionBetweenAbstractComponentAndBounds(component);
			if (component.getDirection() == Direction.FREE && changedDirection == Direction.X
					|| changedDirection == Direction.BOTH) {
				new ChangeVelXCommand(component).execute();
			} else if (component.getDirection() == Direction.FREE && changedDirection == Direction.Y
					|| changedDirection == Direction.BOTH) {
				new ChangeVelYCommand(component).execute();
			} else if (changedDirection != Direction.NONE) {
				new ChangeDirection(component).execute();
			}
			new MoveCommand(component).execute();
		}

		if (gameMakerController.getTotalCollectibles() != 0) {
			collectiblesCollected = 0;
			for (AbstractComponent collectible : gameMakerController.getCollectibles()) {
				if (!collectible.getVisibility()) {
					collectiblesCollected++;
				}
			}
			if (collectiblesCollected == gameMakerController.getTotalCollectibles()) {
				gameOver();
			}
		}
		windowFrame.draw(null);
	}

	public void load() {

		try {
			int brickNum = 0;
			String fileName = windowFrame.showOpenDialog();
			if (!fileName.isEmpty()) {
				FileInputStream fileIn = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				windowFrame.load(in);
				windowFrame.setFocusForGamePanel();
				in.close();
				fileIn.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		windowFrame.draw(null);
	}

	private void gameOver() {
		gameTimer.stopTimer();

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Game Over"));
		int result1 = JOptionPane.showConfirmDialog(null, myPanel, "Close", JOptionPane.OK_CANCEL_OPTION);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 32) {
			for (AbstractComponent component : gameMakerController.getFireComponents()) {
				gameMakerController.createBullet(component);
			}
		}

		List<Command> keyComponents = gameMakerController.getComponentListForKeys(key);
		if (keyComponents != null) {
			for (Command command : keyComponents) {
				command.execute();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commandText = e.getActionCommand();
		if (commandText.equals("Play")) {
			gameTimer.registerObserver(this);

			windowFrame.setFocusForGamePanel();
		} else if (commandText.equals("Save")) {
			save();

			windowFrame.setFocusForGamePanel();
		} else if (commandText.equals("Load")) {
			load();
			// loadComponentList();

			windowFrame.setFocusForGamePanel();
		}
	}
}
