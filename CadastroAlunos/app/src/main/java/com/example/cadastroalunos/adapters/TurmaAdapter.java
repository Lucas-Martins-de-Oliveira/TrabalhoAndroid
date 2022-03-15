package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private List<Turma> listaTurmas;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText edDescricaoTurma;
        TextInputEditText edRegimeTurma;
        TextInputEditText edPeriodoTurma;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edDescricaoTurma = (TextInputEditText)itemView.findViewById(R.id.edDescricaoTurma);
            edPeriodoTurma = (TextInputEditText)itemView.findViewById(R.id.edPeriodoTurma);
            edRegimeTurma = (TextInputEditText)itemView.findViewById(R.id.edRegimeTurma);
        }
    }

    @NonNull
    @Override
    public TurmaAdapter.TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        TurmaAdapter.TurmaViewHolder viewHolder = new TurmaAdapter.TurmaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaAdapter.TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);

        holder.edDescricaoTurma.setText(turma.getDescricao());
        holder.edRegimeTurma.setText(turma.getRegime());
        holder.edPeriodoTurma.setText(turma.getPeriodo());
    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }
}
