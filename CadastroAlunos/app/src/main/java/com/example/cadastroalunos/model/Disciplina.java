package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina disciplina = (Disciplina) obj;
        return ((nome == disciplina.nome) && (idProfessor == disciplina.idProfessor));
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
