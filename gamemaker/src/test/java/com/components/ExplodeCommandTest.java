package com.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.commands.Command;
import com.commands.ExplodeCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.ObjectProperties;



public class ExplodeCommandTest {
	
	@Test
	public void executeTest() {
		
		ObjectProperties properties = Mockito.mock(ObjectProperties.class);
		AbstractComponent component = new AbstractComponent(properties);
		when(component.getVisibility()).thenReturn(true);
		Command explode = new ExplodeCommand(component);
		explode.execute();
		assertEquals(false,component.getVisibility());		
	}


}










