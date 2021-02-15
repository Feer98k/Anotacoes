package com.projeto.anotacoes.classes.ui.adapter.listaTarefasAdapter;

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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.projeto.anotacoes.classes.database.Database;
import com.projeto.anotacoes.classes.database.dao.SubTarefaDAO;
import com.projeto.anotacoes.classes.database.dao.TarefaDao;
import com.projeto.anotacoes.classes.model.SubTarefa;
import com.projeto.anotacoes.classes.model.Tarefa;
import com.projeto.anotacoes.classes.ui.activity.TarefaFormulario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.TAREFA_INTENT;

public class RecyclerTarefasAdapter extends RecyclerView.Adapter<RecyclerTarefasAdapter.TarefaHolder> {


    private final Context context;
    private final List<Tarefa> listaTarefas;
    private List<SubTarefa> listaSubTarefas;
    TarefaDao dao;
    SubTarefaDAO subTarefaDAO;
    Tarefa tarefa;

    public RecyclerTarefasAdapter(Context context, List<Tarefa> listaTarefas, List<SubTarefa> listaSubTarefas) {
        this.context = context;
        this.listaTarefas = listaTarefas;
        this.listaSubTarefas = listaSubTarefas;
        this.dao = Database.getInstance(context).getTarefaDao();
        this.subTarefaDAO = Database.getInstance(context).getSubTarefaDao();
    }

    @NonNull
    @Override
    public TarefaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater
                .from(context)
                .inflate(R.layout.item_tarefa, parent, false);
        return new TarefaHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull TarefaHolder holder, int position) {
        tarefa = listaTarefas.get(position);
        listaSubTarefas = subTarefaDAO.getSubTarefas(tarefa.getId());
        holder.fabEditarTarefa.setOnClickListener(v -> {
            intentTarefaFornulario(position);
        });

        holder.vincula(tarefa);
    }

    private void intentTarefaFornulario(int posicao) {
        Intent intent = new Intent(context, TarefaFormulario.class);
        intent.putExtra(TAREFA_INTENT, posicao);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public void swap(int first, int targeter) {
        Tarefa primeiro = listaTarefas.get(first);
        Tarefa segundo = listaTarefas.get(targeter);
        int rec = primeiro.getPosicao();
        primeiro.setPosicao(segundo.getPosicao());
        segundo.setPosicao(rec);
        Collections.swap(listaTarefas, first, targeter);
        dao.update(primeiro);
        dao.update(segundo);
        notifyItemMoved(first, targeter);
    }

    public void remove(int posicao) {
        Tarefa tarefa = listaTarefas.get(posicao);
        for (Tarefa a : listaTarefas) {
            if (a.getPosicao() > tarefa.getPosicao()) {
                a.setPosicao(a.getPosicao() - 1);
                dao.update(a);
            }
        }
        notifyItemRemoved(posicao);
        notifyDataSetChanged();
        dao.remove(tarefa);
        listaTarefas.remove(posicao);
    }


    public class TarefaHolder extends RecyclerView.ViewHolder {
        ImageButton fabEditarTarefa;
        TextView titulo;
        ListView listViewSubTarefas;
        CardView cardView;
        TextView campoSubTarefa;
        CheckBox checkBox;
        TextView totalTarefas;
        TextView totalTarefasCompletados;
        TextView divisor;

        public TarefaHolder(@NonNull View itemView) {
            super(itemView);
            fabEditarTarefa = itemView.findViewById(R.id.item_tarefa_editar_tarefa);
            titulo = itemView.findViewById(R.id.item_tarefa_titulo);
            listViewSubTarefas = itemView.findViewById(R.id.item_tarefa_lista_sub_tarefas);
            cardView = itemView.findViewById(R.id.item_tarefa_cardView);
            totalTarefas = itemView.findViewById(R.id.item_tarefa_total_subTarefa);
            divisor = itemView.findViewById(R.id.item_tarefa_barra);
            campoSubTarefa = itemView.findViewById(R.id.item_subTarefa_textView_2);
            checkBox = itemView.findViewById(R.id.item_subTarefa_checkBox);
            totalTarefasCompletados = itemView.findViewById(R.id.item_tarefa_total_subTarefas_completadas);
        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        public void vincula(Tarefa tarefa) {
            List<SubTarefa> listaTotalCompletados = new ArrayList<>();
            titulo.setText(tarefa.getTitulo());
            totalTarefas.setText(String.valueOf(tarefa.getTotalTarefas()));
            divisor.setText("/");
            listViewSubTarefas.setAdapter(new ListTarefasViewAdapter(context, listaSubTarefas, tarefa));
            setSubTarefaCompletado(listaTotalCompletados);

            onItemClick();
            totalTarefasCompletados.setText(String.valueOf(listaTotalCompletados.size()));
        }

        private void setSubTarefaCompletado(List<SubTarefa> listaTotalCompletados) {
            for (SubTarefa sub : listaSubTarefas) {
                if (sub.isCompletado()) {
                    listaTotalCompletados.add(sub);
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void onItemClick() {
            listViewSubTarefas.setOnItemClickListener((parent, view, position, id) -> {
                SubTarefa subTarefa = (SubTarefa) parent.getItemAtPosition(position);
                TextView textView = view.findViewById(R.id.item_subTarefa_textView_2);
                CheckBox checkBox = view.findViewById(R.id.item_subTarefa_checkBox);

                if (!subTarefa.isCompletado()) {
                    subTarefa.setCompletado(true);
                    textView.setForeground(ContextCompat.getDrawable(context, R.drawable.strike_for_fonts));
                    for (SubTarefa a : listaSubTarefas
                    ) {
                        if (a.getPosicaoSubTarefa() > subTarefa.getPosicaoSubTarefa()) {
                            a.setPosicaoSubTarefa(a.getPosicaoSubTarefa() - 1);
                            subTarefaDAO.update(a);
                        }
                        subTarefa.setPosicaoSubTarefa(listaSubTarefas.size());
                    }
                } else {
                    subTarefa.setCompletado(false);
                    textView.setForeground(ContextCompat.getDrawable(context, R.drawable.strike_for_fonts_invisible));
                    checkBox.setChecked(false);
                    for (SubTarefa a : listaSubTarefas
                    ) {
                        if (a.isCompletado())
                            a.setPosicaoSubTarefa(a.getPosicaoSubTarefa() + 1);
                        subTarefa.setPosicaoSubTarefa(subTarefa.getPosicaoSubTarefa() - 1);
                        subTarefaDAO.update(a);
                    }
                }
                subTarefaDAO.update(subTarefa);
                notifyDataSetChanged();
            });
        }


    }

}


