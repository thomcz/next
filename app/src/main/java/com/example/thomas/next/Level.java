package com.example.thomas.next;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Representation of a level.
 */
public class Level implements Parcelable {
    /** the id of the level. **/
    private int id;
    /** the series to complete. **/
    private String series;
    /** the result of this level. **/
    private int result;
    /** the description of the level. **/
    private String description;

    /**
     * Constructor of the level.
     * @param id the level id
     * @param series the series to complete
     * @param result the result of the level
     * @param description the description of the level
     */
    public Level(int id, String series, int result, String description) {
        this.id = id;
        this.series = series;
        this.result = result;
        this.description = description;
    }

    /**
     * The Constructor for Parcel Objects.
     * @param in the Parcel object
     */
    private Level(Parcel in) {
        id = in.readInt();
        series = in.readString();
        result = in.readInt();
        description = in.readString();
    }

    /**
     * Returns the id.
     * @return the id
     */
    public int getId() {
        return id;
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
     * Set the id.
     * @param id to set
     */
    public void setId(int id) {this.id = id; }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(series);
        dest.writeInt(result);
        dest.writeString(description);
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
