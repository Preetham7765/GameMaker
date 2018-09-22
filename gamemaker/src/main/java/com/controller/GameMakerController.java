package com.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import com.components.Ball;
import com.components.Brick;
import com.components.Fire;
import com.components.Paddle;
import com.infrastructure.IComposite;
import java.io.File;
import javax.swing.JFileChooser;
import com.components.Ball;
import com.infrastructure.ObjectProperties;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener, MouseListener {
	
	private String prevObjName = null;
	private WindowFrame windowFrame;

	public GameMakerController(WindowFrame windowFrame)
	{	
		this.windowFrame = windowFrame;
	}

	public void displayButtons() {
		this.windowFrame.getFormPanel().createButtons(this);
	}

//	public void createObject(String objName, int x, int y, int velX, int velY, int width, int height)
//	{
//		if(objName.equals("Ball"))
//		{
//			Ball ball = new Ball(new ObjectProperties(x, y, velX, velY, width, height, "Ball"));
//			windowFrame.getGamePanel().addComponent(ball);	
//		}
//	}

	public void actionPerformed(ActionEvent e) {}
	
//	public void setBackground()
//	{
//		String imagePath = windowFrame.getFormPanel().fileExplorer();
//		windowFrame.getGamePanel().setImage(imagePath);
//	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println("Mouse clicked");
		ObjectProperties selected = new ObjectProperties();
//		selected.setX;
		ObjectProperties formPanelSelected = windowFrame.getFormPanel().getSelected();
		
		if (formPanelSelected != null) {
			
            int x = arg0.getX(); 
            int y = arg0.getY(); 
            selected.setX(x);
            selected.setY(y);
            String type = formPanelSelected.getType();
        	IComposite composite = null;
        	selected.setType(formPanelSelected.getType());
        	selected.setHeight(formPanelSelected.getHeight());
        	selected.setWidth(formPanelSelected.getWidth());
        	selected.setVelX(formPanelSelected.getVelX());
        	selected.setVelY(formPanelSelected.getVelY());
//        	selected.set
//        	selected.set
        	switch(type) {
        		case "Ball":
        		{
        			composite = new Ball(selected);
        			break;	
        		}
        		case "Brick":
        		{
        			composite = new Brick(selected);
        			break;
        		}
        		case "Paddle":
        		{
        			composite = new Paddle(selected);
        			break;
        		}
        		case "Fire":
        		{
        			composite = new Fire(selected);
        			break;
        		}        		
        	}
        	
        	windowFrame.getGamePanel().addComponent(composite);
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
