/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Math.Intersectable;
import java.util.ArrayList;
import Math.Point3;
import Math.Triangle;
import Math.Sphere;
import Math.Ray;
import View.Image;

/**
 * Scene describing:
 * 1. The Triangle(s) to be drawn, 
 * 2. The camera (assumed to be at (0, 0, 0)
 *    Looking at (0, 0, -1)
 *    Up-vector is (0, 1, 0)
 * 3. The projection plane
 * 4. Light(s)
 * 5. Ambient Light
 * 6. Camera position
 * 
 * @author htrefftz
 */
public class Scene {
    
    private static final Scene scene = new Scene();
    
    public static final int WIDTH_IN_PIXELS = 512;
    public static final int HEIGHT_IN_PIXELS = 512;
    
    /** Objects in the scene */
    protected ArrayList<SolidSurface> solidSurfaces;
    /** Point lights in the scene */
    protected ArrayList<Light> lights;
    /** Projection plane */
    protected ProjectionPlane projectionPlane;
    /** Ambient light */
    protected AmbientLight ambientLight;
    /** Camera position */
    protected Point3 cameraPosition;
    /** Image created */
    protected MyColor [][] imageToDraw;
    
    private final boolean DEBUG = false;
    
    private Scene() {  }
    /**
     * Return the single instance of this Scene
     * @return the singleton instance
     */
    public static Scene getInstance() {
        return scene;
    }
    
    /**
     * Constructor
     * Allocate memory for the ArrayLists
     */
    public void init() {
        solidSurfaces = new ArrayList<>();
        lights = new ArrayList<>();
        projectionPlane = ProjectionPlane.getInstance();
        imageToDraw = new MyColor[Scene.HEIGHT_IN_PIXELS+1][Scene.WIDTH_IN_PIXELS+1];
    }

    public void addSolidSurface(SolidSurface solidSurface) {
        solidSurfaces.add(solidSurface);
        if(DEBUG) System.out.println("Added one surface");
    }
    
    public void addLight(Light light) {
        lights.add(light);
        if(DEBUG) System.out.println("Added one light");
    }
    
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }
    
    public void setProjectionPlane(ProjectionPlane pp) {
        projectionPlane = pp;
    }
    
    public void setCameraPosition(Point3 cp) {
        this.cameraPosition = cp;
    }

    public MyColor[][] getImageToDraw() {
        return imageToDraw;
    }
    
    public void throwRays() {
        ProjectionPlane pp = ProjectionPlane.getInstance();
        Point3 p0 = new Point3(0, 0, 0);

        // From bottom to top
        for(int yIndex = 0; yIndex <= Scene.HEIGHT_IN_PIXELS; yIndex++) {
            // From left to right
            for(int xIndex = 0; xIndex <= Scene.WIDTH_IN_PIXELS; xIndex++) {
                Point3 p1 = pp.computePointOnPlane(xIndex, yIndex);
                Ray ray = new Ray(p0, p1);
                imageToDraw[yIndex][xIndex] = new MyColor(0, 0, 0);
                
                double depth = Double.MAX_VALUE;
                for(SolidSurface ss: solidSurfaces) {
                    Intersectable intersectable = ss.getIntersectable();
                    double [] solutions = new double[0];
                    // The object is a triangle
                    if(intersectable instanceof Triangle) {
                        Triangle triangle = (Triangle)(intersectable);
                        solutions = triangle.findIntersections(ray);
                    // the object is a sphere
                    } else if (intersectable instanceof Sphere) {
                        /*
                        Escribir aquí el código para llamar el código
                        findIntersections(ray) de la clase Sphere
                        IMPORTANTE: si hay dos soluciones, se debe asegurar
                        que la solución más cercana a la cámara quede
                        en la posición 0
                        */
                        Sphere sphere = (Sphere)intersectable;
                    }
                    if(solutions.length > 0) {
                        Point3 intersectionPoint = ray.evaluate(solutions[0]);
                        if(DEBUG)
                            System.out.println("intersectionPoint: " + intersectionPoint);
                        if(solutions[0] < depth) {
                            imageToDraw[yIndex][xIndex] =
                                Illuminator.getInstance().computeColor(intersectionPoint, ss);
                            depth = solutions[0];
                        } 
                    } 
                }
            }
        }
    }
    
}
