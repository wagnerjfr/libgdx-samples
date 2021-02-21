package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Flag extends BaseActor
{
    public boolean collected;

    public Flag(float x, float y, Stage s)
    {
       super(x,y,s);
        
       loadTexture("flag.png");

       setBoundaryPolygon(8);
       
       collected = false;
    }
    
}