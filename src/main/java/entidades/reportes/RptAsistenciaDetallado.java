/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.reportes;

import entidades.escalafon.Empleado;
//import entidades.sisgedo.Salida;
import java.util.Date;

/**
 *
 * @author RyuujiMD
 */
public class RptAsistenciaDetallado {

    private Empleado empleado;
    private Date[] horaReferencia;
    private Date[] horaTolerancia;
    private Date[] horaEvento;
    private boolean[] enPermiso;
    private int tipo;
    private String permisos;
    private Date fecha;
    private String regimenLaboral;
    private String referencias;
    private int marcacionesTotales;
    private boolean detalleFinal;

    public boolean isDetalleFinal() {
        return detalleFinal;
    }

    public void setDetalleFinal(boolean detalleFinal) {
        this.detalleFinal = detalleFinal;
    }

    public int getMarcacionesTotales() {
        return marcacionesTotales;
    }

    public void setMarcacionesTotales(int marcacionesTotales) {
        this.marcacionesTotales = marcacionesTotales;
    }

    public RptAsistenciaDetallado() {
        horaReferencia = new Date[4];
        horaTolerancia = new Date[4];
        horaEvento = new Date[4];
        enPermiso = new boolean[4];
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date[] getHoraReferencia() {
        return horaReferencia;
    }

    public void setHoraReferencia(Date[] horaReferencia) {
        this.horaReferencia = horaReferencia;
    }

    public Date[] getHoraTolerancia() {
        return horaTolerancia;
    }

    public void setHoraTolerancia(Date[] horaTolerancia) {
        this.horaTolerancia = horaTolerancia;
    }

    public Date[] getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date[] horaEvento) {
        this.horaEvento = horaEvento;
    }

    public boolean[] getEnPermiso() {
        return enPermiso;
    }

    public void setEnPermiso(boolean[] enPermiso) {
        this.enPermiso = enPermiso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRegimenLaboral() {
        return regimenLaboral;
    }

    public void setRegimenLaboral(String regimenLaboral) {
        this.regimenLaboral = regimenLaboral;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

}
