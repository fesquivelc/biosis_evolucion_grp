/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.sisgedo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RyuujiMD
 */

@Table(name = "tb_spa_papeletas")
@Entity
public class Boleta implements Serializable {
    @Id
    @Column(name = "idPapeleta")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaAutoriza")
    private Date aprobacionFechaHora;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaSalidaDel")
    private Date inicioFechaHora;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaSalidaAl")
    private Date finFechaHora;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRetorno")
    private Date retornoFechaHora;
    @ManyToOne(targetEntity = Motivo.class,optional = false)
    @JoinColumn(name = "idMotivo", referencedColumnName = "idmotivo")
    private Motivo motivo;
    @ManyToOne(targetEntity = UsuarioSISGEDO.class, optional = true)
    @JoinColumn(name = "loginUsuario", referencedColumnName = "idUsuario")
    private UsuarioSISGEDO usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAprobacionFechaHora() {
        return aprobacionFechaHora;
    }

    public void setAprobacionFechaHora(Date aprobacionFechaHora) {
        this.aprobacionFechaHora = aprobacionFechaHora;
    }

    public Date getInicioFechaHora() {
        return inicioFechaHora;
    }

    public void setInicioFechaHora(Date inicioFechaHora) {
        this.inicioFechaHora = inicioFechaHora;
    }

    public Date getFinFechaHora() {
        return finFechaHora;
    }

    public void setFinFechaHora(Date finFechaHora) {
        this.finFechaHora = finFechaHora;
    }

    public Date getRetornoFechaHora() {
        return retornoFechaHora;
    }

    public void setRetornoFechaHora(Date retornoFechaHora) {
        this.retornoFechaHora = retornoFechaHora;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public UsuarioSISGEDO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSISGEDO usuario) {
        this.usuario = usuario;
    }
    
    
}
