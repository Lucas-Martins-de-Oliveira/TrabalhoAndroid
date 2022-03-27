package com.example.cadastroalunos.model;

public class ViewAlunoLancamento {

    private Aluno aluno;
    private Disciplina disciplina;
    private Double nota;
    private Double frequencia;
    private String status;

    public ViewAlunoLancamento() {
    }
    public ViewAlunoLancamento(Aluno aluno, Disciplina disciplina, Double nota, Double frequencia) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public String getStatus() {
        String statusFinal = "Aprovado";
        if ((nota < 60.0) && (frequencia < 70.0)) {
            statusFinal = "Reprovado por Nota e Frequência";
        } else {
            if (nota < 60.0) {
                statusFinal = "Reprovado por Nota";
            } else {
                if (frequencia < 70.0) {
                    statusFinal = "Reprovado por Frequência";
                }
            }
        }
        return statusFinal;
    }
}
