/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.sisgedo;

import com.personal.utiles.FechaUtil;
import controladores.Controlador;
import dao.DAOSISGEDO;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utiles.HerramientaGeneral;

/**
 *
 * @author RyuujiMD
 */
public class BoletaControlador extends Controlador<Boleta>{
    
    private BoletaControlador() {
        super(Boleta.class, new DAOSISGEDO<Boleta>());
    }
    
    public static BoletaControlador getInstance() {
        return BoletaControladorHolder.INSTANCE;
    }
    
    public List<Boleta> permisoXFechaXEmpleadoEntreFecha(Empleado empleado, Date fechaInicio, Date fechaFin){
        String jpql = "SELECT b FROM Boleta b WHERE "
                + "b.retornoFechaHora >= :fechaHoraInicio AND "
                + "(b.inicioFechaHora <= :fechaHoraInicio OR (b.inicioFechaHora >= :fechaHoraInicio AND b.inicioFechaHora <= :fechaHoraFin)) AND "
                + "b.usuario.numeroDocumento = :empleado";
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("empleado", empleado.getNroDocumento());
        parametros.put("fechaHoraInicio", FechaUtil.soloFecha(fechaInicio));
        parametros.put("fechaHoraFin", FechaUtil.unirFechaHora(fechaInicio,HerramientaGeneral.horaFinal));
        return this.getDao().buscar(jpql, parametros);
    }
    
    private static class BoletaControladorHolder {

        private static final BoletaControlador INSTANCE = new BoletaControlador();
    }
}
