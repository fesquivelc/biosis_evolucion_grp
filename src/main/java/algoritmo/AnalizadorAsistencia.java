/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import controladores.AsignacionHorarioControlador;
import controladores.ContratoControlador;
import controladores.FeriadoControlador;
import controladores.PermisoControlador;
import controladores.VacacionControlador;
import controladores.sisgedo.BoletaControlador;
import entidades.AsignacionHorario;
import entidades.Feriado;
import entidades.Marcacion;
import entidades.asistencia.Asistencia;
import entidades.escalafon.Contrato;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Francis
 */
public class AnalizadorAsistencia {
    /*
     VALORES PARA COMPARAR EN LA ASISTENCIA
     */

    int NINGUNO = 0;
    int PERMISO_FECHA = 1;
    int FERIADO = 2;
    int VACACION = 3;
    int PERMISO_HORA = 4;
    /*
    RESULTADOS DE ASISTENCIA
    */
    int REGULAR = 0;
    int TARDANZA = -1;
    int FALTA = -2;
    /*
     LISTADOS QUE CONTIENEN LA INFORMACION TANTO DE FERIADOS, PERMISOS Y VACACIONES PARA EL EMPLEADO
     */
    private List<Feriado> feriadoList;
    private List<Boleta> permisoList;
    private List<Boleta> vacacionList;
    /*
     LISTADO DE LAS MARCACIONES DEL EMPLEADO
     */
    private List<Marcacion> marcacionList;
    /*
    CONTROLADORES
    */
    private final AsignacionHorarioControlador asghorc = new AsignacionHorarioControlador();
    private final BoletaControlador bolc = BoletaControlador.getInstance();
    private final ContratoControlador contc = ContratoControlador.getInstance();
    private final FeriadoControlador ferc = new FeriadoControlador();
    private final PermisoControlador permc = new PermisoControlador();
    private final VacacionControlador vacc = new VacacionControlador();

    private List<Asistencia> analizarAsistencia(List<Empleado> empleadoList, Date fechaInicio, Date fechaFin) {
        List<Asistencia> asistenciaList = new ArrayList<>();
        cargarFeriados(fechaInicio, fechaFin);
        Object objetoPermiso;
        int tipoPermiso;
        empleadoList.stream().forEach(empleado -> {
            cargarSalidas(empleado, fechaInicio, fechaFin);
            List<Contrato> contratos = contc.obtenerContratosXFechas(empleado, fechaInicio, fechaFin);
            Date desde1 = fechaInicio;
            Date hasta1 = fechaFin;
            
            List<AsignacionHorario> asignaciones = asghorc.buscarXEmpleado(empleado, desde1, hasta1);
            
            asignaciones.stream().forEach(asignacion -> {
                Date desde2 = desde1.before(asignacion.getFechaInicio()) ? asignacion.getFechaInicio() : desde1;
                Date hasta2 = hasta1.before(asignacion.getFechaFin()) ? hasta1 : asignacion.getFechaFin();
            });
        });
        
        return asistenciaList;
    }

    private void cargarFeriados(Date fechaInicio, Date fechaFin) {
        this.feriadoList = ferc.buscarXFechas(fechaInicio, fechaFin);
    }

    private void cargarSalidas(Empleado empleado, Date fechaInicio, Date fechaFin) {
        this.permisoList = bolc.permisoXFechaXEmpleadoEntreFecha(empleado, fechaInicio, fechaFin);
    }
}
