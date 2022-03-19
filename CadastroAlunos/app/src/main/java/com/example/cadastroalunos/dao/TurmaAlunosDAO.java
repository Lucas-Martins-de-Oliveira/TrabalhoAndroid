package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.TurmaAlunos;

import java.util.ArrayList;
import java.util.List;

public class TurmaAlunosDAO {
    public static long salvar(TurmaAlunos turmaAlunos) {
        try {
            return turmaAlunos.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o Aluno na Turma: "+ex.getMessage());
            return -1;
        }
    }

    public static TurmaAlunos getTurmaAlunos(int id){
        try{
            return TurmaAlunos.findById(TurmaAlunos.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o Aluno Disciplina: "+ex.getMessage());
            return null;
        }
    }

    public static List<TurmaAlunos> retornaTurmaAlunos(String where, String[]whereArgs, String orderBy){
        List<TurmaAlunos> list = new ArrayList<>();
        try{
            list = TurmaAlunos.find(TurmaAlunos.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Turma Alunos: "+ex.getMessage());
        }
        return list;
    }

    public static List<TurmaAlunos> retornaTurmaAlunosByTurma(long idTurma){
        List<TurmaAlunos> list = new ArrayList<>();
        try{
            //list = TurmaAlunos.find(TurmaAlunos.class, "idturma = ?" + idTurma, new String[]{}, "", "orderBy", "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Turma Alunos: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(TurmaAlunos turmaAlunos){
        try{
            return TurmaAlunos.delete(turmaAlunos);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar a Turma Aluno: "+ex.getMessage());
            return false;
        }
    }
}
