package com.projeto.anotacoes.classes.ui.adapter.listaTarefasAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.projeto.anotacoes.classes.database.Database;
import com.projeto.anotacoes.classes.database.dao.SubTarefaDAO;
import com.projeto.anotacoes.classes.model.SubTarefa;
import com.projeto.anotacoes.classes.model.Tarefa;
import com.projeto.anotacoes.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.List;

public class ListTarefasViewAdapter extends BaseAdapter {
    Context context;
    List<SubTarefa> subTarefaLista;
    Tarefa tarefa;

    public ListTarefasViewAdapter(Context context, List<SubTarefa> subTarefaLista,Tarefa tarefa) {
        this.context = context;
        this.subTarefaLista = subTarefaLista;
        this.tarefa = tarefa;
    }

    @Override
    public int getCount() {
        return subTarefaLista.size();
    }

    @Override
    public SubTarefa getItem(int position) {
        return subTarefaLista.get(position);
    }

    @Override
    public long getItemId(int position) {
        SubTarefa sub = subTarefaLista.get(position);
        return sub.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View inflate = LayoutInflater
                .from(context)
                .inflate(R.layout.item_sub_tarefa_2, parent, false);
        SubTarefa subTarefa = subTarefaLista.get(position);
        TextView textView = inflate.findViewById(R.id.item_subTarefa_textView_2);
        CheckBox checkBox = inflate.findViewById(R.id.item_subTarefa_checkBox);

        if(subTarefa.isCompletado()){
            checkBox.setChecked(true);
            textView.setForeground(ContextCompat.getDrawable(context,R.drawable.strike_for_fonts));
            textView.setTextColor(ContextCompat.getColor(context,R.color.ITEM_CHECKED));
        }
        textView.setText(subTarefa.getDescricaoTarefa());


        return inflate;
    }
}
