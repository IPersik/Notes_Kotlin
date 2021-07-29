package com.example.noteskotlin.data;

import java.util.HashMap;
import java.util.Map;

public class NoteDataMapping {
    public static class Fields{
        public static final String TITLE = "title";
    }

    public static NoteData toNoteData(String id, Map<String, Object> document){
        NoteData answer = new NoteData((String) document.get(Fields.TITLE));
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument(NoteData noteData){
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.TITLE, noteData.getTitle());
        return answer;
    }
}
