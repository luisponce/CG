/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Math.Point3;

/**
 * Projection plane. It is implemented as a singleton, because there should
 * be only on ProjectionPlane in the Scene.
 * @author htrefftz
 */
public class ProjectionPlane {
    /** Minimum X */
    protected double minX;
    /** Maximum X */
    protected double maxX;
    /** Minimum Y */
    protected double minY;
    /** Maximum Y */
    protected double maxY;
    /** Z coordinate of the plane */
    protected double z;
    /** Width of the plane in pixels */
    protected int pixelWidth;
    /** Height of the plane in pixels */
    protected int pixelHeight;
    
    private boolean DEBUG = false;
    
    /**
     * Construct the instance as soon as the class is loaded
     */
    private static final ProjectionPlane projectionPlane = new ProjectionPlane();
    /**
     * Avoid any constructor
     */
    private ProjectionPlane() { }
    /**
     * Provide access to the instance
     * @return instance created as the class was loaded
     */
    public static ProjectionPlane getInstance() {
        return projectionPlane;
    }
    
    /**
     * Provide the intial values
     * @param minX minimum x value of the plane
     * @param maxX maximum x value of the plane
     * @param minY minimum y value of the plane
     * @param maxY maximum y value of the plane
     * @param z z value of the plane
     * @param pixelWidth width of the projection plane, in pixels
     * @param pixelHeight height of the projection plane, in pixels
     */
    public void init(double minX, double maxX, double minY, double maxY,
            double z, int pixelWidth, int pixelHeight ) {
        ProjectionPlane.projectionPlane.minX = minX;
        ProjectionPlane.projectionPlane.maxX = maxX;
        ProjectionPlane.projectionPlane.minY = minY;
        ProjectionPlane.projectionPlane.maxY = maxY;
        ProjectionPlane.projectionPlane.z = z;
        ProjectionPlane.projectionPlane.pixelWidth = pixelWidth;
        ProjectionPlane.projectionPlane.pixelHeight = pixelHeight;
    }
    
    /**
     * Given the index in X direction and the index in Y direction,
     * this method returns the corresponding point on the projection plane
     * @param xIndex
     * @param yIndex
     * @return 
     */
    public Point3 computePointOnPlane(int xIndex, int yIndex) {
        Point3 p = new Point3(0, 0, 0);
        double deltaX = (maxX - minX)/pixelWidth;
        double x = minX + xIndex * deltaX;
        double deltaY = (maxY - minY)/pixelHeight;
        double y = minY + yIndex * deltaY;
        if(DEBUG) System.out.println("In computePointOnPlane: " + x + " " + y + " " + z);
        return new Point3(x, y, z);
    }
    
    /**
     * Main program to test the class
     * @param args not used
     */
    public static void main(String [] args) {
        ProjectionPlane pp = ProjectionPlane.getInstance();
        pp.init(-25d, 25d, -25d, 25d, 50d, 512, 512);
        System.out.println(pp.computePointOnPlane(256, 256));
        System.out.println(pp.computePointOnPlane(0, 0));
    }
    
}
