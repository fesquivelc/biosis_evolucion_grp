/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import entidades.asistencia.Asistencia;
import entidades.asistencia.DetalleAsistencia;
import entidades.reportes.RptAsistenciaDetallado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francis
 */
public class InterpreteGenerico implements Interprete<RptAsistenciaDetallado>{

    @Override
    public List<RptAsistenciaDetallado> interpretar(List<Asistencia> registroAsistencia) {
        List<RptAsistenciaDetallado> registro = new ArrayList<>();
        registroAsistencia.stream().forEach((asistencia) -> {
            asistencia.getDetalleAsistenciaList().stream().forEach((detalle) -> {
                
            });
        });
        return registro;
    }
    
    
    private void unionPermiso(Asistencia registro, List<DetalleAsistencia> permisos){
        registro.getDetalleAsistenciaList().addAll(permisos);
        registro.getDetalleAsistenciaList().sort((r1,r2) -> r1.getHoraReferencia().compareTo(r2.getHoraReferencia()));
    }
}
