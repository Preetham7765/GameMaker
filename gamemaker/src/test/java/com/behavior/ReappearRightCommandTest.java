package com.behavior;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.commands.*;
import com.infrastructure.*;




public class ReappearRightCommandTest {
	
	@Test
	public void leftRepeatComponentTest() {
	
	ObjectProperties properties = new ObjectProperties();
	AbstractComponent component = new AbstractComponent(properties);
	ReappearLeftCommand command = new ReappearLeftCommand(component);
	command.execute();
	assertEquals(0,component.getX());	
	}

}
