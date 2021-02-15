package com.projeto.anotacoes.classes.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projeto.anotacoes.classes.database.Database;
import com.projeto.anotacoes.classes.database.dao.NoteDAO;
import com.projeto.anotacoes.classes.model.Cor;
import com.projeto.anotacoes.classes.model.ListaDeCores;
import com.projeto.anotacoes.classes.model.Note;
import com.projeto.anotacoes.classes.model.OnKeyboardVisibilityListener;
import com.projeto.anotacoes.classes.model.enums.ColorsEnum;
import com.projeto.anotacoes.classes.model.enums.StyleTextEnum;
import com.projeto.anotacoes.classes.ui.adapter.listaCoresFormularioAdapter.ColorsAdapterForm;
import com.projeto.anotacoes.classes.ui.util.DataUtil;
import com.projeto.anotacoes.classes.ui.util.DevolveCorIntegerUtil;
import com.projeto.anotacoes.classes.ui.util.DevolveCorFonteIntegerUtil;
import com.projeto.anotacoes.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.Calendar;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.ALTER_NOTE;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.NEW_INTENT;
import static com.projeto.anotacoes.classes.constants.general.GeneralConstants.NEW_NOTE;


@SuppressWarnings("ALL")
public class NotesForm extends AppCompatActivity implements OnKeyboardVisibilityListener {

    public static final int SIZE_TITLE = 38;
    public static final int SIZE_DESCRIP = 24;
    public static final int LIMITE_MAXIMO_COR_FONTE = 3;
    private EditText titleField;
    private EditText descriptionField;
    private ConstraintLayout rootContentView;
    private TextView rootFuncoes;
    private final ListaDeCores listColors = new ListaDeCores();
    private ColorsAdapterForm colorsAdapter;
    private NoteDAO noteDao;
    private Note note = new Note();
    private List<Note> listNotes;
    private TextView dataEdicao;


    private RecyclerView recyclerViewBackgroundNoteColor;
    private Button btnBoldDesativado, btnItalicDesativado, btnFont;
    private Button btnColorFont;
    private TextView iconColorFont;
    private ImageButton btnBoldSelecionado, btnItalicSelecionado;
    private boolean italic, bold = false;
    private StyleTextEnum estiloTexto = StyleTextEnum.NORMAL;
    private FloatingActionButton fabSalvar;
    private int colorFundoNota = R.color.BRANCO;
    private int corTexto = R.color.black;
    private StyleTextEnum estiloNotaSalva;
    private int VALOR_COR_FONTE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        loadingComponents();
        setTitle(NEW_NOTE);
        setStatusBarColor();
    }

    @Override
    protected void onResume() {
        onColorItemClick();
        refreshListNote();
        fabButonClick();
        setBold();
        setItalic();

        btncorTextoItemClick();
        setKeyboardVisibilityListener(this);
        super.onResume();
    }

    private void setKeyboardVisibilityListener(final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        final View parentView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, parentView.getResources().getDisplayMetrics());
                parentView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = parentView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                alreadyOpen = isShown;
                onKeyboardVisibilityListener.onVisibilityChanged(isShown);
            }
        });
    }

    @Override
    public synchronized void onVisibilityChanged(boolean visible) {
        if (visible == false) {
            setAllElementsToInvisible();

        } else {
            if (note.getStyle() == StyleTextEnum.NORMAL) {
                btnItalicDesativado.setVisibility(VISIBLE);
                btnBoldDesativado.setVisibility(VISIBLE);

            }
            if (note.getStyle() == StyleTextEnum.BOLD) {
                btnBoldDesativado.setVisibility(INVISIBLE);
                btnBoldSelecionado.setVisibility(VISIBLE);
                btnItalicDesativado.setVisibility(VISIBLE);
                bold = true;
                italic = false;

            }
            if (note.getStyle() == StyleTextEnum.ITALICO) {
                btnItalicDesativado.setVisibility(INVISIBLE);
                btnItalicSelecionado.setVisibility(VISIBLE);
                btnBoldDesativado.setVisibility(VISIBLE);
                italic = true;
                bold = false;

            }
            if (note.getStyle() == StyleTextEnum.BOLD_ITALICO) {
                btnBoldDesativado.setVisibility(INVISIBLE);
                btnBoldSelecionado.setVisibility(VISIBLE);
                btnItalicDesativado.setVisibility(INVISIBLE);
                btnItalicSelecionado.setVisibility(VISIBLE);
                italic = true;
                bold = true;
            }
            funcoesBasicasVisibility();
            btnColorFont.setVisibility(VISIBLE);
            iconColorFont.setVisibility(VISIBLE);
        }
    }

    private void funcoesBasicasVisibility() {
        rootFuncoes.setVisibility(VISIBLE);
        fabSalvar.setVisibility(VISIBLE);
        recyclerViewBackgroundNoteColor.setVisibility(VISIBLE);
    }

    private void setAllElementsToInvisible() {
        recyclerViewBackgroundNoteColor.setVisibility(INVISIBLE);
        fabSalvar.setVisibility(INVISIBLE);
        btnItalicDesativado.setVisibility(INVISIBLE);
        btnBoldDesativado.setVisibility(INVISIBLE);
        rootFuncoes.setVisibility(INVISIBLE);
        btnItalicSelecionado.setVisibility(INVISIBLE);
        btnBoldSelecionado.setVisibility(INVISIBLE);
        btnColorFont.setVisibility(INVISIBLE);
        iconColorFont.setVisibility(INVISIBLE);
    }

    private void fabButonClick() {
        fabSalvar.setOnClickListener(v -> {
            createNote();
            finish();
            colorsAdapter.notifyDataSetChanged();
        });
    }

    public void setBold() {
        btnBoldDesativado.setOnClickListener(v -> {
            botaoBoldDesativadoTocado();
            bold = true;
            logicaBold();
        });
        btnBoldSelecionado.setOnClickListener(v -> {
            botaoBoldSelecionadoTocado();

        });
    }

    private void botaoBoldSelecionadoTocado() {
        bold = false;
        btnBoldDesativado.setVisibility(VISIBLE);
        btnBoldSelecionado.setVisibility(INVISIBLE);
        if (italic == true) {
            logicaItalic();
        } else {
            descriptionField.setTypeface(typefaceNormal());
            estiloTexto = StyleTextEnum.NORMAL;
            note.setStyle(estiloTexto);
        }
    }

    private void botaoBoldDesativadoTocado() {
        btnBoldDesativado.setVisibility(INVISIBLE);
        btnBoldSelecionado.setVisibility(VISIBLE);
    }

    private void logicaBold() {
        if (italic == true) {
            descriptionField.setTypeface(typefaceItalicBold());
            estiloTexto = StyleTextEnum.BOLD_ITALICO;
        } else {
            descriptionField.setTypeface(typefaceBold());
            estiloTexto = StyleTextEnum.BOLD;
        }
        note.setStyle(estiloTexto);
    }

    public void setItalic() {
        btnItalicDesativado.setOnClickListener(v -> {
            botaoItalicDesativadoTocado();
            italic = true;
            logicaItalic();
        });
        btnItalicSelecionado.setOnClickListener(v -> {
            botaoItalicoSelecionadoTocado();
        });

    }

    private void botaoItalicoSelecionadoTocado() {
        italic = false;
        btnItalicDesativado.setVisibility(VISIBLE);
        btnItalicSelecionado.setVisibility(INVISIBLE);
        if (bold == true) {
            logicaBold();
        } else {
            descriptionField.setTypeface(typefaceNormal());
            estiloTexto = StyleTextEnum.NORMAL;
            note.setStyle(estiloTexto);
        }
    }

    private void botaoItalicDesativadoTocado() {
        btnItalicDesativado.setVisibility(INVISIBLE);
        btnItalicSelecionado.setVisibility(VISIBLE);
    }

    private void logicaItalic() {

        if (bold == true) {
            estiloTexto = StyleTextEnum.BOLD_ITALICO;
            descriptionField.setTypeface(typefaceItalicBold());

        } else {
            descriptionField.setTypeface(typefaceItalic());
            estiloTexto = StyleTextEnum.ITALICO;
        }
        note.setStyle(estiloTexto);
    }

    public void btncorTextoItemClick() {
        btnColorFont.setOnClickListener(v -> {
           verificaCorNota(VALOR_COR_FONTE);
            VALOR_COR_FONTE++;
            if(VALOR_COR_FONTE == LIMITE_MAXIMO_COR_FONTE){
                VALOR_COR_FONTE =0;
            }
        });
    }

    private Typeface typefaceItalicBold() {
        return Typeface.create((String) null, Typeface.BOLD_ITALIC);
    }

    private Typeface typefaceBold() {
        return Typeface.create((String) null, Typeface.BOLD);
    }

    private Typeface typefaceItalic() {
        return Typeface.create((String) null, Typeface.ITALIC);
    }

    private Typeface typefaceNormal() {
        return Typeface.create((String) null, Typeface.NORMAL);
    }

    private void refreshListNote() {
        listNotes.clear();
        listNotes.addAll(noteDao.allNote());
    }

    public void loadingComponents() {
        noteDao = Database.getInstance(this).getNotaDataDao();
        titleField = findViewById(R.id.formulario_nota_titulo);
        descriptionField = findViewById(R.id.formulario_nota_descricao);
        rootFuncoes = findViewById(R.id.formulario_nota_grade);
        rootContentView = findViewById(R.id.formulario_Fundo);
        recyclerViewBackgroundNoteColor = findViewById(R.id.formulario_nota_lista_notas_cores_background);
        fabSalvar = findViewById(R.id.formulario_nota_adicionar_FAB);
        btnBoldDesativado = findViewById(R.id.formulario_nota_botao_bold);
        btnBoldSelecionado = findViewById(R.id.formulario_nota_botao_bold_select);
        btnItalicDesativado = findViewById(R.id.formulario_nota_botao_italico);
        btnItalicSelecionado = findViewById(R.id.formulario_nota_botao_italico_select);
        btnColorFont = findViewById(R.id.formulario_nota_botao_cor_fonte);
        iconColorFont = findViewById(R.id.formulario_nota_icon_color_font);
        List<Cor> listaCor = listColors.getAllColors();
        dataEdicao = findViewById(R.id.formulario_nota_data_edicao);
        listNotes = noteDao.allNote();
        titleField.setTextSize(SIZE_TITLE);
        descriptionField.setTextSize(SIZE_DESCRIP);
        colorsAdapter = new ColorsAdapterForm(this, listaCor);
        recyclerViewBackgroundNoteColor.setAdapter(colorsAdapter);
        hasIntent();
        verificaCorNota(VALOR_COR_FONTE);

    }

    private void verificaCorNota(int contador) {
        if (contador == 0) {
            corTexto = R.color.black;
        }
        if (contador == 1) {
            corTexto = R.color.BRANCO;
        }
        if (contador == 2) {
            corTexto = R.color.CINZA_LIGHT;
        }
        titleField.setTextColor(ContextCompat.getColor(this, corTexto));
        descriptionField.setTextColor(ContextCompat.getColor(this, corTexto));
        dataEdicao.setTextColor(ContextCompat.getColor(this, corTexto));
        SetDrawablesBackgroundColorUtil.setDrawableColor(this, iconColorFont.getBackground(), corTexto);
    }


    private synchronized void hasIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(NEW_INTENT)) {
            note = (Note) intent.getSerializableExtra(NEW_INTENT);
            fillFields();
            setTitle(ALTER_NOTE);
            dataEdicao.setText("Editado - " + DataUtil.devolveStringCalendar(note.getDataEdicao()));
            preencheComponentesApartirNota();
            verificaEstiloNotaSalva();
            setStatusBarColor();
            VALOR_COR_FONTE = DevolveCorFonteIntegerUtil.getIntegerCor(note.getTextColor());
            verificaCorNota(VALOR_COR_FONTE);
        }
    }


    private void preencheComponentesApartirNota() {
        colorFundoNota = DevolveCorIntegerUtil.DevolveCorInt(note.getDefaultColor());
        rootContentView.setBackgroundColor(getResources().getColor(colorFundoNota));
        titleField.setTextColor(note.getTextColor());
        descriptionField.setTextColor(note.getTextColor());
    }

    private void verificaEstiloNotaSalva() {
        if (note.getStyle() == StyleTextEnum.BOLD_ITALICO) {
            descriptionField.setTypeface(typefaceItalicBold());
        } else if (note.getStyle() == StyleTextEnum.BOLD) {
            descriptionField.setTypeface(typefaceBold());
        } else if (note.getStyle() == StyleTextEnum.ITALICO) {
            descriptionField.setTypeface(typefaceItalic());
        } else if (note.getStyle() == StyleTextEnum.NORMAL) {
            descriptionField.setTypeface(typefaceNormal());
        }
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, colorFundoNota));
        }
    }

    private void fillFields() {
        titleField.setText(note.getTitle());
        descriptionField.setText(note.getDescription());
    }


    public void createNote() {
        int position;
        note.setTitle(titleField.getText().toString());
        note.setDescription(descriptionField.getText().toString());
        if (note.getId() == 0) {
            refreshListNote();
            position = listNotes.size();
            note.setPosition(position);
            note.setStyle(estiloTexto);
            note.setTextColor(corTexto);
            Long idNote = noteDao.insert(note);
        } else if (note.getId() != 0) {
            Calendar editadoAgora = Calendar.getInstance();
            note.setDataEdicao(editadoAgora);
            note.setTextColor(corTexto);
            noteDao.update(note);
        }

    }

    private void onColorItemClick() {
        colorsAdapter.setOnClickListerner(cor -> {
            ColorsEnum corClicada = cor.getCor();
            colorFundoNota = DevolveCorIntegerUtil.DevolveCorInt(corClicada);
            rootContentView.setBackgroundColor(getResources().getColor(colorFundoNota));
            note.setDefaultColor(corClicada);
            setStatusBarColor();

        });
    }

}
