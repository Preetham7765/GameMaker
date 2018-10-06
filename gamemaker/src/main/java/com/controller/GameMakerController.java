package com.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.commands.ChangeDirection;
import com.commands.Command;
import com.commands.MoveDownCommand;
import com.commands.MoveLeftCommand;
import com.commands.MoveRightCommand;
import com.commands.MoveUpCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.Collision;
import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.Direction;
import com.infrastructure.ElementType;
import com.infrastructure.ObjectProperties;
import com.observable.GameTimer;
import com.strategy.DrawOvalColor;
import com.strategy.DrawRectColor;
import com.view.ColliderData;
import com.view.CollisionFormPanel;
import com.view.FormView;
import com.view.ObjectPropertiesPanel;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener, MouseListener {

	private WindowFrame windowFrame;
	private ObjectProperties selectedComponent;
	private FormView formData;
	private ColliderData colliderData;
	private AbstractComponent component;
	private ArrayList<AbstractComponent> allComponents;
	private List<AbstractComponent> timeComponents;
	private ArrayList<Collider> colliders;
	private ArrayList<String> componentNames;
	private HashMap<String, AbstractComponent> componentIdMap;
	private HashMap<Integer, List<Command>> keyActionMap;
	private GameTimer gameTimer;
	private GamePlayController gamePlayController;
	private Collision collision;
	private int idCounter;
	private Direction[] directions = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
	private Random random;

	public GameMakerController(WindowFrame windowFrame, GameTimer gameTimer) {
		this.windowFrame = windowFrame;
		this.allComponents = new ArrayList<>();
		this.timeComponents = new ArrayList<>();
//		this.timeComponents = new HashMap<>();
		this.colliders = new ArrayList<>();
		this.keyActionMap = new HashMap<>();
		this.componentIdMap = new HashMap<>();
		this.componentNames = new ArrayList<>();
		this.gameTimer = gameTimer;
		this.collision = new Collision();
		this.random = new Random();
		initBounds("TOP WALL", 0, 1, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("LEFT WALL", 1, 0, 2, Constants.GAME_PANEL_HEIGHT);
		initBounds("BOTTOM WALL", 0, Constants.GAME_PANEL_HEIGHT - 2, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("RIGHT WALL", Constants.GAME_PANEL_WIDTH - 2, 0, 2, Constants.GAME_PANEL_HEIGHT);
	}

	// Helper method to segregate components based on their movement type, actions
	// and controls
	public void addComponent(int x, int y) {
		System.out.println("print x and y" + x + " " + y);
		component = createAbstractComponent();
		component.setX(x);
		component.setY(y);
		componentIdMap.put(component.getComponentName(), component);
		allComponents.add(component); // Might not be needed as we have it in componentIdMap through which we can
										// iterate
		if (formData.getKeyActionMap() != null) {
			for (Map.Entry<Integer, String> entry : formData.getKeyActionMap().entrySet()) {
				Integer key = entry.getKey();
				Command command = createCommand(entry.getValue(), component);
				if (keyActionMap.containsKey(key)) {
					keyActionMap.get(key).add(command);
				} else {
					keyActionMap.put(key, new ArrayList<Command>());
					keyActionMap.get(key).add(command);
				}
			}
		} else if (formData.getTimeActionArray() != null) {
			
			if(formData.getTimeActionArray().contains(Constants.FREE)) {
				component.setDirection(Direction.FREE);
				timeComponents.add(component);
				return;
			}
			System.out.println("Calling change direction");
			new ChangeDirection(component).execute();
			timeComponents.add(component);
			System.out.println(Arrays.toString(formData.getTimeActionArray().toArray()));
			// System.out.println("Added "+ component.getComponentName() + " to time
			// array");
		}

	}

	// Creates collider type by getting ColliderData from View
	public void addCollider() {

		// O(n2) complexity here. Need to optimize
		List<AbstractComponent> primaryComponents = getComponentByName(colliderData.getPrimaryElement());
		List<AbstractComponent> secondaryComponents = getComponentByName(colliderData.getSecondaryElement());

		for (AbstractComponent primeComponent : primaryComponents)
			for (AbstractComponent secComponent : secondaryComponents) {
				Collider collider = new Collider(primeComponent, secComponent, colliderData.getPrimaryAct(),
						colliderData.getSecondaryAct(), collision);
				colliders.add(collider);
			}
	}

	public void initBounds(String name, int x, int y, int width, int height) {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent component = new AbstractComponent(objectProperties);
		component.setX(x);
		component.setY(y);
		component.setWidth(width);
		component.setHeight(height);
		component.setVisbility(true);
		component.setColor(Color.BLACK);
		component.setDrawable(new DrawRectColor());
		component.setComponentName(name);
		componentIdMap.put(name, component);
		componentNames.add(name);
		windowFrame.getGamePanel().addComponent(component);
		windowFrame.draw(null);
	}

	// Helper method to make command for component based on movement type
	public Command createCommand(String commandType, AbstractComponent component) {
		switch (commandType) {
		case Constants.MOVE_DOWN:
			return new MoveDownCommand(component);
		case Constants.MOVE_UP:
			return new MoveUpCommand(component);
		case Constants.MOVE_LEFT:
			return new MoveLeftCommand(component);
		case Constants.MOVE_RIGHT:
			return new MoveRightCommand(component);
		default:
			return null;
		}
	}

	// Creates Component by taking form data user defined for game element from view
	public AbstractComponent createAbstractComponent() {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent component = new AbstractComponent(objectProperties);
		// component.setX(formData.getX());
		// component.setY(formData.getY());
		component.setComponentName(formData.getElementName() + "_" + Integer.toString(idCounter));
		component.setVelX(formData.getVelX());
		component.setVelY(formData.getVelY());
		component.setColor(Color.BLACK);
		component.setWidth(formData.getWidth());
		component.setHeight(formData.getHeight());
		// component.setComponentName(formData.getElementName());
		component.setVisbility(true);
		if (formData.getElementType() == ElementType.CIRCLE) {
			component.setDrawable(new DrawOvalColor());
		} else if (formData.getElementType() == ElementType.RECTANGLE) {
			component.setDrawable(new DrawRectColor());
		}

		this.idCounter++;
		return component;
	}

	public void save() {
		// pause();
		try {
			String fileName = windowFrame.showSaveDialog();
			if (!fileName.isEmpty()) {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				windowFrame.save(out);
				// out.writeObject(commandQueue);

				out.writeObject(allComponents);// remove this if you need to remove all components in future
				out.writeObject(timeComponents);
				out.writeObject(componentIdMap);
				out.writeObject(colliders);
				out.writeObject(keyActionMap);

				out.close();
				fileOut.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void load() throws ClassNotFoundException {
		// pause();
		// commandQueue.clear();
		try {
			String fileName = windowFrame.showOpenDialog();
			if (!fileName.isEmpty()) {
				FileInputStream fileIn = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				windowFrame.load(in);

				// commandQueue.clear();
				// Deque<Command> loadCmdQueue = (Deque<Command>) in.readObject();
				// commandQueue.addAll(loadCmdQueue);
				// initCommands();

				// .writeObject(allComponents);// remove this if you need to remove all
				// components in future
				allComponents = (ArrayList<AbstractComponent>) in.readObject();
				timeComponents = (List<AbstractComponent>) in.readObject();
				componentIdMap = (HashMap<String, AbstractComponent>) in.readObject();
				colliders = (ArrayList<Collider>) in.readObject();
				keyActionMap = (HashMap<Integer, List<Command>>) in.readObject();

				in.close();
				fileIn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		windowFrame.draw(null);
	}

	private List<AbstractComponent> getComponentByName(String name) {

		List<AbstractComponent> components = new ArrayList<>();

		for (Entry<String, AbstractComponent> component : componentIdMap.entrySet()) {
			if (component.getKey().startsWith(name + "_")) {
				components.add(component.getValue());
			}
		}

		return components;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ComponentType componentType = ComponentType.valueOf(e.getActionCommand().toUpperCase());

		if (componentType.equals(ComponentType.BACKGROUND)) {
			// setBackground();
		}

		else if (componentType.equals(ComponentType.PLAY)) {
			gameTimer.registerObserver(gamePlayController);
			windowFrame.getGamePanel().addKeyListener(gamePlayController);
			windowFrame.getGamePanel().requestFocus();
			// System.out.println("Gameplayer registered");
		}

		else if (componentType.equals(ComponentType.SAVE)) {
			save();
		}

		else if (componentType.equals(ComponentType.LOAD)) {
			try {
				load();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if (componentType.equals(ComponentType.ELEMENT)) {
			ObjectPropertiesPanel popUp = new ObjectPropertiesPanel();
			formData = popUp.getProperties();
			this.idCounter = 1;
			componentNames.add(formData.getElementName());
			// addComponent();
		} else if (componentType.equals(ComponentType.COLLISION)) {
			CollisionFormPanel popUp = new CollisionFormPanel(componentNames.toArray());
			colliderData = popUp.getProperties();
			addCollider();

			System.out.println("Colliders list " + Arrays.toString(colliders.toArray()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		int x = arg0.getX();
		int y = arg0.getY();

		// AbstractComp
		// selectedComponent.setX(x);
		// selectedComponent.setY(y);

		addComponent(x, y);

		// ComponentType componentType = selectedComponent.getComponentType();
		// System.out.println(componentType);
		// AbstractComponent abstractComponent = component;
		/*
		 * selected.setComponentType(formPanelSelected.getComponentType());
		 * selected.setObjectListType(formPanelSelected.getObjectListType()); //
		 * System.out.println("Game maker controller can collect = " +
		 * formPanelSelected.getObjectListType());
		 * selected.setHeight(formPanelSelected.getHeight());
		 * selected.setWidth(formPanelSelected.getWidth());
		 * selected.setVelX(formPanelSelected.getVelX());
		 * selected.setVelY(formPanelSelected.getVelY());
		 * selected.setCanCollect(formPanelSelected.getCanCollect());
		 */
		// switch (componentType) {
		// case BALL: {
		// abstractComponent = new Ball(selectedComponent);
		// break;
		// }
		// case BRICK: {
		// // System.out.println( selected.getObjectListType() + " Brick set as
		// // collectible");
		// abstractComponent = new Brick(selectedComponent);
		// break;
		// }
		// case PADDLE: {
		// abstractComponent = new Paddle(selectedComponent);
		// break;
		// }
		// case FIRE: {
		// abstractComponent = new Fire(selectedComponent);
		// break;
		// }
		// case BACKGROUND: {
		// // windowFrame.createSetBackgroundButton();
		// }
		//
		// default:
		// System.out.println("Error: Invalid Component");
		// }

		// if (component.getObjectProperties().getObjectListType() ==
		// ObjectListType.COLLECTIBLE) {
		// // set behavior to the object to visibility
		// System.out.println("visisbility");
		//
		// Visibility visibility = new Visibility(selectedComponent);
		// // component.setActionBehavior(visibility);
		// }
		//
		// if (component.getObjectProperties().getObjectListType() ==
		// ObjectListType.EVENT
		// || component.getObjectProperties().getObjectListType() ==
		// ObjectListType.ACTION) {
		// // set behavior to move
		//
		// Move move = new Move(selectedComponent);
		// // component.setActionBehavior(move);
		// }

		// System.out.println(" comp typ " +
		// component.getObjectProperties().getObjectListType());
		windowFrame.getGamePanel().addComponent(component);
		windowFrame.draw(null);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public List<Command> getComponentListForKeys(int key) {
		if (keyActionMap.containsKey(key)) {
			return keyActionMap.get(key);
		}
		return null;
	}

	public AbstractComponent getComponent() {
		return component;
	}

	public void setComponent(AbstractComponent component) {
		this.component = component;
	}

	public ArrayList<AbstractComponent> getAllComponents() {
		return allComponents;
	}

	public void setAllComponents(ArrayList<AbstractComponent> allComponents) {
		this.allComponents = allComponents;
	}

	public List<AbstractComponent> getTimeComponents() {
		return timeComponents;
	}

	public void setTimeComponents(List<AbstractComponent> timeComponents) {
		this.timeComponents = timeComponents;
	}

	public ArrayList<Collider> getColliders() {
		return colliders;
	}

	public void setColliders(ArrayList<Collider> colliders) {
		this.colliders = colliders;
	}

	public HashMap<String, AbstractComponent> getComponentIdMap() {
		return componentIdMap;
	}

	public void setComponentIdMap(HashMap<String, AbstractComponent> componentIdMap) {
		this.componentIdMap = componentIdMap;
	}

	public HashMap<Integer, List<Command>> getKeyActionMap() {
		return keyActionMap;
	}

	public void setKeyActionMap(HashMap<Integer, List<Command>> keyActionMap) {
		this.keyActionMap = keyActionMap;
	}

	public void setGamePlayController(GamePlayController gamePlayController) {
		this.gamePlayController = gamePlayController;
	}

	public GamePlayController getGamePlayController() {
		return this.gamePlayController;
	}
}