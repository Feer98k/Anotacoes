package com.project.annotations.classes.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.project.annotations.classes.database.conveter.ColorsEnumConverter;
import com.project.annotations.classes.database.conveter.DataNotaConverter;
import com.project.annotations.classes.database.conveter.StyleTextEnumConverter;
import com.project.annotations.classes.database.dao.NoteDAO;
import com.project.annotations.classes.database.dao.SubTaskDAO;
import com.project.annotations.classes.database.dao.TaskDAO;
import com.project.annotations.classes.model.Note;
import com.project.annotations.classes.model.SubTask;
import com.project.annotations.classes.model.Task;

import static com.project.annotations.classes.constants.general.GeneralConstants.CEEP_DB;
import static com.project.annotations.classes.database.migrations.Migrations.MIGRATION;


@androidx.room.Database(entities = {Note.class, Task.class, SubTask.class}, version = 10, exportSchema = false)
@TypeConverters({ColorsEnumConverter.class, StyleTextEnumConverter.class, DataNotaConverter.class})
public abstract class Database extends RoomDatabase {


    public abstract NoteDAO getNotaDataDao();

    public abstract TaskDAO getTarefaDao();

    public abstract SubTaskDAO getSubTarefaDao();

    public static Database getInstance(Context context) {
        return Room.databaseBuilder(context, Database.class, CEEP_DB)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION)
                .build();
    }
}
