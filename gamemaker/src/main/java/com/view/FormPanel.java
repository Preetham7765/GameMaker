
package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controller.GameMakerController;
import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.IAddActionListener;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite, IAddActionListener {

	private ObjectProperties active;
	private List<ObjectPanelButton> objectButtons; // should we need it lets see?
	private String backgroundPath;
	private GameMakerController controller;
	GridBagConstraints c = new GridBagConstraints();

	public FormPanel(WindowFrame window) {
		super();
		this.objectButtons = new ArrayList<>();
		this.active = new ObjectProperties();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		//setBackground( new Color(0xFFFFCC));
	}

	public ObjectProperties getActive() {
		return active;
	}

	public void setSelected(ObjectProperties selected) {
		this.active = selected;
	}

	public void initializeFormPanel() {

		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
		c.insets = new Insets(20, 20, 20, 20);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.weightx=1;
		
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=2;
		JLabel designGame=new JLabel("DESIGNER");
		designGame.setFont(new Font("Helvetica", Font.BOLD,20));
		designGame.setHorizontalAlignment(JLabel.CENTER);
		this.add(designGame, c);
		
		c.gridwidth=1;
		c.gridx=0;
		c.gridy=1;
		this.add(createSetBackgroundButton(), c);

		c.gridx=0;
		c.gridy=2;
		this.add(createButton(),c);
		
		c.gridx=1;
		c.gridy=2;
		this.add(createCollisionButton(),c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		JLabel playGame=new JLabel("PLAYER");
		playGame.setFont(new Font("Helvetica", Font.BOLD,20));
		playGame.setHorizontalAlignment(JLabel.CENTER);
		this.add(playGame, c);

		c.gridx=0;
		c.gridy=4;
		c.gridwidth=1;
		this.add(createLoadButton(),c);
		
		c.gridx=0;
		c.gridy=5;
		this.add(createSaveButton(),c);
		
		c.gridx=0;
		c.gridy=6;
		this.add(createPlayButton(),c);
	}

	public ObjectProperties getActiveObjectProperties() {
		return active;
	}

	public ObjectPanelButton createSetBackgroundButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.BACKGROUND, null);
		this.objectButtons.add(button);
		button.setPreferredSize(new Dimension(150, 50));
		return button;
	}

	private ObjectPanelButton createButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.ELEMENT, Color.YELLOW);
		this.objectButtons.add(button);
		button.setPreferredSize(new Dimension(150, 50));
		return button;
	}

	private ObjectPanelButton createCollisionButton() {
		ObjectPanelButton collisionButton = new ObjectPanelButton(ComponentType.COLLISION, Color.YELLOW);
		this.objectButtons.add(collisionButton);
		collisionButton.setPreferredSize(new Dimension(150, 50));
		return collisionButton;
	}

	private ObjectPanelButton createLoadButton() {
		ObjectPanelButton loadButton = new ObjectPanelButton(ComponentType.LOAD, Color.CYAN);
		loadButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(loadButton);
		return loadButton;
	}

	private ObjectPanelButton createSaveButton() {
		ObjectPanelButton saveButton = new ObjectPanelButton(ComponentType.SAVE, Color.BLUE);
		saveButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(saveButton);
		return saveButton;
	}

	private ObjectPanelButton createPlayButton() {
		ObjectPanelButton playButton = new ObjectPanelButton(ComponentType.PLAY, Color.GREEN);
		playButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(playButton);
		return playButton;
	}

	public void draw(Graphics g) {
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackground(String background) {
		this.backgroundPath = background;
	}

	@Override
	public void save(ObjectOutputStream op) {
	}

	@Override
	public void load(ObjectInputStream ip) {
	}

	@Override
	public void addActionListener(ActionListener listener) {
		for (ObjectPanelButton button : objectButtons) {
			button.addActionListener(listener);
		}
	}
}
