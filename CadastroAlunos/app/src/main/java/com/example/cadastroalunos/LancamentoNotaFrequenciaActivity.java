package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ControleNotaFrequenciaDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaAlunosDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.dao.TurmaDisciplinasDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.ControleNotaFrequencia;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.model.TurmaAlunos;
import com.example.cadastroalunos.model.TurmaDisciplinas;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class LancamentoNotaFrequenciaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private MaterialSpinner spAluno;
    private MaterialSpinner spDisciplina;
    private TextInputEditText edNota;
    private TextInputEditText edFrequencia;
    private LinearLayout lnPrincipal;
    private List<ControleNotaFrequencia> listCNF = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_nota_frequencia);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Lançamento de Nota e Frequência");

        lnPrincipal = findViewById(R.id.lnPrincipal);
        spTurma = findViewById(R.id.spTurma);
        spAluno = findViewById(R.id.spAluno);
        spDisciplina = findViewById(R.id.spDisciplina);
        edNota = findViewById(R.id.edNota);
        edFrequencia = findViewById(R.id.edFrequencia);

        iniciarSpinnerTurma();
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
                //TODO: adicionar método  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar método  de salvar dados
                validaCampos();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void validaCampos() {
        //Valida a turma
        Turma turma = (Turma) spTurma.getSelectedItem();
        if(spTurma.getSelectedItem() == null){
            spTurma.setError("Informe uma Turma!");
            spTurma.requestFocus();
            return;
        }

        //Valida o Aluno
        Aluno aluno = (Aluno) spAluno.getSelectedItem();
        if(aluno == null){
            spAluno.setError("Informe um Aluno!");
            spAluno.requestFocus();
            return;
        }

        //Valida a Disciplina
        Disciplina disciplina = (Disciplina) spDisciplina.getSelectedItem();
        if(disciplina == null){
            spDisciplina.setError("Informe a Disciplina!");
            spDisciplina.requestFocus();
            return;
        }

        Double nota = 0.0;
        try {
            nota = Double.parseDouble(edNota.getText().toString());
            if (!between(nota, 0.0, 100.0)) {
                throw new Exception("Informe uma nota entre 0 - 100!");
            }
        } catch(Exception e){
            edNota.setError("Informe uma nota entre 0 - 100!");
            edNota.requestFocus();
            return;
        }

        Double frequencia = 0.0;
        try {
            frequencia = Double.parseDouble(edFrequencia.getText().toString());
            if (!between(frequencia, 0.0, 100.0)) {
                throw new Exception("Informe uma frequencia entre 0 - 100!");
            }
        } catch (Exception e) {
            edFrequencia.setError("Informe uma frequencia entre 0 - 100!");
            edFrequencia.requestFocus();
            return;
        }

        TurmaAlunos turmaAluno = TurmaAlunosDAO.retornaTurmaAlunoByTurmaByAluno(turma.getId(), aluno.getId());
        if (turmaAluno == null) {
            spAluno.setError("Aluno: " + aluno.getNome() + " não encontrado para a Turma: " + turma.getDescricao() + "!");
            spAluno.requestFocus();
            return;
        }

        TurmaDisciplinas turmaDisciplinas = TurmaDisciplinasDAO.retornaTurmaDisciplinaByTurmaByDisciplina(turma.getId(), disciplina.getId());
        if (turmaDisciplinas == null) {
            spDisciplina.setError("Disciplina: " + disciplina.getNome() + " não encontrado para a Turma: " + turma.getDescricao() + "!");
            spDisciplina.requestFocus();
            return;
        }

        salvaLancamento(turmaAluno.getId(), turmaDisciplinas.getId(), nota, frequencia);
    }

    public void iniciarSpinnerTurma() {

        //Carrega as turmas
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
                    iniciarSpinnerAlunos(turma.getId());
                    iniciarSpinnerDisciplinas(turma.getId());
                    spAluno.setVisibility(View.VISIBLE);
                    spDisciplina.setVisibility(View.VISIBLE);
                } else {
                    spAluno.setVisibility(View.GONE);
                    spDisciplina.setVisibility(View.GONE);
                    iniciarSpinnerAlunos(-1);
                    iniciarSpinnerDisciplinas(-1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void limparCampos() {
        edFrequencia.setText("");
        edNota.setText("");
        spDisciplina.setSelection(-1);
        spDisciplina.setFocusable(true);
    }

    public void iniciarSpinnerAlunos(long idTurma) {

        //Carrega os Alunos
        List<Aluno> spListAluno = new ArrayList<>();
        List<TurmaAlunos> listAluno = new ArrayList<>();
        listAluno = TurmaAlunosDAO.retornaTurmaAlunosByTurma(idTurma);

        for (TurmaAlunos ta : listAluno) {
            spListAluno.add(AlunoDAO.getAluno(ta.getIdAluno()));
        }

        ArrayAdapter adapterAluno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  spListAluno);

        spAluno.setAdapter(adapterAluno);

    }

    public void iniciarSpinnerDisciplinas(long idTurma) {

        //Carrega as Disciplinas
        List<Disciplina> spListDisciplina = new ArrayList<>();
        List<TurmaDisciplinas> listDisciplinas = new ArrayList<>();
        listDisciplinas = TurmaDisciplinasDAO.retornaTurmaDisciplinasByTurma(idTurma);

        for (TurmaDisciplinas td : listDisciplinas) {
            spListDisciplina.add(DisciplinaDAO.getDisciplina(td.getIdDisciplina()));
        }

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  spListDisciplina);

        spDisciplina.setAdapter(adapterDisciplinas);
    }

    public void salvaLancamento(long idTurmaAluno, long idTurmaDisciplina, Double nota, Double frequencia) {
        ControleNotaFrequencia cnf = new ControleNotaFrequencia();
        cnf.setIdTurmaAluno(idTurmaAluno);
        cnf.setIdTurmaDisciplina(idTurmaDisciplina);
        cnf.setNota(nota);
        cnf.setFrequencia(frequencia);

        if(ControleNotaFrequenciaDAO.salvar(cnf) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o aluno lançamento " +
                    "verifique o log", 0);
    }

    public static boolean between(double variable, double minValueInclusive, double maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }
}