package com.example.noteskotlin.data

import java.util.*

class NoteDataMapping {

    object Fields {
        const val TITLE : String = "title"
    }

    companion object{

        @JvmStatic
        fun toNoteData(id: String, document: Map<String, Any>): NoteData {
            val answer = NoteData((document[Fields.TITLE] as String))
            answer.id
            return answer
        }

        @JvmStatic
        fun toDocument(noteData: NoteData): Map<String, Any> {
            val answer: MutableMap<String, Any> = HashMap()
            answer[Fields.TITLE] = noteData.title
            return answer
        }
    }
}