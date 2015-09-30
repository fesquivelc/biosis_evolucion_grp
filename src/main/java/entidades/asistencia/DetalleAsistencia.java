/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.asistencia;

import entidades.DetalleJornada;
import java.util.Date;

/**
 *
 * @author Francis
 */
public class DetalleAsistencia {
    private Date horaInicio;
    private Date horaFin;
    private boolean permisoInicio;
    private boolean permisoFin;
    private DetalleJornada detalle;

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isPermisoInicio() {
        return permisoInicio;
    }

    public void setPermisoInicio(boolean permisoInicio) {
        this.permisoInicio = permisoInicio;
    }

    public boolean isPermisoFin() {
        return permisoFin;
    }

    public void setPermisoFin(boolean permisoFin) {
        this.permisoFin = permisoFin;
    }

    public DetalleJornada getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleJornada detalle) {
        this.detalle = detalle;
    }
    
    
}
