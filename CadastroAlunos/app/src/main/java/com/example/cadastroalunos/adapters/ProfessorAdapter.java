package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> listaProfessores;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeProfessor = (TextInputEditText)itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor = (TextInputEditText)itemView.findViewById(R.id.edCpfProfessor);
        }
    }

    @NonNull
    @Override
    public ProfessorAdapter.ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        ProfessorAdapter.ProfessorViewHolder viewHolder = new ProfessorAdapter.ProfessorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorAdapter.ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edNomeProfessor.setText(professor.getNome());
        holder.edCpfProfessor.setText(professor.getCpf());
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }
}
