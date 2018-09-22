package com.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.infrastructure.IComposite;
import com.infrastructure.IPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements IComposite, IPanel {

	private ArrayList<IComposite> compositeList;
	
	public MainPanel() {
		super();
		setBorder( BorderFactory.createLineBorder(Color.red));
		this.compositeList = new ArrayList<IComposite>();
		setLayout(new FlowLayout());
		setFocusable(true);
		requestFocusInWindow();
	}

	public void draw(Graphics g) {
		for(IComposite composite : compositeList) {
			composite.draw(g);
		}	
	}

	public Rectangle getBounds() {
		return null;
	}

	public ArrayList<IComposite> getCompositeList() {
		return compositeList;
	}

	public void addComponent(IComposite composite) {
		compositeList.add(composite);
		this.add((JPanel)composite);
	}

	public void removeComponent(IComposite composite) {
		compositeList.remove(composite);
	}

}