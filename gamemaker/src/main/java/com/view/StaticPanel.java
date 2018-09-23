package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.components.Clock;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.IPanel;
import com.infrastructure.ObjectProperties;
import com.infrastructure.StaticPanelButton; 

public class StaticPanel extends JPanel implements IComposite, IPanel{

	private WindowFrame windowFrame;
	public ObjectProperties selected = new ObjectProperties();
	private ArrayList<AbstractComponent> compositeList;
	
	public StaticPanel(WindowFrame window)
	{
		super();
		compositeList=new ArrayList<>();
		this.windowFrame=window;
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		//setBackground(Color.BLACK);
		
		
	}
	
	public void createButtons() {
		
		createPlayButton();
		createPauseButton();
		createRestartButton();
		createSaveButton();
		createLoadButton();
		
	}
	private void createPlayButton() {
		StaticPanelButton playButton = new StaticPanelButton("Play", Color.green, windowFrame);
		playButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(playButton);
	}

	private void createPauseButton() {
		StaticPanelButton pauseButton = new StaticPanelButton("Pause", Color.red, windowFrame );
		pauseButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(pauseButton);
	}

	private void createRestartButton() {
		StaticPanelButton restartButton = new StaticPanelButton("Restart", Color.blue, windowFrame);
		restartButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(restartButton);
	
	}
	
	private void createSaveButton() {
		StaticPanelButton saveButton = new StaticPanelButton("Save", Color.blue, windowFrame);
		saveButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(saveButton);
	
	}
	
	private void createLoadButton() {
		StaticPanelButton loadButton = new StaticPanelButton("Load", Color.blue, windowFrame);
		loadButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(loadButton);
	
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
		repaint();
	}

	@Override
	public void addComponent(AbstractComponent asbtractComponent) throws Exception {
		 compositeList.add(asbtractComponent);
		
	}

	@Override
	public void removeComponent(AbstractComponent asbtractComponent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		for(IComposite composite : compositeList) {
			System.out.println(composite);
			composite.draw(g);
		}	
	}

}
