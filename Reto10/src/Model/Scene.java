/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Math.Intersectable;
import java.util.ArrayList;
import Math.Point3;
import Math.Point2;
import Math.Triangle;
import Math.Ray;
import Math.Triangle2D;
import Math.Intersection;
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
    
    public void throwRays() {
        ProjectionPlane pp = ProjectionPlane.getInstance();
        Point3 p0 = new Point3(0, 0, 0);
        // From bottom to top
        for(int yIndex = 0; yIndex <= Scene.HEIGHT_IN_PIXELS; yIndex++) {
            // From left to right
            for(int xIndex = 0; xIndex <= Scene.WIDTH_IN_PIXELS; xIndex++) {
                Point3 p1 = pp.computePointOnPlane(xIndex, yIndex);
                Ray ray = new Ray(p0, p1);
                // TODO: change this to visit all surfaces in the 
                // ArrayList
                /*TriangularSurface ts = (TriangularSurface)solidSurfaces.get(0);
                Triangle triangle = ts.triangle;
                Intersection [] solutions = triangle.findIntersections(ray);
                if(solutions.length > 0) {
                    Point3 intersectionPoint = ray.evaluate(solutions[0].getT());
                    if(DEBUG)
                        System.out.println("intersectionPoint: " + intersectionPoint);
                    imageToDraw[yIndex][xIndex] =
                        Illuminator.getInstance().computeColor(intersectionPoint, 
                                ts, solutions[0]);
                    //if(DEBUG) 
                        //System.out.println("Color: " + imageToDraw[yIndex][xIndex]);
                } else {
                    // TODO create a dafault background color
                    imageToDraw[yIndex][xIndex] = new MyColor(0, 0, 0);
                }*/
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
    
    public void moduloTest() {
        for(int i = -20; i <= 20; i++) {
            int j = i % 15;
            if (j < 0) j += 15;
            System.out.println(j);
        }
        
    }
    
    public static void main(String [] args) {
        Scene myScene = Scene.getInstance();
        myScene.init();
        // The object to be drawn
        Triangle triangle = new Triangle(
            new Point3(-200,-200,-500),
            new Point3(200,-200,-500),
            new Point3(-200,200,-500));
        triangle.computeNormal();
        
        Triangle triangle2 = new Triangle(
            new Point3(-100,-200,-600),
            new Point3(300,-200,-600),
            new Point3(300,200,-600));
        triangle.computeNormal();
        

        Material material = new Material(
            //new MyColor(0d, 1d, 1d),
            new MyColor(1d, 0d, 0d),
            0.1d,       // Ka
            0.5d,       // Kd
            0.4d,       // Ks
            128          // n
        );
        TriangularSurface triangularSurface = 
                new TriangularSurface(material, triangle);
        TriangularSurface triangularSurface2 = new TriangularSurface(material, triangle2);
        
        // ST coordinates for texture mapping
        Triangle2D stCoordinates = new Triangle2D(
                new Point2(0d, 0d),
                new Point2(2d, 0d),
                new Point2(0d, 2d)
        );
        triangularSurface.setTextureSTCoordinates(stCoordinates);
        triangularSurface2.setTextureSTCoordinates(stCoordinates);
        
        // Read the texture image
        Texture texture = new Texture("earthmap1k.ppm");
        triangularSurface.setTexture(texture);
        triangularSurface2.setTexture(texture);
        
        // Add the Solid Triange to the scene
        myScene.addSolidSurface(triangularSurface);
        myScene.addSolidSurface(triangularSurface2);
        // The light for the scene
        Light light = new Light(
            new Point3(-100, -100, -400),
            new MyColor(0.9d, 0.9d, 0.9d)
        );
        myScene.addLight(light);
        // Add the ambient light
        AmbientLight ambientLight = new AmbientLight(new MyColor(0.1, 0.1, 0.1));
        myScene.setAmbientLight(ambientLight);
        // The projection plane
        ProjectionPlane projectionPlane = ProjectionPlane.getInstance();
        //projectionPlane.init(-25d, 25d, -25d, 25d, 50, 512, 512);
        projectionPlane.init(-25d, 25d, 
                -25d, 25d, 
                -50, 
                Scene.WIDTH_IN_PIXELS, Scene.HEIGHT_IN_PIXELS);
        myScene.setProjectionPlane(projectionPlane);
        // Camera Position
        myScene.setCameraPosition(new Point3(0d, 0d, 0d));
        // Throw the rays to intersect with the objects in the scene
        myScene.throwRays();
        if(myScene.DEBUG) System.out.println("finished");
        // Save the image to a file
        Image image = new Image(Scene.getInstance().imageToDraw);
        image.escribirImagen("imagen.ppm");
        image.escribirImagenEnsayo("imagenEnsayo.ppm");
        myScene.moduloTest();
    }
}
