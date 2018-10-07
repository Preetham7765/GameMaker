package com.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Map.Entry;
import java.util.Random;

import com.commands.BulletCommand;
import com.commands.ChangeDirection;


import javax.swing.JFileChooser;

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
import com.strategy.DrawOvalImage;
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
	private	ArrayList<AbstractComponent> timeComponents;
	private ArrayList<AbstractComponent> bullets;
	private ArrayList<Collider> colliders;
	private ArrayList<String> componentNames;
	private HashMap<String, AbstractComponent> componentIdMap;
	private HashMap<Integer, List<Command>> keyActionMap;
	private ArrayList<AbstractComponent> rotatorList;
	private GameTimer gameTimer;
	private GamePlayController gamePlayController;
	private Collision collision;
	private int score;
	private int idCounter;
	private Direction[] directions = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
	private Random random;

	public GameMakerController(WindowFrame windowFrame, GameTimer gameTimer) {
		this.windowFrame = windowFrame;
		allComponents = new ArrayList<>();
		timeComponents = new ArrayList<>();
		colliders = new ArrayList<>();
		keyActionMap = new HashMap<>();
		componentIdMap = new HashMap<>();
		rotatorList = new ArrayList<>();
		componentNames = new ArrayList<>();
		this.gameTimer = gameTimer;
		this.collision = new Collision();
		this.random = new Random();
		initBounds("TOPWALL", 0, 1, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("LEFTWALL", 1, 0, 2, Constants.GAME_PANEL_HEIGHT);
		initBounds("BOTTOMWALL", 0, Constants.GAME_PANEL_HEIGHT, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("RIGHTWALL", Constants.GAME_PANEL_WIDTH, 0, 2, Constants.GAME_PANEL_HEIGHT);
	}

	//Helper method to segregate components based on their movement type, actions and controls 
	public void addComponent(int x, int y) {
		Command command;
		component = createAbstractComponent();
		if(component!=null)
		{
			component.setX(x);
			component.setY(y);
			componentIdMap.put(component.getComponentName(), component);
			allComponents.add(component); // Might not be needed as we have it in componentIdMap through which we can
			// iterate
			if (formData.getKeyActionMap() != null) {
				for (Map.Entry<Integer, String> entry : formData.getKeyActionMap().entrySet()) {
					Integer key = entry.getKey();
					command = createCommand(entry.getValue(), component);
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
					//				timeComponents.add(component);
					//				return;
				}
				else if((formData.getTimeActionArray()).size() == 4) {
					System.out.println("Calling change direction");
					new ChangeDirection(component).execute();
					System.out.println(Arrays.toString(formData.getTimeActionArray().toArray()));
					//					timeComponents.add(component);
					// System.out.println("Added "+ component.getComponentName() + " to time
					// array");
				}
				if(!formData.isRotateable())
					timeComponents.add(component);
			}


			if(formData.isCollectible()) {
				component.setCollectible(true);
				allComponents.add(component);
			}

			if(formData.isRotateable())
				rotatorList.add(component);

			System.out.println("keyActionMap :: "+keyActionMap.toString());
		}
	}

	public AbstractComponent addBullets(AbstractComponent component) {
		System.out.println("addBullets ------");
		AbstractComponent bulletComponent = new AbstractComponent();
		bulletComponent.setX(component.getX()+(component.getWidth()/2));
		bulletComponent.setY(component.getY());
		bulletComponent.setVelX(0);//change later
		bulletComponent.setVelY(10);//change later
		bulletComponent.setColor(Color.BLACK);
		bulletComponent.setWidth(2);//change later
		bulletComponent.setHeight(7);//change later
		bulletComponent.setComponentName("Bullet");//change later
		bulletComponent.setVisbility(true);
		bulletComponent.setDrawable(new DrawRectColor());
		return bulletComponent;
	}
	//Creates collider type by getting ColliderData from View
	public void addCollider() {
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
		componentIdMap.put(name+"_", component);
		componentNames.add(name);
		windowFrame.getGamePanel().addComponent(component);
		windowFrame.draw(null);
	}

	//Helper method to make command for component based on movement type
	public Command createCommand(String commandType, AbstractComponent component) {
		System.out.println("createCommand --> commandType -- "+commandType);
		switch(commandType) {
		case Constants.MOVE_DOWN:
			return new MoveDownCommand(component);
		case Constants.MOVE_UP:
			return new MoveUpCommand(component);
		case Constants.MOVE_LEFT:
			return new MoveLeftCommand(component);
		case Constants.MOVE_RIGHT:
			return new MoveRightCommand(component);
		case Constants.FIRE:
			return new BulletCommand(component);
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
		if(formData!=null)
		{
			component.setComponentName(formData.getElementName() + "_" + Integer.toString(idCounter));
			component.setVelX(formData.getVelX());
			component.setVelY(formData.getVelY());
			component.setColor(formData.getColor());
			component.setWidth(formData.getWidth());
			component.setHeight(formData.getHeight());
			component.setImage(formData.getBackgroundLocation());
			component.setVisbility(true);
			if(null!=component.getImage() && !component.getImage().equalsIgnoreCase("")){
				component.setDrawable(new DrawOvalImage());
			}
			else{
				if(formData.getElementType() == ElementType.CIRCLE) {
					component.setDrawable(new DrawOvalColor());
				}
				else if(formData.getElementType() == ElementType.RECTANGLE)
				{
					component.setDrawable(new DrawRectColor());
				}
			}

			this.idCounter++;
		}
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
				out.writeObject(componentNames);

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

				allComponents = (ArrayList<AbstractComponent>)in.readObject();
				timeComponents = (ArrayList<AbstractComponent>)in.readObject();
				componentIdMap = (HashMap<String,AbstractComponent>)in.readObject();
				colliders = (ArrayList<Collider>)in.readObject();
				keyActionMap = (HashMap<Integer, List<Command>>)in.readObject();
				componentNames=(ArrayList<String>)in.readObject();

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
			System.out.println("Key name and component name:" + component.getKey() +" "+ component.getValue().getComponentName());
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

			//JPanel panel = new JPanel(new ImageIcon("images/background.png").getImage());
			windowFrame.getGamePanel().setImgPath(fileExplorer());
			windowFrame.getGamePanel().repaint();
			//windowFrame.getContentPane()	
		}

		else if (componentType.equals(ComponentType.PLAY)) {
			gameTimer.registerObserver(gamePlayController);
			windowFrame.getGamePanel().addKeyListener(gamePlayController);
			windowFrame.getGamePanel().requestFocus();
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
			CollisionFormPanel popUp = new CollisionFormPanel(componentNames.toArray(),colliders);
			System.out.println("vrishali "+colliders);
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

	public String fileExplorer() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(windowFrame.getGamePanel());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		System.out.println("Return NULL");
		return null;

	}

	public List<Command> getComponentListForKeys(int key){
		if(keyActionMap.containsKey(key)) {
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

	public void setTimeComponents(ArrayList<AbstractComponent> timeComponents) {
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

	public ArrayList<AbstractComponent> getRotatorList() {
		return rotatorList;
	}

	public void setRotatorList(ArrayList<AbstractComponent> rotatorList) {
		this.rotatorList = rotatorList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
