package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class Disciplina extends SugarRecord {
    private String nome;

    public Disciplina() {

    }

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
