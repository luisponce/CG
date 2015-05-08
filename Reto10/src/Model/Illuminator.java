/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Math.Intersectable;
import Math.Point3;
import Math.Triangle;
import Math.Vector3;
//import java.util.ArrayList;

/**
 * This class holds the computeColor method, which finds the color of a 
 * single pixel. It is implemented as a singleton, since there is only one
 * in the scene.
 * @author htrefftz
 */
public class Illuminator {
    
    private final boolean DEBUG = false;
    
    /** Single instance */
    private static final Illuminator illuminator = new Illuminator();
    /** Avoid other constructors */
    private Illuminator() { }
    /**
     * Returns the single instance of the illuminator
     * @return instance of this illuminator
     */
    public static Illuminator getInstance() {
        return illuminator;
    }
    /** 
     * Computes the color of the point on the surface
     * @param point 3D coordinates of the point to draw
     * @param solidSurface properties of the object to be drawn
     * @return color of the point on the surface
     */
    public MyColor computeColor(Point3 point, SolidSurface solidSurface) {
        if(DEBUG) System.out.println("En computeColor");
        // Light
        Light light = Scene.getInstance().lights.get(0);
        MyColor lightColor = light.color;
        Point3 lightPosition = light.position;
        // Ambient Light
        AmbientLight ambientLight = Scene.getInstance().ambientLight;
        MyColor ambientLightColor = ambientLight.color;
        // Material and object
        Material material = solidSurface.getMaterial();
        MyColor objectColor = material.color;
        double Ka = material.ka;
        double Kd = material.kd;
        double Ks = material.ks;
        double n = material.n;
        // Normal of the surface
        Vector3 normal = new Vector3();
        Intersectable intersectable = solidSurface.getIntersectable();
        if (intersectable instanceof Triangle) {
            Triangle triangle = (Triangle)(solidSurface.getIntersectable());
            normal = triangle.computeNormal();
            normal.normalize();
        }
        // Position of the camera
        Point3 cameraPosition = Scene.getInstance().cameraPosition;
        // Vector pointing to the light
        Vector3 toLight = new Vector3(point, lightPosition);
        toLight.normalize();
        
        // Ambient component
        MyColor ambientColor = ambientLightColor.timesMyColor(objectColor);
        ambientColor = ambientColor.timesScalar(Ka);
        
        // Diffuse component
        double diffuseFactor = Vector3.dotProduct(normal, toLight);
        MyColor diffuseColor = objectColor.timesScalar(diffuseFactor);
        diffuseColor = diffuseColor.timesMyColor(lightColor);
        diffuseColor = diffuseColor.timesScalar(Kd);
        
        // Specular component
        Vector3 u = Vector3.timesScalar(toLight, -1);
        Vector3 r = Vector3.reflect(u, normal);
        r.normalize();
        Vector3 v = new Vector3(point, cameraPosition);
        v.normalize();
        double specularFactor = Vector3.dotProduct(r, v);
        specularFactor = Math.pow(specularFactor, n);
        MyColor specularColor = lightColor.timesScalar(specularFactor);
        specularColor = specularColor.timesScalar(Ks);
        
        // Add the three components
        MyColor finalColor = ambientColor.addMyColor(diffuseColor);
        finalColor = finalColor.addMyColor(specularColor);
        return finalColor;
    }
}
