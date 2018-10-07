package com.view;

import java.awt.Color;

import javax.swing.JButton;

import com.infrastructure.ComponentType;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton {

	public ObjectPanelButton(ComponentType componentType, Color yellow) {

		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);

		if (componentType == ComponentType.ELEMENT)
			setText("Element");

		if (componentType == ComponentType.COLLISION)
			setText("Collision");

		// added by cbrahme
		if (componentType == ComponentType.STATIC)
			setText("Static Component");

		if (componentType == ComponentType.BACKGROUND)
			setText("Background");

		if (componentType == ComponentType.SAVE)
			setText("Save");

		if (componentType == ComponentType.LOAD)
			setText("Load");

		if (componentType == ComponentType.PLAY)
			setText("Play");

	}

	public void setBackground() {
		//windowFrame.getGamePanel().setImgPath(imgPath);
		//windowFrame.getGamePanel().setImage(imagePath);
	}
}
