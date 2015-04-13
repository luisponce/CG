/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CG.Transform;

import CG.Matriz3;
import CG.Punto3;
import CG.Vector3;

/**
 *
 
 */
public class Camera extends Matriz3 {
  
    public Camera(Punto3 pos, Vector3 V, Punto3 lookat) {
        super();
        
        //Punto3 lookat = new Punto3(50.0,50.0,-22.5);
        
        Vector3 N = new Vector3(lookat, pos);
        Vector3 n = N.normalized();
        
        Vector3 U = Vector3.cross(V, n);
        Vector3 u = U.normalized();
        
        Vector3 v = Vector3.cross(n, u);
        
        double[][] r = {{u.getX(), u.getY(), u.getZ(),   0},
                        {v.getX(), v.getY(), v.getZ(),   0},
                        {n.getX(), n.getY(), n.getZ(),   0},
                        {0, 0, 0, 1}};
        
        this.setMatrix(r);
    }  
}
