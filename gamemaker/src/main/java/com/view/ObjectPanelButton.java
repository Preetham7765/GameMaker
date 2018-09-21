package com.view;

import java.awt.Dimension;

import javax.swing.JButton;

import com.controller.GameMakerController;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton {
	private int width = 100;
	private int height = 30;
	public ObjectPanelButton(String name, GameMakerController controller) {
		setText(name);
		setActionCommand(name);
		addActionListener(controller);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}
}
