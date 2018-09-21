package com.gamemaker;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.view.FormPanel;
import com.view.GamePanel;
import com.view.MainPanel;
import com.view.Window1;
import com.view.Window2;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static void makeGame() {
		Window1 window1 = new Window1();

		MainPanel mainPanel = new MainPanel();
		System.out.println("MainPanel is created");
		window1.addComponent(mainPanel);
		
		FormPanel formPanel = new FormPanel();
		System.out.println("FormPanel is created");
		mainPanel.addComponent(formPanel);

		System.out.println("Form panel added to mainpanel");
		
		GamePanel gamePanel = new GamePanel();
		mainPanel.addComponent(gamePanel);
		
		GameMakerController controller = new GameMakerController();
		System.out.println("Game maker controller created");
		
//		window1.getContentPane().setVisible(false);
		window1.setVisible(true);
		window1.pack();
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
