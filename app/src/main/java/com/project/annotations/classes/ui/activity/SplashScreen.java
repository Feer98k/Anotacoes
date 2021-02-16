package com.project.annotations.classes.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.anotacoes.R;

import static com.project.annotations.classes.constants.general.GeneralConstants.FIRST;
import static com.project.annotations.classes.constants.sharedPreference.LayoutPreference.USER_PREFERENCES;


@SuppressWarnings("deprecation")
public class SplashScreen extends AppCompatActivity {

    private final Handler handle = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        check(handle, preferences);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }

    }

    private void check(Handler handle, SharedPreferences preferences) {

        if (!preferences.contains(FIRST)) {
            createPreferences(preferences);
            handle.postDelayed(this::startListNotes, 2000);

        } else {
            handle.postDelayed(this::startListNotes, 500);
        }
    }

    public void startListNotes() {
        Intent intent = new Intent(this, NotesList.class);
        startActivity(intent);
        finish();
    }

    private void createPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST, true);
        editor.apply();
    }


}