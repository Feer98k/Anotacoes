package com.projeto.anotacoes.classes.ui.adapter.listaTarefasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.anotacoes.classes.database.dao.TarefaDao;
import com.projeto.anotacoes.classes.model.Note;
import com.projeto.anotacoes.classes.model.Tarefa;

import java.util.List;

public class TarefaTouchHelper extends  ItemTouchHelper.Callback {
    final RecyclerTarefasAdapter adapter;
    final List<Tarefa> lista;
    final TarefaDao dao;

    public TarefaTouchHelper(RecyclerTarefasAdapter adapter, List<Tarefa> lista, TarefaDao dao) {
        this.adapter = adapter;
        this.lista = lista;
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
        Tarefa tarefaMovida = lista.get(posicao);
        dao.remove(tarefaMovida);
        adapter.remove(posicao);

    }
}
