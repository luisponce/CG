/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Class to hold the information of a particular material.
 * @author htrefftz
 */
public class Material {
    /** MyColor of this material */
    protected MyColor color;
    /** Ambient constant */
    protected double ka;
    /** Diffuse constant */
    protected double kd;
    /** Specular constant */
    protected double ks;
    /** Specular exponent */
    protected int n;

    public Material(MyColor color, double ka, double kd, double ks, int n) {
        this.color = color;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.n = n;
    }
    
    
}
