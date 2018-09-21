package com.infrastructure;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface IComposite {
	
	public void draw(Graphics g);
	public void addComponent(IComposite composite);
	public void removeComponent(IComposite composite);
	

}
