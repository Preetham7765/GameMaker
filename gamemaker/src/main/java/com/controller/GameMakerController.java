package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.components.Ball;
import com.infrastructure.ObjectProperties;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener {
	
	private String prevObjName = null;
	private WindowFrame windowFrame;

	public GameMakerController(WindowFrame window)
	{
		this.windowFrame = windowFrame;
	}

	public void displayButtons() {
		this.windowFrame.getFormPanel().createButtons(this);
	}
	
	public void createObject(String objName, int x, int y, int velX, int velY, int width, int height)
	{
		if(objName.equals("Ball"))
		{
			Ball ball = new Ball(x, y, velX, velY, width/2);
			windowFrame.getGamePanel().addComponent(ball);
			
		}
	}


	public void actionPerformed(ActionEvent e) {
		
		String objName = e.getActionCommand();
		ObjectProperties objProps = windowFrame.getFormPanel().savePopUp();
		createObject(objName, objProps.x, objProps.y, objProps.velX, objProps.velY, objProps.width, objProps.height);
		
//		if(objName.equals("Ball"))
//		{
//			ObjectProperties objProps;
//			objProps = windowFrame.getFormPanel().savePopUp();
//			Ball ball = new Ball(objProps.x, objProps.y, objProps.velX, objProps.velY, objProps.width/2);
//			windowFrame.getGamePanel().addComponent(ball);
//		}
		
		
	}
	
	
	public void setBackground()
	{
		String imagePath = windowFrame.getFormPanel().fileExplorer();
		windowFrame.getGamePanel().setImage(imagePath);
		
	}
}
