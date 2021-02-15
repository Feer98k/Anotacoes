package com.projeto.anotacoes.classes.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.anotacoes.R;
import com.projeto.anotacoes.classes.database.Database;
import com.projeto.anotacoes.classes.database.dao.SubTarefaDAO;
import com.projeto.anotacoes.classes.database.dao.TarefaDao;
import com.projeto.anotacoes.classes.model.SubTarefa;
import com.projeto.anotacoes.classes.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.EDITAR_TAREFA;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.LIMITE_DE_SUB_TAREFAS_ATINGIDO;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.NOVA_TAREFA;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.TAREFA_INTENT;

public class TarefaFormulario extends AppCompatActivity {

    EditText campoTitulo;
    EditText campoSubTarefa1, campoSubTarefa2, campoSubTarefa3, campoSubTarefa4, campoSubTarefa5,
            campoSubTarefa6, campoSubTarefa7, campoSubTarefa8, campoSubTarefa9, campoSubTarefa10,
            campoSubTarefa11, campoSubTarefa12;

    ImageButton fab1, fab2, fab3, fab4, fab5, fab6, fab7, fab8, fab9, fab10, fab11, fab12;

    ImageButton del1, del2, del3, del4, del5, del6, del7, del8, del9, del10, del11, del12;

    TarefaDao tarefaDao;
    SubTarefaDAO subTarefaDAO;
    Tarefa tarefa;
    SubTarefa subTarefa1, subTarefa2, subTarefa3, subTarefa4, subTarefa5, subTarefa6, subTarefa7,
            subTarefa8, subTarefa9, subTarefa10, subTarefa11, subTarefa12;
    List<SubTarefa> listaSubTarefas;
    @Override
    protected void onResume() {
        hasTarefa();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);
        carregarComponentes();
        carregaSubTarefas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario_tarefa,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        btnSalvar();
        Intent intent = new Intent(getApplicationContext(),TarefaObjetivos.class);
        startActivity(intent);
        finish();
        return true;
    }

    public void carregarComponentes() {
        campoTitulo = findViewById(R.id.formulario_tarefa_titulo);
        tarefaDao = Database.getInstance(this).getTarefaDao();
        subTarefaDAO = Database.getInstance(this).getSubTarefaDao();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.AZUL_TEMA));
        }

    }

    public void carregaSubTarefas() {
        campoSubTarefa1 = findViewById(R.id.formulario_tarefa_subTarefa_1);
        campoSubTarefa2 = findViewById(R.id.formulario_tarefa_subTarefa_2);
        campoSubTarefa3 = findViewById(R.id.formulario_tarefa_subTarefa_3);
        campoSubTarefa4 = findViewById(R.id.formulario_tarefa_subTarefa_4);
        campoSubTarefa5 = findViewById(R.id.formulario_tarefa_subTarefa_5);
        campoSubTarefa6 = findViewById(R.id.formulario_tarefa_subTarefa_6);
        campoSubTarefa7 = findViewById(R.id.formulario_tarefa_subTarefa_7);
        campoSubTarefa8 = findViewById(R.id.formulario_tarefa_subTarefa_8);
        campoSubTarefa9 = findViewById(R.id.formulario_tarefa_subTarefa_9);
        campoSubTarefa10 = findViewById(R.id.formulario_tarefa_subTarefa_10);
        campoSubTarefa11 = findViewById(R.id.formulario_tarefa_subTarefa_11);
        campoSubTarefa12 = findViewById(R.id.formulario_tarefa_subTarefa_12);
        fab1 = findViewById(R.id.formulario_tarefa_add_1);
        fab2 = findViewById(R.id.formulario_tarefa_add_2);
        fab3 = findViewById(R.id.formulario_tarefa_add_3);
        fab4 = findViewById(R.id.formulario_tarefa_add_4);
        fab5 = findViewById(R.id.formulario_tarefa_add_5);
        fab6 = findViewById(R.id.formulario_tarefa_add_6);
        fab7 = findViewById(R.id.formulario_tarefa_add_7);
        fab8 = findViewById(R.id.formulario_tarefa_add_8);
        fab9 = findViewById(R.id.formulario_tarefa_add_9);
        fab10 = findViewById(R.id.formulario_tarefa_add_10);
        fab11 = findViewById(R.id.formulario_tarefa_add_11);
        fab12 = findViewById(R.id.formulario_tarefa_add_12);
        del1 = findViewById(R.id.formulario_tarefa_delete_1);
        del2 = findViewById(R.id.formulario_tarefa_delete_2);
        del3 = findViewById(R.id.formulario_tarefa_delete_3);
        del4 = findViewById(R.id.formulario_tarefa_delete_4);
        del5 = findViewById(R.id.formulario_tarefa_delete_5);
        del6 = findViewById(R.id.formulario_tarefa_delete_6);
        del7 = findViewById(R.id.formulario_tarefa_delete_7);
        del8 = findViewById(R.id.formulario_tarefa_delete_8);
        del9 = findViewById(R.id.formulario_tarefa_delete_9);
        del10 = findViewById(R.id.formulario_tarefa_delete_10);
        del11 = findViewById(R.id.formulario_tarefa_delete_11);
        del12 = findViewById(R.id.formulario_tarefa_delete_12);
        subTarefa1 = new SubTarefa();
        subTarefa2 = new SubTarefa();
        subTarefa3 = new SubTarefa();
        subTarefa4 = new SubTarefa();
        subTarefa5 = new SubTarefa();
        subTarefa6 = new SubTarefa();
        subTarefa7 = new SubTarefa();
        subTarefa8 = new SubTarefa();
        subTarefa9 = new SubTarefa();
        subTarefa10 = new SubTarefa();
        subTarefa11 = new SubTarefa();
        subTarefa12 = new SubTarefa();
        btnAddItemClick();
        btnDelItemClick();
    }

    public void btnAddItemClick() {
        fab1.setOnClickListener(v -> {
            campoSubTarefa2.setVisibility(VISIBLE);
            fab2.setVisibility(VISIBLE);
            del2.setVisibility(VISIBLE);

        });
        fab2.setOnClickListener(v -> {
            campoSubTarefa3.setVisibility(VISIBLE);
            fab3.setVisibility(VISIBLE);
            del3.setVisibility(VISIBLE);

        });
        fab3.setOnClickListener(v -> {
            campoSubTarefa4.setVisibility(VISIBLE);
            fab4.setVisibility(VISIBLE);
            del4.setVisibility(VISIBLE);

        });
        fab4.setOnClickListener(v -> {
            campoSubTarefa5.setVisibility(VISIBLE);
            fab5.setVisibility(VISIBLE);
            del5.setVisibility(VISIBLE);

        });
        fab5.setOnClickListener(v -> {
            campoSubTarefa6.setVisibility(VISIBLE);
            fab6.setVisibility(VISIBLE);
            del6.setVisibility(VISIBLE);

        });
        fab6.setOnClickListener(v -> {
            campoSubTarefa7.setVisibility(VISIBLE);
            fab7.setVisibility(VISIBLE);
            del7.setVisibility(VISIBLE);

        });
        fab7.setOnClickListener(v -> {
            campoSubTarefa8.setVisibility(VISIBLE);
            fab8.setVisibility(VISIBLE);
            del8.setVisibility(VISIBLE);

        });
        fab8.setOnClickListener(v -> {
            campoSubTarefa9.setVisibility(VISIBLE);
            fab9.setVisibility(VISIBLE);
            del9.setVisibility(VISIBLE);

        });
        fab9.setOnClickListener(v -> {
            campoSubTarefa10.setVisibility(VISIBLE);
            fab10.setVisibility(VISIBLE);
            del10.setVisibility(VISIBLE);

        });
        fab10.setOnClickListener(v -> {
            campoSubTarefa11.setVisibility(VISIBLE);
            fab11.setVisibility(VISIBLE);
            del11.setVisibility(VISIBLE);
        });
        fab11.setOnClickListener(v -> {
            campoSubTarefa12.setVisibility(VISIBLE);
            fab12.setVisibility(VISIBLE);
            del12.setVisibility(VISIBLE);
        });
        fab12.setOnClickListener(v -> {
            Toast.makeText(this, LIMITE_DE_SUB_TAREFAS_ATINGIDO, Toast.LENGTH_SHORT).show();
        });

    }

    public void btnDelItemClick() {
        del1.setOnClickListener(v -> {
            campoSubTarefa1.setText(null);
            subTarefaDAO.remove(subTarefa1);
            listaSubTarefas.remove(subTarefa1);
        });
        del2.setOnClickListener(v -> {
            campoSubTarefa2.setText(null);
            campoSubTarefa2.setVisibility(INVISIBLE);
            del2.setVisibility(INVISIBLE);
            fab2.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa2);
            listaSubTarefas.remove(subTarefa2);
        });
        del3.setOnClickListener(v -> {
            campoSubTarefa3.setText(null);
            campoSubTarefa3.setVisibility(INVISIBLE);
            del3.setVisibility(INVISIBLE);
            fab3.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa3);
            listaSubTarefas.remove(subTarefa3);
        });
        del4.setOnClickListener(v -> {
            campoSubTarefa4.setText(null);
            campoSubTarefa4.setVisibility(INVISIBLE);
            del4.setVisibility(INVISIBLE);
            fab4.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa4);
            listaSubTarefas.remove(subTarefa4);
        });
        del5.setOnClickListener(v -> {
            campoSubTarefa5.setText(null);
            campoSubTarefa5.setVisibility(INVISIBLE);
            del5.setVisibility(INVISIBLE);
            fab5.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa5);
            listaSubTarefas.remove(subTarefa5);
        });
        del6.setOnClickListener(v -> {
            campoSubTarefa6.setText(null);
            campoSubTarefa6.setVisibility(INVISIBLE);
            del6.setVisibility(INVISIBLE);
            fab6.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa6);
            listaSubTarefas.remove(subTarefa6);
        });
        del7.setOnClickListener(v -> {
            campoSubTarefa7.setText(null);
            campoSubTarefa7.setVisibility(INVISIBLE);
            del7.setVisibility(INVISIBLE);
            fab7.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa7);
            listaSubTarefas.remove(subTarefa7);
        });
        del8.setOnClickListener(v -> {
            campoSubTarefa8.setText(null);
            campoSubTarefa8.setVisibility(INVISIBLE);
            del8.setVisibility(INVISIBLE);
            fab8.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa8);
            listaSubTarefas.remove(subTarefa8);
        });
        del9.setOnClickListener(v -> {
            campoSubTarefa9.setText(null);
            campoSubTarefa9.setVisibility(INVISIBLE);
            del9.setVisibility(INVISIBLE);
            fab9.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa9);
            listaSubTarefas.remove(subTarefa9);
        });
        del10.setOnClickListener(v -> {
            campoSubTarefa10.setText(null);
            campoSubTarefa10.setVisibility(INVISIBLE);
            del10.setVisibility(INVISIBLE);
            fab10.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa10);
            listaSubTarefas.remove(subTarefa10);
        });
        del11.setOnClickListener(v -> {
            campoSubTarefa11.setText(null);
            campoSubTarefa11.setVisibility(INVISIBLE);
            del11.setVisibility(INVISIBLE);
            fab11.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa11);
            listaSubTarefas.remove(subTarefa11);
        });
        del12.setOnClickListener(v -> {
            campoSubTarefa12.setText(null);
            campoSubTarefa12.setVisibility(INVISIBLE);
            del12.setVisibility(INVISIBLE);
            fab12.setVisibility(INVISIBLE);
            subTarefaDAO.remove(subTarefa12);
            listaSubTarefas.remove(subTarefa12);
        });
    }

    public void hasTarefa() {

        Intent intent = getIntent();
        if (intent.hasExtra(TAREFA_INTENT)) {
            int posicao = (int) intent.getSerializableExtra(TAREFA_INTENT);
            tarefa = tarefaDao.getTarefas().get(posicao);
            setTitle(EDITAR_TAREFA);
            campoTitulo.setText(tarefa.getTitulo());
            listaSubTarefas = subTarefaDAO.getSubTarefas(tarefa.getId());
            tentaPreencherCampos();
        } else {
            tarefa = new Tarefa();
            setTitle(NOVA_TAREFA);
            listaSubTarefas = new ArrayList<>();
        }
    }

    private void tentaPreencherCampos() {
        try {
            subTarefa1 = listaSubTarefas.get(0);
            if (subTarefa1 != null) {
                campoSubTarefa1.setText(subTarefa1.getDescricaoTarefa());
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            subTarefa2 = listaSubTarefas.get(1);
            if (subTarefa2 != null) {
                campoSubTarefa2.setText(subTarefa2.getDescricaoTarefa());
                fab2.setVisibility(VISIBLE);
                campoSubTarefa2.setVisibility(VISIBLE);
                del2.setVisibility(VISIBLE);
            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa3 = listaSubTarefas.get(2);
            if (subTarefa3 != null) {
                campoSubTarefa3.setText(subTarefa3.getDescricaoTarefa());
                fab3.setVisibility(VISIBLE);
                campoSubTarefa3.setVisibility(VISIBLE);
                del3.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa4 = listaSubTarefas.get(3);
            if (subTarefa4 != null) {
                campoSubTarefa4.setText(subTarefa4.getDescricaoTarefa());
                fab4.setVisibility(VISIBLE);
                campoSubTarefa4.setVisibility(VISIBLE);
                del4.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa5 = listaSubTarefas.get(4);
            if (subTarefa5 != null) {
                campoSubTarefa5.setText(subTarefa5.getDescricaoTarefa());
                fab5.setVisibility(VISIBLE);
                campoSubTarefa5.setVisibility(VISIBLE);
                del5.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa6 = listaSubTarefas.get(5);
            if (subTarefa6 != null) {
                campoSubTarefa6.setText(subTarefa6.getDescricaoTarefa());
                fab6.setVisibility(VISIBLE);
                campoSubTarefa6.setVisibility(VISIBLE);
                del6.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa7 = listaSubTarefas.get(6);
            if (subTarefa7 != null) {
                campoSubTarefa7.setText(subTarefa7.getDescricaoTarefa());
                fab7.setVisibility(VISIBLE);
                campoSubTarefa7.setVisibility(VISIBLE);
                del7.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            subTarefa8 = listaSubTarefas.get(7);
            if (subTarefa8 != null) {
                campoSubTarefa8.setText(subTarefa8.getDescricaoTarefa());
                fab8.setVisibility(VISIBLE);
                campoSubTarefa8.setVisibility(VISIBLE);
                del8.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa9 = listaSubTarefas.get(8);
            if (subTarefa9 != null) {
                campoSubTarefa9.setText(subTarefa9.getDescricaoTarefa());
                fab9.setVisibility(VISIBLE);
                campoSubTarefa9.setVisibility(VISIBLE);
                del9.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa10 = listaSubTarefas.get(9);
            if (subTarefa10 != null) {
                campoSubTarefa10.setText(subTarefa10.getDescricaoTarefa());
                fab10.setVisibility(VISIBLE);
                campoSubTarefa10.setVisibility(VISIBLE);
                del10.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa11 = listaSubTarefas.get(10);
            if (subTarefa11 != null) {
                campoSubTarefa11.setText(subTarefa11.getDescricaoTarefa());
                fab11.setVisibility(VISIBLE);
                campoSubTarefa11.setVisibility(VISIBLE);
                del11.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            subTarefa12 = listaSubTarefas.get(11);
            if (subTarefa12 != null) {
                campoSubTarefa12.setText(subTarefa12.getDescricaoTarefa());
                fab12.setVisibility(VISIBLE);
                campoSubTarefa12.setVisibility(VISIBLE);
                del12.setVisibility(VISIBLE);

            }
        } catch (IndexOutOfBoundsException e) {

        }
    }



    private synchronized void btnSalvar() {
        preencheTarefas();
        if (tarefa.getId() == 0) {
            int posicao = tarefaDao.getTarefas().size();
            tarefa.setPosicao(posicao);
            tarefa.setTotalTarefas(listaSubTarefas.size());
            int idTarefa = tarefaDao.salva(tarefa).intValue();
            for (SubTarefa sub : listaSubTarefas) {
                sub.setIdTarefa(idTarefa);
                sub.setCompletado(false);
                sub.setPosicaoSubTarefa(subTarefaDAO.getSubTarefas(tarefa.getId()).size());
                subTarefaDAO.salva(sub);
            }
        }
        /**
         * TODO
         * reparar bug de list
         */
        if (tarefa.getId() != 0) {
            tarefaDao.update(tarefa);
            for (SubTarefa sub : listaSubTarefas) {
                sub.setIdTarefa(tarefa.getId());
                subTarefaDAO.remove(sub);
                subTarefaDAO.salva(sub);
            }
        }
        listaSubTarefas = subTarefaDAO.getSubTarefas(tarefa.getId());

        tarefa.setTotalTarefas(listaSubTarefas.size());
        tarefaDao.update(tarefa);
        finish();
    }

    private void preencheTarefas() {
        tarefa.setTitulo(campoTitulo.getText().toString());
        if (!campoSubTarefa1.getText().toString().isEmpty()) {
            subTarefa1.setDescricaoTarefa(campoSubTarefa1.getText().toString());
            listaSubTarefas.add(subTarefa1);
        }
        if (!campoSubTarefa2.getText().toString().isEmpty()) {
            subTarefa2.setDescricaoTarefa(campoSubTarefa2.getText().toString());
            listaSubTarefas.add(subTarefa2);
        }
        if (!campoSubTarefa3.getText().toString().isEmpty()) {
            subTarefa3.setDescricaoTarefa(campoSubTarefa3.getText().toString());
            listaSubTarefas.add(subTarefa3);
        }
        if (!campoSubTarefa4.getText().toString().isEmpty()) {
            subTarefa4.setDescricaoTarefa(campoSubTarefa4.getText().toString());
            listaSubTarefas.add(subTarefa4);
        }
        if (!campoSubTarefa5.getText().toString().isEmpty()) {
            subTarefa5.setDescricaoTarefa(campoSubTarefa5.getText().toString());
            listaSubTarefas.add(subTarefa5);
        }
        if (!campoSubTarefa6.getText().toString().isEmpty()) {
            subTarefa6.setDescricaoTarefa(campoSubTarefa6.getText().toString());
            listaSubTarefas.add(subTarefa6);
        }
        if (!campoSubTarefa7.getText().toString().isEmpty()) {
            subTarefa7.setDescricaoTarefa(campoSubTarefa7.getText().toString());
            listaSubTarefas.add(subTarefa7);
        }
        if (!campoSubTarefa8.getText().toString().isEmpty()) {
            subTarefa8.setDescricaoTarefa(campoSubTarefa8.getText().toString());
            listaSubTarefas.add(subTarefa8);
        }
        if (!campoSubTarefa9.getText().toString().isEmpty()) {
            subTarefa9.setDescricaoTarefa(campoSubTarefa9.getText().toString());
            listaSubTarefas.add(subTarefa9);
        }
        if (!campoSubTarefa10.getText().toString().isEmpty()) {
            subTarefa10.setDescricaoTarefa(campoSubTarefa10.getText().toString());
            listaSubTarefas.add(subTarefa10);
        }
        if (!campoSubTarefa11.getText().toString().isEmpty()) {
            subTarefa11.setDescricaoTarefa(campoSubTarefa11.getText().toString());
            listaSubTarefas.add(subTarefa11);
        }
        if (!campoSubTarefa12.getText().toString().isEmpty()) {
            subTarefa12.setDescricaoTarefa(campoSubTarefa12.getText().toString());
            listaSubTarefas.add(subTarefa12);
        }
    }

}