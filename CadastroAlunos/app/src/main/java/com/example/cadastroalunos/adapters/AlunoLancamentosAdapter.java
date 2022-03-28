package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.ViewAlunoLancamento;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AlunoLancamentosAdapter extends RecyclerView.Adapter<AlunoLancamentosAdapter.AlunoLancamentosViewHolder> {

    private List<ViewAlunoLancamento> listaAlunoLancamentos;
    private Context context;

    public AlunoLancamentosAdapter(List<ViewAlunoLancamento> listaAlunoLancamentos, Context context) {
        this.listaAlunoLancamentos = listaAlunoLancamentos;
        this.context = context;
    }

    public static class AlunoLancamentosViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeAluno;
        TextInputEditText edNomeDisciplina;
        TextInputEditText edNota;
        TextInputEditText edFrequencia;
        TextInputEditText edStatus;

        public AlunoLancamentosViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeAluno = (TextInputEditText)itemView.findViewById(R.id.edNomeAluno);
            edNomeDisciplina = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);
            edNota = (TextInputEditText)itemView.findViewById(R.id.edNota);
            edFrequencia = (TextInputEditText)itemView.findViewById(R.id.edFrequencia);
            edStatus = (TextInputEditText)itemView.findViewById(R.id.edStatusDisciplina);
        }
    }

    @Override
    public AlunoLancamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_aluno_lancamentos, parent, false);

        AlunoLancamentosAdapter.AlunoLancamentosViewHolder viewHolder = new AlunoLancamentosViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoLancamentosViewHolder holder, int position) {
        ViewAlunoLancamento ViewAlunoLancamento = listaAlunoLancamentos.get(position);

        holder.edNomeAluno.setText(ViewAlunoLancamento.getAluno().getNome());
        holder.edNomeDisciplina.setText(ViewAlunoLancamento.getDisciplina().getNome());
        holder.edNota.setText(ViewAlunoLancamento.getNota().toString());
        holder.edFrequencia.setText(ViewAlunoLancamento.getFrequencia().toString());
        holder.edStatus.setText(ViewAlunoLancamento.getStatus());
    }

    @Override
    public int getItemCount() {
        return listaAlunoLancamentos.size();
    }


}
