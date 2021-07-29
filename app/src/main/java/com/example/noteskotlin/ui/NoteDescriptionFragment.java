package com.example.noteskotlin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.noteskotlin.R;
import com.example.noteskotlin.ImageNotes;

public class NoteDescriptionFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    private ImageNotes imageNotes;

    public static NoteDescriptionFragment newInstance(ImageNotes imageNotes) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, imageNotes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageNotes = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_notes, container, false);

        TextView textViewName = view.findViewById(R.id.name_of_notes);
        TextView textViewBody = view.findViewById(R.id.text_of_notes);
        TextView textViewDate = view.findViewById(R.id.date_of_notes);
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        textViewName.setText(imageNotes.getNoteName(getContext()));
        textViewBody.setText(imageNotes.getNoteBody(getContext()));
        textViewDate.setText(imageNotes.getNoteDateYear(getContext())+" "+ imageNotes.getNoteDateMonth(getContext())+" "+ imageNotes.getNoteDateDay(getContext()));
        datePicker.init(imageNotes.getNoteDateYear(getContext()), imageNotes.getNoteDateMonth(getContext()), imageNotes.getNoteDateDay(getContext()), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                imageNotes.setNoteDate(getContext(),year, monthOfYear,dayOfMonth);
                textViewDate.setText(new StringBuilder().append(imageNotes.getNoteDateYear(getContext())).append(" ").append(imageNotes.getNoteDateMonth(getContext())).append(" ").append(imageNotes.getNoteDateDay(getContext())).toString());
            }
        });
        return view;
    }
}
