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

import com.example.cadastroalunos.adapters.DisciplinaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaAlunosDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.dao.TurmaDisciplinasDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.model.TurmaAlunos;
import com.example.cadastroalunos.model.TurmaDisciplinas;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaDisciplinaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spDisciplina;
    private LinearLayout lnPrincipal;
    private RecyclerView rvListaTurmaDisciplinas;
    private List<Disciplina> listDisciplina = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma_disciplina);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de Disciplinas na Turma");

        lnPrincipal = findViewById(R.id.lnPrincipal);

        iniciarSpinnerDisciplinas();
        iniciaSpinnerTurma();

        carregaListaDisciplinasByTurma(-1);
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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position >= 0) {
                    Turma turma = (Turma) parent.getItemAtPosition(position);
                    carregaListaDisciplinasByTurma(turma.getId());
                } else {
                    carregaListaDisciplinasByTurma(-1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void iniciarSpinnerDisciplinas() {
        //Carrega as Disciplinas
        spDisciplina = findViewById(R.id.spDisciplina);

        List<Disciplina> listspDisciplinas = new ArrayList<>();
        listspDisciplinas = DisciplinaDAO.retornaDisciplinas("", new String[]{}, "nome asc");


        ArrayAdapter adapterDisciplina = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  listspDisciplinas);

        spDisciplina.setAdapter(adapterDisciplina);
    }

    private void validaCampos() {

        //Valida a turma
        if(spTurma.getSelectedItem() == null){
            spTurma.setError("Informe uma Turma!");
            spTurma.requestFocus();
            return;
        }

        if (listDisciplina.isEmpty()) {
            spDisciplina.setError("Informe pelo menos 1 disciplina para a Turma!");
            spDisciplina.requestFocus();
            return;
        }

        salvarTurmaDisciplinas();
    }

    public void salvarTurmaDisciplinas() {

        Turma turma = (Turma) spTurma.getSelectedItem();

        for (Disciplina disciplina : listDisciplina) {
            TurmaDisciplinas turmaDisciplinas = new TurmaDisciplinas();
            turmaDisciplinas.setIdTurma(turma.getId());
            turmaDisciplinas.setIdDisciplina(disciplina.getId());
            turmaDisciplinas.save();
        }

        setResult(RESULT_OK);
        finish();
    }

    public void AddDisciplina(View view) {

        //Valida a turma
        if(spTurma.getSelectedItem() == null){
            spTurma.setError("Selecione uma Turma!");
            spTurma.requestFocus();
            return;
        }

        //Valida a Disciplina
        if(spDisciplina.getSelectedItem() == null){
            spDisciplina.setError("Selecione uma Disciplina!");
            spDisciplina.requestFocus();
            return;
        }

        Disciplina disciplina = (Disciplina) spDisciplina.getSelectedItem();

        if (listDisciplina.contains(disciplina)) {
            spDisciplina.setError(disciplina.getNome() + " já adicionado, Verifique!");
            spDisciplina.requestFocus();
            return;
        }

        listDisciplina.add(disciplina);

        atualizaListaDisciplina();
    }

    public void atualizaListaDisciplina(){

        rvListaTurmaDisciplinas = findViewById(R.id.rvListaTurmaDisciplinas);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listDisciplina, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurmaDisciplinas.setLayoutManager(llm);
        rvListaTurmaDisciplinas.setAdapter(adapter);
    }

    public void carregaListaDisciplinasByTurma(long idTurma) {

        listDisciplina.clear();
        if (idTurma != -1) {
            List<TurmaDisciplinas> listTurmaDisciplinas = new ArrayList<>();
            listTurmaDisciplinas = TurmaDisciplinasDAO.retornaTurmaDisciplinasByTurma(idTurma);
            for (TurmaDisciplinas turmaDisciplinas : listTurmaDisciplinas) {
                Disciplina disciplina = DisciplinaDAO.getDisciplina(turmaDisciplinas.getIdDisciplina());
                if (disciplina != null) {
                    listDisciplina.add(disciplina);
                }
            }
        }
        atualizaListaDisciplina();
    }
}