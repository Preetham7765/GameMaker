package com.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.infrastructure.Constants;
import com.infrastructure.ElementType;

public class ObjectPropertiesPanel extends JPanel {

	private FormView formData;
	private ElementType elementType;
	private JTextField vXField;
	private JTextField vYField;
	private JTextField widthField;
	private JTextField heightField;
	private JCheckBox collectible;
	private JRadioButton keyDependent;
	private JRadioButton timeDependent;
	private JComboBox elementTypes;
	private ButtonGroup group;
	private int result;
	
	/*private JCheckBox left = new JCheckBox(Constants.LEFT_KEY);
	private JCheckBox right = new JCheckBox(Constants.RIGHT_KEY);
	private JCheckBox up = new JCheckBox(Constants.UP_KEY);
	private JCheckBox down = new JCheckBox(Constants.DOWN_KEY);
	private JCheckBox space = new JCheckBox(Constants.SPACE);*/
	
	
	private void createFormElements() {

		vXField = new JTextField(Integer.toString(formData.getVelX()), 7);
		vYField = new JTextField(Integer.toString(formData.getVelY()), 7);
		widthField = new JTextField(Integer.toString(formData.getWidth()), 7);
		heightField = new JTextField(Integer.toString(formData.getHeight()), 7);
		collectible = new JCheckBox(Constants.COLLECTIBLE);
		keyDependent = new JRadioButton(Constants.KEY_DEPENDENT);
		timeDependent = new JRadioButton(Constants.TIME_DEPENDENT);		
		Object[] elements=ElementType.values();
		elementTypes=new JComboBox(elements);
		
		group = new ButtonGroup();
	}
	
	public ObjectPropertiesPanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		formData = new FormView();
		createFormElements();
		addElements();
		/*result = JOptionPane.showConfirmDialog(null, this, "Please Enter X and Y Values",
				JOptionPane.OK_CANCEL_OPTION);*/
		Object[] options = { "OK" };
		 result = JOptionPane.showOptionDialog(null, this,
				Constants.ADD_ELEMENT, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);
	}

	private void addElements() {

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		c.insets = new Insets(5, 10, 10, 10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel(Constants.ELEMENT_TYPE),c);
		
		c.gridx = 1;
		c.gridy = 0;
		this.add(elementTypes,c);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(new JLabel(Constants.VEL_X),c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(vXField,c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(new JLabel(Constants.VEL_Y),c);
		
		c.gridx = 1;
		c.gridy = 2;
		this.add(vYField,c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(new JLabel(Constants.WIDTH),c);
		
		c.gridx = 1;
		c.gridy = 3;
		this.add(widthField,c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.add(new JLabel(Constants.HEIGHT),c);
		
		c.gridx = 1;
		c.gridy = 4;
		this.add(heightField,c);
		
		c.gridx = 0;
		c.gridy = 5;
		this.add(collectible,c);
		
		c.gridx = 0;
		c.gridy = 6;
		this.add(keyDependent,c);
		
		c.gridx = 1;
		c.gridy = 6;
		this.add(timeDependent,c);
	}
	
	public FormView getProperties() {
		
		if (result == JOptionPane.OK_OPTION) {
			formData.setVelX(Integer.parseInt(vXField.getText()));
			formData.setVelY(Integer.parseInt(vYField.getText()));
			formData.setWidth(Integer.parseInt(widthField.getText()));
			formData.setHeight(Integer.parseInt(heightField.getText()));
			formData.setCollectible(collectible.isSelected());
			//active.setCanCollect(canCollectField.isSelected());
		/*	int n = 1;
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
			}*/
		}
		return formData;
	}
	
}
