package com.project.annotations.classes.database.conveter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class DataNotaConverter {
    @TypeConverter
    public Long toLong(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar instanceNow = Calendar.getInstance();
        if (value != null) {
            instanceNow.setTimeInMillis(value);
        }
        return instanceNow;
    }
}
