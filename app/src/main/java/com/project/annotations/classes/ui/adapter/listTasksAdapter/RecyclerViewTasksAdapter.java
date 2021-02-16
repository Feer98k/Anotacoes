package com.project.annotations.classes.ui.adapter.listTasksAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.project.annotations.classes.database.Database;
import com.project.annotations.classes.database.dao.SubTaskDAO;
import com.project.annotations.classes.database.dao.TaskDAO;
import com.project.annotations.classes.model.SubTask;
import com.project.annotations.classes.model.Task;
import com.project.annotations.classes.ui.activity.TaskForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.project.annotations.classes.constants.general.GeneralConstants.TASK_INTENT;
import static com.project.annotations.classes.constants.general.GeneralConstants.TEXT_DIVIDER;

public class RecyclerViewTasksAdapter extends RecyclerView.Adapter<RecyclerViewTasksAdapter.TaskHolder> {


    private final Context context;
    private final List<Task> taskList;
    private List<SubTask> subTaskList;
    private final TaskDAO taskDAO;
    private final SubTaskDAO subTaskDAO;

    public RecyclerViewTasksAdapter(Context context, List<Task> listaTasks) {
        this.context = context;
        this.taskList = listaTasks;
        this.taskDAO = Database.getInstance(context).getTarefaDao();
        this.subTaskDAO = Database.getInstance(context).getSubTarefaDao();
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater
                .from(context)
                .inflate(R.layout.item_task, parent, false);
        return new TaskHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = taskList.get(position);
        subTaskList = subTaskDAO.getSubTask(task.getId());
        holder.editTaskButton.setOnClickListener(v -> toTaskForm(position));
        holder.bind(task);
    }

    private void toTaskForm(int position) {
        Intent intent = new Intent(context, TaskForm.class);
        intent.putExtra(TASK_INTENT, position);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void swap(int first, int target) {
        Task firstTask = taskList.get(first);
        Task secondTask = taskList.get(target);
        int rec = firstTask.getPosition();
        firstTask.setPosition(secondTask.getPosition());
        secondTask.setPosition(rec);
        Collections.swap(taskList, first, target);
        taskDAO.update(firstTask);
        taskDAO.update(secondTask);
        notifyItemMoved(first, target);
    }

    public void remove(int posicao) {
        Task task = taskList.get(posicao);
        for (Task a : taskList) {
            if (a.getPosition() > task.getPosition()) {
                a.setPosition(a.getPosition() - 1);
                taskDAO.update(a);
            }
        }
        notifyItemRemoved(posicao);
        notifyDataSetChanged();
        taskDAO.remove(task);
        taskList.remove(posicao);
    }


    public class TaskHolder extends RecyclerView.ViewHolder {
        private final ImageButton editTaskButton;
        private final TextView titleField;
        private final ListView subTaskListView;
        private final TextView taskSizeView;
        private final TextView subTasksCompletedSizeView;
        private final TextView divider;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            editTaskButton = itemView.findViewById(R.id.item_task_edit_subTask_button);
            titleField = itemView.findViewById(R.id.item_task_title_textView);
            subTaskListView = itemView.findViewById(R.id.item_task_list_subTasks_listView);
            taskSizeView = itemView.findViewById(R.id.item_task_total_subTasks_textView);
            divider = itemView.findViewById(R.id.item_task_divider);
            subTasksCompletedSizeView = itemView.findViewById(R.id.item_task_instance_completed_subTasks);
        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        public void bind(Task task) {
            List<SubTask> subTasksCompletedList = new ArrayList<>();
            titleField.setText(task.getTitle());
            taskSizeView.setText(String.valueOf(task.getTotalTasks()));
            divider.setText(TEXT_DIVIDER);
            subTaskListView.setAdapter(new ListViewTasksAdapter(context, subTaskList));

            setSubTaskCompleted(subTasksCompletedList);
            onItemClick();
            subTasksCompletedSizeView.setText(String.valueOf(subTasksCompletedList.size()));
        }

        private void setSubTaskCompleted(List<SubTask> list) {
            for (SubTask sub : subTaskList) {
                if (sub.isCompletedTask()) {
                    list.add(sub);
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void onItemClick() {
            subTaskListView.setOnItemClickListener((parent, view, position, id) -> {
                SubTask subTask = (SubTask) parent.getItemAtPosition(position);
                TextView textView = view.findViewById(R.id.item_sub_task_description_field_textView);
                CheckBox checkBox = view.findViewById(R.id.item_sub_task_checkbox);

                if (!subTask.isCompletedTask()) {
                    subTask.setCompletedTask(true);
                    textView.setForeground(ContextCompat.getDrawable(context, R.drawable.strike_for_fonts));
                    for (SubTask a : subTaskList
                    ) {
                        if (a.getSubTaskPosition() > subTask.getSubTaskPosition()) {
                            a.setSubTaskPosition(a.getSubTaskPosition() - 1);
                            subTaskDAO.update(a);
                        }
                        subTask.setSubTaskPosition(subTaskList.size());
                    }
                } else {
                    subTask.setCompletedTask(false);
                    textView.setForeground(ContextCompat.getDrawable(context, R.drawable.strike_for_fonts_invisible));
                    checkBox.setChecked(false);
                    for (SubTask a : subTaskList
                    ) {
                        if (a.isCompletedTask())
                            a.setSubTaskPosition(a.getSubTaskPosition() + 1);
                        subTask.setSubTaskPosition(subTask.getSubTaskPosition() - 1);
                        subTaskDAO.update(a);
                    }
                }
                subTaskDAO.update(subTask);
                notifyDataSetChanged();
            });
        }
    }

}


