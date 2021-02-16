package com.project.annotations.classes.database.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
    public static final Migration[] MIGRATION = {new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Nota_nova` (" +
                    "`titulo` TEXT, " +
                    "`descricao` TEXT, " +
                    "`posicao` INTEGER NOT NULL," +
                    " `corPadrao` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
            database.execSQL("DROP TABLE Nota");
            database.execSQL("ALTER TABLE Nota_nova RENAME TO Nota");
        }
    }, new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note` (" +
                    "`title` TEXT, " +
                    "`description` TEXT, " +
                    "`position` INTEGER NOT NULL, " +
                    "`defaultColor` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

            database.execSQL("DROP TABLE Nota");

        }
    }, new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note_nova` (" +
                    "`title` TEXT, " +
                    "`description` TEXT," +
                    " `position` INTEGER NOT NULL," +
                    " `textColor` INTEGER NOT NULL," +
                    " `style` TEXT, " +
                    "`defaultColor` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
            database.execSQL("DROP TABLE Note");
            database.execSQL("ALTER TABLE Note_nova Rename to Note");
        }
    }, new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note_nova` (`title` TEXT," +
                    " `description` TEXT," +
                    " `position` INTEGER NOT NULL," +
                    " `textColor` INTEGER NOT NULL, " +
                    "`style` TEXT," +
                    " `defaultColor` TEXT," +
                    " `dataEdicao` INTEGER," +
                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
            database.execSQL("DROP TABLE Note");
            database.execSQL("ALTER TABLE Note_nova Rename to Note");

        }
    }, new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Tarefa_nova` (" +
                    "`posicao` INTEGER NOT NULL," +
                    " `titulo` TEXT," +
                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

            database.execSQL("ALTER TABLE Tarefa_nova RENAME TO Tarefa");

            database.execSQL("CREATE TABLE IF NOT EXISTS `SubTarefa_nova` (`descricaoTarefa` TEXT," +
                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `idTarefa` INTEGER NOT NULL)");
            database.execSQL("INSERT INTO SubTarefa_nova(idTarefa) SELECT id FROM Tarefa ");

            database.execSQL("ALTER TABLE SubTarefa_nova RENAME TO SubTarefa");
        }
    }, new Migration(6, 7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `SubTarefa_nova` (`descricaoTarefa` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`idTarefa` INTEGER NOT NULL, " +
                    "`completado` INTEGER NOT NULL)");
            database.execSQL("DROP TABLE SubTarefa");
            database.execSQL("ALTER TABLE SubTarefa_nova RENAME TO SubTarefa");
        }
    }, new Migration(7, 8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Tarefa_nova` (" +
                    "`posicao` INTEGER NOT NULL, " +
                    "`titulo` TEXT," +
                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`totalTarefas` INTEGER NOT NULL," +
                    " `totalRealizadas` INTEGER NOT NULL)");
            database.execSQL("DROP TABLE Tarefa");
            database.execSQL("ALTER TABLE Tarefa_nova RENAME TO Tarefa");
        }
    }, new Migration(8, 9) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `SubTarefa_nova` (" +
                    "`descricaoTarefa` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`idTarefa` INTEGER NOT NULL, " +
                    "`completado` INTEGER NOT NULL, " +
                    "`posicaoSubTarefa` INTEGER NOT NULL)");
            database.execSQL("DROP TABLE SubTarefa");
            database.execSQL("ALTER TABLE  SubTarefa_nova RENAME TO SubTarefa");
        }
    }, new Migration(9, 10) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note_new` (`title` TEXT, " +
                    "`description` TEXT, " +
                    "`position` INTEGER NOT NULL, " +
                    "`textColor` INTEGER NOT NULL," +
                    " `style` TEXT, " +
                    "`defaultColor` TEXT," +
                    " `dataEdicao` INTEGER, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Task_new` (`position` INTEGER NOT NULL," +
                    " `title` TEXT," +
                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `totalTasks` INTEGER NOT NULL, " +
                    "`completedTasks` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `SubTask_new` (`taskDescription` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `taskID` INTEGER NOT NULL," +
                    " `isCompletedTask` INTEGER NOT NULL, " +
                    "`subTaskPosition` INTEGER NOT NULL)");
            database.execSQL("DROP TABLE Note");
            database.execSQL("DROP TABLE Task");
            database.execSQL("DROP TABLE SubTask");
            database.execSQL("ALTER TABLE Note_new RENAME TO Note");
            database.execSQL("ALTER TABLE Task_new RENAME TO Task");
            database.execSQL("ALTER TABLE SubTask_new RENAME TO SubTask");


        }
    }};

}
