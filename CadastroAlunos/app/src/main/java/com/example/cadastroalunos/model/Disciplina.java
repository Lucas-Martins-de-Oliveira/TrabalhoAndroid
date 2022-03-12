package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class Disciplina extends SugarRecord {
    private String nome;
    private Integer idProfessor;

    public Disciplina() {

    }

    public Disciplina(String nome, Integer idProfessor) {
        this.nome = nome;
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIDProfessor() {
        return idProfessor;
    }

    public void setIDProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }
}
