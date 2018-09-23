package com.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.infrastructure.AbstractComponent;
import com.infrastructure.IComposite;
import com.view.WindowFrame;

public class GamePlayController implements IComposite, KeyListener{
	
	private ArrayList<IComposite> actionList;
	private ArrayList<IComposite> eventList;
	private ArrayList<IComposite> collectibleList;
	
	private ArrayList<IComposite> compositeList;
	
	WindowFrame windowFrame;
	
	public GamePlayController(WindowFrame windowFrame) {
		
		this.windowFrame = windowFrame;
		
		actionList = new ArrayList<>();
		eventList = new ArrayList<>();
		collectibleList = new ArrayList<>();
		
		compositeList = windowFrame.getGamePanel().getCompositeList();
		for(IComposite component : compositeList) {
			
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
