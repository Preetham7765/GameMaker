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
	public final static String ROTATEABLE = "Rotateable";
	public final static String KEY_DEPENDENT = "Move using Keys";
	public final static String TIME_DEPENDENT = "Move with Time";
	public final static String ADD_ELEMENT = "Add Element";
	public final static String VEL_X = "Velocity X";
	public final static String VEL_Y = "Velocity Y";
	public final static String WIDTH = "Width";
	public final static String HEIGHT = "Height";
	public final static String ELEMENT_TYPE = "Element Type";
	public final static String ELEMENT_NAME = "Element Name";
	//public final static String COLLECTIBLE = "Collectible";
	
	public final static String LEFT_KEY = "Left";
	public final static String RIGHT_KEY = "Right";
	public final static String UP_KEY = "Up";
	public final static String DOWN_KEY = "Down";
	public final static String SPACE = "Space";
	public final static String FREE = "Move freely";
	
	public final static String SELECT_KEY = "Select Key";
	public final static String MOVE_LEFT = "Move Left";
	public final static String MOVE_RIGHT = "Move Right";
	public final static String MOVE_UP = "Move Up";
	public final static String MOVE_DOWN = "Move Down";
	public final static String EXPLODE = "Explode";
	public final static String FIRE = "Fire";
	
	public final static String COLLISION = "Add Collision";
	public final static String PRIMARY_ELE = "Element One";
	public final static String SECONDARY_ELE = "Element Two";
	public final static String PRIMARY_ACT = "Element One Action";
	public final static String SECONDARY_ACT = "Element Two Action";
	public final static String SOUND = "Sound";

	public final static String COLOR = "Color";
	public final static String BACKGOUND = "Background";
	public final static String MOVEMENT = "Movement";
	public final static String IMAGE = "Image";
	
	public final static String COLOR_SEL = "Select Color";
	public final static String IMAGE_SEL = "Browse Image";
}