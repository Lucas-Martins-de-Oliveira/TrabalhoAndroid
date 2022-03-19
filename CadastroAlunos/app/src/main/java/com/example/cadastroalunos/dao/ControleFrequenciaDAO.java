package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.ControleFrequencia;

import java.util.ArrayList;
import java.util.List;

public class ControleFrequenciaDAO {
    public static long salvar(ControleFrequencia controleFrequencia){
        try{
            return controleFrequencia.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o controle de frequências: "+ex.getMessage());
            return -1;
        }
    }

    public static ControleFrequencia getControleFrequencia(int id){
        try{
            return ControleFrequencia.findById(ControleFrequencia.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o controle de frequências: "+ex.getMessage());
            return null;
        }
    }

    public static List<ControleFrequencia> retornaControleFrequencias(String where, String[]whereArgs, String orderBy){
        List<ControleFrequencia> list = new ArrayList<>();
        try{
            list = ControleFrequencia.find(ControleFrequencia.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de controle das frequências: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(ControleFrequencia controleFrequencia){
        try{
            return ControleFrequencia.delete(controleFrequencia);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o controle das frequências: "+ex.getMessage());
            return false;
        }
    }
}
