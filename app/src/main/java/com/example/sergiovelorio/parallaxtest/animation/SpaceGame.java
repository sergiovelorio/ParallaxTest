package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.sergiovelorio.parallaxtest.util.Constants;

public class SpaceGame extends Game {

    public SpriteBatch batch;
    public ScrollingBackground scrollingBackground;
    public MainMenuScreen mms;
    public int index = 0;

    public SpaceGame() {}


    @Override
    public void create () {
        batch = new SpriteBatch();
        this.scrollingBackground = new ScrollingBackground(Constants.am, index);
        this.mms = new MainMenuScreen(this);
        this.setScreen(this.mms);
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
    public void dispose() {
        try{
            super.dispose();
            if(batch != null){
                batch.dispose();
                batch = null;
            }
            if(Constants.am != null){
                Constants.am.clear();
                Constants.am.dispose();
                Constants.am = null;
            }
            scrollingBackground = null;
        }catch(Exception e){}
    }

    public void unload(){
        if(Constants.am != null)
            Constants.am.clear();
    }
}