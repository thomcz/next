package com.example.thomas.next.object;

import java.util.ArrayList;

/**
 * Representation of a stage.
 */
public class Stage {
    /** The name of the stage. */
    private String name;

    private int size;
    private ArrayList<Level> levels;
    private int imageRessource;

    public Stage(String name, int size, ArrayList<Level> levels, int imageRessource) {
        this.name = name;
        this.size = size;
        this.levels = levels;
        this.imageRessource = imageRessource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public int getImageRessource() {
        return imageRessource;
    }

    public void setImageRessource(int imageRessource) {
        this.imageRessource = imageRessource;
    }
}
