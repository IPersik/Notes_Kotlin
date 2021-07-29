package com.example.noteskotlin.data;

public interface NoteSource {
    NoteSource init(NoteSourceResponse noteSourceResponse);
    int size();
    NoteData getNoteDate(int position);
    void addNote(NoteData noteData);
    void deleteNote(int position);
    void updateNote(int position, NoteData noteData);
    void clearNote();
}