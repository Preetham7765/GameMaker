package com.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.infrastructure.ComponentType;
import com.infrastructure.ObjectListType;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton {
	
//	private String name;
//	private WindowFrame windowFrame
	
	public ObjectPanelButton(ComponentType componentType, Color yellow) {

		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		
		if(componentType == ComponentType.BALL)
			setText("Ball");
		
		if(componentType == ComponentType.BRICK)
			setText("Brick");
		
		if(componentType == ComponentType.PADDLE)
			setText("Paddle");
		
		if(componentType == ComponentType.CLOCK)
			setText("Clock");
		
		if(componentType == ComponentType.FIRE)
			setText("Fire");
		
		if(componentType == ComponentType.BACKGROUND)
			setText("Background");
		
		if(componentType == ComponentType.SAVE)
			setText("Save");
		
		if(componentType == ComponentType.LOAD)
			setText("Load");
		
		if(componentType == ComponentType.PLAY)
			setText("Play");

	}


	public void setBackground()
	{
//		String imagePath = windowFrame.getFormPanel().fileExplorer();
//		windowFrame.getGamePanel().setImage(imagePath);
	}
}
