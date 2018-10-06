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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private ArrayList<Collider> colliders;
	private HashMap<String, AbstractComponent> componentIdMap;
	private HashMap<Integer, List<Command>> keyActionMap;
	private GameTimer gameTimer;
	private GamePlayController gamePlayController;
	private Collision collision;

	public GameMakerController(WindowFrame windowFrame, GameTimer gameTimer) {
		this.windowFrame = windowFrame;
		allComponents = new ArrayList<>();
		timeComponents = new ArrayList<>();
		colliders = new ArrayList<>();
		keyActionMap = new HashMap<>();
		componentIdMap = new HashMap<>();
		this.gameTimer = gameTimer;
		this.collision = new Collision();
		initBounds("TOP WALL", 0, 1, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("LEFT WALL", 1, 0, 2, Constants.GAME_PANEL_HEIGHT);
		initBounds("BOTTOM WALL", 0, Constants.GAME_PANEL_HEIGHT-2, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("RIGHT WALL", Constants.GAME_PANEL_WIDTH-2, 0, 2, Constants.GAME_PANEL_HEIGHT);
	}

	//Helper method to segregate components based on their movement type, actions and controls 
	public void addComponent() {
		component = createAbstractComponent();
		componentIdMap.put(component.getComponentName(), component);
		allComponents.add(component);	//Might not be needed as we have it in componentIdMap through which we can iterate
		if(formData.getKeyActionMap() != null) {
			for(Map.Entry<Integer, String> entry : formData.getKeyActionMap().entrySet()) {
				Integer key = entry.getKey();
				Command command = createCommand(entry.getValue(), component);
				if(keyActionMap.containsKey(key)) {
					keyActionMap.get(key).add(command);
				}
				else {
					keyActionMap.put(key, new ArrayList<Command>());
					keyActionMap.get(key).add(command);
				}
			}
		}

		else if(formData.getTimeActionArray() != null) {
			 timeComponents.add(component);
//			 System.out.println("Added "+ component.getComponentName() + " to time array");

		}

	}

	//Creates collider type by getting ColliderData from View
	public void addCollider() {
		AbstractComponent primaryComponent = componentIdMap.get(colliderData.getPrimaryElement());
		AbstractComponent secondaryComponent = componentIdMap.get(colliderData.getSecondaryElement());
		Collider collider = new Collider(primaryComponent, secondaryComponent, colliderData.getPrimaryAct(), colliderData.getSecondaryAct(),collision);
		System.out.println("Collider add with name: "+ primaryComponent.getComponentName() + " and "+ colliderData.getSecondaryElement() );
		colliders.add(collider);
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
		windowFrame.getGamePanel().addComponent(component);
		windowFrame.draw(null);
	}

	//Helper method to make command for component based on movement type
	public Command createCommand(String commandType, AbstractComponent component) {
		switch(commandType) {
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

	//Creates Component by taking form data user defined for game element from view
	public AbstractComponent createAbstractComponent() {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent component = new AbstractComponent(objectProperties);
		component.setX(formData.getX());
		component.setY(formData.getY());
		component.setVelX(formData.getVelX());
		component.setVelY(formData.getVelY());
		component.setColor(formData.getColor());
		component.setWidth(formData.getWidth());
		component.setHeight(formData.getHeight());
		component.setComponentName(formData.getElementName());
		component.setImage(formData.getBackgroundLocation());
		component.setVisbility(true);

		if(null!=component.getImage() && !component.getImage().equalsIgnoreCase(""))
		{
			component.setDrawable(new DrawOvalImage());
		}
		else
		{
			if(formData.getElementType() == ElementType.CIRCLE) 
			{
				component.setDrawable(new DrawOvalColor());
			}
			else if(formData.getElementType() == ElementType.RECTANGLE)
			{
				component.setDrawable(new DrawRectColor());
			}
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
				
//				.writeObject(allComponents);// remove this if you need to remove all components in future
				allComponents = (ArrayList<AbstractComponent>)in.readObject();
				timeComponents = (ArrayList<AbstractComponent>)in.readObject();
				componentIdMap = (HashMap<String,AbstractComponent>)in.readObject();
				colliders = (ArrayList<Collider>)in.readObject();
				keyActionMap = (HashMap<Integer, List<Command>>)in.readObject();
				
				in.close();
				fileIn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		windowFrame.draw(null);
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
			addComponent();
		}
		else if (componentType.equals(ComponentType.COLLISION)) {
			CollisionFormPanel popUp = new CollisionFormPanel(componentIdMap.keySet().toArray());
			colliderData = popUp.getProperties();
			addCollider();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

  System.out.println("hiiiiiiiiiiiiiii");
  selectedComponent = component.getObjectProperties();

		if (selectedComponent != null) {
			 System.out.println("okkkkkkkkkkkkk");
			int x = arg0.getX();
			int y = arg0.getY();
			selectedComponent.setX(x);
			selectedComponent.setY(y);


			windowFrame.getGamePanel().addComponent(component);
			windowFrame.draw(null);
		}
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

	public ArrayList<AbstractComponent> getTimeComponents() {
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
}
