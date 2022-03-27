package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.ControleNotaFrequencia;

import java.util.ArrayList;
import java.util.List;

public class ControleNotaFrequenciaDAO {
    public static long salvar(ControleNotaFrequencia controleNotaFrequencia) {
        try {
            return controleNotaFrequencia.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o Controle de nota e frequência: "+ex.getMessage());
            return -1;
        }
    }

    public static ControleNotaFrequencia getControleNotaFrequencia(int id){
        try{
            return ControleNotaFrequencia.findById(ControleNotaFrequencia.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o Controle de nota e frequência: "+ex.getMessage());
            return null;
        }
    }

    public static List<ControleNotaFrequencia> retornaControleNotaFrequencia(String where, String[]whereArgs, String orderBy){
        List<ControleNotaFrequencia> list = new ArrayList<>();
        try{
            list = ControleNotaFrequencia.find(ControleNotaFrequencia.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Controle de nota e frequência: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(ControleNotaFrequencia controleNotaFrequencia){
        try{
            return ControleNotaFrequencia.delete(controleNotaFrequencia);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o controle de nota e frequência: "+ex.getMessage());
            return false;
        }
    }
}
