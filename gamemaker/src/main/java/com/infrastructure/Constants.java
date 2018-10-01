package com.infrastructure;

public interface Constants {

	//---------------------------/components/Ball.java-------------------------
	
	public static final int velX = 1;
	public static final int velY = 1 ;
	public static final int ballRadius = 40 ;
	
	//---------------------------/components/Fire.java-------------------------
	
	public static final String fireImagePath = "./icons/fire.png";

	public final static int FRAME_WIDTH = 1300;
	public final static int  FRAME_HEIGHT = 900;
	public final static int  FORM_PANEL_HEIGHT = 900;
	public final static int  FORM_PANEL_WIDTH = 400;
	public final static int  GAME_PANEL_WIDTH = 900;
	public final static int  GAME_PANEL_HEIGHT = 900;
	
	//----------------------------/observable/GameTimer.java/------------------
	public final static int TIMER_COUNT = 25;
	
	public final static String COLLECTIBLE = "Collectible";
	public final static String KEY_DEPENDENT = "Move using Keys";
	public final static String TIME_DEPENDENT = "Move with Time";
	public final static String ADD_ELEMENT = "Add Element";
	public final static String VEL_X = "Velocity X";
	public final static String VEL_Y = "Velocity Y";
	public final static String WIDTH = "Width";
	public final static String HEIGHT = "Height";
	public final static String ELEMENT_TYPE = "Element Type";
	//public final static String COLLECTIBLE = "Collectible";

}