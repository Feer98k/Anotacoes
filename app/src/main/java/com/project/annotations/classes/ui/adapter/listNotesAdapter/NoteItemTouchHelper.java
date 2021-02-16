package com.project.annotations.classes.ui.adapter.listNotesAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.project.annotations.classes.database.dao.NoteDAO;
import com.project.annotations.classes.model.Note;

import java.util.List;

public class NoteItemTouchHelper extends ItemTouchHelper.Callback {
    private final RecyclerViewNotesAdapter noteListAdapter;
    private final List<Note> listNotes;
    private final NoteDAO noteDAO;

    public NoteItemTouchHelper(RecyclerViewNotesAdapter noteListAdapter, List<Note> listaNotes, NoteDAO NoteDAO) {
        this.noteListAdapter = noteListAdapter;
        this.listNotes = listaNotes;
        this.noteDAO = NoteDAO;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int movement = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int allMovement = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(allMovement, movement);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int first = viewHolder.getBindingAdapterPosition();
        int targetInt = target.getBindingAdapterPosition();
        noteListAdapter.swap(first, targetInt);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int positionNoteInteger = viewHolder.getBindingAdapterPosition();
        Note note = listNotes.get(positionNoteInteger);
        noteDAO.remove(note);
        noteListAdapter.remove(positionNoteInteger);

    }
}
