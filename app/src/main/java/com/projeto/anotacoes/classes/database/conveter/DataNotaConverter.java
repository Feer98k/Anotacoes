package com.projeto.anotacoes.classes.database.conveter;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataNotaConverter {
    @TypeConverter
    public Long toLong(Calendar calendar){
        return  calendar.getTimeInMillis();
    }
    @TypeConverter
    public Calendar paraCalendar(Long valor){
        Calendar momentoAtual = Calendar.getInstance();
        if(valor != null){
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }
}
