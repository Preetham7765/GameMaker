package com.gamemaker;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.controller.GameMakerController;
import com.controller.GamePlayController;
import com.infrastructure.Constants;
import com.observable.GameTimer;
import com.view.FormPanel;
import com.view.GamePanel;
import com.view.MainPanel;
import com.view.WindowFrame;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static void makeGame() {
		GameTimer gameTimer = new GameTimer();
		
		WindowFrame windowFrame = new WindowFrame();

		MainPanel mainPanel = new MainPanel();
		System.out.println("MainPanel is created");
		windowFrame.addComponent(mainPanel);
		
		FormPanel formPanel = new FormPanel(windowFrame);
//		windowFrame.setFormPanel(formPanel);
		System.out.println("FormPanel is created");
		mainPanel.addComponent(formPanel);

		System.out.println("Form panel added to mainpanel");
		
		GamePanel gamePanel = new GamePanel();
		mainPanel.addComponent(gamePanel);
		windowFrame.setGamePanel(gamePanel);
		
		GameMakerController gmController = new GameMakerController(windowFrame);
		System.out.println("Game maker controller created");
		
		GamePlayController gpController = new GamePlayController(windowFrame);
		System.out.println("Game maker controller created");
		
		windowFrame.setFormPanel(formPanel);
		windowFrame.setGamePanel(gamePanel);
		formPanel.createButtons(gmController);
		gamePanel.addControllerListener(gmController);

		windowFrame.setVisible(true);
		windowFrame.pack();
//		formPanel.createButt?ons(controller);

	}
    
	public static void main( String[] args )
    {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				makeGame();
//				JFrame jf = new JFrame();
//				jf.setVisible(true);
//				new Window1();
			}
		});
    }
}
