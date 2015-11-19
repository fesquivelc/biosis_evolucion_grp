/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

/**
 *
 * @author Francis
 */
public class pruebas {
    static class Francis{
        double holi;

        public double getHoli() {
            return holi;
        }

        public void setHoli(double holi) {
            this.holi = holi;
        }
        
    }
    public static void main(String[] args) {
        Francis francis = new Francis();
        System.out.println("FRANCIS: "+(francis.getHoli()+2));
    }
}
