package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.sergiovelorio.parallaxtest.util.Constants;


public class ParallaxScreen implements Screen {
    public SpriteBatch batch;
    public ParallaxBackground scrollingBackground;
    public Input i;

    public ParallaxScreen() {
        batch = new SpriteBatch();
        this.scrollingBackground = new ParallaxBackground(Constants.am);

        i = Gdx.input;
        i.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void render (float delta) {
        batch.begin();
        scrollingBackground.updateAndRender(Constants.GLOBAL, batch);
        batch.end();
    }

    @Override public void resize (int width, int height) {}
    @Override public void dispose() {}
    @Override public void resume() {}
    @Override public void pause() {}
    @Override public void show() {}
    @Override public void hide() {}
}