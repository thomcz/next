package com.org.thomcz.next.object;

import java.util.ArrayList;

/**
 * Representation of a stage.
 */
public class Stage {
    /** The name of the stage. */
    private String name;
    private boolean unlocked;
    private ArrayList<Level> levels;
    private int imageRessource;

    public Stage(String name, ArrayList<Level> levels, int imageRessource) {
        this.name = name;
        this.levels = levels;
        this.imageRessource = imageRessource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getUnlocked() { return unlocked; }

    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }

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
