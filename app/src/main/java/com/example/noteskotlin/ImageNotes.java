package com.example.noteskotlin;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;


public class ImageNotes extends Fragment implements Parcelable {

    private int noteIndex;

    private static final String KEY_NOTE_YEAR= "key_note_year_";
    private static final String KEY_NOTE_MONTH= "key_note_monthOfYear_";
    private static final String KEY_NOTE_DAY= "";
    private static final String KEY_PREF= "note_date";

    public ImageNotes(int contentIndex) {
        this.noteIndex = contentIndex;
    }

    public int getNoteIndex(){
        return noteIndex;
    }

    public String getNoteName(Context mContext) {
        return mContext.getResources().getStringArray(R.array.nameNotes)[noteIndex];
    }

    public String getNoteBody(Context mContext) {
        return mContext.getResources().getStringArray(R.array.textNotes)[noteIndex];
    }

    public int getNoteDateYear(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_YEAR+noteIndex, -1);
    }
    public int getNoteDateMonth(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_MONTH+noteIndex, -1);
    }
    public int getNoteDateDay(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_DAY+noteIndex, -1);
    }

    public void setNoteDate(Context mContext, int year, int monthOfYear, int dayOfMonth) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_NOTE_YEAR+noteIndex, year);
        editor.putInt(KEY_NOTE_MONTH+noteIndex, monthOfYear);
        editor.putInt(KEY_NOTE_DAY+noteIndex, dayOfMonth);
        editor.apply();
    }


    protected ImageNotes(Parcel in) {
        noteIndex = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getNoteIndex());
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ImageNotes> CREATOR = new Parcelable.Creator<ImageNotes>() {
        @Override
        public ImageNotes createFromParcel(Parcel in) {
            return new ImageNotes(in);
        }

        @Override
        public  ImageNotes[] newArray(int size) {
            return new ImageNotes[size];
        }
    };

}
