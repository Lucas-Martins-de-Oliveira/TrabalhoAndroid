package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class ControleFrequencia extends SugarRecord {

    private long idTurmaAluno;
    private long idTurmaDisciplina;
    private double frequencia;

    public ControleFrequencia() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControleFrequencia controleNotas = (ControleFrequencia) o;
        return ((idTurmaAluno == controleNotas.idTurmaAluno) && (idTurmaDisciplina == controleNotas.idTurmaDisciplina));
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequencia);
    }
}
