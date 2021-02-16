package com.project.annotations.classes.ui.activity;

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
import com.project.annotations.classes.database.Database;
import com.project.annotations.classes.database.dao.NoteDAO;
import com.project.annotations.classes.model.Color;
import com.project.annotations.classes.model.ColorList;
import com.project.annotations.classes.model.Note;
import com.project.annotations.classes.model.OnKeyboardVisibilityListener;
import com.project.annotations.classes.model.enums.ColorsEnum;
import com.project.annotations.classes.model.enums.StyleTextEnum;
import com.project.annotations.classes.ui.adapter.listaCoresFormularioAdapter.RecyclerViewColorsAdapter;
import com.project.annotations.classes.ui.util.DataUtil;
import com.project.annotations.classes.ui.util.ReturnColorIntegerUtil;
import com.project.annotations.classes.ui.util.ReturnIntegerColorUtil;
import com.project.annotations.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.Calendar;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.project.annotations.classes.constants.general.GeneralConstants.ALTER_NOTE;
import static com.project.annotations.classes.constants.general.GeneralConstants.EDITADO;
import static com.project.annotations.classes.constants.general.GeneralConstants.INITIAL_VALUE;
import static com.project.annotations.classes.constants.general.GeneralConstants.NEW_INTENT;
import static com.project.annotations.classes.constants.general.GeneralConstants.NEW_NOTE;


public class NotesForm extends AppCompatActivity implements OnKeyboardVisibilityListener {

    public static final int SIZE_TITLE_FIELD = 38;
    public static final int SIZE_DESCRIPTION_FIELD = 24;
    public static final int SIZE_COLOR_FONT_LIMIT = 3;

    private EditText titleEditField;
    private EditText descriptionEditField;

    private TextView dataEditionFieldView;
    private TextView gradeFunctionsFieldView;
    private TextView iconColorFontFieldView;


    private ConstraintLayout rootLayout;
    private final ColorList listColors = new ColorList();

    private RecyclerView recyclerViewBackground;
    private RecyclerViewColorsAdapter recyclerViewColorsAdapter;

    private NoteDAO noteDAO;
    private Note note;
    private List<Note> notesList;

    private Button btnBoldDisabled, btnItalicDisabled;
    private Button btnColorFont;

    private ImageButton btnBoldEnabled, btnItalicEnabled;

    private FloatingActionButton fabInsert;


    private StyleTextEnum textStyle = StyleTextEnum.NORMAL;
    private int integerColorBackgroundNote = R.color.BRANCO;
    private int textColorInteger = R.color.black;

    private int fontColorInteger = INITIAL_VALUE;
    private boolean italic, bold = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_note);
        loadingComponents();
        setTitle(NEW_NOTE);
        setStatusBarColor();
    }

    @Override
    protected void onResume() {
        onColorItemClick();
        refreshListNote();
        btnFabOnClick();
        setBold();
        setItalic();

        btnColorFontOnClick();
        setKeyboardVisibilityListener(this);
        super.onResume();
    }

    private void setKeyboardVisibilityListener(final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        final View parentView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, parentView.getResources().getDisplayMetrics());
                parentView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = parentView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                onKeyboardVisibilityListener.onVisibilityChanged(isShown);
            }
        });
    }

    @Override
    public synchronized void onVisibilityChanged(boolean visible) {
        if (!visible) {
            changeAllElementsToInvisible();
        } else {
            if (note.getStyle() == StyleTextEnum.NORMAL) {
                btnItalicDisabled.setVisibility(VISIBLE);
                btnBoldDisabled.setVisibility(VISIBLE);

            }
            if (note.getStyle() == StyleTextEnum.BOLD) {
                btnBoldDisabled.setVisibility(INVISIBLE);
                btnBoldEnabled.setVisibility(VISIBLE);
                btnItalicDisabled.setVisibility(VISIBLE);
                bold = true;
                italic = false;

            }
            if (note.getStyle() == StyleTextEnum.ITALICO) {
                btnItalicDisabled.setVisibility(INVISIBLE);
                btnItalicEnabled.setVisibility(VISIBLE);
                btnBoldDisabled.setVisibility(VISIBLE);
                italic = true;
                bold = false;

            }
            if (note.getStyle() == StyleTextEnum.BOLD_ITALICO) {
                btnBoldDisabled.setVisibility(INVISIBLE);
                btnBoldEnabled.setVisibility(VISIBLE);
                btnItalicDisabled.setVisibility(INVISIBLE);
                btnItalicEnabled.setVisibility(VISIBLE);
                italic = true;
                bold = true;
            }
            functionsVisibility();
            btnColorFont.setVisibility(VISIBLE);
            iconColorFontFieldView.setVisibility(VISIBLE);
        }
    }

    private void functionsVisibility() {
        gradeFunctionsFieldView.setVisibility(VISIBLE);
        fabInsert.setVisibility(VISIBLE);
        recyclerViewBackground.setVisibility(VISIBLE);
    }

    private void changeAllElementsToInvisible() {
        recyclerViewBackground.setVisibility(INVISIBLE);
        fabInsert.setVisibility(INVISIBLE);
        btnItalicDisabled.setVisibility(INVISIBLE);
        btnBoldDisabled.setVisibility(INVISIBLE);
        gradeFunctionsFieldView.setVisibility(INVISIBLE);
        btnItalicEnabled.setVisibility(INVISIBLE);
        btnBoldEnabled.setVisibility(INVISIBLE);
        btnColorFont.setVisibility(INVISIBLE);
        iconColorFontFieldView.setVisibility(INVISIBLE);
    }

    private void btnFabOnClick() {
        fabInsert.setOnClickListener(v -> {
            createNote();
            finish();
            recyclerViewColorsAdapter.notifyDataSetChanged();
        });
    }

    public void setBold() {
        btnBoldDisabled.setOnClickListener(this::btnBoldDisabledClick);
        btnBoldEnabled.setOnClickListener(v -> btnBoldEnabledOnClick());
    }

    private void btnBoldEnabledOnClick() {
        bold = false;
        btnBoldDisabled.setVisibility(VISIBLE);
        btnBoldEnabled.setVisibility(INVISIBLE);
        if (italic) {
            italicLogic();
        } else {
            descriptionEditField.setTypeface(typefaceNormal());
            textStyle = StyleTextEnum.NORMAL;
            note.setStyle(textStyle);
        }
    }

    private void btnBoldDisabledOnClick() {
        btnBoldDisabled.setVisibility(INVISIBLE);
        btnBoldEnabled.setVisibility(VISIBLE);
    }

    private void boldLogic() {
        if (italic) {
            descriptionEditField.setTypeface(typefaceItalicBold());
            textStyle = StyleTextEnum.BOLD_ITALICO;
        } else {
            descriptionEditField.setTypeface(typefaceBold());
            textStyle = StyleTextEnum.BOLD;
        }
        note.setStyle(textStyle);
    }

    public void setItalic() {
        btnItalicDisabled.setOnClickListener(this::btnItalicDisabledClick);
        btnItalicEnabled.setOnClickListener(v -> btnItalicEnabledOnClick());
    }

    private void btnItalicEnabledOnClick() {
        italic = false;
        btnItalicDisabled.setVisibility(VISIBLE);
        btnItalicEnabled.setVisibility(INVISIBLE);
        if (bold) {
            boldLogic();
        } else {
            descriptionEditField.setTypeface(typefaceNormal());
            textStyle = StyleTextEnum.NORMAL;
            note.setStyle(textStyle);
        }
    }

    private void btnItalicDisabledOnClick() {
        btnItalicDisabled.setVisibility(INVISIBLE);
        btnItalicEnabled.setVisibility(VISIBLE);
    }

    private void italicLogic() {

        if (bold) {
            textStyle = StyleTextEnum.BOLD_ITALICO;
            descriptionEditField.setTypeface(typefaceItalicBold());

        } else {
            descriptionEditField.setTypeface(typefaceItalic());
            textStyle = StyleTextEnum.ITALICO;
        }
        note.setStyle(textStyle);
    }

    public void btnColorFontOnClick() {
        btnColorFont.setOnClickListener(v -> {
            checkNoteColor(fontColorInteger);
            fontColorInteger++;
            if (fontColorInteger == SIZE_COLOR_FONT_LIMIT) {
                fontColorInteger = 0;
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
        notesList.clear();
        notesList.addAll(noteDAO.allNote());
    }

    public void loadingComponents() {
        noteDAO = Database.getInstance(this).getNotaDataDao();
        titleEditField = findViewById(R.id.form_note_title_editText);
        descriptionEditField = findViewById(R.id.form_note_description_editText);
        gradeFunctionsFieldView = findViewById(R.id.form_note_grade_textView);
        rootLayout = findViewById(R.id.form_note_constraint_layout);
        recyclerViewBackground = findViewById(R.id.form_note_colors_background_note_recyclerView);
        fabInsert = findViewById(R.id.form_note_add_note_fab);
        btnBoldDisabled = findViewById(R.id.form_note_bold_disabled_button);
        btnBoldEnabled = findViewById(R.id.form_note_bold_enabled_button);
        btnItalicDisabled = findViewById(R.id.form_note_italic_disabled_button);
        btnItalicEnabled = findViewById(R.id.form_note_italic_enabled_button);
        btnColorFont = findViewById(R.id.form_note_color_font_button);
        iconColorFontFieldView = findViewById(R.id.formulario_note_colorIcon_background_textView);
        List<Color> listaColor = listColors.getAllColors();
        dataEditionFieldView = findViewById(R.id.form_note_data_edition_textView);
        notesList = noteDAO.allNote();
        titleEditField.setTextSize(SIZE_TITLE_FIELD);
        descriptionEditField.setTextSize(SIZE_DESCRIPTION_FIELD);
        recyclerViewColorsAdapter = new RecyclerViewColorsAdapter(this, listaColor);
        recyclerViewBackground.setAdapter(recyclerViewColorsAdapter);
        hasIntent();
        checkNoteColor(fontColorInteger);

    }

    private void checkNoteColor(int counter) {
        if (counter == 0) {
            textColorInteger = R.color.black;
        }
        if (counter == 1) {
            textColorInteger = R.color.BRANCO;
        }
        if (counter == 2) {
            textColorInteger = R.color.CINZA_LIGHT;
        }
        titleEditField.setTextColor(ContextCompat.getColor(this, textColorInteger));
        descriptionEditField.setTextColor(ContextCompat.getColor(this, textColorInteger));
        dataEditionFieldView.setTextColor(ContextCompat.getColor(this, textColorInteger));
        SetDrawablesBackgroundColorUtil.setDrawableColor(this, iconColorFontFieldView.getBackground(), textColorInteger);
    }


    private synchronized void hasIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(NEW_INTENT)) {
            note = (Note) intent.getSerializableExtra(NEW_INTENT);
            fillFields();
            setTitle(ALTER_NOTE);
            String dataNote = DataUtil.devolveStringCalendar(note.getDataEdicao());
            dataEditionFieldView.setText(EDITADO.concat(dataNote));
            fillComponentsByNote();
            checkNoteStyle();
            setStatusBarColor();
            fontColorInteger = ReturnIntegerColorUtil.getIntegerCor(note.getTextColor());
            checkNoteColor(fontColorInteger);
        } else {
            note = new Note();
        }
    }


    private void fillComponentsByNote() {
        integerColorBackgroundNote = ReturnColorIntegerUtil.ReturnColor(note.getDefaultColor());
        rootLayout.setBackgroundColor(getResources().getColor(integerColorBackgroundNote));
        titleEditField.setTextColor(note.getTextColor());
        descriptionEditField.setTextColor(note.getTextColor());
    }

    private void checkNoteStyle() {
        if (note.getStyle() == StyleTextEnum.BOLD_ITALICO) {
            descriptionEditField.setTypeface(typefaceItalicBold());
        } else if (note.getStyle() == StyleTextEnum.BOLD) {
            descriptionEditField.setTypeface(typefaceBold());
        } else if (note.getStyle() == StyleTextEnum.ITALICO) {
            descriptionEditField.setTypeface(typefaceItalic());
        } else if (note.getStyle() == StyleTextEnum.NORMAL) {
            descriptionEditField.setTypeface(typefaceNormal());
        }
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, integerColorBackgroundNote));
        }
    }

    private void fillFields() {
        titleEditField.setText(note.getTitle());
        descriptionEditField.setText(note.getDescription());
    }

    public void createNote() {
        int position;
        note.setTitle(titleEditField.getText().toString());
        note.setDescription(descriptionEditField.getText().toString());
        if (note.getId() == 0) {
            refreshListNote();
            position = notesList.size();
            note.setPosition(position);
            note.setStyle(textStyle);
            note.setTextColor(textColorInteger);
            noteDAO.insert(note);
        } else if (note.getId() != 0) {
            Calendar instanceNow = Calendar.getInstance();
            note.setDataEdicao(instanceNow);
            note.setTextColor(textColorInteger);
            noteDAO.update(note);
        }

    }

    private void onColorItemClick() {
        recyclerViewColorsAdapter.setOnClickListener(color -> {
            ColorsEnum colorEnumOnCLick = color.getCor();
            integerColorBackgroundNote = ReturnColorIntegerUtil.ReturnColor(colorEnumOnCLick);
            rootLayout.setBackgroundColor(getResources().getColor(integerColorBackgroundNote));
            note.setDefaultColor(colorEnumOnCLick);
            setStatusBarColor();

        });
    }

    private void btnBoldDisabledClick(View v) {
        btnBoldDisabledOnClick();
        bold = true;
        boldLogic();
    }

    private void btnItalicDisabledClick(View v) {
        btnItalicDisabledOnClick();
        italic = true;
        italicLogic();
    }
}
