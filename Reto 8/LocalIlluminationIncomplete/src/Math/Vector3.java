/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * Holds the information of a 3x2 vector
 * @author htrefftz
 */
public class Vector3 {
    /** Array with 3 positions to store the vector */
    protected double [] vector;
    
    /**
     * Construct a vector with all zeroes.
     */
    public Vector3() {
        vector = new double[3];
        vector[0] = 0;
        vector[1] = 0;
        vector[2] = 0;
    }
    
    /**
     * Construct a vector with the three provided numbers
     * @param v0 x value
     * @param v1 y value
     * @param v2 z value
     */
    public Vector3(double v0, double v1, double v2) {
        vector = new double[3];
        vector[0] = v0;
        vector[1] = v1;
        vector[2] = v2;
    }
    
    /**
     * Creates a vector with two points
     * @param p0 initial point
     * @param p1 final point
     */
    public Vector3(Point3 p0, Point3 p1) {
        vector = new double[3];
        vector[0] = p1.x - p0.x;
        vector[1] = p1.y - p0.y;
        vector[2] = p1.z - p0.z;
    }
    /**
     * Compute the cross product of two vectors
     * @param v1 vector 1
     * @param v2 vector 2
     * @return Cross product of v1 and v2
     */
    public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        double x = v1.vector[1] * v2.vector[2] - v1.vector[2] * v2.vector[1];
        double y = -(v1.vector[0] * v2.vector[2] - v1.vector[2] * v2.vector[0]);
        double z = v1.vector[0] * v2.vector[1] - v1.vector[1] * v2.vector[0];
        return new Vector3(x, y, z);
    }
    
    /**
     * Computes the dot product of two vectors
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return dot product of v1 . v2
     */
    public static double dotProduct(Vector3 v1, Vector3 v2) {
        double acum = v1.vector[0] * v2.vector[0];
        acum += v1.vector[1] * v2.vector[1];
        acum += v1.vector[2] * v2.vector[2];
        return acum;
    }
    
    /**
     * Returns a new vector with the multiplication of a vector and a scalar
     * @param vector vector to be scaled by the scalar
     * @param scalar factor to multiply the vector with
     * @return new vector resulting from multiplying the vector times the
     * scalar
     */
    public static Vector3 timesScalar(Vector3 vector, double scalar) {
        double newX = vector.vector[0] * scalar;
        double newY = vector.vector[1] * scalar;
        double newZ = vector.vector[2] * scalar;
        return new Vector3(newX, newY, newZ);
    }
    
    /**
     * Adds two vectors and returns the result
     * @param v1 Vector one to be added
     * @param v2 Vector two to be added
     * @return new vector with the result of v1 + v2
     */
    public static Vector3 add(Vector3 v1, Vector3 v2) {
        double newX = v1.vector[0] + v2.vector[0];
        double newY = v1.vector[1] + v2.vector[1];
        double newZ = v1.vector[2] + v2.vector[2];
        return new Vector3(newX, newY, newZ);
    }
    
    /**
     * Returns a new vector equals v2 - v1
     * @param v2 Vector to subtract from
     * @param v1 Vector to subtract 
     * @return new Vector3 with the result of v2 - v1
     */
    public static Vector3 subtract(Vector3 v2, Vector3 v1) {
        double newX = v2.vector[0] - v1.vector[0];
        double newY = v2.vector[1] - v1.vector[1];
        double newZ = v2.vector[2] - v1.vector[2];
        return new Vector3(newX, newY, newZ);
    }
    
    
    
    /**
     * Normalize this vector. Length is one after this operation. 
     */
    public void normalize() {
        double length = Math.sqrt(vector[0]*vector[0] + 
                vector[1]*vector[1] + 
                vector[2]*vector[2]);
        vector[0] = vector[0] / length;
        vector[1] = vector[1] / length;
        vector[2] = vector[2] / length;
    }

    /**
     * Returns the reflection of a vector u on a surface with normal n.
     * Vector u points towards the surface, vector r (result) points
     * away from the surface.
     * n is assumed to be normalized.
     * @param u vector that will be reflected on the surface
     * @param n normal of the surface
     * @return  reflected vector, facing away from the surface
     */
    public static Vector3 reflect(Vector3 u, Vector3 n) {
        double scalar = Vector3.dotProduct(u, n) * -2d;
        Vector3 result = Vector3.timesScalar(n, scalar);
        result = Vector3.add(result, u);
        return result;
    }
    /**
     * Convert to string
     * @return printed vector 
     */
    @Override
    public String toString() {
        String s = new String();
        s += vector[0] + " " + vector[1] + " " + vector[2];
        return s;
    }
    
    public static void main(String [] args) {
        Vector3 normal = new Vector3(0d, 1d, 0d);
        Vector3 u = new Vector3(-1, -1, -1);
        Vector3 r = Vector3.reflect(u, normal);
        System.out.println(r);
    }
}
