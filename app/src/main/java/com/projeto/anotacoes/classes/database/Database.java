package com.projeto.anotacoes.classes.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.projeto.anotacoes.classes.database.conveter.StyleTextEnumConverter;
import com.projeto.anotacoes.classes.database.conveter.DataNotaConverter;
import com.projeto.anotacoes.classes.database.dao.SubTarefaDAO;
import com.projeto.anotacoes.classes.database.dao.TarefaDao;
import com.projeto.anotacoes.classes.model.Note;
import com.projeto.anotacoes.classes.database.conveter.ColorsEnumConverter;
import com.projeto.anotacoes.classes.database.dao.NoteDAO;
import com.projeto.anotacoes.classes.model.SubTarefa;
import com.projeto.anotacoes.classes.model.Tarefa;

import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.CEEP_DB;
import static com.projeto.anotacoes.classes.database.migrations.Migrations.MIGRATION;


@androidx.room.Database(entities = {Note.class, Tarefa.class, SubTarefa.class},version = 9,exportSchema = false)
@TypeConverters({ColorsEnumConverter.class, StyleTextEnumConverter.class, DataNotaConverter.class})
public abstract class Database extends RoomDatabase {


    public abstract NoteDAO getNotaDataDao();
    public abstract TarefaDao getTarefaDao();
    public abstract SubTarefaDAO getSubTarefaDao();

    public static  Database getInstance(Context context){
        return Room.databaseBuilder(context,Database.class, CEEP_DB)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION)
                .build();
    }
}
