package com.gamemaker;

import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.controller.GameMakerController;
import com.controller.GamePlayController;
import com.observable.GameTimer;
import com.view.FormPanel;
import com.view.GamePanel;
import com.view.MainPanel;
import com.view.WindowFrame;

public class App {

	protected static Logger logger = LogManager.getLogger(App.class);

	public static void makeGame() {

		GameTimer gameTimer = new GameTimer();
		gameTimer.startTimer();

		{
			WindowFrame windowFrame = new WindowFrame();

			MainPanel mainPanel = new MainPanel();
			windowFrame.addComponent(mainPanel);

			FormPanel formPanel = new FormPanel(windowFrame);
			mainPanel.addComponent(formPanel);

			GamePanel gamePanel = new GamePanel();
			mainPanel.addComponent(gamePanel);
			windowFrame.setGamePanel(gamePanel);

			GameMakerController gmController = new GameMakerController(windowFrame, gameTimer);

			GamePlayController gpController = new GamePlayController(windowFrame, gameTimer, gmController);

			gmController.setGamePlayController(gpController);
			formPanel.initializeFormPanel();

			gamePanel.addControllerListener(gmController);

			windowFrame.addActionListener(gmController);

			windowFrame.setVisible(true);
			windowFrame.pack();

		} 
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				makeGame();
			}
		});
	}
}
