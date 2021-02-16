package com.project.annotations.classes.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.anotacoes.R;
import com.project.annotations.classes.database.Database;
import com.project.annotations.classes.database.dao.SubTaskDAO;
import com.project.annotations.classes.database.dao.TaskDAO;
import com.project.annotations.classes.model.SubTask;
import com.project.annotations.classes.model.Task;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.project.annotations.classes.constants.general.GeneralConstants.EDIT_TASK;
import static com.project.annotations.classes.constants.general.GeneralConstants.NEW_TASK;
import static com.project.annotations.classes.constants.general.GeneralConstants.SUB_TASKS_LIMIT;
import static com.project.annotations.classes.constants.general.GeneralConstants.TASK_INTENT;

public class TaskForm extends AppCompatActivity {

    private EditText titleEditField;


    private TaskDAO taskDAO;
    private SubTaskDAO subTaskDAO;
    private Task task;
    private List<SubTask> subTaskList;

    private SubTask subTask1, subTask2, subTask3, subTask4, subTask5, subTask6, subTask7,
            subTask8, subTask9, subTask10, subTask11, subTask12;
    private EditText subTaskEditField1, subTaskEditField2, subTaskEditField3, subTaskEditField4,
            subTaskEditField5, subTaskEditField6, subTaskEditField7, subTaskEditField8,
            subTaskEditField9, subTaskEditField10, subTaskEditField11, subTaskEditField12;
    private ImageButton btnAdd1, btnAdd2, btnAdd3, btnAdd4, btnAdd5, btnAdd6, btnAdd7,
            btnAdd8, btnAdd9, btnAdd10, btnAdd11, btnAdd12;
    private ImageButton btnDell1, btnDell2, btnDell3, btnDell4, btnDell5, btnDell6, btnDell7,
            btnDell8, btnDell9, btnDell10, btnDell11, btnDell12;

    @Override
    protected void onResume() {
        hasTask();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_task);
        loadingDatabase();
        loadingAllComponents();
        setStatusBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        btnInsert();
        Intent intent = new Intent(getApplicationContext(), TaskList.class);
        startActivity(intent);
        finish();
        return true;
    }

    public void loadingDatabase() {
        taskDAO = Database.getInstance(this).getTarefaDao();
        subTaskDAO = Database.getInstance(this).getSubTarefaDao();

    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }
    }

    public void loadingAllComponents() {
        titleEditField = findViewById(R.id.form_task_title_editText);
        subTaskEditField1 = findViewById(R.id.form_task_subTask_editText_1);
        subTaskEditField2 = findViewById(R.id.form_task_subTask_editText_2);
        subTaskEditField3 = findViewById(R.id.form_task_subTask_editText_3);
        subTaskEditField4 = findViewById(R.id.form_task_subTask_editText_4);
        subTaskEditField5 = findViewById(R.id.form_task_subTask_editText_5);
        subTaskEditField6 = findViewById(R.id.form_task_subTask_editText_6);
        subTaskEditField7 = findViewById(R.id.form_task_subTask_editText_7);
        subTaskEditField8 = findViewById(R.id.form_task_subTask_editText_8);
        subTaskEditField9 = findViewById(R.id.form_task_subTask_editText_9);
        subTaskEditField10 = findViewById(R.id.form_task_subTask_editText_10);
        subTaskEditField11 = findViewById(R.id.form_task_subTask_editText_11);
        subTaskEditField12 = findViewById(R.id.form_task_subTask_editText_12);
        btnAdd1 = findViewById(R.id.form_task_add_button_1);
        btnAdd2 = findViewById(R.id.form_task_add_button_2);
        btnAdd3 = findViewById(R.id.form_task_add_button_3);
        btnAdd4 = findViewById(R.id.form_task_add_button_4);
        btnAdd5 = findViewById(R.id.form_task_add_button_5);
        btnAdd6 = findViewById(R.id.form_task_add_button_6);
        btnAdd7 = findViewById(R.id.form_task_add_button_7);
        btnAdd8 = findViewById(R.id.form_task_add_button_8);
        btnAdd9 = findViewById(R.id.form_task_add_button_9);
        btnAdd10 = findViewById(R.id.form_task_add_button_10);
        btnAdd11 = findViewById(R.id.form_task_add_button_11);
        btnAdd12 = findViewById(R.id.form_task_add_button_12);
        btnDell1 = findViewById(R.id.form_task_dell_button_1);
        btnDell2 = findViewById(R.id.form_task_dell_button_2);
        btnDell3 = findViewById(R.id.form_task_dell_button_3);
        btnDell4 = findViewById(R.id.form_task_dell_button_4);
        btnDell5 = findViewById(R.id.form_task_dell_button_5);
        btnDell6 = findViewById(R.id.form_task_dell_button_6);
        btnDell7 = findViewById(R.id.form_task_dell_button_7);
        btnDell8 = findViewById(R.id.form_task_dell_button_8);
        btnDell9 = findViewById(R.id.form_task_dell_button_9);
        btnDell10 = findViewById(R.id.form_task_dell_button_10);
        btnDell11 = findViewById(R.id.form_task_dell_button_11);
        btnDell12 = findViewById(R.id.form_task_dell_button_12);
        subTask1 = new SubTask();
        subTask2 = new SubTask();
        subTask3 = new SubTask();
        subTask4 = new SubTask();
        subTask5 = new SubTask();
        subTask6 = new SubTask();
        subTask7 = new SubTask();
        subTask8 = new SubTask();
        subTask9 = new SubTask();
        subTask10 = new SubTask();
        subTask11 = new SubTask();
        subTask12 = new SubTask();
        btnAddItemClick();
        btnDelItemClick();
    }

    public void btnAddItemClick() {
        btnAdd1.setOnClickListener(v -> {
            subTaskEditField2.setVisibility(VISIBLE);
            btnAdd2.setVisibility(VISIBLE);
            btnDell2.setVisibility(VISIBLE);

        });
        btnAdd2.setOnClickListener(v -> {
            subTaskEditField3.setVisibility(VISIBLE);
            btnAdd3.setVisibility(VISIBLE);
            btnDell3.setVisibility(VISIBLE);

        });
        btnAdd3.setOnClickListener(v -> {
            subTaskEditField4.setVisibility(VISIBLE);
            btnAdd4.setVisibility(VISIBLE);
            btnDell4.setVisibility(VISIBLE);

        });
        btnAdd4.setOnClickListener(v -> {
            subTaskEditField5.setVisibility(VISIBLE);
            btnAdd5.setVisibility(VISIBLE);
            btnDell5.setVisibility(VISIBLE);

        });
        btnAdd5.setOnClickListener(v -> {
            subTaskEditField6.setVisibility(VISIBLE);
            btnAdd6.setVisibility(VISIBLE);
            btnDell6.setVisibility(VISIBLE);

        });
        btnAdd6.setOnClickListener(v -> {
            subTaskEditField7.setVisibility(VISIBLE);
            btnAdd7.setVisibility(VISIBLE);
            btnDell7.setVisibility(VISIBLE);

        });
        btnAdd7.setOnClickListener(v -> {
            subTaskEditField8.setVisibility(VISIBLE);
            btnAdd8.setVisibility(VISIBLE);
            btnDell8.setVisibility(VISIBLE);

        });
        btnAdd8.setOnClickListener(v -> {
            subTaskEditField9.setVisibility(VISIBLE);
            btnAdd9.setVisibility(VISIBLE);
            btnDell9.setVisibility(VISIBLE);

        });
        btnAdd9.setOnClickListener(v -> {
            subTaskEditField10.setVisibility(VISIBLE);
            btnAdd10.setVisibility(VISIBLE);
            btnDell10.setVisibility(VISIBLE);

        });
        btnAdd10.setOnClickListener(v -> {
            subTaskEditField11.setVisibility(VISIBLE);
            btnAdd11.setVisibility(VISIBLE);
            btnDell11.setVisibility(VISIBLE);
        });
        btnAdd11.setOnClickListener(v -> {
            subTaskEditField12.setVisibility(VISIBLE);
            btnAdd12.setVisibility(VISIBLE);
            btnDell12.setVisibility(VISIBLE);
        });
        btnAdd12.setOnClickListener(v -> Toast.makeText(this, SUB_TASKS_LIMIT, Toast.LENGTH_SHORT).show());

    }

    public void btnDelItemClick() {
        btnDell1.setOnClickListener(v -> {
            subTaskEditField1.setText(null);
            subTaskDAO.remove(subTask1);
            subTaskList.remove(subTask1);
        });
        btnDell2.setOnClickListener(v -> {
            subTaskEditField2.setText(null);
            subTaskEditField2.setVisibility(INVISIBLE);
            btnDell2.setVisibility(INVISIBLE);
            btnAdd2.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask2);
            subTaskList.remove(subTask2);
        });
        btnDell3.setOnClickListener(v -> {
            subTaskEditField3.setText(null);
            subTaskEditField3.setVisibility(INVISIBLE);
            btnDell3.setVisibility(INVISIBLE);
            btnAdd3.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask3);
            subTaskList.remove(subTask3);
        });
        btnDell4.setOnClickListener(v -> {
            subTaskEditField4.setText(null);
            subTaskEditField4.setVisibility(INVISIBLE);
            btnDell4.setVisibility(INVISIBLE);
            btnAdd4.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask4);
            subTaskList.remove(subTask4);
        });
        btnDell5.setOnClickListener(v -> {
            subTaskEditField5.setText(null);
            subTaskEditField5.setVisibility(INVISIBLE);
            btnDell5.setVisibility(INVISIBLE);
            btnAdd5.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask5);
            subTaskList.remove(subTask5);
        });
        btnDell6.setOnClickListener(v -> {
            subTaskEditField6.setText(null);
            subTaskEditField6.setVisibility(INVISIBLE);
            btnDell6.setVisibility(INVISIBLE);
            btnAdd6.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask6);
            subTaskList.remove(subTask6);
        });
        btnDell7.setOnClickListener(v -> {
            subTaskEditField7.setText(null);
            subTaskEditField7.setVisibility(INVISIBLE);
            btnDell7.setVisibility(INVISIBLE);
            btnAdd7.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask7);
            subTaskList.remove(subTask7);
        });
        btnDell8.setOnClickListener(v -> {
            subTaskEditField8.setText(null);
            subTaskEditField8.setVisibility(INVISIBLE);
            btnDell8.setVisibility(INVISIBLE);
            btnAdd8.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask8);
            subTaskList.remove(subTask8);
        });
        btnDell9.setOnClickListener(v -> {
            subTaskEditField9.setText(null);
            subTaskEditField9.setVisibility(INVISIBLE);
            btnDell9.setVisibility(INVISIBLE);
            btnAdd9.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask9);
            subTaskList.remove(subTask9);
        });
        btnDell10.setOnClickListener(v -> {
            subTaskEditField10.setText(null);
            subTaskEditField10.setVisibility(INVISIBLE);
            btnDell10.setVisibility(INVISIBLE);
            btnAdd10.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask10);
            subTaskList.remove(subTask10);
        });
        btnDell11.setOnClickListener(v -> {
            subTaskEditField11.setText(null);
            subTaskEditField11.setVisibility(INVISIBLE);
            btnDell11.setVisibility(INVISIBLE);
            btnAdd11.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask11);
            subTaskList.remove(subTask11);
        });
        btnDell12.setOnClickListener(v -> {
            subTaskEditField12.setText(null);
            subTaskEditField12.setVisibility(INVISIBLE);
            btnDell12.setVisibility(INVISIBLE);
            btnAdd12.setVisibility(INVISIBLE);
            subTaskDAO.remove(subTask12);
            subTaskList.remove(subTask12);
        });
    }

    public void hasTask() {

        Intent intent = getIntent();
        if (intent.hasExtra(TASK_INTENT)) {
            int posicao = (int) intent.getSerializableExtra(TASK_INTENT);
            task = taskDAO.getAllTasks().get(posicao);
            setTitle(EDIT_TASK);
            titleEditField.setText(task.getTitle());
            subTaskList = subTaskDAO.getSubTask(task.getId());
            tryFillFieldsByTaskList();
        } else {
            task = new Task();
            setTitle(NEW_TASK);
            subTaskList = new ArrayList<>();
        }
    }

    private void tryFillFieldsByTaskList() {
        try {
            subTask1 = subTaskList.get(0);
            if (subTask1 != null) {
                subTaskEditField1.setText(subTask1.getTaskDescription());
            }
        } catch (IndexOutOfBoundsException ignored) {

        }

        try {
            subTask2 = subTaskList.get(1);
            if (subTask2 != null) {
                subTaskEditField2.setText(subTask2.getTaskDescription());
                btnAdd2.setVisibility(VISIBLE);
                subTaskEditField2.setVisibility(VISIBLE);
                btnDell2.setVisibility(VISIBLE);
            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask3 = subTaskList.get(2);
            if (subTask3 != null) {
                subTaskEditField3.setText(subTask3.getTaskDescription());
                btnAdd3.setVisibility(VISIBLE);
                subTaskEditField3.setVisibility(VISIBLE);
                btnDell3.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask4 = subTaskList.get(3);
            if (subTask4 != null) {
                subTaskEditField4.setText(subTask4.getTaskDescription());
                btnAdd4.setVisibility(VISIBLE);
                subTaskEditField4.setVisibility(VISIBLE);
                btnDell4.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask5 = subTaskList.get(4);
            if (subTask5 != null) {
                subTaskEditField5.setText(subTask5.getTaskDescription());
                btnAdd5.setVisibility(VISIBLE);
                subTaskEditField5.setVisibility(VISIBLE);
                btnDell5.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask6 = subTaskList.get(5);
            if (subTask6 != null) {
                subTaskEditField6.setText(subTask6.getTaskDescription());
                btnAdd6.setVisibility(VISIBLE);
                subTaskEditField6.setVisibility(VISIBLE);
                btnDell6.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask7 = subTaskList.get(6);
            if (subTask7 != null) {
                subTaskEditField7.setText(subTask7.getTaskDescription());
                btnAdd7.setVisibility(VISIBLE);
                subTaskEditField7.setVisibility(VISIBLE);
                btnDell7.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }

        try {
            subTask8 = subTaskList.get(7);
            if (subTask8 != null) {
                subTaskEditField8.setText(subTask8.getTaskDescription());
                btnAdd8.setVisibility(VISIBLE);
                subTaskEditField8.setVisibility(VISIBLE);
                btnDell8.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask9 = subTaskList.get(8);
            if (subTask9 != null) {
                subTaskEditField9.setText(subTask9.getTaskDescription());
                btnAdd9.setVisibility(VISIBLE);
                subTaskEditField9.setVisibility(VISIBLE);
                btnDell9.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask10 = subTaskList.get(9);
            if (subTask10 != null) {
                subTaskEditField10.setText(subTask10.getTaskDescription());
                btnAdd10.setVisibility(VISIBLE);
                subTaskEditField10.setVisibility(VISIBLE);
                btnDell10.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask11 = subTaskList.get(10);
            if (subTask11 != null) {
                subTaskEditField11.setText(subTask11.getTaskDescription());
                btnAdd11.setVisibility(VISIBLE);
                subTaskEditField11.setVisibility(VISIBLE);
                btnDell11.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
        try {
            subTask12 = subTaskList.get(11);
            if (subTask12 != null) {
                subTaskEditField12.setText(subTask12.getTaskDescription());
                btnAdd12.setVisibility(VISIBLE);
                subTaskEditField12.setVisibility(VISIBLE);
                btnDell12.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException ignored) {

        }
    }


    private synchronized void btnInsert() {
        fillSubTasks();
        if (task.getId() == 0) {
            int posicao = taskDAO.getAllTasks().size();
            task.setPosition(posicao);
            task.setTotalTasks(subTaskList.size());
            int taskID = taskDAO.insert(task).intValue();
            for (SubTask sub : subTaskList) {
                sub.setTaskID(taskID);
                sub.setCompletedTask(false);
                sub.setSubTaskPosition(subTaskDAO.getSubTask(task.getId()).size());
                subTaskDAO.insert(sub);
            }
        }

        if (task.getId() != 0) {
            taskDAO.update(task);
            for (SubTask sub : subTaskList) {
                sub.setTaskID(task.getId());
                subTaskDAO.remove(sub);
                subTaskDAO.insert(sub);
            }
        }
        subTaskList = subTaskDAO.getSubTask(task.getId());

        task.setTotalTasks(subTaskList.size());
        taskDAO.update(task);
        finish();
    }

    private void fillSubTasks() {
        task.setTitle(titleEditField.getText().toString());
        if (!subTaskEditField1.getText().toString().isEmpty()) {
            subTask1.setTaskDescription(subTaskEditField1.getText().toString());
            subTaskList.add(subTask1);
        }
        if (!subTaskEditField2.getText().toString().isEmpty()) {
            subTask2.setTaskDescription(subTaskEditField2.getText().toString());
            subTaskList.add(subTask2);
        }
        if (!subTaskEditField3.getText().toString().isEmpty()) {
            subTask3.setTaskDescription(subTaskEditField3.getText().toString());
            subTaskList.add(subTask3);
        }
        if (!subTaskEditField4.getText().toString().isEmpty()) {
            subTask4.setTaskDescription(subTaskEditField4.getText().toString());
            subTaskList.add(subTask4);
        }
        if (!subTaskEditField5.getText().toString().isEmpty()) {
            subTask5.setTaskDescription(subTaskEditField5.getText().toString());
            subTaskList.add(subTask5);
        }
        if (!subTaskEditField6.getText().toString().isEmpty()) {
            subTask6.setTaskDescription(subTaskEditField6.getText().toString());
            subTaskList.add(subTask6);
        }
        if (!subTaskEditField7.getText().toString().isEmpty()) {
            subTask7.setTaskDescription(subTaskEditField7.getText().toString());
            subTaskList.add(subTask7);
        }
        if (!subTaskEditField8.getText().toString().isEmpty()) {
            subTask8.setTaskDescription(subTaskEditField8.getText().toString());
            subTaskList.add(subTask8);
        }
        if (!subTaskEditField9.getText().toString().isEmpty()) {
            subTask9.setTaskDescription(subTaskEditField9.getText().toString());
            subTaskList.add(subTask9);
        }
        if (!subTaskEditField10.getText().toString().isEmpty()) {
            subTask10.setTaskDescription(subTaskEditField10.getText().toString());
            subTaskList.add(subTask10);
        }
        if (!subTaskEditField11.getText().toString().isEmpty()) {
            subTask11.setTaskDescription(subTaskEditField11.getText().toString());
            subTaskList.add(subTask11);
        }
        if (!subTaskEditField12.getText().toString().isEmpty()) {
            subTask12.setTaskDescription(subTaskEditField12.getText().toString());
            subTaskList.add(subTask12);
        }
    }

}