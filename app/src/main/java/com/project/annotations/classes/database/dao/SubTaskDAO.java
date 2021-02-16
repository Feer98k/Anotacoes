package com.project.annotations.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.annotations.classes.model.SubTask;

import java.util.List;

@Dao
public interface SubTaskDAO {

    @Query("SELECT * FROM SubTask " +
            "WHERE taskID = :taskID " +
            "ORDER BY subTaskPosition ")
    List<SubTask> getSubTask(int taskID);

    @Insert
    void insert(SubTask... subTasks);

    @Delete
    void remove(SubTask subTask);

    @Update()
    void update(SubTask... subTask);
}
