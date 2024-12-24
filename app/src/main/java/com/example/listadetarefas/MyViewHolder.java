package com.example.listadetarefas;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView txtTarefa;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTarefa = itemView.findViewById(R.id.txtTarefa);
    }
}
