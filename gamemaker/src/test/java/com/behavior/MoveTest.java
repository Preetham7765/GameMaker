package com.behavior;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.commands.MoveCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.ObjectProperties;

@TestInstance(Lifecycle.PER_CLASS)
public class MoveTest {
	
	@Mock
	ObjectProperties properties;
	
	@InjectMocks @Spy
	AbstractComponent component;
	
	@InjectMocks
	MoveCommand moveCommand;
	
	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		moveCommand = new MoveCommand(component);
	}
	
	@Test
	public void testExecute() {	
		when(component.getX()).thenReturn(1);
		when(component.getY()).thenReturn(1);
		when(component.getVelX()).thenReturn(2);
		when(component.getVelY()).thenReturn(2);
		System.out.println(component.getVelX());
		moveCommand.execute();
		System.out.println(moveCommand.abstractComponent.getVelX());
		assertEquals(3, component.getX());
		assertEquals(3, component.getY());
	}
}
