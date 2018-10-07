package com.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Direction;
import com.infrastructure.Drawable;
import com.infrastructure.ObjectProperties;
import com.strategy.DrawRectColor;

class AbstractComponentTest {

	ObjectProperties objectProperties;
	AbstractComponent component;

	@BeforeEach
	void setup() {
		objectProperties = new ObjectProperties();
		component = Mockito.mock(AbstractComponent.class);
		component.setObjectProperties(objectProperties);

		when(component.getX()).thenReturn(100);
		when(component.getY()).thenReturn(100);
		when(component.getVelX()).thenReturn(5);
		when(component.getVelY()).thenReturn(5);
		when(component.getWidth()).thenReturn(20);
		when(component.getHeight()).thenReturn(20);
	}

	@Test
	void coordinateTest() {
		assertEquals(100, component.getX());
		assertEquals(100, component.getY());
	}

	@Test
	void dimensionsTest() {
		assertEquals(20, component.getWidth());
		assertEquals(20, component.getHeight());
	}

	@Test
	void velocityTest() {
		assertEquals(5, component.getVelX());
		assertEquals(5, component.getVelY());
	}

	@Test
	void visibilityTest() {
		when(component.getVisibility()).thenReturn(false);
		assertEquals(false, component.getVisibility());
	}

	@Test
	void directionTest() {
		when(component.getDirection()).thenReturn(Direction.FREE);
		assertEquals(Direction.FREE, component.getDirection());
	}

	@Test
	void drawTest() {
//		Drawable drawable = mock(DrawRectColor.class);
//		ArgumentCaptor<AbstractComponent> argument = ArgumentCaptor.forClass(AbstractComponent.class);
//		Mockito.doNothing().when(drawable).draw(argument.capture(), any(Graphics.class));
//		when(component.getDirection()).thenReturn(Direction.BOTH);
//		drawable.draw(component, any(Graphics.class));
//		verify(drawable).draw(argument.capture(), null);
//		assertEquals(component, argument.getValue());
	}

}
