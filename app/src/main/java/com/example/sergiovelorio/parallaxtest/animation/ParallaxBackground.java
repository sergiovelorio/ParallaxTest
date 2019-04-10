package com.example.sergiovelorio.parallaxtest.animation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.sergiovelorio.parallaxtest.util.Constants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class ParallaxBackground {

    AssetManager am;
    Layers layers;

    ArrayList<_Texture> layer1 = new ArrayList<>();
    ArrayList<_Texture> layer2 = new ArrayList<>();
    ArrayList<_Texture> layer3 = new ArrayList<>();
    ArrayList<_Texture> layer4 = new ArrayList<>();
    ArrayList<_Texture> layerB = new ArrayList<>();
    ArrayList<_Texture> layerT = new ArrayList<>();

    Hashtable<String, Boolean> toLoad = new Hashtable<>();
    Hashtable<String, Boolean> queued = new Hashtable<>();
    Set<String> keys;

    public ParallaxBackground(AssetManager am, Layers layers) {

        this.am = am;
        this.layers = layers;

        ArrayList<String> all_images = layers.all_images();

        for(int i = 0; i < all_images.size(); i++){
            toLoad.put(all_images.get(i), false);
            queued.put(all_images.get(i), false);
        }

        for(int i=0; i<layers.getLayer1().size()*8; i++)
            layer1.add(new _Texture(layers.getLayer1().get(0), i*Constants.SCREEN_HEIGHT,layers.getLayer1().size()*8, false));

        for(int i=0; i<layers.getLayer2().size()*4; i++)
            layer2.add(new _Texture(layers.getLayer2().get(0), i*Constants.SCREEN_HEIGHT,layers.getLayer2().size()*4, false));

        for(int i=0; i<layers.getLayer3().size()*2; i++)
            layer3.add(new _Texture(layers.getLayer3().get(0), i*Constants.SCREEN_HEIGHT,layers.getLayer3().size()*2, false));

        for(int i=0; i<layers.getLayer4().size(); i++)
            layer4.add(new _Texture(layers.getLayer4().get(0), i*Constants.SCREEN_HEIGHT,layers.getLayer4().size(), false));

        for(int i=0; i<layers.getBackgrounds().size(); i++)
            layerB.add(new _Texture(layers.getBackgrounds().get(0),i*Constants.SCREEN_HEIGHT,layers.getBackgrounds().size(), false));

        for(int i=0; i<layers.getTransitions().size(); i++)
            layerT.add(new _Texture(layers.getTransitions().get(0),((i + 1)*Constants.SCREEN_HEIGHT*8)-Constants.SCREEN_HEIGHT/2,layers.getLayer3().size()*2,true));
    }

    public void updateAndRender (float globalR, SpriteBatch batch) {

        am.update();

        for (Hashtable.Entry<String, Boolean> entry : toLoad.entrySet())
            entry.setValue(false);

        for(int i = 0; i < layer1.size(); i++)
            toLoad.put(layer1.get(i).image, toLoad.get(layer1.get(i).image)|layer1.get(i).onScreen(globalR));

        for(int i = 0; i < layer2.size(); i++)
            toLoad.put(layer2.get(i).image, toLoad.get(layer2.get(i).image)|layer2.get(i).onScreen(globalR/2));

        for(int i = 0; i < layer3.size(); i++)
            toLoad.put(layer3.get(i).image, toLoad.get(layer3.get(i).image)|layer3.get(i).onScreen(globalR/4));

        for(int i = 0; i < layer4.size(); i++)
            toLoad.put(layer4.get(i).image, toLoad.get(layer4.get(i).image)|layer4.get(i).onScreen(globalR/8));

        for(int i = 0; i < layerB.size(); i++)
            toLoad.put(layerB.get(i).image, toLoad.get(layerB.get(i).image)|layerB.get(i).onScreen(globalR/8));

        keys = toLoad.keySet();

        for(String key: keys){
            if(toLoad.get(key)) {
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
            layerB.get(i).draw(globalR/8, batch);

        if(layers.getStatics().size() > 0){
            if(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))% layers.getStatics().size() < layers.getStatics().size()){
                if(!am.isLoaded((layers.getStatics().get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))% layers.getStatics().size())), Texture.class)){
                    if(am.getQueuedAssets() == 0){
                        am.load((layers.getStatics().get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))% layers.getStatics().size())), Texture.class);
                    }
                }
                else{
                    try {
                        batch.draw(am.get((layers.getStatics().get(((int) (-globalR/(Constants.SCREEN_HEIGHT *32)))% layers.getStatics().size())), Texture.class), 0, Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        for(int i = 0; i < layer4.size(); i++)
            layer4.get(i).draw(globalR/8, batch);

        for(int i = 0; i < layer3.size(); i++)
            layer3.get(i).draw(globalR/4, batch);

        for(int i = 0; i < layer2.size(); i++)
            layer2.get(i).draw(globalR/2, batch);

        for(int i = 0; i < layer1.size(); i++)
            layer1.get(i).draw(globalR, batch);
    }

    public class _Texture {
        final String image;
        final float x;
        boolean transition;
        int layer_size, c;
        float d;

        public _Texture(String image, float x, int layer_size, boolean transition){
            this.layer_size = layer_size;
            this.c = 0;
            this.d = 0;
            this.image = image;
            this.transition = transition;
            this.x = x;
        }

        public void draw(float global, SpriteBatch batch) {
            if (am.isLoaded(image, Texture.class)) {
                batch.draw(am.get(image, Texture.class), x + d + global, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_HEIGHT);
            }
        }

        public boolean onScreen(float global){
            c = (int)(-(global) /(layer_size*Constants.SCREEN_HEIGHT));

            if(!transition){
                if(-global > layer_size*Constants.SCREEN_HEIGHT*(0.5f+c) & x + d < layer_size *Constants.SCREEN_HEIGHT*(0.5f+c)){
                    d = layer_size*Constants.SCREEN_HEIGHT + layer_size*Constants.SCREEN_HEIGHT*c;
                }
                else{
                    if(x > 0)
                        d = layer_size*Constants.SCREEN_HEIGHT*c;
                }
            } else{
                d = layer_size*Constants.SCREEN_HEIGHT*c;
            }

            return (x + d + global <= Constants.SCREEN_WIDTH + Constants.SCREEN_HEIGHT & x + d + global + Constants.SCREEN_HEIGHT >= -Constants.SCREEN_HEIGHT);
        }
    }
}