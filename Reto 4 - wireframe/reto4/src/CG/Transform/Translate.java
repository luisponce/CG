/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CG.Transform;

import CG.Matriz3;

/**
 * esta clase representa las transormaciones de traslacion
 */
public class Translate extends CG.Matriz3 {
    
    public Translate(double dx, double dy, double dz) {
        super();
        
        double[][] t = {{1, 0, 0, dx},
                        {0, 1, 0, dy},
                        {0, 0, 1, dz},
                        {0, 0, 0, 1}};
        
        this.setMatrix(t);
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de traslacion:");
        
        double dx = 5;
        double dy = 3.5;
        double dz = 6.3;
        System.out.println("dx = "+dx);
        System.out.println("dy = "+dy);
        
        Matriz3 m1 = new Translate(dx, dy, dz);
        System.out.println("T = \n"+m1.toString());
    }
    
}
