package com.projeto.anotacoes.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.projeto.anotacoes.classes.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert()
    Long insert(Note note);

    @Delete()
    void remove(Note note);

    @Update()
     void update(Note note);


    @Query("SELECT * FROM Note  ORDER BY position DESC")
    List<Note> allNote();


}
