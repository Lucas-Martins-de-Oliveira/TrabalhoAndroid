package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {
    private String nome;
    private String cpf;

    public Professor() {
    }

    public Professor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Professor professor = (Professor) obj;
        return cpf == professor.cpf;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
