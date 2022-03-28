package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.adapters.AlunoLancamentosAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ControleNotaFrequenciaDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaAlunosDAO;
import com.example.cadastroalunos.dao.TurmaDisciplinasDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.ControleNotaFrequencia;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.TurmaAlunos;
import com.example.cadastroalunos.model.TurmaDisciplinas;
import com.example.cadastroalunos.model.ViewAlunoLancamento;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaLancamentoNotaFrequenciaActivity extends AppCompatActivity {

    private RecyclerView rvListaLancamentoNotaFrequencia;
    private LinearLayout lnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lancamento_nota_frequencia);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Listagem de Notas e Frequências");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        lnLista = findViewById(R.id.lnLista);

        atualizaListaLancamentos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add:
                abrirLancamentoNotasFrequencia();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirLancamentoNotasFrequencia() {
        Intent intent = new Intent(this, LancamentoNotaFrequenciaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Util.customSnackBar(lnLista, "Lançamento salvo com sucesso!", 1);
        }
        atualizaListaLancamentos();
    }

    private void atualizaListaLancamentos() {
        List<ControleNotaFrequencia> listaControleNotaFrequencia = new ArrayList<>();
        listaControleNotaFrequencia = ControleNotaFrequenciaDAO.retornaControleNotaFrequencia("", new String[]{}, "id_turma_aluno asc, id_turma_disciplina asc");
        Log.e("PHS", "Tamanho da lista: "+listaControleNotaFrequencia.size());

        rvListaLancamentoNotaFrequencia = findViewById(R.id.rvListaLancamentoNotaFrequencia);
        List<ViewAlunoLancamento> listaViewAlunoLancamento = new ArrayList<>();

        for (ControleNotaFrequencia cnf : listaControleNotaFrequencia) {

            ViewAlunoLancamento viewAlunoLancamento = new ViewAlunoLancamento();
            TurmaAlunos turmaAlunos = TurmaAlunosDAO.getTurmaAlunos(cnf.getIdTurmaAluno());
            Aluno aluno = AlunoDAO.getAluno(turmaAlunos.getIdAluno());
            viewAlunoLancamento.setAluno(aluno);

            TurmaDisciplinas turmaDisciplinas = TurmaDisciplinasDAO.getTurmaDisciplinas(cnf.getIdTurmaDisciplina());
            Disciplina disciplina = DisciplinaDAO.getDisciplina(turmaDisciplinas.getIdDisciplina());
            viewAlunoLancamento.setDisciplina(disciplina);
            viewAlunoLancamento.setNota(cnf.getNota());
            viewAlunoLancamento.setFrequencia(cnf.getFrequencia());

            listaViewAlunoLancamento.add(viewAlunoLancamento);
        }

        AlunoLancamentosAdapter adapter = new AlunoLancamentosAdapter(listaViewAlunoLancamento, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaLancamentoNotaFrequencia.setLayoutManager(llm);
        rvListaLancamentoNotaFrequencia.setAdapter(adapter);
    }
}