package com.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.controller.GameMakerController;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.ObjectProperties;
import com.observable.GameTimer;
import com.view.FormView;
import com.view.GamePanel;
import com.view.WindowFrame;

class GameMakerControllerTest {
	@Mock
	AbstractComponent component;
	
	@InjectMocks
	GameMakerController gameMakerController;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddComponent() {
		when(gameMakerController.createAbstractComponent()).thenReturn(component);
		
	}
	
}
