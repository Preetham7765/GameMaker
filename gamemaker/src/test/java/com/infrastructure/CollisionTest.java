package com.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CollisionTest {

	@Mock
	AbstractComponent component1;
	
	@Mock
	AbstractComponent component2;
	
	@InjectMocks
	Collision collision;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		when(component1.getShape()).thenReturn(ComponentShape.RECTANGLE);
		when(component1.getX()).thenReturn(10);
		when(component1.getY()).thenReturn(10);
		when(component1.getWidth()).thenReturn(20);
		when(component1.getHeight()).thenReturn(20);
		when(component2.getX()).thenReturn(15);
		when(component2.getY()).thenReturn(15);
		when(component2.getWidth()).thenReturn(10);
		when(component2.getHeight()).thenReturn(10);
	}
	
	@Test
	void testCollision() {
		Collision collision = new Collision();
		assertEquals(Direction.X, collision.checkCollisionBetweenAbstractComponents(component1, component2));
	}

}
