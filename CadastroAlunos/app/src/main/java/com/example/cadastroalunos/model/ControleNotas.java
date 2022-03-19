package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class ControleNotas extends SugarRecord {

    private long idTurmaAluno;
    private long idTurmaDisciplina;
    private double nota;

    public ControleNotas() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControleNotas controleNotas = (ControleNotas) o;
        return ((idTurmaAluno == controleNotas.idTurmaAluno) && (idTurmaDisciplina == controleNotas.idTurmaDisciplina));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota);
    }
}
