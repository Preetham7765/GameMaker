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

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton implements ActionListener {
	private String width ;
	private String height ;
	private Color color;
	String name;
	ObjectProperties objProp = new ObjectProperties();
	private FormPanel formPanel;
	public HashMap<String, Object> selected = new HashMap<String, Object>();
	
	JTextField vXField;
	JTextField vYField;
	JTextField widthField;
	JTextField heightField;
	
	
	public ObjectPanelButton(String name, Color yellow, FormPanel formPanel) {
		this.name=name;
		this.width=width;
		this.height=height;
		setText(name);
		setActionCommand(name);
		addActionListener(this);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		this.formPanel = formPanel;
		   vXField = new JTextField(Integer.toString(objProp.velX) ,5);
			  vYField = new JTextField(Integer.toString(objProp.velY) ,5);
		      widthField = new JTextField(Integer.toString(objProp.width), 5);
			  heightField = new JTextField(Integer.toString(objProp.height) ,5);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		

	   

		
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
			  objProp.velX = Integer.parseInt(vXField.getText());
			  objProp.velY = Integer.parseInt(vYField.getText());
			  objProp.width = Integer.parseInt(widthField.getText());
			  objProp.height = Integer.parseInt(heightField.getText());
			  selected = new HashMap<String, Object>();
			selected.put("Type", name);
			selected.put("Color", color);
			selected.put("Width", objProp.width);
			selected.put("Height", objProp.height);
			selected.put("velX", objProp.velX);
			selected.put("velY", objProp.velY);	    	
			formPanel.setSelected(selected);
	      }
	}
}
