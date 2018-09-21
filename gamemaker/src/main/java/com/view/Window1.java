package com.view;

//import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class Window1 extends JFrame implements IComposite {
	private ArrayList<IComposite> compositeList;
	
	public Window1() {
		super();
		this.compositeList = new ArrayList<IComposite>();
		initializeUI();
	}

	private void initializeUI() {
		setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public void setVisible() {
		super.setVisible(true);
	}
	
	public void draw(Graphics g) {
		for(IComposite composite : compositeList) {
			composite.draw(g);
		}
	}

	public ArrayList<IComposite> getCompositeList() {
		return compositeList;
	}
	
	public void addComponent(IComposite composite) {
		compositeList.add(composite);
		this.add((JPanel)composite);
	}

	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		compositeList.remove(composite);
	}
}
