package com.projeto.anotacoes.classes.database.conveter;

import androidx.room.TypeConverter;

import com.projeto.anotacoes.classes.model.enums.StyleTextEnum;

public class StyleTextEnumConverter {
    @TypeConverter
    public String styleToString(StyleTextEnum style) {
        if (style != null) {
            return style.name();
        }
        return null;
    }
    @TypeConverter
    public StyleTextEnum stringToStyleEnum(String value){
        if (value!=null){
            return StyleTextEnum.valueOf(value);
        }
        return null;
    }
}
