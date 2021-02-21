package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.time.Duration;
import java.time.Instant;

public class RaceScreen extends BaseScreen
{
    private Message message;
    private Label timeLabel;
    private Spaceship spaceship;
    private boolean stopClock;
    private DialogBox dialogBox;
    private TilemapActor tma;

    public void initialize()
    {
        tma = new TilemapActor("tilemap.tmx", mainStage);

        for (MapObject obj : tma.getTileList("Rock") )
        {
            MapProperties props = obj.getProperties();
            new Rock( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        for (MapObject obj : tma.getTileList("Flag") )
        {
            MapProperties props = obj.getProperties();
            new Flag( (float)props.get("x"), (float)props.get("y"), mainStage );
        }

        createStars();

        spaceship = new Spaceship(340,50, mainStage);

        Table targetTable = new Table();
        targetTable.setFillParent(true);
        targetTable.add().colspan(4).expandY();
        targetTable.row();
        mainStage.addActor(targetTable); 

        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle );
        startButton.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;

                message.displayCountdown();
                startButton.setVisible(false);
                return true;
            }
        );

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

        Texture buttonTex = new Texture( Gdx.files.internal("undo.png") );
        TextureRegion buttonRegion =  new TextureRegion(buttonTex);
        buttonStyle.up = new TextureRegionDrawable( buttonRegion );

        Button restartButton = new Button( buttonStyle );
        restartButton.setColor( Color.CYAN );

        restartButton.addListener(
            (Event e) ->
            {
                InputEvent ie = (InputEvent)e;
                if ( ie.getType().equals(InputEvent.Type.touchDown) )
                {
                    Constants.startTimer = null;
                    RaceGame.setActiveScreen( new RaceScreen() );
                }
                return false;
            }
        );

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setBackgroundColor( Color.LIGHT_GRAY );
        dialogBox.setOpacity(0.75f);
        dialogBox.setFontColor( Color.YELLOW );
        dialogBox.setDialogSize(600, 100);
        dialogBox.setFontScale(1.00f);
        dialogBox.alignCenter();
        dialogBox.setVisible(false);

        timeLabel = new Label("Time: 0", BaseGame.labelStyle);
        timeLabel.setAlignment(Align.right);

        message = new Message(0,0, uiStage);
        message.setOpacity(0);

        uiTable.pad(10);
        uiTable.add(startButton).width(200).left();
        uiTable.add(timeLabel).width(300);
        uiTable.add(restartButton).top();
        uiTable.row();
        uiTable.add(message).colspan(3).expandX().expandY();
        uiTable.row();
        uiTable.add(dialogBox).colspan(3);

    }

    public void update(float dt)
    {
        if (Constants.startTimer != null && !stopClock)
            timeLabel.setText( "Time: " + Duration.between(Constants.startTimer, Instant.now()).getSeconds());

        for (BaseActor rockActor : BaseActor.getList(mainStage, "Rock"))
            spaceship.preventOverlap(rockActor);

        for (BaseActor starActor : BaseActor.getList(mainStage, "Star"))
        {
            Star star = (Star)starActor;
            if ( spaceship.overlaps(star) && !star.collected )
            {
                star.collected = true;
                star.clearActions();
                star.addAction( Actions.fadeOut(1) );
                star.addAction( Actions.run(Constants.TONE::play) );
                star.addAction( Actions.after( Actions.removeActor() ) );

                Whirlpool whirl = new Whirlpool(0,0, mainStage);
                whirl.centerAtActor( star );
                whirl.setOpacity(0.25f);
            }
        }

        if ( BaseActor.count(mainStage, "Star") == 0 )
        {
            for (BaseActor flagActor : BaseActor.getList(mainStage, "Flag"))
            {
                Flag flag = (Flag) flagActor;
                if ( spaceship.overlaps(flag) && !stopClock)
                {
                    stopClock = true;
                    double time = Duration.between(Constants.startTimer, Instant.now()).toMillis() / 1000.0;

                    if (Constants.fastestLap > time)
                        Constants.fastestLap = time;

                    dialogBox.showTime(time);

                    Constants.startTimer = Instant.now();
                    stopClock = false;
                    createStars();
                }
            }
        }
    }

    private void createStars() {
        for (MapObject obj : tma.getTileList("Star") )
        {
            MapProperties props = obj.getProperties();
            new Star( (float)props.get("x"), (float)props.get("y"), mainStage );
        }
    }

    public boolean keyDown(int keycode)
    {
        return false;  
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}