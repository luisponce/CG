/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * Class to hold the information for a 3D point.
 * @author htrefftz
 */
public class Point3 {
    /** x */
    protected double x;
    /** y */
    protected double y;
    /** z */
    protected double z;
    
    /**
     * Constructor or a point
     * @param x x coordinate
     * @param y y coordinate 
     * @param z z coordinate
     */
    public Point3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString() {
        String s = x + " " + y + " " + z;
        return s;
    }
}
