package com.example.sergiovelorio.parallaxtest.animation;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.sergiovelorio.parallaxtest.util.Constants;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication {

    public View v;
    private int prev, init;
    private int movement;
    public float global_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        v = initializeForView(new Parallax(layers()), new AndroidApplicationConfiguration());

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                boolean animate = false;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        prev = (int)event.getX();
                        init = (int)event.getX();
                        animate = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(event.getPointerCount() > 1){
                            movement = (int) event.getX() - init;
                            if(movement > 100){
                                movement = 100;
                            }
                            if(movement < -100){
                                movement = -100;
                            }
                            animate = true;
                            global_scroll += movement;
                            if(global_scroll > 0) {
                                global_scroll = 0;
                            }
                        }
                        else{
                            if (prev == 0) {
                                prev = (int) event.getX();
                            } else {
                                movement = (int) event.getX() - prev;
                                prev = (int) event.getX();
                            }
                            animate = true;
                            global_scroll += movement;
                            if(global_scroll > 0) {
                                global_scroll = 0;
                            }
                        }
                        break;

                }

                Constants.GLOBAL = global_scroll;

                return animate;
            }
        });

        setContentView(v);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public Layers layers(){
        Layers layers = new Layers();

        ArrayList<String> layer1 = new ArrayList<>();
        ArrayList<String> layer2 = new ArrayList<>();
        ArrayList<String> layer3 = new ArrayList<>();
        ArrayList<String> layer4 = new ArrayList<>();
        ArrayList<String> statics = new ArrayList<>();
        ArrayList<String> backgrounds = new ArrayList<>();
        ArrayList<String> transitions = new ArrayList<>();

        layer1.add("l1.png");
        layer1.add("l1.png");
        layer1.add("l1.png");
        layer1.add("l1.png");
        layer2.add("l2.png");
        layer2.add("l2.png");
        layer2.add("l2.png");
        layer2.add("l2.png");
        layer3.add("l3.png");
        layer3.add("l3.png");
        layer3.add("l3.png");
        layer3.add("l3.png");
        layer4.add("l4.png");
        layer4.add("l4.png");
        layer4.add("l4.png");
        layer4.add("l4.png");

        statics.add("l7.png");
        backgrounds.add("l8.png");
        backgrounds.add("l8.png");
        backgrounds.add("l8.png");
        backgrounds.add("l8.png");

        layers.setLayer1(layer1);
        layers.setLayer2(layer2);
        layers.setLayer3(layer3);
        layers.setLayer4(layer4);
        layers.setStatics(statics);
        layers.setBackgrounds(backgrounds);
        layers.setTransitions(transitions);

        return layers;
    }
}
