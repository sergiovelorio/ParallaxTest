package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.example.sergiovelorio.parallaxtest.util.Constants;

import java.util.Iterator;

public class MainMenuScreen implements Screen {
    final SpaceGame game;
    public Input i;
    private boolean readingCheersFlag = false;

    public MainMenuScreen (final SpaceGame game) {
        this.game = game;

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
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.scrollingBackground.updateAndRender(Constants.GLOBAL, game.batch, delta);
        game.batch.end();
    }

    @Override public void resize (int width, int height) {}
    @Override public void dispose() {}
    @Override public void resume() {}
    @Override public void pause() {}
    @Override public void show() {}
    @Override public void hide() {}

}