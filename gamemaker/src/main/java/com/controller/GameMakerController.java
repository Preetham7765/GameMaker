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
import com.components.Paddle;
import com.infrastructure.IComposite;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener, MouseListener {
	
	private String prevObjName = null;
	private WindowFrame windowFrame;

	public GameMakerController(WindowFrame windowFrame)
	{
		
		this.windowFrame = windowFrame;
		
	}
	public void createObject(String objName, int x, int y, int velX, int velY, int width, int height)
	{
		
	}


	public void actionPerformed(ActionEvent arg0) {
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse clicked");
		HashMap<String, Object> selected = windowFrame.getFormPanel().getSelected();
		//windowFrame.getGamePanel().addMouseListener(this);
		
		if (selected != null) {
            int x = arg0.getX(); 
            int y = arg0.getY(); 
           
        	String type = (String) selected.get("Type");
        	Color color = (Color) selected.get("Color");
        	Integer width = (Integer) selected.get("Width");
        	Integer height =  (Integer) selected.get("Height");
            Integer velX = (Integer) selected.get("velX");
            Integer velY = (Integer) selected.get("velY");
            System.out.println("Type:" +type);
        	IComposite composite = null;
        	switch(type) {
        		case "Ball":
        		{
        			composite = new Ball(x, y, velX, velY, width/2);
        			break;
        			
        		}
        		case "Brick":
        		{
        			composite = new Brick(x, y, velX, velY, width, height);
        			break;
        		}
        		case "Paddle":
        		{
        			composite = new Paddle(x, y, velX, velY, width, height);
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
