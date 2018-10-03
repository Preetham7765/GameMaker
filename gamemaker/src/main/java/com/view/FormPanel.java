
package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.IAddActionListener;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite, IAddActionListener {

	private ObjectProperties active;
	private List<ObjectPanelButton> objectButtons; // should we need it lets see?

	private GameMakerController controller;
	// private WindowFrame windowFrame;

	public FormPanel(WindowFrame window) {
		super();
		// this.windowFrame = window;
		this.objectButtons = new ArrayList<>();
		this.active = new ObjectProperties();
		setBorder(BorderFactory.createLineBorder(Color.red));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}

	public ObjectProperties getActive() {
		return active;
	}

	public void setSelected(ObjectProperties selected) {
		this.active = selected;
	}

	public String fileExplorer() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		System.out.println("Return NULL");
		return null;

	}

	public void createButtons() {

		JLabel backgroundText = new JLabel("Choose your background");
		backgroundText.setForeground(Color.red);
		backgroundText.setFont(new Font("Helvetica", Font.BOLD, 20));
		this.add(Box.createRigidArea(new Dimension(10, 50)));
		this.add(backgroundText);
		createSetBackgroundButton();

		JLabel select_object = new JLabel("Select any object to add to GamePanel");
		select_object.setForeground(Color.red);
		select_object.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.add(Box.createRigidArea(new Dimension(10, 50)));
		this.add(select_object);
		
		this.add(createButton());
		this.add(createCollisionButton());
		/*createBallButton();
		createBrickButton();
		createPaddleButton();
		createFireButton();*/

		JLabel gameFunc = new JLabel("Game Options:");
		gameFunc.setForeground(Color.red);
		gameFunc.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.add(Box.createRigidArea(new Dimension(10, 50)));
		this.add(gameFunc);
		createLoadButton();
		createSaveButton();
		createPlayButton();
	}

	public ObjectProperties getActiveObjectProperties() {
		return active;
	}

	public void createSetBackgroundButton() {
		ObjectPanelButton setBackgroundButton = new ObjectPanelButton(ComponentType.BACKGROUND, null);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(setBackgroundButton);
		this.objectButtons.add(setBackgroundButton);
	}
	
	private ObjectPanelButton createButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.ELEMENT, Color.YELLOW);
		
		//JButton button=new JButton(name);
		//fireButton.setBackground(Color.yellow);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		//this.add(fireButton);
		this.objectButtons.add(button);
		return button;
	}
	
	private ObjectPanelButton createCollisionButton() {
		ObjectPanelButton collisionButton = new ObjectPanelButton(ComponentType.COLLISION, Color.YELLOW);
		
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.objectButtons.add(collisionButton);
		return collisionButton;
	}

	/*private void createFireButton() {
		ObjectPanelButton fireButton = new ObjectPanelButton(ComponentType.FIRE, Color.YELLOW);
		fireButton.setBackground(Color.yellow);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(fireButton);
		this.objectButtons.add(fireButton);
	}

	private void createPaddleButton() {
		ObjectPanelButton paddleButton = new ObjectPanelButton(ComponentType.PADDLE, Color.red);
		paddleButton.setBackground(Color.red);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(paddleButton);
		this.objectButtons.add(paddleButton);
	}

	private void createBrickButton() {
		ObjectPanelButton brickButton = new ObjectPanelButton(ComponentType.BRICK, Color.blue);
		brickButton.setBackground(Color.blue);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(brickButton);
		this.objectButtons.add(brickButton);
	}*/

	/*private void createBallButton() {
		ObjectPanelButton ballButton = new ObjectPanelButton(ComponentType.BALL, Color.green);
		ballButton.setBackground(Color.green);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(ballButton);
		this.objectButtons.add(ballButton);
	}*/

	private void createLoadButton() {
		ObjectPanelButton loadButton = new ObjectPanelButton(ComponentType.LOAD, Color.CYAN);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(loadButton);
		this.objectButtons.add(loadButton);
	}

	private void createSaveButton() {
		ObjectPanelButton saveButton = new ObjectPanelButton(ComponentType.SAVE, Color.BLUE);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(saveButton);
		this.objectButtons.add(saveButton);
	}

	private void createPlayButton() {
		ObjectPanelButton playButton = new ObjectPanelButton(ComponentType.PLAY, Color.GREEN);
		this.add(Box.createRigidArea(new Dimension(30, 30)));
		this.add(playButton);
		this.objectButtons.add(playButton);
	}

	public void draw(Graphics g) {
	}

	@Override
	public void save(ObjectOutputStream op) {
	}

	@Override
	public void load(ObjectInputStream ip) {
	}

	@Override
	public void addActionListener(ActionListener listener) {
		for (ObjectPanelButton button : objectButtons) {
			button.addActionListener(listener);
		}
	}
}
