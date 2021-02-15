package com.projeto.anotacoes.classes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.anotacoes.R;
import com.projeto.anotacoes.classes.model.enums.ColorsEnum;
import com.projeto.anotacoes.classes.model.enums.StyleTextEnum;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Note implements Serializable {



    private String title;
    private String description;
    private long position;
    private int textColor = R.color.BRANCO;
    private StyleTextEnum style = StyleTextEnum.NORMAL;
    private ColorsEnum defaultColor = ColorsEnum.WHITE;
    private Calendar dataEdicao = Calendar.getInstance();
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Calendar getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Calendar dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public StyleTextEnum getStyle() {
        return style;
    }

    public void setStyle(StyleTextEnum style) {
        this.style = style;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorsEnum getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(ColorsEnum defaultColor) {
        this.defaultColor = defaultColor;
    }

}