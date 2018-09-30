package com.view;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.infrastructure.ComponentType;
import com.infrastructure.ObjectListType;
import com.infrastructure.ObjectProperties;

public class ObjectPropertiesPanel extends JPanel {

	private ObjectProperties active;

	private JTextField vXField;
	private JTextField vYField;
	private JTextField widthField;
	private JTextField heightField;
	private JCheckBox canCollectField;

	private JRadioButton collectible;
	private JRadioButton event;
	private JRadioButton action;
	private ButtonGroup group;
	private int result;

	private void createFormElements() {

		vXField = new JTextField(Integer.toString(active.getVelX()), 5);
		vYField = new JTextField(Integer.toString(active.getVelY()), 5);
		widthField = new JTextField(Integer.toString(active.getWidth()), 5);
		heightField = new JTextField(Integer.toString(active.getHeight()), 5);
		canCollectField = new JCheckBox("Can collect the collectibles");
		collectible = new JRadioButton("Collectible");
		event = new JRadioButton("Player object");
		action = new JRadioButton("Game Object");
		group = new ButtonGroup();

	}
	
	public ObjectPropertiesPanel(ComponentType type) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		active = new ObjectProperties();
		active.setComponentType(type);
		
		createFormElements();
		addElements();
		result = JOptionPane.showConfirmDialog(null, this, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);
	
	}

	private void addElements() {

		this.add(Box.createVerticalStrut(15)); // a spacer
		this.add(new JLabel("Velocity X: "));
		this.add(vXField);
		this.add(Box.createVerticalStrut(15)); // a spacer
		this.add(new JLabel("Velocity Y: "));
		this.add(vYField);
		this.add(Box.createVerticalStrut(15)); // a spacer
		this.add(new JLabel("Width: "));
		this.add(widthField);
		this.add(Box.createVerticalStrut(15)); // a spacer
		this.add(new JLabel("Height: "));
		this.add(heightField);
		this.add(canCollectField);
		this.add(event);
		this.add(Box.createHorizontalStrut(15));
		this.add(action);
		this.add(Box.createHorizontalStrut(15));
		this.add(collectible);

	}
	
	public ObjectProperties getProperties() {
		
		if (result == JOptionPane.OK_OPTION) {
			active.setVelX(Integer.parseInt(vXField.getText()));
			active.setVelY(Integer.parseInt(vYField.getText()));
			active.setWidth(Integer.parseInt(widthField.getText()));
			active.setHeight(Integer.parseInt(heightField.getText()));
			active.setCanCollect(canCollectField.isSelected());
			int n = 1;
			for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); n++) {
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) {
					String text = button.getText();
					if (text.equals("Collectible")) {
						active.setObjectListType(ObjectListType.COLLECTIBLE);
						// System.out.println("Game maker controller can collect = here ");

					} else if (text.equals("Player object")) {
						active.setObjectListType(ObjectListType.EVENT);

					} else if (text.equals("Game Object")) {
						active.setObjectListType(ObjectListType.ACTION);

					}
				}
			}
		}
		return active;
	}
	
}
