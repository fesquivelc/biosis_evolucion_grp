/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import com.personal.utiles.FechaUtil;
import entidades.asistencia.Asistencia;
import entidades.asistencia.DetalleAsistencia;
import entidades.reportes.RptAsistenciaDetallado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utiles.HerramientaGeneral;

/**
 *
 * @author Francis
 */
public class InterpreteGenerico implements Interprete<RptAsistenciaDetallado> {

    /*
     EN ESTE INTÉRPRETE SE TOMA COMO FALTA CON EL SÓLO HECHO QUE EL EMPLEADO NO HAYA
     MARCADO UNA VEZ
    
     TARDANZA CON CUALQUIER TARDANZA QUE TENGA EN EL DÍA 
    
     SE HARÁN EN BLOQUES DE 4 PARA EL REPORTE
     */
    @Override
    public List<RptAsistenciaDetallado> interpretar(List<Asistencia> registroAsistencia) {
        List<RptAsistenciaDetallado> registro = new ArrayList<>();

        for (Asistencia asistencia : registroAsistencia) {
                       
            RptAsistenciaDetallado detalleAsistencia = null;

            if (asistencia.getResultado() == AnalizadorAsistencia.ASISTENCIA) {
                Long marcacionesMaximas = asistencia.getDetalleAsistenciaList().stream().filter(d -> d.getHoraReferencia() != null).count();
//                System.out.println("MARCACIONES MAXIMAS: "+marcacionesMaximas);
                int tipo = this.obtenerTipo(asistencia.getDetalleAsistenciaList(), marcacionesMaximas.intValue());
                int contador = 0;
                int marcacionContador = 0;
                for(DetalleAsistencia detalle : asistencia.getDetalleAsistenciaList()){
                    marcacionContador++;
                    if(contador == 0){
                        detalleAsistencia = new RptAsistenciaDetallado();
                        detalleAsistencia.setEmpleado(asistencia.getEmpleado());
                        detalleAsistencia.setFecha(asistencia.getFecha());
                        detalleAsistencia.setTipo(tipo);
                        detalleAsistencia.setPermisos(this.traducirPermisos(asistencia.getPermisoList()));
                        detalleAsistencia.setReferencias(this.traducirReferencias(asistencia.getDetalleAsistenciaList()));
                    }
                    
                    detalleAsistencia.getEnPermiso()[contador] = detalle.isPermiso();
                    detalleAsistencia.getHoraReferencia()[contador] = detalle.getHoraReferencia();
                    detalleAsistencia.getHoraTolerancia()[contador] = detalle.getHoraReferenciaTolerancia();
                    detalleAsistencia.getHoraEvento()[contador] = detalle.getHoraEvento();
                    detalleAsistencia.setMarcacionesTotales(marcacionesMaximas.intValue());

                    contador++;
                    detalleAsistencia.setDetalleFinal(marcacionContador == marcacionesMaximas.intValue());
                    if(contador == 4){
                        
                        registro.add(detalleAsistencia);
//                        detalleAsistencia = null;
                        contador = 0;
                    }
                }
            } else {
                detalleAsistencia = new RptAsistenciaDetallado();
                detalleAsistencia.setEmpleado(asistencia.getEmpleado());
                detalleAsistencia.setTipo(asistencia.getResultado());
                detalleAsistencia.setFecha(asistencia.getFecha());
                detalleAsistencia.setPermisos(obtenerMotivo(asistencia.getResultado(), asistencia));
                registro.add(detalleAsistencia);
            }

            

        }

        return registro;
    }

    private String traducirPermisos(List<DetalleAsistencia> permisoList) {
        String permisos = "";
        Date horaInicio = null;
        Date horaFin = null;
        for (DetalleAsistencia detalle : permisoList) {
            if (detalle.isBandera()) {
                horaInicio = detalle.getHoraReferencia();
            } else {
                horaFin = detalle.getHoraReferencia();

                permisos += String.format(" %s - %s: %s ",
                        HerramientaGeneral.formatoHoraMinuto.format(horaInicio),
                        HerramientaGeneral.formatoHoraMinuto.format(horaFin),
                        detalle.getMotivo());
            }
        }
        return permisos;
    }

    private String traducirReferencias(List<DetalleAsistencia> detalleAsistenciaList) {
        String detalles = "";
        Date horaInicio = null;
        Date horaFin = null;
        for (DetalleAsistencia detalle : detalleAsistenciaList) {
            if (detalle.isBandera()) {
                horaInicio = detalle.getHoraReferencia();
            } else {
                horaFin = detalle.getHoraReferencia();

                detalles += String.format(" %s - %s ",
                        HerramientaGeneral.formatoHoraMinuto.format(horaInicio),
                        HerramientaGeneral.formatoHoraMinuto.format(horaFin));
            }
        }
        return detalles;
    }

    private int obtenerTipo(List<DetalleAsistencia> detalleAsistenciaList, int conteo) {
        
//        System.out.println("CONTEO: "+conteo.intValue());
        
        int marcacionesPendientes = 0;
        boolean hayTardanza = false;
        for(int i = 0; i < conteo; i++){
            DetalleAsistencia detalle = detalleAsistenciaList.get(i);
            
            if (detalle.getHoraEvento() == null) {
                if (!detalle.isPermiso()) {
                    marcacionesPendientes++;
                }
            } else {
                hayTardanza = hayTardanza || detalle.isBandera() && FechaUtil.soloHora(detalle.getHoraEvento()).after(FechaUtil.soloHora(detalle.getHoraReferenciaTolerancia()));
            }
        }
        
        if(marcacionesPendientes > 0)
            return AnalizadorAsistencia.FALTA;
        else if(hayTardanza)
            return AnalizadorAsistencia.TARDANZA;
        else
            return AnalizadorAsistencia.REGULAR;
    }

    private String obtenerMotivo(int tipo, Asistencia asistencia) {
        switch (tipo) {
            case AnalizadorAsistencia.FERIADO:
                return asistencia.getFeriado().getNombre();
            case AnalizadorAsistencia.VACACION:
                return "";
            case AnalizadorAsistencia.PERMISO_FECHA:
                return asistencia.getPermiso().getDocumento();
            default:
                return "";
        }
    }
}
