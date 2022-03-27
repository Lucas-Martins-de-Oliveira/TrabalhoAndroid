package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.TurmaAlunosDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.model.TurmaAlunos;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaAlunoActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spAluno;
    private LinearLayout lnPrincipal;
    private RecyclerView rvListaTurmaAlunos;
    private List<Aluno> listAluno = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma_aluno);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de Alunos na Turma");

        lnPrincipal = findViewById(R.id.lnPrincipal);

        iniciaSpinnerAluno();
        iniciaSpinnerTurma();
        carregaAlunosTurma(-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        // TODO: Implementar o limpar campos
    }

    private void iniciaSpinnerTurma() {

        //Carrega as turmas
        spTurma = findViewById(R.id.spTurma);

        List<Turma> listTurma = new ArrayList<>();
        listTurma = TurmaDAO.retornaTurma("", new String[]{}, "descricao asc");


        ArrayAdapter adapterTurma = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  listTurma);

        spTurma.setAdapter(adapterTurma);

        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {

                if (pos >= 0) {
                    Turma turma = (Turma) arg0.getItemAtPosition(pos);
                    carregaAlunosTurma(turma.getId());
                } else {
                    carregaAlunosTurma(-1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void iniciaSpinnerAluno() {
        //Carrega os Alunos
        spAluno = findViewById(R.id.spAluno);

        List<Aluno> spListAluno = new ArrayList<>();
        spListAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");


        ArrayAdapter adapterAluno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  spListAluno);

        spAluno.setAdapter(adapterAluno);
    }

    private void carregaAlunosTurma(long idTurma) {
        listAluno.clear();
        if (idTurma != -1) {
            List<TurmaAlunos> listTurmaAlunos = new ArrayList<>();
            listTurmaAlunos = TurmaAlunosDAO.retornaTurmaAlunosByTurma(idTurma);
            for (TurmaAlunos turmaAlunos : listTurmaAlunos) {
                Aluno aluno = AlunoDAO.getAluno((int) turmaAlunos.getIdAluno());
                if (aluno != null) {
                    listAluno.add(aluno);
                }
            }
        }
        atualizaListaAluno();
    }

    private void validaCampos() {

        //Valida a turma
        if(spTurma.getSelectedItem() == null){
            spTurma.setError("Informe uma Turma!");
            spTurma.requestFocus();
            return;
        }

        if (listAluno.isEmpty()) {
            spAluno.setError("Informe pelo menos 1 aluno para a Turma!");
            spAluno.requestFocus();
            return;
        }

        salvarTurmaAlunos();
    }

    public void salvarTurmaAlunos() {

        Turma turma = (Turma) spTurma.getSelectedItem();

        for (Aluno aluno : listAluno) {
            TurmaAlunos turmaAlunos = new TurmaAlunos();
            turmaAlunos.setIdTurma(turma.getId());
            turmaAlunos.setIdAluno(aluno.getId());
            turmaAlunos.save();
        }

        setResult(RESULT_OK);
        finish();
    }

    public void AddAluno(View view) {

        //Valida a Turma
        if(spTurma.getSelectedItem() == null){
            spTurma.setError("Selecione uma Turma!");
            spTurma.requestFocus();
            return;
        }

        //Valida o Aluno
        if(spAluno.getSelectedItem() == null){
            spAluno.setError("Selecione um Aluno!");
            spAluno.requestFocus();
            return;
        }

        Aluno aluno = (Aluno) spAluno.getSelectedItem();

        if (listAluno.contains(aluno)) {
            spAluno.setError(aluno.getNome() + " já adicionado, Verifique!");
            spAluno.requestFocus();
            return;
        }

        listAluno.add(aluno);

        atualizaListaAluno();
    }

    public void atualizaListaAluno(){

        rvListaTurmaAlunos = findViewById(R.id.rvListaTurmaAlunos);
        AlunoAdapter adapter = new AlunoAdapter(listAluno, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurmaAlunos.setLayoutManager(llm);
        rvListaTurmaAlunos.setAdapter(adapter);
    }
}