package com.example.noteskotlin.data;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteSourceFirebase implements NoteSource {

    static String NOTE_COLLECTION = "Notes";
    private static final String TAG = "[CardsSourceFirebaseImpl]";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    CollectionReference collectionReference = firebaseFirestore.collection(NOTE_COLLECTION);
    private List<NoteData> notesData = new ArrayList<NoteData>();

    public List<NoteData> getNoteSource() {
        return notesData;
    }

    @Override
    public NoteSource init(NoteSourceResponse noteSourceResponse) {
        collectionReference.orderBy(NoteDataMapping.Fields.TITLE, Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                notesData = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> documentMap = document.getData();
                        String id = document.getId();
                        NoteData noteData = NoteDataMapping.toNoteData(id, documentMap);
                        notesData.add(noteData);
                    }
                }
                noteSourceResponse.initialized(NoteSourceFirebase.this);
            }
        });
        return this;
    }

    @Override
    public int size() {
        if (notesData == null){
            return 0;
        }
        return notesData.size();
    }

    @Override
    public NoteData getNoteDate(int position) {
        return notesData.get(position);
    }

    @Override
    public void addNote(NoteData noteData) {
        collectionReference.add(NoteDataMapping.toDocument(noteData)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

            public void onSuccess(DocumentReference documentReference) {
                noteData.setId(documentReference.getId());
            }
        });
        notesData.add(noteData);
    }

    @Override
    public void deleteNote(int position) {
        collectionReference.document(notesData.get(position).getId()).delete();

        notesData.remove(position);
    }

    @Override
    public void updateNote(int position, NoteData noteData) {
        collectionReference.document(notesData.get(position).getId()).set(NoteDataMapping.toDocument(noteData));
        notesData.set(position, noteData);
    }

    @Override
    public void clearNote() {
        for (NoteData noteData : notesData) {
            collectionReference.document(noteData.getId()).delete();
        }
        notesData.clear();
    }
}
