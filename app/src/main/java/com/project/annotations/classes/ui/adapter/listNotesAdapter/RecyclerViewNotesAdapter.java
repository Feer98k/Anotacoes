package com.project.annotations.classes.ui.adapter.listNotesAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.project.annotations.classes.database.dao.NoteDAO;
import com.project.annotations.classes.model.Note;
import com.project.annotations.classes.model.enums.ColorsEnum;
import com.project.annotations.classes.ui.util.ReturnColorIntegerUtil;
import com.project.annotations.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.Collections;
import java.util.List;


public class RecyclerViewNotesAdapter extends RecyclerView.Adapter<RecyclerViewNotesAdapter.NoteViewHolder> {

    private final Context context;
    private final List<Note> noteList;
    private OnClickListener onClickListener;
    private final NoteDAO noteDAO;


    public RecyclerViewNotesAdapter(Context context, List<Note> noteList, NoteDAO dataDao) {
        this.context = context;
        this.noteList = noteList;
        this.noteDAO = dataDao;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(inflater);
    }

    @Override
    public synchronized void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note noteBind = noteList.get(position);
        ColorsEnum colorDefault = noteBind.getDefaultColor();
        int colorInteger = ReturnColorIntegerUtil.ReturnColor(colorDefault);
        SetDrawablesBackgroundColorUtil.setDrawableColor(context, holder.itemView.getBackground(), colorInteger);
        holder.bind(noteBind);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public void remove(int bindingAdapterPosition) {
        Note note = noteList.get(bindingAdapterPosition);
        for (Note a : noteList) {
            if (a.getPosition() > note.getPosition()) {
                a.setPosition(a.getPosition() - 1);
                noteDAO.update(a);
            }
        }
        notifyItemRemoved(bindingAdapterPosition);
        noteList.remove(bindingAdapterPosition);
    }

    public void swap(int first, int target) {
        Note firstNote = noteList.get(first);
        Note secondNote = noteList.get(target);
        long rec = firstNote.getPosition();
        firstNote.setPosition(secondNote.getPosition());
        secondNote.setPosition(rec);
        Collections.swap(noteList, first, target);
        noteDAO.update(firstNote);
        noteDAO.update(secondNote);
        notifyItemMoved(first, target);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private Note note;
        private final TextView titleField;
        private final TextView descriptionField;
        final CardView cardView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleField = itemView.findViewById(R.id.item_note_title_textView);
            descriptionField = itemView.findViewById(R.id.item_note_description_textView);
            cardView = itemView.findViewById(R.id.item_nota_cardView);
            itemView.setOnClickListener(v -> onClickListener.onItemClick(note));
        }

        public void bind(Note note) {
            this.note = note;
            titleField.setText(note.getTitle());
            descriptionField.setText(note.getDescription());
            int noteColorInteger = (note.getTextColor());
            titleField.setTextColor(ContextCompat.getColor(context, noteColorInteger));
            descriptionField.setTextColor(ContextCompat.getColor(context, noteColorInteger));

        }
    }

}
