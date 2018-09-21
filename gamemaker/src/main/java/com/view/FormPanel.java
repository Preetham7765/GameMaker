package com.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite {
	
	private GameMakerController controller;
	public FormPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setVisible(true);
		JLabel select_object = new JLabel("Select any object to add to GamePanel");
		this.add(select_object);
	}

	private void createButtons(GameMakerController controller) {
		this.controller = controller;
		createBallButton();
		createBrickButton();
		createPaddleButton();
		createFireButton();
	}

	private void createFireButton() {
		ObjectPanelButton fireButton = new ObjectPanelButton("Fire", controller);
		this.add(fireButton);
	}

	private void createPaddleButton() {
		ObjectPanelButton paddleButton = new ObjectPanelButton("Paddle", controller);
		this.add(paddleButton);
	}

	private void createBrickButton() {
		ObjectPanelButton brickButton = new ObjectPanelButton("Brick", controller);
		this.add(brickButton);		
	}

	private void createBallButton() {
		ObjectPanelButton ballButton = new ObjectPanelButton("Ball", controller);
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
