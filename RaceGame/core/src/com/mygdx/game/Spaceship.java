package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spaceship extends BaseActor
{
    private Thrusters thrusters;

    public Spaceship(float x, float y, Stage s)
    {
        super(x,y,s);

        loadTexture( "spaceship.png" );
        setBoundaryPolygon(8);

        setScale(.6f);
        setAcceleration(400);
        setMaxSpeed(200);
        setDeceleration(100);

        thrusters = new Thrusters(0,0, s);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(), 
            getHeight()/2 - thrusters.getHeight()/2 );
    }

    public void act(float dt)
    {
        super.act( dt );

        if (Constants.startTimer == null)
            dt = 0;

        float degreesPerSecond = 120; // degrees per second
        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            rotateBy(degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            rotateBy(-degreesPerSecond * dt);

        if (Gdx.input.isKeyPressed(Keys.UP)) 
        {
            accelerateAtAngle( getRotation() );
            thrusters.setVisible(true);
        }
        else
        {
            thrusters.setVisible(false);
        }

        applyPhysics(dt);

        alignCamera();
    }

}