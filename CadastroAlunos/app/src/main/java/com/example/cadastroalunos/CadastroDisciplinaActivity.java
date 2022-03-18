package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edNomeDisciplina;
    private MaterialSpinner spProfessor;
    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Cadastro de Disciplina");

        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        lnPrincipal = findViewById(R.id.lnPrincipal);

        iniciaSpinners();
    }

    //Validação dos campos
    private void validaCampos(){
        //Valida o campo do nome da disciplina
        if(edNomeDisciplina.getText().toString().equals("")){
            edNomeDisciplina.setError("Informe o Nome da Disciplina!");
            edNomeDisciplina.requestFocus();
            return;
        }

        //Valida a professor
        if(spProfessor.getSelectedItem() == null){
            spProfessor.setError("Informe um professor para a Disciplina!");
            spProfessor.requestFocus();
            return;
        }

        salvarDisciplina();
    }

    private void limparCampos() {
        edNomeDisciplina.setText("");
        spProfessor.setSelection(Adapter.NO_SELECTION);
    }

    public void salvarDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(edNomeDisciplina.getText().toString());
        Professor professor = (Professor) spProfessor.getSelectedItem();
        disciplina.setIDProfessor(professor.getId());

        if(DisciplinaDAO.salvar(disciplina) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a disciplina ("+disciplina.getNome()+") " +
                    "verifique o log", 0);


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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void iniciaSpinners(){
        spProfessor = findViewById(R.id.spProfessor);

        List<Professor> listProfessor = new ArrayList<>();
        listProfessor = ProfessorDAO.retornaProfessores("", new String[]{}, "nome asc");

        ArrayAdapter adapterProfessor = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  listProfessor);

        spProfessor.setAdapter(adapterProfessor);

    }
}