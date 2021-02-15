package com.projeto.anotacoes.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.projeto.anotacoes.classes.model.SubTarefa;

import java.util.List;

@Dao
public interface SubTarefaDAO {

    @Query("SELECT * FROM SubTarefa WHERE idTarefa = :idTarefa ORDER BY posicaoSubTarefa ")
    List<SubTarefa> getSubTarefas(int idTarefa);

    @Insert
    void salva(SubTarefa...subTarefas);

    @Delete
    void remove(SubTarefa subTarefa);

    @Update()
    void update(SubTarefa... subTarefa);
}
