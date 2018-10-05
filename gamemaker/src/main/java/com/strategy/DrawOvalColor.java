package com.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawOvalColor implements Drawable{

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(component.getColor());
		g2d.fill(new Ellipse2D.Double(component.getX(), component.getY(), component.getWidth(), component.getHeight()));
	}
}
