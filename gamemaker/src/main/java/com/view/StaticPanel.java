package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.IPanel;
import com.infrastructure.ObjectProperties;
import com.infrastructure.StaticPanelButton; 

public class StaticPanel extends JPanel implements IComposite, IPanel{

	private WindowFrame windowFrame;
	public ObjectProperties selected = new ObjectProperties();
	
	public StaticPanel(WindowFrame window)
	{
		super();
		this.windowFrame=window;
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setBackground(Color.darkGray);
	}
	
	public void createButtons() {
		
		createPlayButton();
		createPauseButton();
		createRestartButton();
		
	}
	private void createPlayButton() {
		StaticPanelButton playButton = new StaticPanelButton("Play", Color.green, windowFrame);
		this.add(Box.createRigidArea(new Dimension(10, 80)));
		this.add(playButton);
	}

	private void createPauseButton() {
		StaticPanelButton pauseButton = new StaticPanelButton("Pause", Color.red, windowFrame );
		this.add(Box.createRigidArea(new Dimension(10, 80)));
		this.add(pauseButton);
	}

	private void createRestartButton() {
		StaticPanelButton restartButton = new StaticPanelButton("Restart", Color.blue, windowFrame);
		this.add(Box.createRigidArea(new Dimension(10, 80)));
		this.add(restartButton);
	
	}

	public ObjectProperties getSelected() {
		return selected;
	}

	public void setSelected(ObjectProperties selected) {
		this.selected = selected;
	}

	@Override
	public void addComponent(IComposite composite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(AbstractComponent asbtractComponent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(AbstractComponent asbtractComponent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
