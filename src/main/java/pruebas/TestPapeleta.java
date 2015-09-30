/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controladores.sisgedo.BoletaControlador;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Francis
 */
public class TestPapeleta {
    public static void main(String[] args) {
        BoletaControlador bolc = BoletaControlador.getInstance();
        Empleado empleado = new Empleado();
        /*
        ESCRIBE EL NÚMERO DEL DNI
        */
        empleado.setNroDocumento("");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 8, 1);
        Date fechaInicio = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date fechaFin = cal.getTime();
        List<Boleta> boletaList = bolc.permisoXFechaXEmpleadoEntreFecha(empleado, fechaInicio, fechaFin);
        
        boletaList.stream().forEach((boleta) -> {
            System.out.println(String.format("%s %s %s %s", 
                    boleta.getUsuario().getNumeroDocumento(), boleta.getInicioFechaHora(), boleta.getFinFechaHora(), boleta.getRetornoFechaHora()));
        });
    }
}
