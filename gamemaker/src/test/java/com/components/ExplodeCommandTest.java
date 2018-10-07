package com.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.commands.Command;
import com.commands.ExplodeCommand;
import com.infrastructure.AbstractComponent;

public class ExplodeCommandTest {

	@Test
	public void executeTest() {

		AbstractComponent component = Mockito.mock(AbstractComponent.class);
		Command explode = new ExplodeCommand(component);
		explode.execute();
		// Mockito.verify(component).setVisbility(true);
		assertEquals(false, component.getVisibility());
	}

}
