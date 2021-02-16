package com.project.annotations.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.project.annotations.classes.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM Task ORDER BY position DESC ")
    List<Task> getAllTasks();

    @Insert
    Long insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void remove(Task task);
}
