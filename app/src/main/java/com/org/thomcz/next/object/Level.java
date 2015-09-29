package com.org.thomcz.next.object;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Representation of a level.
 */
public class Level implements Parcelable {
    /** the id of the level. **/
    private int id;
    /** shows if the level is unlocked by the user. */
    private boolean unlocked;

    private boolean solved;
    /** the series to complete. **/
    private String series;
    /** the result of this level. **/
    private int result;
    /** the description of the level. **/
    private String description;
    /** the score of the level. **/
    private int score;
    /** the extra score of the level. **/
    private int actualScore;

    /**
     * Constructor of the level.
     * @param id the level id
     * @param unlocked if level is unlocked
     * @param series the series to complete
     * @param result the result of the level
     * @param description the description of the level
     * @param score score of the level
     */
    public Level(int id, boolean unlocked, boolean solved, String series, int result, String description, int score, int actualScore) {
        this.id = id;
        this.unlocked = unlocked;
        this.solved = solved;
        this.series = series;
        this.result = result;
        this.description = description;
        this.score = score;
        this.actualScore = actualScore;

    }

    /**
     * The Constructor for Parcel Objects.
     * @param in the Parcel object
     */
    private Level(Parcel in) {
        id = in.readInt();
        unlocked = (boolean) in.readValue(null);
        solved = (boolean) in.readValue(null);
        series = in.readString();
        result = in.readInt();
        description = in.readString();
        score = in.readInt();
        actualScore = in.readInt();
    }

    /**
     * Returns the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns if the level is unlocked.
     * @return true if it is unlocked, false otherwise
     */
    public boolean getUnlocked() {
        return unlocked;
    }

    /**
     * Returns if the level is solved.
     * @return true if it is solved, false otherwise
     */
    public boolean getSolved() {
        return solved;
    }

    /**
     * Returns the series.
     * @return the series
     */
    public String getSeries() {
        return series;
    }

    /**
     * Returns the result.
     * @return the result
     */
    public int getResult() {
        return result;
    }

    /**
     * Returns the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the score.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the extra score.
     * @return the extra score
     */
    public int getActualScore() {
        return actualScore;
    }

    /**
     * Set the id.
     * @param id to set
     */
    public void setId(int id) {this.id = id; }

    /**
     * Set if the level is unlocked.
     * @param unlocked true if the level is unlocked, false otherwise
     */
    public void setUnlocked(boolean unlocked) {this.unlocked = unlocked; }

    /**
     * Set if the level is solved.
     * @param solved true if the level is solved, false otherwise
     */
    public void setSolved(boolean solved) {this.solved = solved; }

    /**
     * Set the series.
     * @param series to set
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * Set the result.
     * @param result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Set the description.
     * @param description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the score.
     * @param score to set
     */
    public void setScore(int score) {this.score = score; }

    /**
     * Set the actual score.
     * @param actualScore to set
     */
    public void setActualScore(int actualScore) {this.actualScore = actualScore; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(unlocked);
        dest.writeValue(solved);
        dest.writeString(series);
        dest.writeInt(result);
        dest.writeString(description);
        dest.writeInt(score);
        dest.writeInt(actualScore);
    }

    public static final Parcelable.Creator<Level> CREATOR = new Parcelable.Creator<Level>() {
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        public Level[] newArray(int size) {
            return new Level[size];
        }
    };
}
