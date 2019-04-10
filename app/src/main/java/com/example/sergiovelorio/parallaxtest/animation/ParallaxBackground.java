package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.Game;

public class ParallaxBackground extends Game {

    public ParallaxBackground() {}

    @Override
    public void create () {
        this.setScreen(new ParallaxScreen());
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {}
}