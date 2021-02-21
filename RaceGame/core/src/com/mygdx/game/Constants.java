package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.time.Instant;

public class Constants {

    public static Instant startTimer;

    public static double fastestLap = Double.MAX_VALUE;

    public static final Sound BLIP = Gdx.audio.newSound(Gdx.files.internal("blip.wav"));
    public static final Sound TONE = Gdx.audio.newSound(Gdx.files.internal("tone.wav"));

}
