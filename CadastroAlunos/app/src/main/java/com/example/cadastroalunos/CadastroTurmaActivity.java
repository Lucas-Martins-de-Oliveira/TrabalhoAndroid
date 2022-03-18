package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.adapters.DisciplinaAdapter;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.model.TurmaDisciplinas;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edDescricaoTurma;
    private TextInputEditText edRegimeTurma;
    private TextInputEditText edPeriodoTurma;
    private MaterialSpinner spTurmaDisciplina;
    private LinearLayout lnPrincipal;
    private RecyclerView rvListaTurmaDisciplinas;
    List<Disciplina> listDisciplina = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de Turma");

        edDescricaoTurma = findViewById(R.id.edDescricaoTurma);
        edRegimeTurma = findViewById(R.id.edRegimeTurma);
        edPeriodoTurma = findViewById(R.id.edPeriodoTurma);
        lnPrincipal = findViewById(R.id.lnPrincipal);

        iniciaSpinners();

        atualizaListaDisciplina();
    }

    private void validaCampos() {
        if (edDescricaoTurma.getText().toString().equals("")) {
            edDescricaoTurma.setError("informe a Descrição da Turma!");
            edDescricaoTurma.requestFocus();
            return;
        }

        if (edRegimeTurma.getText().toString().equals("")) {
            edRegimeTurma.setError("Informe o Regime da Turma!");
            edRegimeTurma.requestFocus();
            return;
        }

        if (edPeriodoTurma.getText().toString().equals("")) {
            edPeriodoTurma.setError("Informe o Período da Turma!");
            edPeriodoTurma.requestFocus();
            return;
        }

        if (listDisciplina.isEmpty()) {
            spTurmaDisciplina.setError("Informe pelo menos 1 disciplina para a Turma!");
            spTurmaDisciplina.requestFocus();
            return;
        }

        salvarTurma();
    }

    public void salvarTurma() {
        Turma turma = new Turma();
        turma.setDescricao(edDescricaoTurma.getText().toString());
        turma.setPeriodo(edPeriodoTurma.getText().toString());
        turma.setRegime(edRegimeTurma.getText().toString());

        long idTurma = TurmaDAO.salvar(turma);

        if (idTurma > 0) {

            for (Disciplina disciplina : listDisciplina) {
                TurmaDisciplinas turmaDisciplinas = new TurmaDisciplinas();
                turmaDisciplinas.setIdTurma(idTurma);
                turmaDisciplinas.setIdDisciplina(disciplina.getId());
                turmaDisciplinas.save();
            }

            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a Turma ("+turma.getDescricao()+") " +
                    "verifique o log", 0);
        }
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
        edPeriodoTurma.setText("");
        edRegimeTurma.setText("");
        edDescricaoTurma.setText("");
    }

    private void iniciaSpinners() {

        spTurmaDisciplina = findViewById(R.id.spTurmaDisciplina);

        List<Disciplina> listDisciplina = new ArrayList<>();
        listDisciplina = DisciplinaDAO.retornaDisciplinas("", new String[]{}, "nome asc");


        ArrayAdapter adapterTurmaDisciplina = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  listDisciplina);

        spTurmaDisciplina.setAdapter(adapterTurmaDisciplina);

    }

    public void AddDisciplina(View view) {
        //Valida a turma
        if(spTurmaDisciplina.getSelectedItem() == null){
            spTurmaDisciplina.setError("Selecione uma Disciplina!");
            spTurmaDisciplina.requestFocus();
            return;
        }

        Disciplina disciplina = (Disciplina) spTurmaDisciplina.getSelectedItem();

        if (listDisciplina.contains(disciplina)) {
            spTurmaDisciplina.setError(disciplina.getNome() + " já adicionado, Verifique!");
            spTurmaDisciplina.requestFocus();
            return;
        }

        listDisciplina.add((Disciplina) spTurmaDisciplina.getSelectedItem());

        atualizaListaDisciplina();
    }

    public void atualizaListaDisciplina(){

        rvListaTurmaDisciplinas = findViewById(R.id.rvListaTurmaDisciplinas);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listDisciplina, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurmaDisciplinas.setLayoutManager(llm);
        rvListaTurmaDisciplinas.setAdapter(adapter);
    }
}