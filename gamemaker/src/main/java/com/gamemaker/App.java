package com.gamemaker;

import java.awt.FlowLayout;

import javax.swing.SwingUtilities;

import com.controller.GameMakerController;
import com.infrastructure.Constants;
import com.view.FormPanel;
import com.view.MainPanel;
import com.view.Window1;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static void makeGame() {
		Window1 window1 = new Window1();
		System.out.println("Window is created");

//		window1.setSize(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
//		window1.getContentPane().setLayout(new FlowLayout());

		MainPanel mainPanel = new MainPanel();
		System.out.println("MainPanel is created");
		window1.addComponent(mainPanel);
		
		FormPanel formPanel = new FormPanel();
		System.out.println("FormPanel is created");
		mainPanel.addComponent(formPanel);
		System.out.println("Form panel added to mainpanel");
		
		GameMakerController controller = new GameMakerController();
		System.out.println("Game maker controller created");
//		window1.getContentPane().setVisible(false);
		System.out.println(window1.getComponent(0).getClass());
		window1.setVisible(true);
		window1.pack();

		
	}
    
	public static void main( String[] args )
    {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				makeGame();	
			}
		});
    }
}
