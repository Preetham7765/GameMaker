package com.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

	private JCheckBox left;
	private JCheckBox right;
	private JCheckBox up;
	private JCheckBox down;
	private JCheckBox space;
	
	private JCheckBox leftMove;
	private JCheckBox rightMove;
	private JCheckBox upMove;
	private JCheckBox downMove;
	private JCheckBox spaceMove;

	private JComboBox leftCombo;
	private JComboBox rightCombo;
	private	JComboBox upCombo;
	private	JComboBox downCombo;
	private	JComboBox spaceCombo;
	
	private ButtonGroup radioGroup;

	private JPanel keyPanel;

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

		//keyPanel = new JPanel();

		left = new JCheckBox(Constants.LEFT_KEY);
		right = new JCheckBox(Constants.RIGHT_KEY);
		up = new JCheckBox(Constants.UP_KEY);
		down = new JCheckBox(Constants.DOWN_KEY);
		space = new JCheckBox(Constants.SPACE);
		
		leftMove = new JCheckBox(Constants.LEFT_KEY);
		rightMove = new JCheckBox(Constants.RIGHT_KEY);
		upMove = new JCheckBox(Constants.UP_KEY);
		downMove = new JCheckBox(Constants.DOWN_KEY);
		spaceMove = new JCheckBox(Constants.SPACE);
		
		Object[] keyActions = { Constants.SELECT_KEY, Constants.MOVE_LEFT, Constants.MOVE_RIGHT, Constants.MOVE_UP,
				Constants.MOVE_DOWN, Constants.EXPLODE , Constants.FIRE};
		leftCombo = new JComboBox(keyActions);
		rightCombo = new JComboBox(keyActions);
		upCombo = new JComboBox(keyActions);
		downCombo = new JComboBox(keyActions);
		spaceCombo = new JComboBox(keyActions);

		radioGroup = new ButtonGroup();
	}

	public ObjectPropertiesPanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		formData = new FormView();
		createFormElements();
		addElements();
		Object[] options = { "OK" };
		result = JOptionPane.showOptionDialog(null, this,
				Constants.ADD_ELEMENT, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);
	}

	private JPanel addPropertiesPanel()
	{
		JPanel properties=new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		properties.setLayout(gridbag);
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		properties.add(new JLabel(Constants.ELEMENT_TYPE),c);

		c.gridx = 1;
		c.gridy = 0;
		properties.add(elementTypes,c);

		c.gridx = 0;
		c.gridy = 1;
		properties.add(new JLabel(Constants.VEL_X),c);

		c.gridx = 1;
		c.gridy = 1;
		properties.add(vXField,c);

		c.gridx = 0;
		c.gridy = 2;
		properties.add(new JLabel(Constants.VEL_Y),c);

		c.gridx = 1;
		c.gridy = 2;
		properties.add(vYField,c);

		c.gridx = 0;
		c.gridy = 3;
		properties.add(new JLabel(Constants.WIDTH),c);

		c.gridx = 1;
		c.gridy = 3;
		properties.add(widthField,c);

		c.gridx = 0;
		c.gridy = 4;
		properties.add(new JLabel(Constants.HEIGHT),c);

		c.gridx = 1;
		c.gridy = 4;
		properties.add(heightField,c);

		c.gridx = 0;
		c.gridy = 5;
		properties.add(collectible,c);

		c.gridx = 0;
		c.gridy = 6;
		properties.add(keyDependent,c);

		c.gridx = 4;
		c.gridy = 6;
		properties.add(timeDependent,c);
		
		radioGroup.add(keyDependent);
		radioGroup.add(timeDependent);
		
		c.gridx = 0;
		c.gridy = 7;
		properties.add(left,c);

		c.gridx = 1;
		c.gridy = 7;
		properties.add(leftCombo,c);

		c.gridx = 0;
		c.gridy = 8;
		properties.add(right,c);

		c.gridx = 1;
		c.gridy = 8;
		properties.add(rightCombo,c);

		c.gridx = 0;
		c.gridy = 9;
		properties.add(up,c);

		c.gridx = 1;
		c.gridy = 9;
		properties.add(upCombo,c);

		c.gridx = 0;
		c.gridy = 10;
		properties.add(down,c);

		c.gridx = 1;
		c.gridy = 10;
		properties.add(downCombo,c);

		c.gridx = 0;
		c.gridy = 11;
		properties.add(space,c);

		c.gridx = 1;
		c.gridy = 11;
		properties.add(spaceCombo,c);
		
		c.gridx = 4;
		c.gridy = 7;
		properties.add(leftMove,c);
		
		c.gridx = 4;
		c.gridy = 8;
		properties.add(rightMove,c);
		
		c.gridx = 4;
		c.gridy = 9;
		properties.add(upMove,c);
		
		c.gridx = 4;
		c.gridy = 10;
		properties.add(downMove,c);
		
		disableKeyElements();
		keyDependent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(keyDependent.isSelected())
				{
					enableKeyElements();
					disableTimeElements();
				}
				else
				{
					disableKeyElements();
					enableTimeElements();
				}
			}
		});
		
		disableTimeElements();
		timeDependent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timeDependent.isSelected())
				{
					enableTimeElements();
					disableKeyElements();
				}
				else
				{
					disableTimeElements();
					enableKeyElements();
				}
			}
		});

		return properties;
	}

	private void addElements() 
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;

		this.add(addPropertiesPanel(), c);		

	}
	
	public void disableKeyElements()
	{
		left.setEnabled(false);
		right.setEnabled(false);
		up.setEnabled(false);
		down.setEnabled(false);
		space.setEnabled(false);
		leftCombo.setEnabled(false);
		rightCombo.setEnabled(false);
		upCombo.setEnabled(false);
		downCombo.setEnabled(false);
		spaceCombo.setEnabled(false);
	}
	
	public void enableKeyElements()
	{
		left.setEnabled(true);
		right.setEnabled(true);
		up.setEnabled(true);
		down.setEnabled(true);
		space.setEnabled(true);
		leftCombo.setEnabled(true);
		rightCombo.setEnabled(true);
		upCombo.setEnabled(true);
		downCombo.setEnabled(true);
		spaceCombo.setEnabled(true);
	}
	
	public void enableTimeElements()
	{
		leftMove.setEnabled(true);
		rightMove.setEnabled(true);
		upMove.setEnabled(true);
		downMove.setEnabled(true);
	}

	public void disableTimeElements()
	{
		leftMove.setEnabled(false);
		rightMove.setEnabled(false);
		upMove.setEnabled(false);
		downMove.setEnabled(false);
	}

	/*public void disablePanel(JPanel panel) {
		System.out.println("Disabled panel");
		Component[] com = panel.getComponents();

		for (int a = 0; a < com.length; a++) {
			com[a].setEnabled(false);
		}
	}

	public void enablePanel(JPanel panel) {
		System.out.println("Enabled panel");

		Component[] com = panel.getComponents();

		for (int a = 0; a < com.length; a++) {
			com[a].setEnabled(true);
		}
	}*/
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
