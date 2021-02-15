package com.projeto.anotacoes.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.projeto.anotacoes.classes.model.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {
    @Query("SELECT * FROM Tarefa ORDER BY posicao DESC ")
    List<Tarefa> getTarefas();

    @Insert
    Long salva(Tarefa tarefas);

    @Update
    void update(Tarefa primeiro);

    @Delete
    void remove(Tarefa tarefaMovida);
}
