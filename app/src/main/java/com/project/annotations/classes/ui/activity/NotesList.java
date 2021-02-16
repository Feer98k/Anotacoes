package com.project.annotations.classes.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.anotacoes.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.project.annotations.classes.database.Database;
import com.project.annotations.classes.database.dao.NoteDAO;
import com.project.annotations.classes.model.Note;
import com.project.annotations.classes.ui.adapter.listNotesAdapter.NoteItemTouchHelper;
import com.project.annotations.classes.ui.adapter.listNotesAdapter.RecyclerViewNotesAdapter;

import java.util.List;

import static com.project.annotations.classes.constants.general.GeneralConstants.NEW_INTENT;
import static com.project.annotations.classes.constants.general.GeneralConstants.NOTE;
import static com.project.annotations.classes.constants.sharedPreference.LayoutPreference.GRID;
import static com.project.annotations.classes.constants.sharedPreference.LayoutPreference.LINEAR;
import static com.project.annotations.classes.constants.sharedPreference.LayoutPreference.USER_PREFERENCES;


public class NotesList extends AppCompatActivity {


    private RecyclerView recyclerViewListNotes;
    private RecyclerViewNotesAdapter noteListAdapter;
    private NoteDAO noteDAO;

    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SharedPreferences preferences;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notes_list);
        super.onCreate(savedInstanceState);
        setList();
        startLayoutManagerSet();
        checkPreferenceLayout();
        onItemNoteClick();
        btnForm();
    }


    @Override
    protected void onResume() {
        refreshList();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_lista, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem linear = menu.findItem(R.id.menu_linear);
        MenuItem grid = menu.findItem(R.id.menu_grid);
        visibilityLogicMenuItem(linear, grid);
        linearItemMenuClick(linear, grid);
        gridItemMenuClick(linear, grid);


        return true;
    }

    private void visibilityLogicMenuItem(MenuItem linear, MenuItem grid) {
        if (preferences.getBoolean(LINEAR, true)) {
            linear.setVisible(false);
            grid.setVisible(true);
        } else if (preferences.getBoolean(GRID, true)) {
            linear.setVisible(true);
            grid.setVisible(false);
        }
    }

    private void linearItemMenuClick(MenuItem linear, MenuItem grid) {
        linear.setOnMenuItemClickListener(item -> {
            createLinear(preferences);
            setToLinear();
            linear.setVisible(false);
            grid.setVisible(true);
            return false;
        });
    }

    private void gridItemMenuClick(MenuItem linear, MenuItem grid) {
        grid.setOnMenuItemClickListener(item -> {
            createGrid(preferences);
            setToGrid();
            linear.setVisible(true);
            grid.setVisible(false);
            return false;
        });
    }

    private void startLayoutManagerSet() {
        linearLayoutManager =
                new LinearLayoutManager(this);
        staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    private synchronized void setList() {
        noteDAO = Database.getInstance(this).getNotaDataDao();
        notesList = noteDAO.allNote();
        recyclerViewListNotes = findViewById(R.id.notes_list_recyclerView);
        noteListAdapter = new RecyclerViewNotesAdapter(this, notesList, noteDAO);
        recyclerViewListNotes.setAdapter(noteListAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchHelper(noteListAdapter, notesList, noteDAO));
        itemTouchHelper.attachToRecyclerView(recyclerViewListNotes);
        setTitle(NOTE);

    }


    private void btnForm() {
        final FloatingActionMenu fabMenu = findViewById(R.id.notes_list_menu_fab);
        FloatingActionButton fabNewNote = findViewById(R.id.notes_list_new_note_fab);
        FloatingActionButton fabNewTask = findViewById(R.id.notes_list_new_task_fab);
        ImageButton btnTask = findViewById(R.id.notes_list_list_tasks_button);
        btnTask.setOnClickListener(v -> tasksIntent());
        fabMenu.setClosedOnTouchOutside(true);
        fabNewNote.setOnClickListener(v -> {
            intentFormNote();
            fabMenu.close(true);
        });
        fabNewTask.setOnClickListener(v -> {
            newTaskIntent();
            fabMenu.close(true);
        });
    }

    private void intentFormNote() {
        Intent intent = new Intent(getApplicationContext(), NotesForm.class);
        startActivity(intent);

    }

    private void newTaskIntent() {
        Intent intent = new Intent(getApplicationContext(), TaskForm.class);
        startActivity(intent);
    }

    private void tasksIntent() {
        Intent intent = new Intent(getApplicationContext(), TaskList.class);
        startActivity(intent);
    }

    private void onItemNoteClick() {
        noteListAdapter.setOnClickListener(note -> {
            refreshList();
            Intent intent = (new Intent(getApplicationContext(), NotesForm.class));
            intent.putExtra(NEW_INTENT, note);
            startActivity(intent);
            Log.d("TAG", "onItemNoteClick: " + note.getDataEdicao().getTime());
        });
    }

    private void createLinear(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LINEAR, true);
        editor.putBoolean(GRID, false);
        editor.apply();
    }

    private void createGrid(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LINEAR, false);
        editor.putBoolean(GRID, true);

        editor.apply();
    }

    public void setToLinear() {
        recyclerViewListNotes.setLayoutManager(linearLayoutManager);
    }

    public void setToGrid() {
        recyclerViewListNotes.setLayoutManager(staggeredGridLayoutManager);
    }

    private void checkPreferenceLayout() {
        preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        boolean linearPreference = preferences.getBoolean(LINEAR, true);
        if (linearPreference) {
            setToLinear();
        } else {
            setToGrid();
        }
    }

    private void refreshList() {
        notesList.clear();
        notesList.addAll(noteDAO.allNote());
        noteListAdapter.notifyDataSetChanged();
    }

}