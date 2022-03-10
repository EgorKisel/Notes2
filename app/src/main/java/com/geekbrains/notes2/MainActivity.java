package com.geekbrains.notes2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NoteListFragment.Contract, EditNoteFragment.Contract{
    private static final String NOTES_LIST_FRAGMENT_TAG = "NOTES_LIST_FRAGMENT_TAG";
    private boolean isTwoPanelMod = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTwoPanelMod = findViewById(R.id.optional_fragment_container) != null;
        showNoteList();
        
    }

    private void showNoteList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, new NoteListFragment(), NOTES_LIST_FRAGMENT_TAG)
                .commit();
    }

    private void showEditNote (){
        showEditNote(null);
    }

    private void showEditNote(@Nullable NoteEntity note){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!isTwoPanelMod) {
            transaction.addToBackStack(null);
        }
        transaction.replace(isTwoPanelMod ? R.id.optional_fragment_container: R.id.main_fragment_container, EditNoteFragment.newInstance(note))
                .commit();
    }

    @Override
    public void onCreateNote() {
        showEditNote();
    }

    @Override
    public void editNote(NoteEntity note) {
        showEditNote(note);
    }

    @Override
    public void onSaveNote(NoteEntity note) {
        getSupportFragmentManager().popBackStack();
        NoteListFragment noteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentByTag(NOTES_LIST_FRAGMENT_TAG);
        noteListFragment.addNote(note);
    }
}