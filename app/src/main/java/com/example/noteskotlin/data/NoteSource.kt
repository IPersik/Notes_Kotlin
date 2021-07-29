package com.example.noteskotlin.data

interface NoteSource {
    fun init(noteSourceResponse: NoteSourceResponse?): NoteSource
    fun size(): Int
    fun getNoteDate(position: Int): NoteData?
    fun addNote(noteData: NoteData)
    fun deleteNote(position: Int)
    fun updateNote(position: Int, noteData: NoteData)
    fun clearNote()
}