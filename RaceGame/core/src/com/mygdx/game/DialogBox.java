package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class DialogBox extends BaseActor
{
    private final Label dialogLabel;
    private final float padding = 16;

    public DialogBox(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("dialog-translucent.png");

        dialogLabel = new Label(" ", BaseGame.labelStyle);
        dialogLabel.setWrap(true);
        dialogLabel.setAlignment( Align.topLeft );
        dialogLabel.setPosition( padding, padding );
        this.setDialogSize( getWidth(), getHeight() );
        this.addActor(dialogLabel);
    }

    public void showTime(double time) {
        Action showTime =
            Actions.sequence(
                Actions.run( () -> this.setText("Your time was: " + time + " seconds!")),
                Actions.run( () -> this.setVisible(true) ),
                Actions.delay(2f),
                Actions.run( () -> this.setVisible(false) ),
                Actions.delay(5f),
                Actions.run( () -> this.setText("Fastest lap is: " + Constants.fastestLap + " seconds!")),
                Actions.run( () -> this.setVisible(true) ),
                Actions.delay(2f),
                Actions.run( () -> this.setVisible(false) )
                );

        addAction(showTime);
    }

    public void setDialogSize(float width, float height)
    {
        this.setSize(width, height);
        dialogLabel.setWidth( width - 2 * padding );
        dialogLabel.setHeight( height - 2 * padding );
    }
    
    public void setText(String text)
    {  dialogLabel.setText(text);  }

    public void setFontScale(float scale)
    {  dialogLabel.setFontScale(scale);  }

    public void setFontColor(Color color)
    {  dialogLabel.setColor(color);  }

    public void setBackgroundColor(Color color)
    {  this.setColor(color);  }
    
    public void alignTopLeft()
    {  dialogLabel.setAlignment( Align.topLeft );  }
    
    public void alignCenter()
    {  dialogLabel.setAlignment( Align.center );  }
}