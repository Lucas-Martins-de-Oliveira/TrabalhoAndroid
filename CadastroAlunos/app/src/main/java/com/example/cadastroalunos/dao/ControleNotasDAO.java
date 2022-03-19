package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.ControleNotas;
import com.example.cadastroalunos.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class ControleNotasDAO {

    public static long salvar(ControleNotas controleNotas){
        try{
            return controleNotas.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o controle de notas: "+ex.getMessage());
            return -1;
        }
    }

    public static ControleNotas getControleNotas(int id){
        try{
            return ControleNotas.findById(ControleNotas.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o controle de notas: "+ex.getMessage());
            return null;
        }
    }

    public static List<ControleNotas> retornaControleNotas(String where, String[]whereArgs, String orderBy){
        List<ControleNotas> list = new ArrayList<>();
        try{
            list = ControleNotas.find(ControleNotas.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de controle das notas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(ControleNotas controleNotas){
        try{
            return ControleNotas.delete(controleNotas);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o controle das notas: "+ex.getMessage());
            return false;
        }
    }
}
