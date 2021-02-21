package com.mygdx.game;

public class SpaceGame extends BaseGame
{
    public void create() 
    {        
        super.create();
        setActiveScreen( new LevelScreen() );
    }
}