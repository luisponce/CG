/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author htrefftz
 */
public class Intersection {
    /** parameter of the ray that intersects the object */
    double t;
    /** u parameter of the surface at the intersection */
    double u;
    /** v parameter of the surface at the intersection */
    double v;

    /**
     * Constructor
     * @param t parameter of the ray that intersects the object
     * @param u u value of the surface at the intersection
     * @param v v value of the surface at the intersection
     */
    public Intersection(double t, double u, double v) {
        this.t = t;
        this.u = u;
        this.v = v;
    }

    public double getT() {
        return t;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }
    
    
}
