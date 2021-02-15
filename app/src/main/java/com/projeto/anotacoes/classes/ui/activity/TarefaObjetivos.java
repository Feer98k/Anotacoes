package com.projeto.anotacoes.classes.ui.activity;

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
import com.projeto.anotacoes.classes.database.Database;
import com.projeto.anotacoes.classes.database.dao.SubTarefaDAO;
import com.projeto.anotacoes.classes.database.dao.TarefaDao;
import com.projeto.anotacoes.classes.model.SubTarefa;
import com.projeto.anotacoes.classes.model.Tarefa;
import com.projeto.anotacoes.classes.ui.adapter.listaTarefasAdapter.RecyclerTarefasAdapter;
import com.projeto.anotacoes.classes.ui.adapter.listaTarefasAdapter.TarefaTouchHelper;

import java.util.List;

import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.TAREFAS;

public class TarefaObjetivos extends AppCompatActivity {

    RecyclerView recyclerTarefas;
    RecyclerTarefasAdapter adapter;
    FloatingActionButton floatingActionButton;
    List<Tarefa> listaTarefas;
    List<SubTarefa> listaSubTarefas;
    TarefaDao tarefaDao;
    SubTarefaDAO subTarefaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        setTitle(TAREFAS);
        btnMenus();
        configurarLista();
        setStatusBarColor();

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizar();
        fabAdd();

    }
    public void atualizar(){
        listaTarefas.clear();
        listaTarefas.addAll(tarefaDao.getTarefas());
        adapter.notifyDataSetChanged();
    }

    public void fabAdd() {
        floatingActionButton = findViewById(R.id.tarefa_objetivos_fab);
        floatingActionButton.setOnClickListener(v -> {
            Intent novaTarefa = new Intent(getApplicationContext(), TarefaFormulario.class);
            startActivity(novaTarefa);
        });
    }

    public void btnMenus() {
        ImageButton btnNota = findViewById(R.id.lista_notas_btn_notas);
        btnNota.setOnClickListener(v -> itentNotas());
    }

    public void itentNotas() {
        Intent intent = new Intent(getApplicationContext(), NotesList.class);
        startActivity(intent);
        finish();
    }

    public void configurarLista() {
        tarefaDao = Database.getInstance(this).getTarefaDao();
        subTarefaDao = Database.getInstance(this).getSubTarefaDao();
        listaTarefas = tarefaDao.getTarefas();
        adapter = new RecyclerTarefasAdapter(this, listaTarefas, listaSubTarefas);
        recyclerTarefas = findViewById(R.id.lista_notas_lista_tarefas);
        recyclerTarefas.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TarefaTouchHelper(adapter, listaTarefas, tarefaDao));
        itemTouchHelper.attachToRecyclerView(recyclerTarefas);

    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }
    }


}
