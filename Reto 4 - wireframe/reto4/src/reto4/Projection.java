/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto4;

import CG.Matriz2;
import CG.Matriz3;
import CG.Punto2;
import CG.Punto3;
import CG.Transform.Perspective;
import CG.Transform.Scale;
import CG.Transform.Translate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 *
 */
public class Projection extends JPanel{
    ArrayList<Punto3> vertices3D = new ArrayList<>();
    ArrayList<Punto2> vertices = new ArrayList<>();
    ArrayList<Integer[]> edges = new ArrayList<>();
    private int x;
    private int y;
    public Graphics2D graphics2;
    //private Main principal;
    
    public Projection(){
      setSize(500, 500);
      setBackground(Color.decode("#FFFFFF"));
      setLayout(null);
      setPreferredSize(new Dimension(500, 500));

       TitledBorder border = BorderFactory.createTitledBorder("Dibujador de proyecciones");
       setBorder(border);
       //this.principal = principal;
    }
    
    public void Repaint(){
        repaint();
    }
    
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
    
    public void from2Dto3D(int pe){
        vertices.clear();
        for (int i = 0; i < vertices3D.size(); i++) {
            
            
             Punto3 p = (Punto3.preTimes(vertices3D.get(i),
                    Matriz3.transpose((new Perspective(pe)))));
             
             p.divideByW();
             
             vertices.add(new Punto2(p.getX(), p.getY()));
        }
    }
    
    private void addEdge(int I, int II){
        Integer[] linea1 = new Integer[2];
        linea1[0] = I;
        linea1[1] = II;
        edges.add(linea1);
    }
    
    public void init(){
            
       Dimension size = getSize();
       // Insets son los bordes y los títulos de la ventana.
       Insets insets = getInsets();

        int screenW =  size.width - insets.left - insets.right;
        int screenH =  size.height - insets.top - insets.bottom;

        x = screenW/2;
        y = screenH/2;
        
        vertices3D.clear();
        vertices3D.add(new Punto3(40, 40, 20));//0
        vertices3D.add(new Punto3(40, 40, 25));//1
        
        vertices3D.add(new Punto3(60, 40, 20));//2
        vertices3D.add(new Punto3(60, 40, 25));//3
        
        vertices3D.add(new Punto3(40, 60, 20));//4
        vertices3D.add(new Punto3(40, 60, 25));//5
        
        vertices3D.add(new Punto3(60, 60, 20));//6
        vertices3D.add(new Punto3(60, 60, 25));//7
        
        from2Dto3D(25);
        
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        
        addEdge(1, 3);
        addEdge(1, 5);
        
        addEdge(2, 3);
        addEdge(2, 6);
        
        addEdge(3, 7);
        
        addEdge(4, 5);
        addEdge(4, 6);
        
        addEdge(5, 7);
        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Graphics2D g2d = (Graphics2D) g;
        graphics2 = (Graphics2D) g;
        
        graphics2.setColor(Color.blue); 
        
        graphics2.drawLine(-50+x, y, x+50, y);
        graphics2.drawLine(x, y+50, x, y-50);
        
        graphics2.setColor(Color.black);
        
        paintAllEdges(graphics2);
       
        
        /*for(int i = 0; i<8; i++) {
            vertices3D.set(i, Punto3.preTimes(vertices3D.get(i),
                    Matriz3.transpose((new Translate(20.0, 20.0, 15.0)))));
        }
        
        from2Dto3D();
        
        graphics2.setColor(Color.orange);
        paintAllEdges(graphics2);
        
        for(int i = 0; i<8; i++) {
            vertices3D.set(i, Punto3.preTimes(vertices3D.get(i),
                    Matriz3.transpose((new Scale(15.0, 15.0, 15.0)))));
        }
        
        from2Dto3D();
        
        graphics2.setColor(Color.green);
        paintAllEdges(graphics2);
        */
         
        
    } 
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Transforming");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      Projection c = new Projection();
      
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
      
      c.init(c.getGraphics());
    }*/
}
