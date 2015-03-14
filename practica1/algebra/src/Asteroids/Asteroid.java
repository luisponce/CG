/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Asteroids;

import Game.GameObject;
import CG.Punto2;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 */
public class Asteroid extends GameObject {

    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    private int width = 8;
    private int height = 8;
    
    private void fillVerticesAndEdges(int x, int y){
        vertices.add(new Punto2 (-2 + x,-(4 - y)));
        vertices.add(new Punto2 (2 + x,-(4 - y)));
        vertices.add(new Punto2 (4 + x,-(2 - y)));
        vertices.add(new Punto2 (4 + x,-(-2 - y)));
        vertices.add(new Punto2 (2 + x,-(-4 - y)));
        vertices.add(new Punto2 (-2 + x,-(-4 - y)));
        vertices.add(new Punto2 (-4 + x,-(-2 - y)));
        vertices.add(new Punto2 (-4 + x,-(2 - y)));
        vertices.add(new Punto2 (-2 + x,-(4 - y)));
        
        for (int i = 0; i < 8; i++) {
            Integer[] linea1 = new Integer[2];
            linea1[0] = i;
            linea1[1] = (i + 1) % 8;
            edges.add(linea1);
        }
    }

    public Asteroid(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e, int width, int height, Graphics2D g2d) {
        super(x, y, v, e, width, height, g2d);
        fillVerticesAndEdges(x,y);
        this.setVertices(vertices);
        this.setEdges(edges);
        this.setHeight(height);
        this.setWidth(width);
    }
    
    

    @Override
    public void collidedWith(GameObject him) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}