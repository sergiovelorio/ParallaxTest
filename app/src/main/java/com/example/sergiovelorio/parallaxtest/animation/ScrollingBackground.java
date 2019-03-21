package com.example.sergiovelorio.parallaxtest.animation;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.example.sergiovelorio.parallaxtest.util.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;


public class ScrollingBackground {

    ArrayList<Tex_ture> layer1 = new ArrayList<>();
    ArrayList<Tex_ture> layer2 = new ArrayList<>();
    ArrayList<Tex_ture> layer3 = new ArrayList<>();
    ArrayList<Tex_ture> layer4 = new ArrayList<>();
    ArrayList<Tex_ture> layerB = new ArrayList<>();
    ArrayList<Tex_ture> layerT = new ArrayList<>();
    float imageScale;
    public int scenes;
    boolean loadingStatic;
    BitmapFont bmf;

    Hashtable<String, Boolean> toLoad = new Hashtable<String, Boolean>();
    Hashtable<String, Boolean> queued = new Hashtable<String, Boolean>();
    Set<String> keys;

    AssetManager am;

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    float y1;
    int speed;
    int goalSpeed;
    boolean speedFixed;

    ArrayList<String> lma1 = new ArrayList<>();
    ArrayList<String> lma2 = new ArrayList<>();
    ArrayList<String> lma3 = new ArrayList<>();
    ArrayList<String> lma4 = new ArrayList<>();
    ArrayList<String> statics = new ArrayList<>();
    ArrayList<String> backgrounds = new ArrayList<>();
    ArrayList<String> transitions = new ArrayList<>();

    private Animation<TextureRegion> animation;


    int index;

    int aux = 0;

    public ScrollingBackground (AssetManager am, int index) {

        this.am = am;
        loadingStatic = false;

        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto/Roboto-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Constants.BASE_SIZE * 569 / 100 / 2;
        bmf = generator.generateFont(parameter);
        generator.dispose();*/

        this.index = index;

        y1 = 0;
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        speedFixed = true;
        backgrounds.add(0, "l8.png");
        backgrounds.add(1, "l8.png");
        backgrounds.add(2, "l8.png");
        backgrounds.add(3, "l8.png");
        toLoad.put("l8.png", false);
        queued.put("l8.png", false);
        statics.add(0, "l7.png");
        lma1.add(0, "l1.png");
        lma2.add(0, "l2.png");
        lma3.add(0, "l3.png");
        lma4.add(0, "l4.png");
        lma1.add(1, "l1.png");
        lma2.add(1, "l2.png");
        lma3.add(1, "l3.png");
        lma4.add(1, "l4.png");
        lma1.add(2, "l1.png");
        lma2.add(2, "l2.png");
        lma3.add(2, "l3.png");
        lma4.add(2, "l4.png");
        lma1.add(3, "l1.png");
        lma2.add(3, "l2.png");
        lma3.add(3, "l3.png");
        lma4.add(3, "l4.png");
        toLoad.put("l1.png", false);
        toLoad.put("l2.png", false);
        toLoad.put("l3.png", false);
        toLoad.put("l4.png", false);
        queued.put("l1.png", false);
        queued.put("l2.png", false);
        queued.put("l3.png", false);
        queued.put("l4.png", false);


        imageScale = 1;
        scenes = lma1.size()/4;

        for(int i=0; i<lma1.size()*8; i++)
            layer1.add(new Tex_ture((lma1.get(0)),i*Constants.SCREEN_HEIGHT,lma1.size()*8,false));

        for(int i=0; i<lma2.size()*4; i++)
            layer2.add(new Tex_ture((lma2.get(0)),i*Constants.SCREEN_HEIGHT,lma2.size()*4,false));

        for(int i=0; i<lma3.size()*2; i++)
            layer3.add(new Tex_ture((lma3.get(0)),i*Constants.SCREEN_HEIGHT,lma3.size()*2,false));

        for(int i=0; i<lma4.size(); i++)
            layer4.add(new Tex_ture((lma4.get(0)),i*Constants.SCREEN_HEIGHT,lma4.size(),false));

        for(int i=0; i<backgrounds.size(); i++)
            layerB.add(new Tex_ture((backgrounds.get(0)),i*Constants.SCREEN_HEIGHT,backgrounds.size(),false));

        for(int i=0; i<transitions.size(); i++)
            layerT.add(new Tex_ture((transitions.get(0)),((i + 1)*Constants.SCREEN_HEIGHT*8)-Constants.SCREEN_HEIGHT/2,lma3.size()*2,true));


    }

    public void updateAndRender (float globalR, SpriteBatch batch, float deltaTime) {
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        am.update();

        for (Hashtable.Entry<String, Boolean> entry : toLoad.entrySet())
            entry.setValue(false);

        for(int i = 0; i < layer1.size(); i++)
            toLoad.put(layer1.get(i).i, toLoad.get(layer1.get(i).i)|layer1.get(i).onScreen(globalR));

        for(int i = 0; i < layer2.size(); i++)
            toLoad.put(layer2.get(i).i, toLoad.get(layer2.get(i).i)|layer2.get(i).onScreen(globalR/2));

        for(int i = 0; i < layer3.size(); i++)
            toLoad.put(layer3.get(i).i, toLoad.get(layer3.get(i).i)|layer3.get(i).onScreen(globalR/4));

        for(int i = 0; i < layer4.size(); i++)
            toLoad.put(layer4.get(i).i, toLoad.get(layer4.get(i).i)|layer4.get(i).onScreen(globalR/8));

        for(int i = 0; i < layerB.size(); i++)
            toLoad.put(layerB.get(i).i, toLoad.get(layerB.get(i).i)|layerB.get(i).onScreen(globalR/8));

        keys = toLoad.keySet();

        aux = 0;

        for(String key: keys){
            if(toLoad.get(key)) {
                aux++;
                if(!am.isLoaded(key, Texture.class)){
                    if(!queued.get(key)){
                        am.load(key, Texture.class);
                        queued.put(key, true);
                    }
                }
            }
            else{
                if(am.isLoaded(key)){
                    am.unload(key);
                    queued.put(key, false);
                }
            }
        }

        for(int i = 0; i < layerB.size(); i++)
            layerB.get(i).draw(globalR/8,batch,1);

        if(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))%statics.size() < statics.size()){
            if(!am.isLoaded((statics.get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))%statics.size())), Texture.class)){
                if(am.getQueuedAssets() == 0){
                    am.load((statics.get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))%statics.size())), Texture.class);
                }
            }
            else{
                try {
                    batch.draw(am.get((statics.get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))%statics.size())), Texture.class), 0, Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        for(int i = 0; i < layer4.size(); i++)
            layer4.get(i).draw(globalR/8,batch,1);

        for(int i = 0; i < layer3.size(); i++)
            layer3.get(i).draw(globalR/4,batch,1);

        for(int i = 0; i < layer2.size(); i++)
            layer2.get(i).draw(globalR/2,batch,1);

        for(int i = 0; i < layer1.size(); i++)
            layer1.get(i).draw(globalR,batch,1);
    }

    /*private Animation<TextureRegion> getConfettiAnimation() {
        ConfettiAnimation animation = new ConfettiAnimation();
        return animation.generateConffetiAnimation();
    }*/

    public class Tex_ture {
        final String i;
        final float x;
        int b;
        float d;
        int c;
        boolean t;

        public Tex_ture(String i, float x, int b, boolean t){
            this.i = i;
            this.x = x;
            this.b = b;
            this.d = 0;
            this.c = 0;
            this.t = t;
        }

        public void draw(float global, SpriteBatch batch, float imageMod) {
            if (am.isLoaded(i, Texture.class)) {
                batch.draw(am.get(i, Texture.class), x + d + global, 0, Constants.SCREEN_HEIGHT*imageMod, Constants.SCREEN_HEIGHT*imageMod);
            }
        }

        public boolean onScreen(float global){
            c = (int)(-(global) /(b*Constants.SCREEN_HEIGHT));

            if(!t){
                if(-global > b*Constants.SCREEN_HEIGHT*(0.5f+c) & x + d < b*Constants.SCREEN_HEIGHT*(0.5f+c)){
                    d = b*Constants.SCREEN_HEIGHT + b*Constants.SCREEN_HEIGHT*c;
                }
                else{
                    if(x > 0)
                        d = b*Constants.SCREEN_HEIGHT*c;
                }
            } else{
                d = b*Constants.SCREEN_HEIGHT*c;
            }

            return (x + d + global <= Constants.SCREEN_WIDTH + Constants.SCREEN_HEIGHT & x + d + global + Constants.SCREEN_HEIGHT >= -Constants.SCREEN_HEIGHT);
        }
    }
}