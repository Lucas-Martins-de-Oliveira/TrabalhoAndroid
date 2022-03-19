package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.TurmaDisciplinas;

import java.util.ArrayList;
import java.util.List;

public class TurmaDisciplinasDAO {
    public static long salvar(TurmaDisciplinas turmaDisciplinas) {
        try {
            return turmaDisciplinas.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar a Disciplina na Turma: "+ex.getMessage());
            return -1;
        }
    }

    public static TurmaDisciplinas getTurmaDisciplinas(int id){
        try{
            return TurmaDisciplinas.findById(TurmaDisciplinas.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar a Turma Disciplina: "+ex.getMessage());
            return null;
        }
    }

    public static List<TurmaDisciplinas> retornaTurmaDisciplinas(String where, String[]whereArgs, String orderBy){
        List<TurmaDisciplinas> list = new ArrayList<>();
        try{
            list = TurmaDisciplinas.find(TurmaDisciplinas.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Turma Disciplinas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(TurmaDisciplinas turmaDisciplinas){
        try{
            return TurmaDisciplinas.delete(turmaDisciplinas);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar a Turma Disciplina: "+ex.getMessage());
            return false;
        }
    }
}
