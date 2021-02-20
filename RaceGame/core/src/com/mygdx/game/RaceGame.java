package com.mygdx.game;

public class RaceGame extends BaseGame
{
	public void create()
	{
		super.create();
		setActiveScreen( new RaceScreen() );
	}
}
