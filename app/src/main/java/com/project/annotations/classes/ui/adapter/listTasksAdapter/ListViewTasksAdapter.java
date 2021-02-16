package com.project.annotations.classes.ui.adapter.listTasksAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.anotacoes.R;
import com.project.annotations.classes.model.SubTask;

import java.util.List;

public class ListViewTasksAdapter extends BaseAdapter {
    private final Context context;
    private final List<SubTask> subTaskList;

    public ListViewTasksAdapter(Context context, List<SubTask> subTaskList) {
        this.context = context;
        this.subTaskList = subTaskList;
    }

    @Override
    public int getCount() {
        return subTaskList.size();
    }

    @Override
    public SubTask getItem(int position) {
        return subTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        SubTask sub = subTaskList.get(position);
        return sub.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View inflate = LayoutInflater
                .from(context)
                .inflate(R.layout.item_sub_task_secondary, parent, false);

        SubTask subTask = subTaskList.get(position);
        TextView textView = inflate.findViewById(R.id.item_sub_task_description_field_textView);
        CheckBox checkBox = inflate.findViewById(R.id.item_sub_task_checkbox);
        changeTextView(subTask, textView, checkBox);
        textView.setText(subTask.getTaskDescription());
        return inflate;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeTextView(SubTask subTask, TextView textView, CheckBox checkBox) {
        if (subTask.isCompletedTask()) {
            checkBox.setChecked(true);
            textView.setForeground(ContextCompat.getDrawable(context, R.drawable.strike_for_fonts));
            textView.setTextColor(ContextCompat.getColor(context, R.color.ITEM_CHECKED));
        }
    }
}
