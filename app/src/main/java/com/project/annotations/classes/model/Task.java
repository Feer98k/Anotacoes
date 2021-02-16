package com.project.annotations.classes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static com.project.annotations.classes.constants.general.GeneralConstants.INITIAL_VALUE;

@Entity
public class Task implements Serializable {

    int position = INITIAL_VALUE;
    private String title;
    @PrimaryKey(autoGenerate = true)
    private int id = INITIAL_VALUE;
    private int totalTasks = INITIAL_VALUE;
    private int completedTasks = INITIAL_VALUE;

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
