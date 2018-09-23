package com.components;

import java.awt.image.BufferedImage;

import com.infrastructure.ObjectProperties;

public class FireTest {
	

	Fire fire;
	ObjectProperties objectProperties;
	
	public FireTest()
	{
		objectProperties=new ObjectProperties();
		objectProperties.setCanCollect(true);
		objectProperties.setCollectible(true);
		objectProperties.setEvent(true);
		objectProperties.setHeight(10);
		objectProperties.setType("Fire");
		objectProperties.setWidth(40);
		objectProperties.setX(120);
		objectProperties.setY(30);
		
		fire = new Fire(objectProperties);
	}
	
	public void setImageTest()
	{
		BufferedImage image;
		//fire.setImage(image);
	}
}
