package com.gamemaker;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.components.Clock;
import com.controller.GameMakerController;
import com.controller.GamePlayController;
import com.infrastructure.ObjectProperties;
import com.observable.GameTimer;
import com.view.FormPanel;
import com.view.GamePanel;
import com.view.MainPanel;
import com.view.StaticPanel;
import com.view.WindowFrame;

public class App {

	public static Logger logger = LogManager.getLogger(App.class);

	public static void makeGame() {

		GameTimer gameTimer = new GameTimer();
		gameTimer.startTimer();

		/*String[] gameChoice = { "Game Maker", "Game Play" };

		JPanel game = new JPanel();
		game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
		JComboBox<String> typeList = new JComboBox<>(gameChoice);
		game.add(typeList);

		int result = JOptionPane.showConfirmDialog(null, game, "Choose Now:", JOptionPane.OK_CANCEL_OPTION);

		// get the selected item:
		String selectType = (String) typeList.getSelectedItem();*/

		//if (selectType == "Game Maker" && result == JOptionPane.OK_OPTION)
		{

			WindowFrame windowFrame = new WindowFrame();

			MainPanel mainPanel = new MainPanel();
			System.out.println("MainPanel is created");
			windowFrame.addComponent(mainPanel);

			FormPanel formPanel = new FormPanel(windowFrame);
			System.out.println("FormPanel is created");
			mainPanel.addComponent(formPanel);

			System.out.println(formPanel.getBackground());

			System.out.println("Form panel added to mainpanel");

			GamePanel gamePanel = new GamePanel();
			mainPanel.addComponent(gamePanel);
			windowFrame.setGamePanel(gamePanel);

			GameMakerController gmController = new GameMakerController(windowFrame, gameTimer);
			System.out.println("Game maker controller created");

			GamePlayController gpController = new GamePlayController(windowFrame, gameTimer, gmController);
			System.out.println("Game maker controller created");

			gmController.setGamePlayController(gpController);
			formPanel.initializeFormPanel();

			gamePanel.addControllerListener(gmController);

			windowFrame.addActionListener(gmController);

			windowFrame.setVisible(true);
			windowFrame.pack();

		} /*else if (selectType == "Game Play" && result == JOptionPane.OK_OPTION) {
			ObjectProperties objectProperties = new ObjectProperties();
			objectProperties.setX(20);
			objectProperties.setY(50);

			WindowFrame windowFrame = new WindowFrame();

			MainPanel mainPanel = new MainPanel();
			windowFrame.addComponent(mainPanel);

			StaticPanel staticPanel = new StaticPanel(windowFrame);
			Clock clock = new Clock(objectProperties);
			try 
			{
				staticPanel.addComponent(clock);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}

			mainPanel.addComponent(staticPanel);

			GamePanel gamePanel = new GamePanel();
			mainPanel.addComponent(gamePanel);
			mainPanel.addComponent(staticPanel);
			windowFrame.setGamePanel(gamePanel);
			windowFrame.setVisible(true);
			windowFrame.pack();
		}*/
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				makeGame();
			}
		});
	}
}
