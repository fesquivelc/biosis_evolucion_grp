/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import com.personal.utiles.ModeloTabla;
import entidades.reportes.RptNoMarca;
import java.util.List;
import utiles.HerramientaGeneral;

/**
 *
 * @author Francis
 */
public class MTNoMarcan extends ModeloTabla<RptNoMarca>{

    public MTNoMarcan(List<RptNoMarca> datos) {
        super(datos);
        this.nombreColumnas = new String[]{"Empleado","Fecha","Hora de referencia"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        RptNoMarca noMarca = this.datos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return noMarca.getEmpleado().getNombreCompleto();
            case 1:
                return HerramientaGeneral.formatoNombreDiaFecha.format(noMarca.getFecha());
            case 2:
                return HerramientaGeneral.formatoHoraMinuto.format(noMarca.getReferencia());
            default:
                return null;
        }
    }
    
}
