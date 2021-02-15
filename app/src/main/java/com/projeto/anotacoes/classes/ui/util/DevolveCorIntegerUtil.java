package com.projeto.anotacoes.classes.ui.util;

import com.example.anotacoes.R;
import com.projeto.anotacoes.classes.model.enums.ColorsEnum;

public class DevolveCorIntegerUtil {
    public static int DevolveCorInt(ColorsEnum colorDefault) {
        switch (colorDefault) {
            case WHITE:
                return R.color.BRANCO;
            case BLUE:
                return R.color.AZUL;

            case RED:
                return R.color.VERMELHO;

            case YELLOW:
                return R.color.AMARELO;

            case GREEN:
                return R.color.VERDE;

            case LILAC:
                return R.color.LILÁS;

            case GRAY:
                return R.color.CINZA;

            case BROWN:
                return R.color.MARROM;

            case PURPLE:
                return R.color.ROXO;

        }
        return R.color.BRANCO;
    }
}
