package com.example.thomas.next;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

/**
 * Created by thomas on 13.05.15.
 */
public class Level implements Parcelable{
    //TODO Levels in xml verlagern
    private int id;
    private String series;
    private int result;
    private String resultString;
    //private int imageRessource;

    public Level(int id, String series, int result, String resultString) {
        this.id = id;
        this.series = series;
        this.result = result;
        this.resultString = resultString;
        //this.imageRessource = imageRessource;
    }
    private Level(Parcel in) {
        id = in.readInt();
        series = in.readString();
        result = in.readInt();
        resultString = in.readString();
        //imageRessource = in.readInt();
    }

    public int getId() {
        return id;
    }
    public String getSeries() {
        return series;
    }
    public int getResult() {
        return result;
    }
    public String getResultString() {
        return resultString;
    }
    //public int getImageRessource() { return imageRessource; }

    public void setId(int id) {this.id = id; }
    public void setSeries(String series) {
        this.series = series;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
    //public void setImageRessource(int imageRessource) { this.imageRessource = imageRessource; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(series);
        dest.writeInt(result);
        dest.writeString(resultString);
        //dest.writeInt(imageRessource);
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
