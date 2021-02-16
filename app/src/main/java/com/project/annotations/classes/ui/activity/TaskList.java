package com.project.annotations.classes.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.annotations.classes.database.Database;
import com.project.annotations.classes.database.dao.TaskDAO;
import com.project.annotations.classes.model.Task;
import com.project.annotations.classes.ui.adapter.listTasksAdapter.RecyclerViewTasksAdapter;
import com.project.annotations.classes.ui.adapter.listTasksAdapter.TaskTouchHelper;

import java.util.List;

import static com.project.annotations.classes.constants.general.GeneralConstants.TASKS;

public class TaskList extends AppCompatActivity {

    private RecyclerViewTasksAdapter recyclerViewTasksAdapter;
    private List<Task> taskList;
    private TaskDAO taskDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        setTitle(TASKS);
        btnMenus();
        setList();
        setStatusBarColor();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
        fabAdd();

    }

    public void refreshList() {
        taskList.clear();
        taskList.addAll(taskDAO.getAllTasks());
        recyclerViewTasksAdapter.notifyDataSetChanged();
    }

    public void fabAdd() {
        FloatingActionButton fabAddNewTask = findViewById(R.id.tasks_list_add_fab);
        fabAddNewTask.setOnClickListener(v -> {
            Intent newTask = new Intent(getApplicationContext(), TaskForm.class);
            startActivity(newTask);
        });
    }

    public void btnMenus() {
        ImageButton btnNote = findViewById(R.id.tasks_list_btn_list_tasks_button);
        btnNote.setOnClickListener(v -> intentToNotes());
    }

    public void intentToNotes() {
        Intent intent = new Intent(getApplicationContext(), NotesList.class);
        startActivity(intent);
        finish();
    }

    public void setList() {
        taskDAO = Database.getInstance(this).getTarefaDao();
        Database.getInstance(this).getSubTarefaDao();
        taskList = taskDAO.getAllTasks();
        recyclerViewTasksAdapter = new RecyclerViewTasksAdapter(this, taskList);
        RecyclerView recyclerViewTask = findViewById(R.id.tasks_list_recyclerView_tasks);
        recyclerViewTask.setAdapter(recyclerViewTasksAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TaskTouchHelper(recyclerViewTasksAdapter, taskList, taskDAO));
        itemTouchHelper.attachToRecyclerView(recyclerViewTask);

    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }
    }


}
