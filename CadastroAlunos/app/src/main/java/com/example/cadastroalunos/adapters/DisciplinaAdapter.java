package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder>{

    private List<Disciplina> listaDisciplinas;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeDisplina;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeDisplina = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);

        }
    }

    @Override
    public DisciplinaAdapter.DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        DisciplinaAdapter.DisciplinaViewHolder viewHolder = new DisciplinaAdapter.DisciplinaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaAdapter.DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplinas.get(position);

        holder.edNomeDisplina.setText(disciplina.getNome());

    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}
