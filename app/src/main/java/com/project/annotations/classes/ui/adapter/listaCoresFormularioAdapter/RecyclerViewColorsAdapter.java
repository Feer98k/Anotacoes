package com.project.annotations.classes.ui.adapter.listaCoresFormularioAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anotacoes.R;
import com.project.annotations.classes.model.Color;
import com.project.annotations.classes.model.enums.ColorsEnum;
import com.project.annotations.classes.ui.util.ReturnColorIntegerUtil;
import com.project.annotations.classes.ui.util.SetDrawablesBackgroundColorUtil;

import java.util.List;

public class RecyclerViewColorsAdapter extends RecyclerView.Adapter<RecyclerViewColorsAdapter.ColorsHolder> {

    private final Context context;
    private final List<Color> listColors;
    private OnClickColorsListener onClickColorsListener;


    public RecyclerViewColorsAdapter(Context context, List<Color> listColors) {
        this.context = context;
        this.listColors = listColors;
    }


    public void setOnClickListener(OnClickColorsListener onClickColorsListener) {
        this.onClickColorsListener = onClickColorsListener;
    }

    @NonNull
    @Override
    public ColorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_colors, parent, false);
        return new ColorsHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorsHolder holder, int position) {
        Color color = listColors.get(position);
        ColorsEnum colorDefault = color.getCor();
        int colorInteger = ReturnColorIntegerUtil.ReturnColor(colorDefault);
        SetDrawablesBackgroundColorUtil.setDrawableColor(context, holder.item.getBackground(), colorInteger);
        holder.bind(color);
    }

    @Override
    public int getItemCount() {
        return listColors.size();
    }

    class ColorsHolder extends RecyclerView.ViewHolder {
        private Color colors;
        private final View item;

        public ColorsHolder(View itemView) {
            super(itemView);
            itemClick(itemView);
            item = itemView.findViewById(R.id.item_colors_color);
        }

        private void itemClick(View itemView) {
            itemView.setOnClickListener(v -> onClickColorsListener.onItemClick(colors));
        }

        public void bind(Color colors) {
            this.colors = colors;
        }
    }

}
