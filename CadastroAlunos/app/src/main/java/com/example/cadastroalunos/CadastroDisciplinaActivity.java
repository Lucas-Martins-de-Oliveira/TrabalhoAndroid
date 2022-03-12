package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edNomeDisciplina;
    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Cadastro de Disciplina");

        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        lnPrincipal = findViewById(R.id.lnPrincipal);

    }

    //Validação dos campos
    private void validaCampos(){
        //Valida o campo do nome da disciplina
        if(edNomeDisciplina.getText().toString().equals("")){
            edNomeDisciplina.setError("Informe o Nome da Disciplina!");
            edNomeDisciplina.requestFocus();
            return;
        }

        salvarDisciplina();
    }

    private void limparCampos() {
        edNomeDisciplina.setText("");
    }

    public void salvarDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(edNomeDisciplina.getText().toString());

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
}