package com.geekbrains.notes2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NoteListFragment.Contract, EditNoteFragment.Contract{
    private static final String NOTES_LIST_FRAGMENT_TAG = "NOTES_LIST_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNoteList();
        
    }

    private void showNoteList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NoteListFragment(), NOTES_LIST_FRAGMENT_TAG)
                .commit();
    }
    private void showEditNote(){
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, new EditNoteFragment())
                .commit();
    }

    @Override
    public void onCreateNote() {
        showEditNote();
    }

    @Override
    public void onSaveNote(NoteEntity note) {
        getSupportFragmentManager().popBackStack();
        NoteListFragment noteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentByTag(NOTES_LIST_FRAGMENT_TAG);
        noteListFragment.addNote(note);
    }
}