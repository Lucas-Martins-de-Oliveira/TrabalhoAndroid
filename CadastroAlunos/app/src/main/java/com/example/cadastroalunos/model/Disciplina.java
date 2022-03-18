package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class Disciplina extends SugarRecord {
    private String nome;

    private long idProfessor;

    public Disciplina() {

    }

    public Disciplina(String nome, long idProfessor) {
        this.nome = nome;
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIDProfessor() {
        return idProfessor;
    }

    public void setIDProfessor(long idProfessor) {
        this.idProfessor = idProfessor;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }
}
