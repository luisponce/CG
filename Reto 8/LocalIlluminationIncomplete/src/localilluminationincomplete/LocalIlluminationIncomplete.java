/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localilluminationincomplete;

import Math.Point3;
import Math.Triangle;
import Math.Sphere;
import Model.AmbientLight;
import Model.Light;
import Model.Material;
import Model.MyColor;
import Model.ProjectionPlane;
import Model.Scene;
import Model.Surface;
import View.Image;


/**
 *
 * @author htrefftz
 */
public class LocalIlluminationIncomplete {

    public final static boolean DEBUG = true;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scene myScene = Scene.getInstance();
        myScene.init();
        // The object to be drawn
        Triangle triangleABC = new Triangle(
            new Point3(0,50*3,-500), // -200 -200 -500
            new Point3(20.71*3,60.355*3,-500), // 200 -200 -500
            new Point3(0,70.71*3,-500)); // -200 200 -500
        triangleABC.computeNormal();
        
        Triangle triangleADC = new Triangle( //ACD
            new Point3(0,50*3,-500),
            new Point3(0,70.71*3,-500),
            new Point3(-20.71*3,60.355*3,-500));
        triangleADC.computeNormal();
        
        
        Triangle triangleDAF = new Triangle( //ADF
            new Point3(0,50*3,-500),
            new Point3(-20.71*3,60.355*3,-500),
            new Point3(0,-20.71*3,-500));
        triangleDAF.computeNormal();
        
        Triangle triangleDEF = new Triangle(
            new Point3(-20.71*3,60.355*3,-500),
            new Point3(-20.71*3,0,-500),
            new Point3(0,-20.71*3,-500));
        triangleDEF.computeNormal();
        
        
        Triangle triangleBAF = new Triangle( //AFC
            new Point3(20.71*3,60.355*3,-500),
            new Point3(0,50*3,-500),
            new Point3(0,-20.71*3,-500));
        triangleDEF.computeNormal();
        
        Triangle triangleBFG = new Triangle( 
            new Point3(20.71*3,60.355*3,-500),
            new Point3(0,-20.71*3,-500),
            new Point3(20.71*3,0,-500));
        triangleDEF.computeNormal();
        
        
        

        Material material = new Material(
            new MyColor(0d, 1d, 1d),
            0.1d,       // Ka
            0.5d,       // Kd
            0.4d,       // Ks
            128          // n
        );
        Surface triangularSurfaceABC = 
                new Surface(material, triangleABC);
        myScene.addSolidSurface(triangularSurfaceABC);
        
        Surface triangularSurfaceADC = new Surface(material, triangleADC);
        myScene.addSolidSurface(triangularSurfaceADC);
        
        Surface triangularSurfaceDAF = new Surface(material, triangleDAF);
        myScene.addSolidSurface(triangularSurfaceDAF);
        
        Surface triangularSurfaceDEF = new Surface(material, triangleDEF);
        myScene.addSolidSurface(triangularSurfaceDEF);
        
        Surface triangularSurfaceBAF = new Surface(material, triangleBAF);
        myScene.addSolidSurface(triangularSurfaceBAF);
        
        Surface triangularSurfaceBFG = new Surface(material, triangleBFG);
        myScene.addSolidSurface(triangularSurfaceBFG);
        
        
       
        
        
        // The sphere to be added
        Sphere sphere = new Sphere(new Point3(50d, 50d, -500d), 100d);
        Surface sphereSurface = 
                new Surface(material, sphere);
        myScene.addSolidSurface(sphereSurface);
        
        // The light for the scene
        Light light = new Light(
            new Point3(-150, 120, -300),
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
        if(DEBUG) System.out.println("finished");
        // Save the image to a file
        Image image = new Image(Scene.getInstance().getImageToDraw());
        image.escribirImagen("imagen.ppm");
    }
    
}
