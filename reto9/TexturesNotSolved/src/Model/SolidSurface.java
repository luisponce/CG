/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Math.Intersectable;

/**
 * All objects implementing this interface have to return a material.
 * @author htrefftz
 */
public interface SolidSurface {
    public Material getMaterial();
    public Intersectable getIntersectable();
}
