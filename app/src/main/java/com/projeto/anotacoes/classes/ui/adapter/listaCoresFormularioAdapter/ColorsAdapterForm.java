package com.projeto.anotacoes.classes.ui.adapter.listaCoresFormularioAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.anotacoes.R;
import com.projeto.anotacoes.classes.model.enums.ColorsEnum;
import com.projeto.anotacoes.classes.model.Cor;
import com.projeto.anotacoes.classes.ui.util.DevolveCorIntegerUtil;
import com.projeto.anotacoes.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.List;

public class ColorsAdapterForm extends RecyclerView.Adapter<ColorsAdapterForm.ColorsHolder> {

    private final Context context;
    private final List<Cor> list;
    private OnClickLCoresListerner onClickListernet;




    public ColorsAdapterForm(Context context, List<Cor> list) {
        this.context = context;
        this.list = list;
    }


    public void setOnClickListerner(OnClickLCoresListerner onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public ColorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_cores, parent, false);

        return new ColorsHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorsHolder holder, int position) {
        Cor color = list.get(position);
        ColorsEnum colorDefault = color.getCor();
        int corInt = DevolveCorIntegerUtil.DevolveCorInt(colorDefault);
        SetDrawablesBackgroundColorUtil.setDrawableColor(context,holder.item.getBackground(),corInt);
        holder.vincule(color);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ColorsHolder extends RecyclerView.ViewHolder {
        Cor colors;
        final View item;

        public ColorsHolder(View itemView) {
            super(itemView);
            itemClick(itemView);
            item = itemView.findViewById(R.id.item_cores);

        }

        private void itemClick(View itemView) {
            itemView.setOnClickListener(v -> onClickListernet.onItemClick(colors));
        }

        public void vincule(Cor colors) {
            this.colors = colors;

        }
    }

}
