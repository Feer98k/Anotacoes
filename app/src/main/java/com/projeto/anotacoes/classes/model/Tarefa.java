package com.projeto.anotacoes.classes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {
    int posicao =0;
    private String titulo;
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private int totalTarefas =0;
    private  int totalRealizadas=0;

    public int getTotalTarefas() {
        return totalTarefas;
    }

    public void setTotalTarefas(int totalTarefas) {
        this.totalTarefas = totalTarefas;
    }

    public int getTotalRealizadas() {
        return totalRealizadas;
    }

    public void setTotalRealizadas(int totalRealizadas) {
        this.totalRealizadas = totalRealizadas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
