package com.example.sergiovelorio.parallaxtest.animation;

import java.util.ArrayList;

public class Layers {
    private ArrayList<String> layer1 = new ArrayList<>();
    private ArrayList<String> layer2 = new ArrayList<>();
    private ArrayList<String> layer3 = new ArrayList<>();
    private ArrayList<String> layer4 = new ArrayList<>();
    private ArrayList<String> statics = new ArrayList<>();
    private ArrayList<String> backgrounds = new ArrayList<>();
    private ArrayList<String> transitions = new ArrayList<>();

    public ArrayList<String> getLayer1() {
        return layer1;
    }

    public void setLayer1(ArrayList<String> layer1) {
        this.layer1 = layer1;
    }

    public ArrayList<String> getLayer2() {
        return layer2;
    }

    public void setLayer2(ArrayList<String> layer2) {
        this.layer2 = layer2;
    }

    public ArrayList<String> getLayer3() {
        return layer3;
    }

    public void setLayer3(ArrayList<String> layer3) {
        this.layer3 = layer3;
    }

    public ArrayList<String> getLayer4() {
        return layer4;
    }

    public void setLayer4(ArrayList<String> layer4) {
        this.layer4 = layer4;
    }

    public ArrayList<String> getStatics() {
        return statics;
    }

    public void setStatics(ArrayList<String> statics) {
        this.statics = statics;
    }

    public ArrayList<String> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(ArrayList<String> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public ArrayList<String> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<String> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<String> all_images(){
        ArrayList<String> images = new ArrayList<>();

        for(int i = 0; i < layer1.size(); i++){ images.add(layer1.get(i)); }
        for(int i = 0; i < layer2.size(); i++){ images.add(layer2.get(i)); }
        for(int i = 0; i < layer3.size(); i++){ images.add(layer3.get(i)); }
        for(int i = 0; i < layer4.size(); i++){ images.add(layer4.get(i)); }
        for(int i = 0; i < backgrounds.size(); i++){ images.add(backgrounds.get(i)); }
        for(int i = 0; i < transitions.size(); i++){ images.add(transitions.get(i)); }
        for(int i = 0; i < statics.size(); i++){ images.add(statics.get(i)); }

        return images;
    }
}
