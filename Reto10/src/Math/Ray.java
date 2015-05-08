/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * Class to hold the information of a ray.
 * The ray is represented as a parametric equation of a line segment
 * P = P0 + t(P1-P0)
 * @author htrefftz
 */
public class Ray {
    /** Starting point */
    Point3 p0;
    /** Ending point */
    Point3 p1;
    
    /**
     * Constructs a ray
     * @param p0 Initial point of the ray.
     * @param p1 Final point of the ray.
     */
    public Ray(Point3 p0, Point3 p1) {
        this.p0 = p0;
        this.p1 = p1;
    }
           
    /**
     * Given the value "t" of the parameter, this function returns the point 
     * in the ray that corresponds to "t"
     * @param t Parameter of the ray.
     * @return Point corresponding to "t" on the ray
     */
    public Point3 evaluate(double t) {
        double x = p0.x + t * (p1.x - p0.x);
        double y = p0.y + t * (p1.y - p0.y);
        double z = p0.z + t * (p1.z - p0.z);
        return new Point3(x, y, z);
    }
    
}
