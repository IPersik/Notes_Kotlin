package com.example.noteskotlin.data;

import android.content.res.Resources;

import com.example.noteskotlin.R;

import java.util.ArrayList;
import java.util.List;

public class NoteSourceIResponseImpl implements NoteSource {
    private List<NoteData> noteSource;
    private Resources resources;

    public NoteSourceIResponseImpl(Resources resources) {
        this.resources = resources;
        noteSource = new ArrayList<>();
    }

    public NoteSourceIResponseImpl init(NoteSourceResponse noteSourceResponse) {
        String[] titles = resources.getStringArray(R.array.nameNotes);
        for (int i = 0; i < titles.length; i++) {
            noteSource.add(new NoteData(titles[i]));
        }
        if (noteSourceResponse != null){
            noteSourceResponse.initialized(this);
        }
        return this;
    }

    /*@Override
    public NoteSource init(NoteSourceResponse noteSourceResponse) {
        return null;
    }*/

    @Override
    public int size() {
        return noteSource.size();
    }

    @Override
    public NoteData getNoteDate(int position) {
        return noteSource.get(position);
    }

    @Override
    public void addNote(NoteData noteData) {
        noteSource.add(noteData);
    }

    @Override
    public void deleteNote(int position) {
        noteSource.remove(position);
    }

    @Override
    public void updateNote(int position, NoteData noteData) {
        noteSource.set(position, noteData);
    }

    @Override
    public void clearNote() {
        noteSource.clear();
    }

    public List<NoteData> getNoteSource() {
        return noteSource;
    }

}
