package com.mygdx.game;

public class StarfishGame extends BaseGame
{
    public void create() 
    {        
        super.create();

        setActiveScreen( new MenuScreen() );
    }
}