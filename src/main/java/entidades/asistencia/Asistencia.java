/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.asistencia;

import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Francis
 */
public class Asistencia {
    private Empleado empleado;
    private List<DetalleAsistencia> detalleAsistenciaList;
    private Date fecha;
    private Boleta permiso1;
    private Boleta permiso2;
    private int resultado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<DetalleAsistencia> getDetalleAsistenciaList() {
        return detalleAsistenciaList;
    }

    public void setDetalleAsistenciaList(List<DetalleAsistencia> detalleAsistenciaList) {
        this.detalleAsistenciaList = detalleAsistenciaList;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boleta getPermiso1() {
        return permiso1;
    }

    public void setPermiso1(Boleta permiso1) {
        this.permiso1 = permiso1;
    }

    public Boleta getPermiso2() {
        return permiso2;
    }

    public void setPermiso2(Boleta permiso2) {
        this.permiso2 = permiso2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    
    
}
