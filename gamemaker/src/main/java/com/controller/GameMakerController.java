package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.components.Ball;
import com.components.Brick;
import com.components.Fire;
import com.components.Paddle;
import com.infrastructure.AbstractComponent;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener, MouseListener {
	
	private WindowFrame windowFrame;

	public GameMakerController(WindowFrame windowFrame)
	{	
		this.windowFrame = windowFrame;
	}

	public void displayButtons() {
		this.windowFrame.getFormPanel().createButtons(this);
	}

	public void actionPerformed(ActionEvent e) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ObjectProperties selected = new ObjectProperties();
		ObjectProperties formPanelSelected = windowFrame.getFormPanel().getSelected();
		
		
		if (formPanelSelected != null) {
			
            int x = arg0.getX(); 
            int y = arg0.getY(); 
            selected.setX(x);
            selected.setY(y);
            String type = formPanelSelected.getType();
        	AbstractComponent abstractComponent = null;
        	selected.setType(formPanelSelected.getType());
        	selected.setHeight(formPanelSelected.getHeight());
        	selected.setWidth(formPanelSelected.getWidth());
        	selected.setVelX(formPanelSelected.getVelX());
        	selected.setVelY(formPanelSelected.getVelY());
        	switch(type) {
        		case "Ball":
        		{
        			abstractComponent = new Ball(selected);
        			break;	
        		}
        		case "Brick":
        		{
        			abstractComponent = new Brick(selected);
        			break;
        		}
        		case "Paddle":
        		{
        			abstractComponent = new Paddle(selected);
        			break;
        		}
        		case "Fire":
        		{
        			abstractComponent = new Fire(selected);
        			break;
        		}        		
        	}
        	
        	windowFrame.getGamePanel().addComponent(abstractComponent);
        	if(abstractComponent.getObjectProperties().isCollectible()) {
        		// set behavior to the object to visibility
        	}
//        	if(!composite.getObjectProperties().isEvent()) {
//        		// set behavior to move
//        	}
        	
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
