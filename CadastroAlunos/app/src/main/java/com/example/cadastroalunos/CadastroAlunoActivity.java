package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    private MaterialSpinner spCursos;
    private MaterialSpinner spPeriodo;
    private LinearLayout lnPrincipal;
    private MaterialSpinner spTurmaAluno;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro de Aluno");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        lnPrincipal = findViewById(R.id.lnPrincipal);

        edDtNascAluno.setFocusable(false);
        edDtMatAluno.setFocusable(false);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));
        iniciaSpinners();

        setDataAtual();
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners(){
        spCursos = findViewById(R.id.spCursos);
        spPeriodo = findViewById(R.id.spPeriodo);
        spTurmaAluno = findViewById(R.id.spTurmaAluno);

        String cursos[] = new String[]{"An??lise e Desenv. Sistemas",
                "Administra????o", "Ci??ncias Cont??beis", "Direito",
                "Farm??cia", "Nutri????o"};

        String periodos[] = new String[]{"1a S??rie",
                "2a S??rie", "3a S??rie", "4a S??rie"};

        List<Turma> listTurma = new ArrayList<>();
        listTurma = TurmaDAO.retornaTurma("", new String[]{}, "descricao asc");

        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  cursos);
        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  periodos);

        ArrayAdapter adapterTurmaAluno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  listTurma);

        spCursos.setAdapter(adapterCursos);
        spPeriodo.setAdapter(adapterPeriodo);
        spTurmaAluno.setAdapter(adapterTurmaAluno);
    }

    //Valida????o dos campos
    private void validaCampos(){
        //Valida o campo Ra Aluno
        if(edRaAluno.getText().toString().equals("")){
            edRaAluno.setError("Informe o RA do Aluno!");
            edRaAluno.requestFocus();
            return;
        }

        //Valida o campo de nome do Aluno
        if(edNomeAluno.getText().toString().equals("")){
            edNomeAluno.setError("Informe o Nome do Aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edCpfAluno.getText().toString().equals("")){
            edCpfAluno.setError("Informe o CPF do Aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edDtNascAluno.getText().toString().equals("")){
            edDtNascAluno.setError("Informe a data de nascimento do Aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edDtMatAluno.getText().toString().equals("")){
            edDtMatAluno.setError("Informe a data de matricula do Aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        //Valida a turma
        if(spTurmaAluno.getSelectedItem() == null){
            spTurmaAluno.setError("Informe uma turma para o Aluno!");
            spTurmaAluno.requestFocus();
            return;
        }

        salvarAluno();
    }

    public void salvarAluno(){
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
        aluno.setCurso(spCursos.getSelectedItem().toString());
        aluno.setPeriodo(spPeriodo.getSelectedItem().toString());
        aluno.setIdTurma(spTurmaAluno.getSelectedItemId());

        if(AlunoDAO.salvar(aluno) > 0) {

            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o aluno ("+aluno.getNome()+") " +
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
                //TODO: adicionar m??todo  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar m??todo  de salvar dados
                validaCampos();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
        spCursos.setSelection(0);
        spPeriodo.setSelection(0);
        spTurmaAluno.setSelection(0);
    }

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText)dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
    }
}