package com.projeto.anotacoes.classes.ui.adapter.listaNotasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.projeto.anotacoes.classes.database.dao.NoteDAO;
import com.projeto.anotacoes.classes.model.Note;

import java.util.List;

public class NotaItemTouchHelper extends ItemTouchHelper.Callback {
    final NotesAdapterList adapter;
    final List<Note> lista;
    final NoteDAO dao;

    public NotaItemTouchHelper(NotesAdapterList adapter, List<Note> listaNotes, NoteDAO dao) {
        this.adapter = adapter;
        this.lista = listaNotes;
        this.dao = dao;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int moviments = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int movimentDraf = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(movimentDraf, moviments);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int first = viewHolder.getBindingAdapterPosition();
        int targeter = target.getBindingAdapterPosition();
        adapter.swap(first, targeter);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicao = viewHolder.getBindingAdapterPosition();
        Note noteMovida = lista.get(posicao);
        dao.remove(noteMovida);
        adapter.remove(posicao);

    }
}
