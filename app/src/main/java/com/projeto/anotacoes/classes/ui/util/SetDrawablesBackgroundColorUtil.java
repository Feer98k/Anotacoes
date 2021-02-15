package com.projeto.anotacoes.classes.ui.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class SetDrawablesBackgroundColorUtil {
    public static void setDrawableColor(Context context, Drawable drawable, int color) {
        Drawable drawableWrap = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context, color));
    }
}
