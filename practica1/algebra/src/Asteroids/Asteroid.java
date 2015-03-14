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
    private static final int w = 24;
    private static final int h = 24;
    
    private void fillVerticesAndEdges(int x, int y, int level){
        if(level == 3) level++;
        vertices.add(new Punto2 (-6*level + x,-(12*level - y)));
        vertices.add(new Punto2 (6*level + x,-(12*level - y)));
        vertices.add(new Punto2 (12*level + x,-(6*level - y)));
        vertices.add(new Punto2 (12*level + x,-(-6*level - y)));
        vertices.add(new Punto2 (6*level + x,-(-12*level - y)));
        vertices.add(new Punto2 (-6*level + x,-(-12*level - y)));
        vertices.add(new Punto2 (-12*level + x,-(-6*level - y)));
        vertices.add(new Punto2 (-12*level + x,-(6*level - y)));
        vertices.add(new Punto2 (-6*level + x,-(12*level - y)));
        
        for (int i = 0; i < 8; i++) {
            Integer[] linea1 = new Integer[2];
            linea1[0] = i;
            linea1[1] = (i + 1) % 8;
            edges.add(linea1);
        }
    }

    public Asteroid(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e, int width, int height, Graphics2D g2d, int level) {
        super(x, y, v, e, w * level, h * level, g2d);
        fillVerticesAndEdges(x,y, level);
        this.setVertices(vertices);
        this.setEdges(edges);
        
        setType(3);
    }
    
    

    @Override
    public void collidedWith(GameObject him) {
        
    }
    
}