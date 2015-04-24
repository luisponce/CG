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
        Triangle triangle = new Triangle(
            new Point3(-200,-200,-500),
            new Point3(200,-200,-500),
            new Point3(-200,200,-500));
        triangle.computeNormal();

        Material material = new Material(
            new MyColor(0d, 1d, 1d),
            0.1d,       // Ka
            0.5d,       // Kd
            0.4d,       // Ks
            128          // n
        );
        Surface triangularSurface = 
                new Surface(material, triangle);
        myScene.addSolidSurface(triangularSurface);
        
        // The sphere to be added
        Sphere sphere = new Sphere(new Point3(50d, 50d, -500d), 100d);
        Surface sphereSurface = 
                new Surface(material, sphere);
        myScene.addSolidSurface(sphereSurface);
        
        // The light for the scene
        Light light = new Light(
            new Point3(-200, -200, -300),
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
