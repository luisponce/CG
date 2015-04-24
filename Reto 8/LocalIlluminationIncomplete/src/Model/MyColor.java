/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Class to hold the information about a color
 * @author htrefftz
 */
public class MyColor {
    /** Red component */
    protected double r;
    /** green component */
    protected double g;
    /** blue component */
    protected double b;
 
    /**
     * Constructor for the color
     * @param r red
     * @param g green
     * @param b blue
     */
    public MyColor(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    /**
     * Returns the red component
     * @return red component
     */
    public double getR() {
        return r;
    }
    /**
     * Returns the green component
     * @return green component
     */
    public double getG() {
        return g;
    }
    /**
     * Returns the blue component
     * @return blue component
     */
    public double getB() {
        return b;
    }
    
    /**
     * Adds this color to another color
     * @param otherColor other color to add to this
     * @return new color
     */
    public MyColor addMyColor(MyColor otherColor) {
        double newR = this.r + otherColor.r;
        double newG = this.g + otherColor.g;
        double newB = this.b + otherColor.b;
        return new MyColor(newR, newG, newB);
    }
    
    /**
     * Multiplies this color with another and returns a new color
     * with the result
     * @param otherColor other color to multiply with this
     * @return new color
     */
    public MyColor timesMyColor(MyColor otherColor) {
        double newR = this.r * otherColor.r;
        double newG = this.g * otherColor.g;
        double newB = this.b * otherColor.b;
        return new MyColor(newR, newG, newB);
    }
    
    /**
     * Multiplies this color with a scalar factor
     * @param scalar number to scale this color 
     * @return new color
     */
    public MyColor timesScalar(double scalar) {
        double newR = this.r * scalar;
        double newG = this.g * scalar;
        double newB = this.b * scalar;
        return new MyColor(newR, newG, newB);
    }
    
    @Override
    public String toString() {
        return r + " " + g + " " + b;
    }
}
