package com.projeto.anotacoes.classes.ui.adapter.listaNotasAdapter;

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
import com.projeto.anotacoes.classes.database.dao.NoteDAO;
import com.projeto.anotacoes.classes.model.Note;
import com.projeto.anotacoes.classes.model.enums.ColorsEnum;
import com.projeto.anotacoes.classes.ui.util.DevolveCorIntegerUtil;
import com.projeto.anotacoes.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.Collections;
import java.util.List;


public class NotesAdapterList extends RecyclerView.Adapter<NotesAdapterList.NoteViewHolder> {

    private final Context context;
    private final List<Note> list;
    private OnClickListernet onClickListernet;
    final NoteDAO dao;


    public NotesAdapterList(Context context, List<Note> list, NoteDAO dataDao) {
        this.context = context;
        this.list = list;
        this.dao = dataDao;
    }

    public void setOnClickListerner(OnClickListernet onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_nota, parent, false);
        return new NoteViewHolder(inflater);
    }

    @Override
    public synchronized void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note noteBind = list.get(position);
        ColorsEnum colorDefault = noteBind.getDefaultColor();
        int corInt = DevolveCorIntegerUtil.DevolveCorInt(colorDefault);
        SetDrawablesBackgroundColorUtil.setDrawableColor(context, holder.itemView.getBackground(), corInt);
        holder.vincule(noteBind);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void remove(int bindingAdapterPosition) {
        Note note = list.get(bindingAdapterPosition);
        for (Note a : list) {
            if (a.getPosition() > note.getPosition()) {
                a.setPosition(a.getPosition() - 1);
                dao.update(a);
            }
        }
        notifyItemRemoved(bindingAdapterPosition);
        list.remove(bindingAdapterPosition);
    }

    public void swap(int first, int targeter) {
        Note primeiro = list.get(first);
        Note segundo = list.get(targeter);
        long rec = primeiro.getPosition();
        primeiro.setPosition(segundo.getPosition());
        segundo.setPosition(rec);
        Collections.swap(list, first, targeter);
        dao.update(primeiro);
        dao.update(segundo);
        notifyItemMoved(first, targeter);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private Note note;
        final TextView titulo;
        final TextView descricao;
        final CardView cardView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            cardView = itemView.findViewById(R.id.item_nota_cardView);
            itemView.setOnClickListener(v -> onClickListernet.onItemClick(note));
        }

        public void vincule(Note note) {
            this.note = note;
            titulo.setText(note.getTitle());
            descricao.setText(note.getDescription());
            int colorNota = (note.getTextColor());
            titulo.setTextColor(ContextCompat.getColor(context, colorNota));
            descricao.setTextColor(ContextCompat.getColor(context, colorNota));

        }
    }

}
