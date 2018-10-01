package com.view;

import java.awt.Color;

import javax.swing.JButton;

import com.infrastructure.ComponentType;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton {

	//	private String name;
	//	private WindowFrame windowFrame

	public ObjectPanelButton(ComponentType componentType, Color yellow) {

		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		
		if (componentType == ComponentType.ELEMENT)
			setText("ELEMENT");

		/*if (componentType == ComponentType.BALL)
			setText("Ball");

		if (componentType == ComponentType.BRICK)
			setText("Brick");

		if (componentType == ComponentType.PADDLE)
			setText("Paddle");

		if (componentType == ComponentType.CLOCK)
			setText("Clock");

		if (componentType == ComponentType.FIRE)
			setText("Fire");
*/
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
		//		String imagePath = windowFrame.getFormPanel().fileExplorer();
		//		windowFrame.getGamePanel().setImage(imagePath);
	}
}
