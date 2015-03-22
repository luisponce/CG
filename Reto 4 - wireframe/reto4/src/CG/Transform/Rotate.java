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
public class Rotate extends CG.Matriz3{
    
    public Rotate(double thetaX, double thetaY, double thetaZ) {
        super();
        
        Matriz3 r = rotateX(thetaX);
        r = Matriz3.times(r, rotateY(thetaY));
        r = Matriz3.times(r, rotateZ(thetaZ));
        
        this.setMatrix(r.getMatrix());
    }
    
    private Matriz3 rotateZ(double theta){
        double cos = Math.cos(Math.toRadians(theta));
        double sin = Math.sin(Math.toRadians(theta));
        
        double[][] r = {{cos, -sin, 0, 0},
                        {sin, cos,  0, 0},
                        {0,   0,    1, 0},
                        {0,   0,    0, 1}};
        
        return new Matriz3(r);
    }
    
    private Matriz3 rotateY(double theta){
        double cos = Math.cos(Math.toRadians(theta));
        double sin = Math.sin(Math.toRadians(theta));
        
        double[][] r = {{cos,  0, sin, 0},
                        {0,    1, 0,   0},
                        {-sin, 0, cos, 0},
                        {0,    0, 0,   1}};
        
        return new Matriz3(r);
    }
    
    private Matriz3 rotateX(double theta){
        double cos = Math.cos(Math.toRadians(theta));
        double sin = Math.sin(Math.toRadians(theta));
        
        double[][] r = {{1, 0,   0,    0},
                        {0, cos, -sin, 0},
                        {0, sin, cos,  0},
                        {0, 0,   0,    1}};
        
        return new Matriz3(r);
    }
}
