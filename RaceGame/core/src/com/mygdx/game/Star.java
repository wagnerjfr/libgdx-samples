package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Star extends BaseActor
{
    public boolean collected;
    
    public Star(float x, float y, Stage s)
    {
       super(x,y,s);
        
       loadTexture("star.png");
       setScale(0.4f);
       
       Action spin = Actions.rotateBy(30, 1);
       this.addAction( Actions.forever(spin) );
       
       setBoundaryPolygon(8);
       
       collected = false;
    }

}