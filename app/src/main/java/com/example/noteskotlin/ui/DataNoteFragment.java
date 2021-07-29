package com.example.noteskotlin.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.example.noteskotlin.MainActivity;
import com.example.noteskotlin.R;
import com.example.noteskotlin.data.NoteData;
import com.example.noteskotlin.observe.Publisher;
import com.google.android.material.textfield.TextInputEditText;


public class DataNoteFragment extends Fragment {

    private static final String ARG_CARD_DATA = "Param_CardData";

    private NoteData noteData;      // Данные по карточке
    private Publisher publisher;    // Паблишер, с его помощью обмениваемся данными

    private TextInputEditText title;
    private TextInputEditText description;
    private DatePicker datePicker;

    public static DataNoteFragment newInstance(NoteData noteData) {
        DataNoteFragment fragment = new DataNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, noteData);
        fragment.setArguments(args);
        return fragment;
    }

    public static DataNoteFragment newInstance() {
        DataNoteFragment fragment = new DataNoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            noteData = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        initView(view);
        if (noteData != null) {
            populateView();
        }
        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
        noteData = collectNoteData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(noteData);
    }

    private NoteData collectNoteData() {
        String title = this.title.getText().toString();
        if (noteData != null) {
            NoteData answer = new NoteData(title);
            answer.setId(noteData.getId());
            return answer;
        } else {
            return new NoteData(title);
        }
    }

    private void populateView() {
        title.setText(noteData.getTitle());
    }

    private void initView(View view) {
        title = view.findViewById(R.id.name_of_notes);
    }
}
