package com.projeto.anotacoes.classes.ui.util;

import com.example.anotacoes.R;

public class DevolveCorFonteIntegerUtil {
    public static int getIntegerCor(int color){
        if(color == R.color.black){
            return 0;
        }
        if(color == R.color.BRANCO){
            return 1;
        }
        if(color == R.color.CINZA_LIGHT){
            return 2;
        }
        return 0;
    }
}
