package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements IComposite {

	private ArrayList<IComposite> compositeList;
	private BufferedImage image;

	public GamePanel() {
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));		
	}
	
	public void draw(Graphics g) {
		for(IComposite composite : compositeList) {
			composite.draw(g);
		}	
	}

	public void addComponent(IComposite composite) {
		// TODO Auto-generated method stub
		compositeList.add(composite);
	}

	public void removeComponent(IComposite composite) {
		// TODO Auto-generated method stub
		compositeList.remove(composite);
	}
	
	public void setImage(String path)
	{
		try {
			image = ImageIO.read(new File(path));
			image = resize(image, Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage resize(BufferedImage img, int width, int height)
	{
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(image != null)
		{
			g.drawImage(image, 0, 0, this);
		}
	}

}
