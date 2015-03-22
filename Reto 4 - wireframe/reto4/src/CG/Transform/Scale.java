/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CG.Transform;

import CG.Matriz3;

/**
 *
 */
public class Scale extends CG.Matriz3{
    public Scale(double sx, double sy, double sz) {
        super();
        
        double[][] s = {{sx, 0, 0, 0},
                        {0, sy, 0, 0},
                        {0, 0, sz, 0},
                        {0, 0, 0, 1}};
        
        this.setMatrix(s);
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de escala:");
        
        double sx = 2;
        double sy = 2;
        double sz = 2;
        System.out.println("sx = "+sx);
        System.out.println("sy = "+sy);
        
        Matriz3 m1 = new Scale(sx, sy, sz);
        System.out.println("S = \n"+m1.toString());
    }
}
