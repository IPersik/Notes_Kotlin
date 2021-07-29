package com.example.noteskotlin.data

import com.example.noteskotlin.data.NoteDataMapping.Companion.toNoteData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.*
import java.util.*

class NoteSourceFirebase : NoteSource {
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    var collectionReference = firebaseFirestore.collection(NOTE_COLLECTION)
    private var notesData: MutableList<NoteData?>? = ArrayList()
    val noteSource: List<NoteData?>?
        get() = notesData

    override fun init(noteSourceResponse: NoteSourceResponse?): NoteSource? {
        collectionReference.orderBy(NoteDataMapping.Fields.TITLE, Query.Direction.DESCENDING).get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
            notesData = ArrayList()
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val documentMap = document.data
                    val id = document.id
                    val noteData = toNoteData(id, documentMap)
                    (notesData as ArrayList<NoteData?>).add(noteData)
                }
            }
            noteSourceResponse!!.initialized(this@NoteSourceFirebase)
        })
        return this
    }

    override fun size(): Int {
        return if (notesData == null) {
            0
        } else notesData!!.size
    }

    override fun getNoteDate(position: Int): NoteData? {
        return notesData!![position]
    }

    override fun addNote(noteData: NoteData?) {
        collectionReference.add(NoteDataMapping.toDocument(noteData!!)).addOnSuccessListener { documentReference -> noteData.id = documentReference.id }
        notesData!!.add(noteData)
    }

    override fun deleteNote(position: Int) {
        collectionReference.document(notesData!![position]!!.id).delete()
        notesData!!.removeAt(position)
    }

    override fun updateNote(position: Int, noteData: NoteData?) {
        collectionReference.document(notesData!![position]!!.id).set(NoteDataMapping.toDocument(noteData!!))
        notesData!![position] = noteData
    }

    override fun clearNote() {
        for (noteData in notesData!!) {
            collectionReference.document(noteData!!.id).delete()
        }
        notesData!!.clear()
    }

    companion object {
        var NOTE_COLLECTION = "Notes"
        private const val TAG = "[CardsSourceFirebaseImpl]"
    }
}