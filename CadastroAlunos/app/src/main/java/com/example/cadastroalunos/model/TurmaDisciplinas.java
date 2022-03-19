package com.example.cadastroalunos.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class TurmaDisciplinas extends SugarRecord {
    private long idTurma;

    private long idDisciplina;


    public long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(long idTurma) {
        this.idTurma = idTurma;
    }

    public long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public TurmaDisciplinas() {
    }

    public TurmaDisciplinas(Long idTurma, Long idDisciplina) {
        this.idTurma = idTurma;
        this.idDisciplina = idDisciplina;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TurmaDisciplinas turmaDisciplinas = (TurmaDisciplinas) obj;
        return ((idTurma == turmaDisciplinas.idTurma) && (idDisciplina == turmaDisciplinas.idDisciplina));
    }
}
