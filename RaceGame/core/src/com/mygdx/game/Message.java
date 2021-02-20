package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.time.Instant;

public class Message extends BaseActor
{
    private final Animation<TextureRegion> countdown3;
    private final Animation<TextureRegion> countdown2;
    private final Animation<TextureRegion> countdown1;
    private final Animation<TextureRegion> countdownGo;

    public Message(float x, float y, Stage s)
    {
        super(x,y,s);

        countdown3 = loadTexture("countdown-3.png");
        countdown2 = loadTexture("countdown-2.png");
        countdown1 = loadTexture("countdown-1.png");
        countdownGo = loadTexture("countdown-go.png");
    }

    public void displayCountdown()
    {
        Action countdown =
            Actions.sequence(
                Actions.run( () -> setAnimation(countdown3) ),
                Actions.run(Constants.BLIP::play),
                Actions.alpha(1),
                Actions.scaleTo(1.1f,1.1f, 0.05f), Actions.scaleTo(1.0f,1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run( () -> setAnimation(countdown2) ),
                Actions.run(Constants.BLIP::play),
                Actions.alpha(1),
                Actions.scaleTo(1.1f,1.1f, 0.05f), Actions.scaleTo(1.0f,1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run( () -> setAnimation(countdown1) ),
                Actions.run(Constants.BLIP::play),
                Actions.alpha(1),
                Actions.scaleTo(1.1f,1.1f, 0.05f), Actions.scaleTo(1.0f,1.0f, 0.05f),
                Actions.delay(0.5f), Actions.fadeOut(0.4f),
                Actions.run( () -> setAnimation(countdownGo) ),
                Actions.run(Constants.TONE::play),
                Actions.run(() -> Constants.startTimer = Instant.now()),
                Actions.alpha(1),
                Actions.fadeOut(1)
                );

        addAction(countdown);
    }
}