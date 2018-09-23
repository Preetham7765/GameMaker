package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.HashMap;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.GameMakerController;

import com.infrastructure.ComponentType;

import com.infrastructure.AbstractComponent;

import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.IPanel;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite, IPanel {
	
	public ObjectProperties selected = new ObjectProperties();
		
	public ObjectProperties getSelected() {
		return selected;
	}

	public void setSelected(ObjectProperties selected) {
		this.selected = selected;
	}

	private GameMakerController controller;
	private WindowFrame windowFrame;
	
	public FormPanel(WindowFrame window) {
		super();
		this.windowFrame = window;
		setBorder( BorderFactory.createLineBorder(Color.black));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));	
	}
	
	public String fileExplorer()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		System.out.println("Return NULL");
		return null;
		
	}
	


	public void createButtons(GameMakerController controller) {
//		this.controller = controller;
		
		JLabel backgroundText = new JLabel("Choose your background");
		this.add(backgroundText);
		createSetBackgroundButton();

		JLabel select_object = new JLabel("Select any object to add to GamePanel");
		this.add(select_object);
		createBallButton();
		createBrickButton();
		createPaddleButton();
		createFireButton();
	}
	
	private void createSetBackgroundButton() {
		ObjectPanelButton setBackgroundButton = new ObjectPanelButton(ComponentType.BACKGROUND, null, windowFrame);
		this.add(setBackgroundButton);
	}

	private void createFireButton() {
		ObjectPanelButton fireButton = new ObjectPanelButton(ComponentType.FIRE, Color.YELLOW, windowFrame);
		this.add(fireButton);
	}

	private void createPaddleButton() {
		ObjectPanelButton paddleButton = new ObjectPanelButton(ComponentType.PADDLE, Color.red, windowFrame );
		this.add(paddleButton);
	}

	private void createBrickButton() {
		ObjectPanelButton brickButton = new ObjectPanelButton(ComponentType.BRICK, Color.blue, windowFrame);
		this.add(brickButton);		
	}

	private void createBallButton() {
		ObjectPanelButton ballButton = new ObjectPanelButton(ComponentType.BALL, Color.BLACK, windowFrame);
		this.add(ballButton);				
	}

	public void draw(Graphics g) {
	}

	public void addComponent(IComposite composite) throws Exception {
		throw new Exception();
	}

	public void removeComponent(IComposite composite) throws Exception {
		throw new Exception();
	}

	@Override
	public void addComponent(AbstractComponent asbtractComponent) throws Exception {
		throw new Exception();		
	}

	@Override
	public void removeComponent(AbstractComponent asbtractComponent) throws Exception {
		throw new Exception();		
	}

}
