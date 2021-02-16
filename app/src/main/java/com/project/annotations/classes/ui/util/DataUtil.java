package com.project.annotations.classes.ui.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.project.annotations.classes.constants.general.GeneralConstants.DATA_PATTERN_PT_BR;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_APRIL;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_AUGUST;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_DECEMBER;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_FEBRUARY;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_JANUARY;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_JULY;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_JUNE;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_MARCH;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_MAY;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_NOVEMBER;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_OCTOBER;
import static com.project.annotations.classes.constants.general.GeneralConstants.MONTH_SEPTEMBER;

public class DataUtil {


    public static String devolveStringCalendar(Calendar noteData) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat(DATA_PATTERN_PT_BR);
        df.format(noteData.getTime());
        String month = null;
        int d = noteData.get(Calendar.DAY_OF_MONTH);
        int m = noteData.get(Calendar.MONTH);
        int h = noteData.get(Calendar.HOUR_OF_DAY);
        int min = noteData.get(Calendar.MINUTE);
        if (m == 1) {
            month = MONTH_JANUARY;
        }
        if (m == 2) {
            month = MONTH_FEBRUARY;
        }
        if (m == 3) {
            month = MONTH_MARCH;
        }
        if (m == 4) {
            month = MONTH_APRIL;
        }
        if (m == 5) {
            month = MONTH_MAY;
        }
        if (m == 6) {
            month = MONTH_JUNE;
        }
        if (m == 7) {
            month = MONTH_JULY;
        }
        if (m == 8) {
            month = MONTH_AUGUST;
        }
        if (m == 9) {
            month = MONTH_SEPTEMBER;
        }
        if (m == 10) {
            month = MONTH_OCTOBER;
        }
        if (m == 11) {
            month = MONTH_NOVEMBER;
        }
        if (m == 12) {
            month = MONTH_DECEMBER;
        }
        return d + " de " + month + " " + h + ":" + min;
    }
}
