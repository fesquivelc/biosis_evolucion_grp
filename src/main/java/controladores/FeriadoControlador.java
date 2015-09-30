/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Feriado;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fesquivelc
 */
public class FeriadoControlador extends Controlador<Feriado> {

    public FeriadoControlador() {
        super(Feriado.class);
    }

    public Feriado buscarXDia(Date fecha) {
        String jpql = "SELECT f FROM Feriado f WHERE :fecha BETWEEN f.fechaInicio AND f.fechaFin";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fecha", fecha);
        List<Feriado> feriados = this.getDao().buscar(jpql, mapa, -1, 1);
        if (feriados.isEmpty()) {
            return null;
        } else {
            return feriados.get(0);
        }
    }

    public List<Feriado> buscarXFechas(Date fechaInicio, Date fechaFin) {
        String jpql = "SELECT f FROM Feriado f WHERE (f.fechaInicio <= :fechaInicio AND f.fechaFin >= :fechaInicio) OR (f.fechaInicio BETWEEN :fechaInicio AND :fechaFin)";
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fechaInicio", fechaInicio);
        mapa.put("fechaFin", fechaFin);
        List<Feriado> feriados = this.getDao().buscar(jpql, mapa, -1, 1);
        return feriados;
    }

}
