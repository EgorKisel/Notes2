package com.geekbrains.notes2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditNoteFragment extends Fragment {
    private Button saveButton;
    private EditText subjectEditText;
    private EditText textEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        saveButton = view.findViewById(R.id.save_button);
        subjectEditText = view.findViewById(R.id.subject_edit_text);
        textEditText = view.findViewById(R.id.text_edit_text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        saveButton.setOnClickListener(v -> {
            getContract().onSaveNote(gatherNote());
        });
    }

    private NoteEntity gatherNote() {

        return new NoteEntity(NoteEntity.generateNewId(),
                subjectEditText.getText().toString(),
                NoteEntity.getCurrentDate(),
                textEditText.getText().toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Contract)) {
            throw new IllegalStateException("Activity must implement Contract");
        }

    }


    private Contract getContract() {
        return (Contract) getActivity();
    }

    interface Contract {
        void onSaveNote(NoteEntity note);
    }
}
