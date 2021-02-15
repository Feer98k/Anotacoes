package com.projeto.anotacoes.classes.ui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.ABRIL;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.AGOSTO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.DEZEMBRO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.FEVEREIRO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.JANEIRO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.JULHO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.JUNHO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.MAIO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.MARÇO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.NOVEMBRO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.OUTUBRO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.SETEMBRO;

public class DataUtil {


    public static String devolveStringCalendar(Calendar dataNota) {
        Calendar calendar = dataNota;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        df.format(calendar.getTime());

        String mesConvertido = "";
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        if (m == 1) {
            mesConvertido = JANEIRO;
        }
        if (m == 2) {
            mesConvertido = FEVEREIRO;
        }
        if (m == 3) {
            mesConvertido = MARÇO;
        }
        if (m == 4) {
            mesConvertido = ABRIL;
        }
        if (m == 5) {
            mesConvertido = MAIO;
        }
        if (m == 6) {
            mesConvertido = JUNHO;
        }
        if (m == 7) {
            mesConvertido = JULHO;
        }
        if (m == 8) {
            mesConvertido = AGOSTO;
        }
        if (m == 9) {
            mesConvertido = SETEMBRO;
        }
        if (m == 10) {
            mesConvertido = OUTUBRO;
        }
        if (m == 11) {
            mesConvertido = NOVEMBRO;
        }
        if (m == 12) {
            mesConvertido = DEZEMBRO;
        }

        return d + " de " + mesConvertido + " " + h + ":" + min;
    }
}
