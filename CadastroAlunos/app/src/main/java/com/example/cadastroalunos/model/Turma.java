package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Turma extends SugarRecord {
    private String descricao;
    private String regime;
    private String periodo;

    public Turma() {
    }

    public Turma(String descricao, String regime, String periodo) {
        this.descricao = descricao;
        this.regime = regime;
        this.periodo = periodo;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Turma turma = (Turma) obj;
        return descricao == turma.descricao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao);
    }

    @NonNull
    @Override
    public String toString() {
        return getDescricao() + " - " + getPeriodo();
    }
}
