package com.example.sergiovelorio.parallaxtest.animation;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.sergiovelorio.parallaxtest.util.Constants;

public class AndroidLauncher extends AndroidApplication {

    public View v;
    private int prev, init;
    private int movement;
    public float global_scroll;
    public float prev_global_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.BASE_SIZE = (int) (Constants.SCREEN_HEIGHT * 1.3f / 100);

        v = initializeForView(new Parallax(), config);

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
                            prev_global_scroll = global_scroll;
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
                            prev_global_scroll = global_scroll;
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
}
