package com.geekbrains.notes2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class NoteListFragment extends Fragment {
    private Button createButton;
    private LinearLayout listLinearLayout;
    private ArrayList<NoteEntity> noteList = new ArrayList<>();

    // Надуваю макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        createButton = view.findViewById(R.id.create_note_button);
        listLinearLayout = view.findViewById(R.id.list_linear_layout);
        return view;
    }

    // Здесь делаю навешивание логики (обрабтчик на createButton) - обработчик createButton
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        renderList(noteList);
        createButton.setOnClickListener(v -> getContract().onCreateNote());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof Contract)){
            throw new IllegalStateException("Activity must implement Contract");
        }

    }

    public void addNote(NoteEntity newNote){
        NoteEntity sameNote = findNoteWithId(newNote.id);
        if (sameNote != null) {
            noteList.remove(sameNote);
        }
        noteList.add(newNote);
        renderList(noteList);
    }

    @Nullable
    private NoteEntity findNoteWithId(String id){
        for (NoteEntity note : noteList){
            if (note.id.equals(id)){
                return note;
            }
        }
        return null;
    }

    private void renderList(List<NoteEntity> notes) {
        listLinearLayout.removeAllViews(); //чтобы заметки добавлялись всё время новые
        for (NoteEntity note : notes) {
            Button button = new Button(getContext());
            button.setText(note.subject);
            button.setOnClickListener(v ->{
                getContract().editNote(note);
            });
            listLinearLayout.addView(button);
        }
    }


    // Завили этот Contract чтобы актвити его заимплементила
    private Contract getContract(){
        return (Contract)getActivity();
    }
    interface Contract{
        void onCreateNote();
        void editNote(NoteEntity note);
    }
}
