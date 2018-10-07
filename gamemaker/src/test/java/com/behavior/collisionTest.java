package com.behavior;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Collision;
import com.infrastructure.ComponentShape;
import com.infrastructure.Direction;

public class collisionTest {
	

	@Test
	public void executeTest1() {
		

	AbstractComponent component1 = Mockito.mock(AbstractComponent.class);
	AbstractComponent component2 = Mockito.mock(AbstractComponent.class);
	when(component1.getShape()).thenReturn(ComponentShape.RECTANGLE);
	when(component1.getX()).thenReturn(10);
	when(component1.getY()).thenReturn(10);
	when(component1.getWidth()).thenReturn(20);
	when(component1.getHeight()).thenReturn(20);
	when(component2.getX()).thenReturn(15);
	when(component2.getY()).thenReturn(15);
	when(component2.getWidth()).thenReturn(10);
	when(component2.getHeight()).thenReturn(10);
	
	Collision collision = new Collision();
	Direction d =collision.checkCollisionBetweenAbstractComponents(component1, component2);
	assertEquals(Direction.X,d);
	}

}
