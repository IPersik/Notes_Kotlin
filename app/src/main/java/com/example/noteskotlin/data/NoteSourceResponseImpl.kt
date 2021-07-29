package com.example.noteskotlin.data

import android.content.res.Resources
import com.example.noteskotlin.R
import java.util.*

class NoteSourceIResponseImpl(private val resources: Resources) : NoteSource {
    private val noteSource: MutableList<NoteData?>
    override fun init(noteSourceResponse: NoteSourceResponse?): NoteSourceIResponseImpl? {
        val titles = resources.getStringArray(R.array.nameNotes)
        for (i in titles.indices) {
            noteSource.add(NoteData(titles[i]))
        }
        noteSourceResponse?.initialized(this)
        return this
    }
    override fun size(): Int {
        return noteSource.size
    }

    override fun getNoteDate(position: Int): NoteData? {
        return noteSource[position]
    }

    override fun addNote(noteData: NoteData?) {
        noteSource.add(noteData)
    }

    override fun deleteNote(position: Int) {
        noteSource.removeAt(position)
    }

    override fun updateNote(position: Int, noteData: NoteData?) {
        noteSource[position] = noteData
    }

    override fun clearNote() {
        noteSource.clear()
    }

    fun getNoteSource(): List<NoteData?> {
        return noteSource
    }

    init {
        noteSource = ArrayList()
    }
}