package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.view.WindowFrame;

public class GameMakerController implements ActionListener {
	
	private String prevObjName = null;
	private WindowFrame windowFrame;

	public GameMakerController(WindowFrame window)
	{
		this.windowFrame = windowFrame;
	}

	
	public void createObject(String objName, int x, int y, int velX, int velY, int width, int height)
	{
		
	}


	public void actionPerformed(ActionEvent arg0) {
		
	}

}
