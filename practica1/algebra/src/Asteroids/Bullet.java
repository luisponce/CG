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
public class Bullet extends GameObject {

    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    private static int w = 1;
    private static int h = 8;
    
    private void fillVerticesAndEdges(int x, int y){
        /*
        vertices.add(new Punto2 (-2 + x,-(-2 - y)));
        vertices.add(new Punto2 (2 + x,-(-2 - y)));
        vertices.add(new Punto2 (2 + x,-(2 - y)));
        vertices.add(new Punto2 (-2 + x,-(2 - y)));
        vertices.add(new Punto2 (-2 + x, -(-2 - y)));
        */
        vertices.add(new Punto2 (0 + x, -(-8 - y)));
        vertices.add(new Punto2 (0 + x, -(8 - y)));
        
        for (int i = 0; i < 1; i++) {
            Integer[] linea1 = new Integer[2];
            linea1[0] = i;
            linea1[1] = (i + 1) % 2;
            edges.add(linea1);
        }
    }

    public Bullet(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e, int width, int height, Graphics2D g2d) {
        super(x, y, v, e, w, h, g2d);
        fillVerticesAndEdges(x,y);
        this.setVertices(vertices);
        this.setEdges(edges);
        
        setType(2);
    }
    
    

    @Override
    public void collidedWith(GameObject him) {
        
    }
    
}