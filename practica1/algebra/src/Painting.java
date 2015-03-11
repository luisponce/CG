/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import CG.*;
import CG.Transform.Rotate;
import CG.Transform.Scale;
import CG.Transform.Translate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */
public class Painting extends JPanel{
    
    ArrayList<Punto2> vertices = new ArrayList<>();
    ArrayList<Integer[]> edges = new ArrayList<>();
    
    private int x;
    private int y;
    
    public void paintEdge(int pos, Graphics2D g2d){
        Integer[] edge = edges.get(pos);
        
        Punto2 v1 = vertices.get(edge[0]);
        Punto2 v2 = vertices.get(edge[1]);
        
        g2d.drawLine(Math.round((float) (v1.getX()+x)),
                Math.round((float) (y-v1.getY())),
                Math.round((float) (x+v2.getX())),
                (int) Math.round(y-v2.getY()));
    }
    
    public void paintAllEdges(Graphics2D g2d){
        for(int i = 0; i<edges.size(); i++){
            paintEdge(i, g2d);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
               
        
        // size es el tamaño de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int screenW =  size.width - insets.left - insets.right;
        int screenH =  size.height - insets.top - insets.bottom;

        x = screenW/2;
        y = screenH/2;
        
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.blue); 
        
        g2d.drawLine(-50+x, y, x+50, y);
        g2d.drawLine(x, y+50, x, y-50);
        
        g2d.setColor(Color.black);
        
        vertices.add(new Punto2(40, 40));
        vertices.add(new Punto2(60, 40));
        vertices.add(new Punto2(60, 60));
        vertices.add(new Punto2(80, 60));
        vertices.add(new Punto2(80, 40));
        vertices.add(new Punto2(100, 40));
        vertices.add(new Punto2(100, 100));
        vertices.add(new Punto2(80, 100));
        vertices.add(new Punto2(80, 120));
        vertices.add(new Punto2(60, 120));
        vertices.add(new Punto2(60, 100));
        vertices.add(new Punto2(40, 100));
        
        
        for(int i = 0; i<12; i++){
            Integer[] linea1 = new Integer[2];
            linea1[0] = i;
            linea1[1] = (i+1)%12;
            edges.add(linea1);
        }
        
        paintAllEdges(g2d);
        
        
        for(int i = 0; i<12; i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(0, 100)))));
        }
        
        g2d.setColor(Color.red);
        paintAllEdges(g2d);
        
        for(int i = 0; i<12; i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Scale(0.5, 0.5)))));
        }
        
        g2d.setColor(Color.orange);
        paintAllEdges(g2d);
        
        for(int i = 0; i<12; i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(-150, 0)))));
        }
        
        g2d.setColor(Color.red);
        paintAllEdges(g2d);
        
        for(int i = 0; i<12; i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Rotate(90)))));
        }
        
        g2d.setColor(Color.green);
        paintAllEdges(g2d);
        
        for(int i = 0; i<12; i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Scale(0.5, 1.1)))));
        }
        
        g2d.setColor(Color.orange);
        paintAllEdges(g2d);
    } 
    
    
    public static void main(String[] args) {
 
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Transforming");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      Painting c = new Painting();
      
      frame.add(c);
      // Asignarle tamaño
      frame.setSize(500, 500);
      
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
      //c.fillWithRandom();
      //no se puede cambiar el tamaño
      frame.setResizable(false);
     
      
      
    }
        
        
        
}
