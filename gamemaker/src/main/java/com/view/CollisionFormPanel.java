package com.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.infrastructure.Collider;
import com.infrastructure.CollisionType;
import com.infrastructure.Constants;
import com.infrastructure.ElementType;

public class CollisionFormPanel extends JPanel
{
	private FormView formData;
	private ColliderData colliderData;
	private Object[] dataList;
	private JComboBox primaryElement;
	private JComboBox secondaryElement;
	private JComboBox primaryAction;
	private JComboBox secondaryAction;
	
	private JButton addSound;
	private JButton addCollision;
	
	private Object[] collisionData;
	
	private ArrayList<Collider> collidersList;
	 
	private int result;
	
	public CollisionFormPanel(Object[] dataList, ArrayList<Collider> colliders) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.dataList = dataList;
		this.collidersList=colliders;
		System.out.println("vri "+colliders);
		colliderData = new ColliderData();
		createFormElements();
		//addElements();
		this.add(addCollisionPanel());
		Object[] options = { "OK" };
		result = JOptionPane.showOptionDialog(null, this,
				Constants.COLLISION, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);
	}
	
	public void createFormElements() {
		
		//Object[] data=dataList;
		primaryElement = new JComboBox(dataList);
		secondaryElement = new JComboBox(dataList);
		
		collisionData = CollisionType.values();
		primaryAction = new JComboBox(collisionData);
		secondaryAction = new JComboBox(collisionData);
		
		//addSound = new JButton("Add Sound");
		//addCollision = new JButton("Add Sound");
	}
	
	/*private void addElements() {
		this.add(addCollisionPanel());
	}*/
	
	private JPanel addCollisionPanel() {
		JPanel collision=new JPanel();
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		collision.setLayout(gridbag);
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth=1;
		
		c.gridx = 0;
		c.gridy = 0;
		collision.add(new JLabel(Constants.PRIMARY_ELE),c);
		
		c.gridx = 1;
		c.gridy = 0;
		collision.add(new JLabel(Constants.SECONDARY_ELE),c);
		
		c.gridx = 2;
		c.gridy = 0;
		collision.add(new JLabel(Constants.PRIMARY_ACT),c);
		
		c.gridx = 3;
		c.gridy = 0;
		collision.add(new JLabel(Constants.SECONDARY_ACT),c);
		
		/*c.gridx = 4;
		c.gridy = 0;
		collision.add(new JLabel(Constants.SOUND),c);*/
		
		int i=0;
		int row=1;
		
		if(!collidersList.isEmpty())
		{
			for(i=0;i<collidersList.size();i++)
			{
				Collider collider=collidersList.get(i);
				
				c.gridx = 0;
				c.gridy = row;
				JComboBox priEle = new JComboBox(dataList);
				priEle.setSelectedItem(collider.getPrimaryComponent().getComponentName());
				collision.add(priEle,c);
				
				c.gridx = 1;
				c.gridy = row;
				JComboBox secEle = new JComboBox(dataList);
				secEle.setSelectedItem(collider.getSecondaryComponent().getComponentName());
				collision.add(secEle,c);
				
				c.gridx = 2;
				c.gridy = row;
				JComboBox priAct = new JComboBox(collisionData);
				priAct.setSelectedItem(collider.getPrimaryCollisionType());
				collision.add(priAct,c);
				
				c.gridx = 3;
				c.gridy = row;
				JComboBox secAct = new JComboBox(collisionData);
				secAct.setSelectedItem(collider.getSecondaryCollisionType());
				collision.add(secAct,c);
				
				row++;
			}
		}
		

		c.gridx = 0;
		c.gridy = row;
		collision.add(primaryElement,c);
		
		c.gridx = 1;
		c.gridy = row;
		collision.add(secondaryElement,c);
		
		c.gridx = 2;
		c.gridy = row;
		collision.add(primaryAction,c);
		
		c.gridx = 3;
		c.gridy = row;
		collision.add(secondaryAction,c);
		
		/*c.gridx = 4;
		c.gridy = 1;
		collision.add(addSound,c);
		
		c.gridx = 2;
		c.gridy = 4;
		collision.add(addCollision,c);*/
		
		return collision;
	}
	
	public ColliderData getProperties() {
		if (result == JOptionPane.OK_OPTION) {
			colliderData.setPrimaryElement((String)primaryElement.getSelectedItem());
			colliderData.setSecondaryElement((String)secondaryElement.getSelectedItem());
			colliderData.setPrimaryAct((CollisionType)primaryAction.getSelectedItem());
			colliderData.setSecondaryAct((CollisionType)secondaryAction.getSelectedItem());
		}
		return colliderData;
	}
 
}