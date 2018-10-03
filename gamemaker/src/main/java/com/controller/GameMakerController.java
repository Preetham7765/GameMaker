package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.behavior.Move;
import com.behavior.Visibility;
import com.commands.Command;
import com.components.Ball;
import com.components.Brick;
import com.components.Fire;
import com.components.Paddle;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.ComponentType;
import com.infrastructure.ElementType;
import com.infrastructure.ObjectListType;
import com.infrastructure.ObjectProperties;
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
	private ArrayList<String> dataList;
	private AbstractComponent component;
	private ArrayList<AbstractComponent> allComponents;
	private	ArrayList<AbstractComponent> timeComponents;
	private ArrayList<Collider> colliders;
	private HashMap<String, AbstractComponent> componentIdMap;
	private HashMap<Integer, List<Command>> keyActionMap;

	public GameMakerController(WindowFrame windowFrame) {
		this.windowFrame = windowFrame;
		allComponents = new ArrayList<>();
		timeComponents = new ArrayList<>();
		colliders = new ArrayList<>();
		keyActionMap = new HashMap<>();
		componentIdMap = new HashMap<>();
		dataList = new ArrayList<String>();
		dataList.add("All");
	}

	/*
	 * public void displayButtons() {
	 * this.windowFrame.getFormPanel().createButtons(); }
	 */

	public void addComponent(AbstractComponent component, ElementType elementType) {
		
		//componentIdMap.put(component.getId(), component);
		allComponents.add(component);
		if(elementType == ElementType.PLAYEROBJECT) {
//			for(Integer key : keyList) {
//				if(keyActionMap.containsKey(key)) {
//					keyActionMap.get(key).add(command);
//				}
//				else {
//					keyActionMap.put(key, new ArrayList<Command>());
//					keyActionMap.get(key).add(command);
//				}
//			}
		}
		if(elementType ==  ElementType.GAMEOBJECT) {
			timeComponents.add(component);
		}
	}
	
	public void addCollider(Collider collider) {
		colliders.add(collider);
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
				out.close();
				fileOut.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void load() {
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
			// setBackground();
		}

		else if (componentType.equals(ComponentType.PLAY)) {

		}

		else if (componentType.equals(ComponentType.SAVE)) {
			save();
		}

		else if (componentType.equals(ComponentType.LOAD)) {
			load();
		}

		else if (componentType.equals(ComponentType.ELEMENT)) {
			ObjectPropertiesPanel popUp = new ObjectPropertiesPanel();
			formData = popUp.getProperties();
			dataList.add(formData.getElementName());
		}
		else if (componentType.equals(ComponentType.COLLISION)) {
			CollisionFormPanel popUp = new CollisionFormPanel(dataList);
			colliderData = popUp.getProperties();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		// ObjectProperties selected = windowFrame.getActiveObjectProperties();

		if (selectedComponent != null) {

			int x = arg0.getX();
			int y = arg0.getY();
			selectedComponent.setX(x);
			selectedComponent.setY(y);

			ComponentType componentType = selectedComponent.getComponentType();
			System.out.println(componentType);
			AbstractComponent abstractComponent = null;
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
			switch (componentType) {
			case BALL: {
				abstractComponent = new Ball(selectedComponent);
				break;
			}
			case BRICK: {
				// System.out.println( selected.getObjectListType() + " Brick set as
				// collectible");
				abstractComponent = new Brick(selectedComponent);
				break;
			}
			case PADDLE: {
				abstractComponent = new Paddle(selectedComponent);
				break;
			}
			case FIRE: {
				abstractComponent = new Fire(selectedComponent);
				break;
			}
			case BACKGROUND: {
				// windowFrame.createSetBackgroundButton();
			}

			default:
				System.out.println("Error: Invalid Component");
			}

			if (abstractComponent.getObjectProperties().getObjectListType() == ObjectListType.COLLECTIBLE) {
				// set behavior to the object to visibility
				System.out.println("visisbility");

				Visibility visibility = new Visibility(selectedComponent);
				// abstractComponent.setActionBehavior(visibility);
			}

			if (abstractComponent.getObjectProperties().getObjectListType() == ObjectListType.EVENT
					|| abstractComponent.getObjectProperties().getObjectListType() == ObjectListType.ACTION) {
				// set behavior to move

				Move move = new Move(selectedComponent);
				// abstractComponent.setActionBehavior(move);
			}

			System.out.println(" comp typ  " + abstractComponent.getObjectProperties().getObjectListType());
			windowFrame.getGamePanel().addComponent(abstractComponent);
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

}
