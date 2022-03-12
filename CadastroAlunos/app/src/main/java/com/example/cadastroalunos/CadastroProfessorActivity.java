package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroProfessorActivity extends AppCompatActivity {

    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de Professores");
        
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        lnPrincipal = findViewById(R.id.lnPrincipal);
    }

    private void validaCampos() {
        if (edNomeProfessor.getText().toString().length() < 3) {
            edNomeProfessor.setError("Informe o Nome do Professor!");
            edNomeProfessor.requestFocus();
            return;
        }

        if (edCpfProfessor.getText().toString().length() < 3) {
            edCpfProfessor.setError("Informe o CPF do Professor!");
            edCpfProfessor.requestFocus();
            return;
        }
    }

    public void salvarProfessor() {
        Professor professor = new Professor();
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setNome(edNomeProfessor.getText().toString());

        if (ProfessorDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o Professor ("+professor.getNome()+") " +
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
        edCpfProfessor.setText("");
        edNomeProfessor.setText("");
    }
}