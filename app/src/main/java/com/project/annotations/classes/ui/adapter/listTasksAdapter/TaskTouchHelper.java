package com.project.annotations.classes.ui.adapter.listTasksAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.project.annotations.classes.database.dao.TaskDAO;
import com.project.annotations.classes.model.Task;

import java.util.List;

public class TaskTouchHelper extends ItemTouchHelper.Callback {
    private final RecyclerViewTasksAdapter recyclerViewTasksAdapter;
    private final List<Task> taskList;
    private final TaskDAO taskDAO;

    public TaskTouchHelper(RecyclerViewTasksAdapter recyclerViewTasksAdapter, List<Task> taskList, TaskDAO taskDAO) {
        this.recyclerViewTasksAdapter = recyclerViewTasksAdapter;
        this.taskList = taskList;
        this.taskDAO = taskDAO;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int movement = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int allMovement = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(allMovement, movement);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int first = viewHolder.getBindingAdapterPosition();
        int targetInt = target.getBindingAdapterPosition();
        recyclerViewTasksAdapter.swap(first, targetInt);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicao = viewHolder.getBindingAdapterPosition();
        Task task = taskList.get(posicao);
        taskDAO.remove(task);
        recyclerViewTasksAdapter.remove(posicao);

    }
}
