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
public class Sphere implements Intersectable {
    /** Center point of the sphere */
    protected Point3 center;
    /** Radius of the sphere */
    protected double radius;
    
    public static final boolean DEBUG = false;

    /**
     * Constructor
     * @param center Center point of the sphere
     * @param radius Radius of the sphere
     */
    public Sphere(Point3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Computes the normal at a point on the surface of the sphere
     * @param point point to compute the normal at
     * @return a normalized vector from the center of the sphere to the point
     * on the surface
     */
    public Vector3 computeNormal(Point3 point) {
        Vector3 normal = new Vector3(center, point);
        normal.normalize();
        return normal;
    }
    
    /**
     * Finds the intersection(s) of this sphere with the ray provided as
     * parameter
     * @param ray Ray to check intersection with this sphere.
     * @return An Array with the value(s) of the parameter in the ray that
     *   determines the intersection(s) of the ray and the sphere.
     *   If there are no intersections, array ret has 0 positions.
     *   If there is only one intersection, array ret has 1 position.
     *   If there are two intersections, array ret has 2 positions.
     */
    @Override
    public double[] findIntersections(Ray ray) {
        double [] ret = new double[2];
        /*
        Escribir aquí el código para hallar la intersección entre el rayo
        y el triángulo
        Leer el comentario en el encabezado del método para ver cómo se
        retornan las posibles soluciones.
        */
        return ret;
    }
    
    public static void main(String [] args) {
        Sphere sphere = new Sphere(new Point3(1d, 0d, 0d), 1d);
        Ray ray1 = new Ray(new Point3(1d, 0d, 10d), new Point3(1d, 0d, 9d));
        double [] solutions = sphere.findIntersections(ray1);
        
        Ray ray2 = new Ray(new Point3(2d, 0d, 10d), new Point3(2d, 0d, 9d));
        solutions = sphere.findIntersections(ray2);

        Ray ray3 = new Ray(new Point3(3d, 0d, 10d), new Point3(3d, 0d, 9d));
        solutions = sphere.findIntersections(ray3);

    }
}
