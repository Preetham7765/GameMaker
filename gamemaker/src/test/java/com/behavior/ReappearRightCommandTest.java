package com.behavior;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.commands.ReappearRightCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.ObjectProperties;

class ReappearRightCommandTest {

	@Test
	void executeTest() {
		AbstractComponent component = Mockito.mock(AbstractComponent.class);
		ReappearRightCommand command = new ReappearRightCommand(component);
		command.execute();
		verify(component,times(1)).setX(Constants.GAME_PANEL_WIDTH);
	}

}
