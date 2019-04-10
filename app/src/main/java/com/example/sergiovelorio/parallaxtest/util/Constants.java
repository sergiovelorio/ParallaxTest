package com.example.sergiovelorio.parallaxtest.util;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;

public class Constants {
    public static AssetManager am = new AssetManager(new customRF());
    public static Context CURRENT_CONTEXT;
    public static int BASE_SIZE;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static float GLOBAL;

    public static class customRF implements FileHandleResolver {
        @Override
        public FileHandle resolve(String fileName) {
            return Gdx.files.internal(fileName);
        }
    }

    static {
        Constants.am.setErrorListener(new AssetErrorListener() {
            @Override
            public void error(AssetDescriptor asset, Throwable throwable) {
                try {
                    File file = new File(asset.fileName);
                    file.delete();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
