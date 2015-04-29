/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Math.Triangle;
import Math.Intersectable;
import Math.Triangle2D;
import Math.Point2;
import Math.Vector3;

/**
 * Class to hold the material and the geometry (triangle) of this triangular
 * surface.
 * @author htrefftz
 */
public class TriangularSurface implements SolidSurface  {
    /** Material of this surface */
    protected Material material;
    /** Geometry (triangle) of this surface */
    protected Triangle triangle;
    /** Texture st coordinates of each vertex */
    protected Triangle2D textureSTCoordinates;
    
    protected Texture texture;

    /**
     * Constructor
     * @param material Material this surface is made of
     * @param triangle Geometry of this triangular surface
     */
    public TriangularSurface(Material material, Triangle triangle) {
        this.material = material;
        this.triangle = triangle;
    }
    
    /**
     * Returns the material properties of this triangular surface
     * @return the material of this surface
     */
    @Override
    public Material getMaterial() {
        return material;
    }
    
    @Override
    public Intersectable getIntersectable() {
        return triangle;
    }

    
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
    public void setTextureSTCoordinates(Triangle2D textureSTCoordinates) {
        this.textureSTCoordinates = textureSTCoordinates;
    }
    
    public Point2 getSTCoordinates(double u, double v) {
        Vector3 a = new Vector3(textureSTCoordinates.getP0().getX(),
            textureSTCoordinates.getP0().getY(), 0d);
        Vector3 b = new Vector3(textureSTCoordinates.getP1().getX(),
            textureSTCoordinates.getP1().getY(), 0d);
        Vector3 c = new Vector3(textureSTCoordinates.getP2().getX(),
            textureSTCoordinates.getP2().getY(), 0d);
        // Compute the point
        Vector3 ab = Vector3.subtract(b, a);
        Vector3 ac = Vector3.subtract(c, a);
        Vector3 result = Vector3.add(a, Vector3.timesScalar(ab, u));
        result = Vector3.add(result, Vector3.timesScalar(ac, v));
        
        Point2 point = new Point2(result.getVector()[0], result.getVector()[1]);
        return point;
    }
}
