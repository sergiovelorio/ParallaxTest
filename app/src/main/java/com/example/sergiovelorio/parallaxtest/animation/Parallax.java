package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.Game;

public class Parallax extends Game {
    Layers layers;

    public Parallax(Layers layers) {
        this.layers = layers;
    }

    @Override
    public void create () {
        this.setScreen(new ParallaxScreen(layers));
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