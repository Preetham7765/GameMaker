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
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.IPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements IComposite, IPanel	 {

	private ArrayList<IComposite> compositeList;
	private BufferedImage image;

	public GamePanel() {
		compositeList = new ArrayList<>();
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));	
	}
	
	public void addControllerListener(GameMakerController controller) {
		addMouseListener(controller);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image != null)
		{
			g.drawImage(image, 0, 0, this);
		}
		System.out.println("Length of composite list: " + compositeList.size());
		for(IComposite composite : compositeList) {
			System.out.println(composite);
			composite.draw(g);
		}		
	}
	
	public void draw(Graphics g) {
        repaint();
	}

	public void addComponent(IComposite composite) {
		compositeList.add(composite);
	}

	public void removeComponent(IComposite composite) {
		compositeList.remove(composite);
	}
	
	public void setImage(String path)
	{
		try {
			image = ImageIO.read(new File(path));
			image = resize(image, Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT);
			draw(null);
			
		} catch (IOException e) {
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
	
//	@Override
//	public void paint(Graphics g)
//	{
//		super.paint(g);
//		if(image != null)
//		{
//			g.drawImage(image, 0, 0, this);
//		}
//	}

}