package com.infrastructure;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface IComposite {
	
	public void draw(Graphics g);
	public Rectangle getBounds();
	 void addComponent(IComposite composite);
	 void removeComponent(IComposite composite);
	

}
