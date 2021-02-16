package com.project.annotations.classes.database.conveter;

import androidx.room.TypeConverter;

import com.project.annotations.classes.model.enums.StyleTextEnum;

public class StyleTextEnumConverter {
    @TypeConverter
    public String styleTextToString(StyleTextEnum style) {
        if (style != null) {
            return style.name();
        }
        return null;
    }

    @TypeConverter
    public StyleTextEnum stringToStyleEnum(String value) {
        if (value != null) {
            return StyleTextEnum.valueOf(value);
        }
        return null;
    }
}
