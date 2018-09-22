package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite {
	
	public HashMap<String, Object> selected = new HashMap<String, Object>();
	
	
	public HashMap<String, Object> getSelected() {
		return selected;
	}

	public void setSelected(HashMap<String, Object> selected) {
		this.selected = selected;
	}

	private GameMakerController controller;
	public FormPanel() {
		super();
		setBorder( BorderFactory.createLineBorder(Color.black));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		JLabel select_object = new JLabel("Select any object to add to GamePanel");
		
		
		
		this.add(select_object);
	}

	public void createButtons(GameMakerController controller) {
		this.controller = controller;
		createBallButton();
		createBrickButton();
		createPaddleButton();
		createFireButton();
	}

	private void createFireButton() {
		ObjectPanelButton fireButton = new ObjectPanelButton("Fire", Color.YELLOW, this);
		this.add(fireButton);
	}

	private void createPaddleButton() {
		ObjectPanelButton paddleButton = new ObjectPanelButton("Paddle", Color.red, this );
		this.add(paddleButton);
	}

	private void createBrickButton() {
		ObjectPanelButton brickButton = new ObjectPanelButton("Brick", Color.blue, this);
		this.add(brickButton);		
	}

	private void createBallButton() {
		ObjectPanelButton ballButton = new ObjectPanelButton("Ball", Color.BLACK, this);
		this.add(ballButton);				
	}

	public void draw(Graphics g) {
	}

	public void addComponent(IComposite composite) {
		return;
	}

	public void removeComponent(IComposite composite) {
		return;
	}

}
