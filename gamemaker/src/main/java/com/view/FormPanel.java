package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.HashMap;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite {
	
	public HashMap<String, Object> selected = new HashMap<String, Object>();
	
	
	public HashMap<String, Object> getSelected() {
		return selected;
	}

	public void setSelected(HashMap<String, Object> selected) {
		this.selected = selected;
	}

	private GameMakerController controller;
	private WindowFrame windowFrame;
	
	public FormPanel(WindowFrame window) {
		
		super();
		this.windowFrame = window;
		setBorder( BorderFactory.createLineBorder(Color.black));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		
		
		
	}
	
	public String fileExplorer()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		System.out.println("Return NULL");
		return null;
		
	}
	


	public void createButtons(GameMakerController controller) {
//		this.controller = controller;
		
		JLabel backgroundText = new JLabel("Choose your background");
		this.add(backgroundText);
		createSetBackgroundButton();

		JLabel select_object = new JLabel("Select any object to add to GamePanel");
		this.add(select_object);
		createBallButton();
		createBrickButton();
		createPaddleButton();
		createFireButton();
	}
	
	private void createSetBackgroundButton() {
		ObjectPanelButton setBackgroundButton = new ObjectPanelButton("Background", null, windowFrame);
		this.add(setBackgroundButton);
	}

	private void createFireButton() {
		ObjectPanelButton fireButton = new ObjectPanelButton("Fire", Color.YELLOW, windowFrame);
		this.add(fireButton);
	}

	private void createPaddleButton() {
		ObjectPanelButton paddleButton = new ObjectPanelButton("Paddle", Color.red, windowFrame );
		this.add(paddleButton);
	}

	private void createBrickButton() {
		ObjectPanelButton brickButton = new ObjectPanelButton("Brick", Color.blue, windowFrame);
		this.add(brickButton);		
	}

	private void createBallButton() {
		ObjectPanelButton ballButton = new ObjectPanelButton("Ball", Color.BLACK, windowFrame);
		this.add(ballButton);				
	}

	public void draw(Graphics g) {
	}

	public void addComponent(IComposite composite) {
		return;
	}

	public void removeComponent(IComposite composite) {
		return;
	}
	
	public static ObjectProperties savePopUp() {
		ObjectProperties objProp = new ObjectProperties();
		 JTextField xField = new JTextField(Integer.toString(objProp.x) ,5);
	     JTextField yField = new JTextField(Integer.toString(objProp.y) ,5);
	     JTextField vXField = new JTextField(Integer.toString(objProp.velX) ,5);
		 JTextField vYField = new JTextField(Integer.toString(objProp.velY) ,5);
	     JTextField width = new JTextField(Integer.toString(objProp.width), 5);
		 JTextField height = new JTextField(Integer.toString(objProp.height) ,5);

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("x: "));
	      myPanel.add(xField);
	      myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("y: "));
	      myPanel.add(yField);
	      myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Velocity X: "));
	      myPanel.add(vXField);
	      myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Velocity Y: "));
	      myPanel.add(vYField);
	      myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Width: "));
	      myPanel.add(width);
	      myPanel.add(Box.createVerticalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Height: "));
	      myPanel.add(height);
	      
	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  objProp.x = Integer.parseInt(xField.getText());
	    	  objProp.y = Integer.parseInt(yField.getText());
	    	  objProp.velX = Integer.parseInt(vXField.getText());
	    	  objProp.velY = Integer.parseInt(vYField.getText());
	    	  objProp.width = Integer.parseInt(width.getText());
	    	  objProp.height = Integer.parseInt(height.getText());
	    }
	      return(objProp);
	}

}
