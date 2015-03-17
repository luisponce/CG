/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CG.Transform;

import CG.Matriz2;

/**
 *
 */
public class Rotate extends CG.Matriz2{
    /**
     * Constructor de la matriz de transformacion de rotacion
     * @param theta Los grados que se desean rotar
     */
    public Rotate(double theta) {
        super();
        
        double cos = Math.cos(Math.toRadians(theta));
        double sin = Math.sin(Math.toRadians(theta));
        
        double[][] r = {{cos, -sin, 0},
                        {sin, cos,  0},
                        {0,   0,    1}};
        
        this.setMatrix(r);
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de rotacion:");
        
        double theta = 180;
        System.out.println("theta = "+theta);
        
        Matriz2 m1 = new Rotate(theta);
        System.out.println("R = \n"+m1.toString());
    }
}
