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
 * @author jonathaneidelman
 */
public class Ship extends GameObject {

    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    private int width = 20;
    private int height = 30;
    private void fillVerticesAndEdges(int x, int y){
        vertices.add(new Punto2 (-5 + x,-(-10 - y)));
        vertices.add(new Punto2 (5 + x,-(-10 - y)));
        vertices.add(new Punto2 (3 + x,-(-5 - y)));
        vertices.add(new Punto2 (5 + x,-(-5 - y)));
        vertices.add(new Punto2 (10 + x,-(-10 - y)));
        vertices.add(new Punto2 (10 + x,-(10 - y)));
        vertices.add(new Punto2 (0 + x,-(20 - y)));
        vertices.add(new Punto2 (-10 + x,-(10 - y)));
        vertices.add(new Punto2 (-10 + x,-(-10 - y)));
        vertices.add(new Punto2 (-5 + x,-(-5 - y)));
        vertices.add(new Punto2 (-3 + x,-(-5 - y)));
        vertices.add(new Punto2 (-5 + x,-(-10 - y)));
        
        for (int i = 0; i < 11; i++) {
            Integer[] linea1 = new Integer[2];
            linea1[0] = i;
            linea1[1] = (i + 1) % 11;
            edges.add(linea1);
        }
    }

    public Ship(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e, int width, int height, Graphics2D g2d) {
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
