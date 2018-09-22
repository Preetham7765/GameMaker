package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton implements ActionListener {
	private String width ;
	private String height ;
	private Color color;
	String name;
	private WindowFrame windowFrame;
	private ObjectProperties selected = new ObjectProperties();
//	public HashMap<String, Object> selected = new HashMap<String, Object>();
//	public ObjectProperties selected = null;
	
	JTextField vXField;
	JTextField vYField;
	JTextField widthField;
	JTextField heightField;
	
	
	public ObjectPanelButton(String name, Color yellow, WindowFrame windowFrame) {
		this.name = name;
		setText(name);
		selected.setType(name);
		setActionCommand(name);
		addActionListener(this);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		this.windowFrame = windowFrame;
		this.windowFrame.getFormPanel().selected = selected;
		vXField = new JTextField(Integer.toString(selected.getVelX()) ,5);
		vYField = new JTextField(Integer.toString(selected.getVelY()) ,5);
	    widthField = new JTextField(Integer.toString(selected.getWidth()), 5);
		heightField = new JTextField(Integer.toString(selected.getHeight()) ,5);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(name == "Background")
		{
			setBackground();
		}
		else
		{
			JPanel myPanel = new JPanel();
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(new JLabel("Velocity X: "));
			myPanel.add(vXField);
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(new JLabel("Velocity Y: "));
			myPanel.add(vYField);
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(new JLabel("Width: "));
			myPanel.add(widthField);
			myPanel.add(Box.createVerticalStrut(15)); // a spacer
			myPanel.add(new JLabel("Height: "));
			myPanel.add(heightField);
	     
			int result1 = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      
			if (result1 == JOptionPane.OK_OPTION) {
				selected.setVelX(Integer.parseInt(vXField.getText()));
				selected.setVelY(Integer.parseInt(vYField.getText()));
				selected.setWidth(Integer.parseInt(widthField.getText()));
				selected.setHeight(Integer.parseInt(heightField.getText()));
				windowFrame.getFormPanel().setSelected(selected);
	      	}
		}
	}
	
	public void setBackground()
	{
		String imagePath = windowFrame.getFormPanel().fileExplorer();
		windowFrame.getGamePanel().setImage(imagePath);
	}
}
