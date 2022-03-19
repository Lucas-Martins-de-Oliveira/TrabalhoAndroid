package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class TurmaAlunos extends SugarRecord {
    private long idTurma;

    private long idAluno;

    public long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(long idTurma) {
        this.idTurma = idTurma;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public TurmaAlunos() {
    }

    public TurmaAlunos(Long idTurma, Long idAluno) {
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TurmaAlunos turmaDisciplinas = (TurmaAlunos) obj;
        return ((idTurma == turmaDisciplinas.idTurma) && (idAluno == turmaDisciplinas.idAluno));
    }
}
