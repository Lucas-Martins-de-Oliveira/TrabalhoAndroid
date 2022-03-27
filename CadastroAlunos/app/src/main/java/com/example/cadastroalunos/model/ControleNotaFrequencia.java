package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class ControleNotaFrequencia extends SugarRecord {

    private long idTurmaAluno;
    private long idTurmaDisciplina;
    private double nota;
    private double frequencia;

    public ControleNotaFrequencia() {

    }

    public ControleNotaFrequencia(long idTurmaAluno, long idTurmaDisciplina, double nota, double frequencia) {
        this.idTurmaAluno = idTurmaAluno;
        this.idTurmaDisciplina = idTurmaDisciplina;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public long getIdTurmaAluno() {
        return idTurmaAluno;
    }

    public void setIdTurmaAluno(long idTurmaAluno) {
        this.idTurmaAluno = idTurmaAluno;
    }

    public long getIdTurmaDisciplina() {
        return idTurmaDisciplina;
    }

    public void setIdTurmaDisciplina(long idTurmaDisciplina) {
        this.idTurmaDisciplina = idTurmaDisciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControleNotaFrequencia controleNotaFrequencia = (ControleNotaFrequencia) o;
        return ((idTurmaAluno == controleNotaFrequencia.idTurmaAluno) && (idTurmaDisciplina == controleNotaFrequencia.idTurmaDisciplina));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota);
    }

}

