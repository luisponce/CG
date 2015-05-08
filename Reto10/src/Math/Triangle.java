/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * Holds the vertices of a triangle.
 * @author htrefftz
 */
public class Triangle implements Intersectable {
    /** Vertex 0 */
    protected Point3 p0;
    /** Vertex 1 */
    protected Point3 p1;
    /** Vertex 2 */
    protected Point3 p2;
    /** Normal */
    protected Vector3 normal;
    
    private final boolean DEBUG = false;

    /**
     * Consructs a triangle given three vertices
     * @param p0 vertex 0
     * @param p1 vertex 1
     * @param p2 vertex 2
     */
    public Triangle(Point3 p0, Point3 p1, Point3 p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }
    /**
     * Computes the myNormal to the triangle
     * @return returns the normal of the triangle
     */
    public Vector3 computeNormal() {
        Vector3 myNormal = Vector3.crossProduct(
                new Vector3(p0, p1), 
                new Vector3(p0, p2)
        );
        myNormal.normalize();
        if(DEBUG) System.out.println("Normal: " + myNormal);
        this.normal = myNormal;
        return normal;
    }
    
    /**
     * Finds if there is an intersection between this triangle and a given
     * ray
     * @param ray
     * @return either a length 0 array if there is no intersection or a
     * length 1 array if there is one. If there is an intersection, the array
     * contains the value "t" of the ray at the intersection point.
     */
    @Override
    public double [] findIntersections(Ray ray) {
        // Matrix of coefficients
        Matrix3x3 m = new Matrix3x3(
                ray.p1.x - ray.p0.x, -(this.p1.x - this.p0.x), -(this.p2.x - this.p0.x), 
                ray.p1.y - ray.p0.y, -(this.p1.y - this.p0.y), -(this.p2.y - this.p0.y), 
                ray.p1.z - ray.p0.z, -(this.p1.z - this.p0.z), -(this.p2.z - this.p0.z) 
        );
        // c vector
        Vector3 c = new Vector3(
                this.p0.x - ray.p0.x,
                this.p0.y - ray.p0.y,
                this.p0.z - ray.p0.z
        );
        // Solve the system
        System3x3 system = new System3x3(m, c);
        double [] intersection = new double[0]; // assume there is no intersection
        try {
            Vector3 solution = system.solve();
            if(DEBUG) System.out.println("Solution: " + solution);

            double t = solution.vector[0];
            double beta = solution.vector[1];
            double gamma = solution.vector[2];
            double alpha = 1d - beta - gamma;
            // Determine if there is an intersection with the triangle or not
            if(0 <= alpha && alpha <= 1d &&
               0 <= beta  && beta  <= 1d &&
               0 <= gamma && gamma <= 1d) {
                // The ray intersects the triangle
                intersection = new double[1];
                intersection[0] = t;
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return intersection;
    }
}
