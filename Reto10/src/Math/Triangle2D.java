/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author htrefftz
 */
public class Triangle2D {
    /** point 0 */
    Point2 p0;
    /** point 1 */
    Point2 p1;
    /** point 2 */
    Point2 p2;

    public Triangle2D(Point2 p0, Point2 p1, Point2 p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point2 getP0() {
        return p0;
    }

    public Point2 getP1() {
        return p1;
    }

    public Point2 getP2() {
        return p2;
    }

    
}
