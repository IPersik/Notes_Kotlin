package com.example.noteskotlin.data;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteData implements Parcelable {
    private String id; //идентификатор
    private String title;

    public NoteData(String title) {
        this.title = title;
    }

    protected NoteData(Parcel in) {
        title = in.readString();
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}