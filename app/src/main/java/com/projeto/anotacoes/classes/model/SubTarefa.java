package com.projeto.anotacoes.classes.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity
public class SubTarefa {
    public static final int CASCADE = ForeignKey.CASCADE;
    private String descricaoTarefa;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Tarefa.class,
            parentColumns = "id",
            childColumns = "idTarefa",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int idTarefa;
    private boolean completado = false;
    private int posicaoSubTarefa =0;

    public int getPosicaoSubTarefa() {
        return posicaoSubTarefa;
    }

    public void setPosicaoSubTarefa(int posicaoSubTarefa) {
        this.posicaoSubTarefa = posicaoSubTarefa;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    @Override
    public String toString() {
        return descricaoTarefa;
    }
}
