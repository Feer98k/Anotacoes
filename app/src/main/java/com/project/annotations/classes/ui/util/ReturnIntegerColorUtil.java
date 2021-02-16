package com.project.annotations.classes.ui.util;

import com.example.anotacoes.R;

public class ReturnIntegerColorUtil {
    public static int getIntegerCor(int color) {
        if (color == R.color.black) {
            return 0;
        }
        if (color == R.color.BRANCO) {
            return 1;
        }
        if (color == R.color.CINZA_LIGHT) {
            return 2;
        }
        return 0;
    }
}
