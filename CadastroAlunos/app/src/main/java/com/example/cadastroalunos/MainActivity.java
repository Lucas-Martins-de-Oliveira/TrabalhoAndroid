package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }


    public void cadastrarAluno(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void cadastrarDisciplina(View view) {
        Intent intent = new Intent(this, ListaDisciplinaActivity.class);
        startActivity(intent);
    }

    public void cadastrarProfessor(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void cadastrarTurma(View view) {
        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void cadastrarTurmaDisciplina(View view) {
        Intent intent = new Intent(this, CadastroTurmaDisciplinaActivity.class);
        startActivity(intent);
    }

    public void cadastrarTurmaAluno(View view) {
        Intent intent = new Intent(this, CadastroTurmaAlunoActivity.class);
        startActivity(intent);
    }

    public void lancarNotasFrequencias(View view) {
        Intent intent = new Intent(this, ListaLancamentoNotaFrequenciaActivity.class);
        startActivity(intent);
    }
}