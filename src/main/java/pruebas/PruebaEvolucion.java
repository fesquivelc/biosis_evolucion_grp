/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import algoritmo.AnalizadorAsistencia;
import algoritmo.Interprete;
import algoritmo.InterpreteDetalle;
import com.personal.utiles.FechaUtil;
import controladores.EmpleadoControlador;
import entidades.asistencia.Asistencia;
import entidades.asistencia.DetalleAsistencia;
import entidades.escalafon.Empleado;
import entidades.reportes.RptAsistenciaDetallado;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utiles.HerramientaGeneral;

/**
 *
 * @author Francis
 */
public class PruebaEvolucion {

    public static void probarInterprete(List<Asistencia> asistenciaList) {
        final Interprete interprete = new InterpreteDetalle();
        List<RptAsistenciaDetallado> asistenciaDetalleList = interprete.interpretar(asistenciaList);
        System.out.println("TERMINANDO DE INTERPRETAR, LISTADO: "+asistenciaDetalleList.size());
        for (RptAsistenciaDetallado detalle : asistenciaDetalleList) {
//            System.out.println("TIPO: "+detalle);
            if (detalle.getTipo() <= 0) {
                String detalleString = String.format(
                        "%s (%s) %s TIPO: %s (%s - %s / %s - %s) %s",
                        detalle.getEmpleado().getNombreCompleto(),
                        detalle.getReferencias(),
                        HerramientaGeneral.formatoFecha.format(detalle.getFecha()),
                        detalle.getTipo(),
                        detalle.getEnPermiso()[0] ? "PERMISO" : detalle.getHoraEvento()[0] == null ? "FALTA" : HerramientaGeneral.formatoHoraMinutoSegundo.format(detalle.getHoraEvento()[0]),
                        detalle.getEnPermiso()[1] ? "PERMISO" : detalle.getHoraEvento()[1] == null ? "FALTA" : HerramientaGeneral.formatoHoraMinutoSegundo.format(detalle.getHoraEvento()[1]),
                        detalle.getEnPermiso()[2] ? "PERMISO" : detalle.getHoraEvento()[2] == null ? "FALTA" : HerramientaGeneral.formatoHoraMinutoSegundo.format(detalle.getHoraEvento()[2]),
                        detalle.getEnPermiso()[3] ? "PERMISO" : detalle.getHoraEvento()[3] == null ? "FALTA" : HerramientaGeneral.formatoHoraMinutoSegundo.format(detalle.getHoraEvento()[3]),
                        detalle.getPermisos()
                );

                System.out.println(detalleString);
            }

        }
    }

    //SELECCIONAMOS A UN EMPLEADO CON PERMISOS CON HORA, PERMISOS CON FECHA, FERIADOS

    public static void main(String[] args) {
        final EmpleadoControlador empc = new EmpleadoControlador();
        final AnalizadorAsistencia analizador = new AnalizadorAsistencia();
        /*
         FECHAS DEL 01 AL 10 DE MAYO
         */
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 4, 1);
        Date fechaInicio = FechaUtil.soloFecha(cal.getTime());
        cal.set(2015, 4, 31);
        Date fechaFinal = FechaUtil.soloFecha(cal.getTime());

        /*
         OBTENER EMPLEADO A UTILIZAR        
         */
        List<Empleado> empleado = empc.buscarXPatron("27916207");//MARLENI
//        List<Empleado> empleado = empc.buscarXPatron("27900188");//BURGOS
//        List<Empleado> empleado = empc.buscarXPatron("27929242");//ABANTO

        /*
         SE DEBE TENER EN CUENTA COMO MOSTRAR LA DATA
         */
        Date horaInicio = new Date();
        List<Asistencia> asistenciaList = analizador.analizarAsistencia(empleado, fechaInicio, fechaFinal);
        Date horaFin = new Date();
        System.out.println("TIEMPO DE DESARROLLO: " + (horaFin.getTime() - horaInicio.getTime()));

        /*
         COMENZAMOS A RECORRER TODA LA ASISTENCIA        
         */
        DateFormat dfFecha = new SimpleDateFormat("EEEE dd.MM.yyyy");
        DateFormat dfHoraMinuto = new SimpleDateFormat("HH:mm");
        DateFormat dfHora = new SimpleDateFormat("HH:mm:ss");
        for (Asistencia asistencia : asistenciaList) {
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("EMPLEAD@: " + asistencia.getEmpleado().getNombreCompleto());
            System.out.println("FECHA: " + dfFecha.format(asistencia.getFecha()).toUpperCase());
            System.out.println("TIPO: " + asistencia.getResultado() + "\t\t(1.- PERMISO X FECHA  2.- FERIADO  3.- VACACION  5.- ASISTENCIA)");
            System.out.println("");

            if (asistencia.getResultado() == 5) {
                String detalleReferencia = "";
                String detalleMarcacion = "";
                String permisos = "";
                for (DetalleAsistencia detalle : asistencia.getDetalleAsistenciaList()) {
                    detalleReferencia += " " + dfHoraMinuto.format(detalle.getHoraReferencia()) + " ";

                    if (detalle.isPermiso()) {
                        detalleMarcacion += " PERMISO ";
                    } else {
                        if (detalle.getHoraEvento() == null) {
                            detalleMarcacion += " FALTA ";
                        } else {
                            detalleMarcacion += " " + dfHora.format(detalle.getHoraEvento()) + " ";
                        }
                    }
                }

                if (asistencia.getPermisoList() != null) {

                    for (DetalleAsistencia permiso : asistencia.getPermisoList()) {
                        permisos += " " + dfHoraMinuto.format(permiso.getHoraReferencia()) + " ";
                    }
                }

                System.out.println("HORARIO REFERENCIA");
                System.out.println(detalleReferencia);
                System.out.println("");
                System.out.println("EVENTOS");
                System.out.println(detalleMarcacion);
                if (!permisos.isEmpty()) {
                    System.out.println("");
                    System.out.println("PERMISOS");
                    System.out.println(permisos);
                }

            }

        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------+");

        probarInterprete(asistenciaList);
        System.exit(0);
    }
}
