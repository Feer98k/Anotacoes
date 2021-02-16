package com.project.annotations.classes.model;


import com.project.annotations.classes.model.enums.ColorsEnum;

public class Color {
    final ColorsEnum cor;

    public Color(ColorsEnum cor) {
        this.cor = cor;
    }

    public ColorsEnum getCor() {
        return cor;
    }

}
