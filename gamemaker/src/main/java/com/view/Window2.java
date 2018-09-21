package com.view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.infrastructure.Constants;
import com.infrastructure.IComposite;

public class Window2 extends JFrame implements IComposite {
	private ArrayList<IComposite> compositeList;

	public Window2() {
		this.compositeList = new ArrayList<IComposite>();
		initializeUI();
	}
	
	private void initializeUI() {
//		super("Game Maker");
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}
	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public void addComponent(IComposite composite) {
		// TODO Auto-generated method stub
//		compositeList.add(composite);
//		this.getContentPane().add((JPanel)composite);		
	}

	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		
	}

}
