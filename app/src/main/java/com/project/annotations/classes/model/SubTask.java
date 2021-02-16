package com.project.annotations.classes.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.project.annotations.classes.constants.general.GeneralConstants.INITIAL_VALUE;

@SuppressWarnings("NullableProblems")
@Entity
public class SubTask {
    public static final int CASCADE = ForeignKey.CASCADE;
    private String taskDescription;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Task.class,
            parentColumns = "id",
            childColumns = "taskID",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int taskID;
    private boolean isCompletedTask = false;
    private int subTaskPosition = INITIAL_VALUE;

    public int getSubTaskPosition() {
        return subTaskPosition;
    }

    public void setSubTaskPosition(int subTaskPosition) {
        this.subTaskPosition = subTaskPosition;
    }

    public boolean isCompletedTask() {
        return isCompletedTask;
    }

    public void setCompletedTask(boolean completedTask) {
        this.isCompletedTask = completedTask;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public String toString() {
        return taskDescription;
    }
}
