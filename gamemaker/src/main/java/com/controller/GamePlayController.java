package com.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.infrastructure.AbstractComponent;
import com.infrastructure.ComponentType;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectListType;
import com.view.WindowFrame;

public class GamePlayController implements IComposite, KeyListener{
	
	private ArrayList<AbstractComponent> actionList;
	private ArrayList<AbstractComponent> eventList;
	private ArrayList<AbstractComponent> collectibleList;
	
	private ArrayList<AbstractComponent> compositeList;
	
	WindowFrame windowFrame;
	
	public GamePlayController(WindowFrame windowFrame) {
		
		this.windowFrame = windowFrame;
		
		actionList = new ArrayList<>();
		eventList = new ArrayList<>();
		collectibleList = new ArrayList<>();
		
		compositeList = windowFrame.getGamePanel().getComponentList();
		for(AbstractComponent abstractComponent : compositeList) {
			ObjectListType objectListType = abstractComponent.getObjectProperties().getObjectListType();
			
			if(objectListType == ObjectListType.ACTION) {
				actionList.add(abstractComponent);
			} else if(objectListType == ObjectListType.EVENT) {
				eventList.add(abstractComponent);
			}else if(objectListType == ObjectListType.COLLECTIBLE) {
				collectibleList.add(abstractComponent);
			}
					
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