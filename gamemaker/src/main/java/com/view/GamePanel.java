package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements IComposite {

	private ArrayList<IComposite> compositeList;

	public GamePanel() {
		compositeList = new ArrayList<>();
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));	
		
	}
	
	public void addControllerListener(GameMakerController controller) {
		addMouseListener(controller);
	}
	@Override
	public void paint(Graphics g) {
		for(IComposite composite : compositeList) {
			composite.draw(g);
		}		
	}
	public void draw(Graphics g) {
        repaint();
			
	}

	public void addComponent(IComposite composite) {
		// TODO Auto-generated method stub
		compositeList.add(composite);
	}

	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		compositeList.remove(composite);
	}

}
