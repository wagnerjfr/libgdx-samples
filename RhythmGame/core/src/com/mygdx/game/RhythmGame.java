package com.mygdx.game;

public class RhythmGame extends BaseGame
{
    public void create() 
    {        
        super.create();
        setActiveScreen( new RhythmScreen() );
    }
}