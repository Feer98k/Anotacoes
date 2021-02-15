package com.projeto.anotacoes.classes.model;


import com.projeto.anotacoes.classes.model.enums.ColorsEnum;

public class Cor {
    final ColorsEnum cor;

    public Cor(ColorsEnum cor) {
        this.cor = cor;
    }

    public ColorsEnum getCor() {
        return cor;
    }

}
