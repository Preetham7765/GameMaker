package com.behavior;



import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.commands.*;
import com.infrastructure.*;



public class ReappearLeftCommandTest {
	
	@Test
	public void leftRepeatComponentTest() {
	
	AbstractComponent component = mock(AbstractComponent.class);
	ReappearLeftCommand command = new ReappearLeftCommand(component);
	command.execute();
	verify(component).setX(0);
	//assertEquals(Constants.GAME_PANEL_WIDTH,component.getX());	
	}

}
