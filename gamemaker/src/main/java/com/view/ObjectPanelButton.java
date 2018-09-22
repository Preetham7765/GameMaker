package com.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton implements ActionListener {
	String name;
	private WindowFrame windowFrame;
	private ObjectProperties selected = new ObjectProperties();
//	public HashMap<String, Object> selected = new HashMap<String, Object>();
//	public ObjectProperties selected = null;
	
	JTextField vXField;
	JTextField vYField;
	JTextField widthField;
	JTextField heightField;
	JCheckBox canCollectField;

	
	JCheckBox collectible;
	JRadioButton event;
	ButtonGroup group;
	
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
		canCollectField = new JCheckBox("Can collect the collectibles");
		
		
		collectible=new JCheckBox("Collectible");
		event=new JRadioButton("Player object");
		group=new ButtonGroup();
		
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

			myPanel.add(canCollectField);
			
			myPanel.add(event);
			myPanel.add(Box.createHorizontalStrut(15));
			
			myPanel.add(collectible);
	     
			int result1 = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      
			if (result1 == JOptionPane.OK_OPTION) {
				selected.setVelX(Integer.parseInt(vXField.getText()));
				selected.setVelY(Integer.parseInt(vYField.getText()));
				selected.setWidth(Integer.parseInt(widthField.getText()));
				selected.setHeight(Integer.parseInt(heightField.getText()));
				selected.setCanCollect(canCollectField.isSelected());
				selected.setCollectible(collectible.isSelected());
				selected.setEvent(event.isSelected());
				
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
