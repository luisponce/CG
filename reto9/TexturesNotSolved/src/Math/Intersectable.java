/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * All objects that are intersectable with a ray have to implement function
 * findIntersections, which return an array with intersections of a ray with
 * this object.
 * @author htrefftz
 */
public interface Intersectable {
    /**
     * 
     * @param ray Ray to intersect this object with.
     * @return array with values of t for the ray intersection(s) with 
     * this object.
     */
    public Intersection [] findIntersections(Ray ray);
}
