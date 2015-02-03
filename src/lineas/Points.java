

package lineas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Random; 


public class Points extends JPanel {
    
    private int cW;//centro de la pantalla en x
    private int cH;//centro de la pantalla en y
    

    public void plotLine(int xi, int yi, int xf, int yf, Graphics g){
        int dy, dx;
        dy = yf - yi;
        dx = xf - xi;
        
        if(dx>0 && dy>0){//cuadrante I
            if(dx>=dy){//Octante I
                int incE = 2 * dy;
                int incNE = 2 * dy - 2 * dx;
                int d = 2 * dy - dx;
                int y = yi;  
                for (int x = xi; x <= xf; x++) {
                   g.drawLine(cW+x, cH-y, cW+x, cH-y);
                   if (d <= 0) {
                      d += incE;
                   } else {
                      d += incNE;
                      y += 1;
                   }
                }
            } else {//Octante II 
                int incE = 2 * dx;
                int incNE = 2 * dx - 2 * dy;
                int d = 2 * dx - dy;
                int x = xi;  
                for (int y = yi; y <= yf; y++) {
                   g.drawLine(cW+x, cH-y, cW+x, cH-y);
                   if (d <= 0) {
                      d += incE;
                   } else {
                      d += incNE;
                      x += 1;
                   }
                }
            }
        } else if (dx<0 && dy>0){//cuadrante II
            if(Math.abs(dx)>Math.abs(dy)){//octante IV
                int incE = 2 * dy;
                int incNE = 2 * dy - 2 * dx;
                int d = 2 * dy - dx;
                int y = yi;  
                for (int x = xi; x > xf; x--) {
                   g.drawLine(cW+x, cH-y, cW+x, cH-y);
                   if (d <= 0) {
                      d += incE;
                   } else {
                      d += incNE;
                      y += 1;
                   }
                }
            } else {//octante III
                
            }
        } else if (dx<0 && dy<0){//cuadrante III
            int tmp = xf;
            xf = xi;
            xi = tmp;
            
            tmp = yf;
            yf = yi;
            yi = tmp;
                    
            dy = yf - yi;
            dx = xf - xi;
            
            if(Math.abs(dx)>Math.abs(dy)){//octante V
                int incE = 2 * dy;
                int incNE = 2 * dy - 2 * dx;
                int d = 2 * dy - dx;
                int y = yi;  
                for (int x = xi; x <= xf; x++) {
                   g.drawLine(cW+x, cH-y, cW+x, cH-y);
                   if (d <= 0) {
                      d += incE;
                   } else {
                      d += incNE;
                      y += 1;
                   }
                }
            } else {//octante VI
                int incE = 2 * dx;
                int incNE = 2 * dx - 2 * dy;
                int d = 2 * dx - dy;
                int x = xi;  
                for (int y = yi; y <= yf; y++) {
                   g.drawLine(cW+x, cH-y, cW+x, cH-y);
                   if (d <= 0) {
                      d += incE;
                   } else {
                      d += incNE;
                      x += 1;
                   }
                }
            }
        } else {//cuadrante IV
            if(Math.abs(dx)>Math.abs(dy)){//octante VIII
            
            } else {//octante VII
                
            }
        }
        
        
    }
    
  /*
   * En esta función se dibuja.
   * La función es llamada por Java2D.
   * Se recibe una variable Graphics, que contiene la información del contexto
   * gráfico.
   * Es necesario hacerle un cast a Graphics2D para trabajar en Java2D.
   */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);

        // size es el tamaño de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int w =  size.width - insets.left - insets.right;
        int h =  size.height - insets.top - insets.bottom;

      
        //encontrar el centro
        cW = w/2;
        cH = h/2;
        g2d.drawLine(cW, cH, cW, cH);
      
        //eje x
        for (int i = (cW - cW/4); i < cW + cW/4; i++) {
            g2d.drawLine(i, cH, i, cH);
        }
      
        g2d.setColor(Color.GREEN);
        //eje y
        for (int i = (cH - cH/4); i < cH + cH/4; i++) {
            g2d.drawLine(cW, i, cW, i);
        }
        
        //Rayos--------------------------------------------------------------
        g2d.setColor(Color.blue);
        //<0,0> -> <60,50>
        plotLine(0,0,60,50, g);
        
        //<0,0> -> <50,50>
        plotLine(0,0,50,50, g);
        
        //<0,0> -> <10,60>
        plotLine(0,0,10,60, g);
        
        //<0,0> -> <-60,-10>
        plotLine(0,0,-60,-10, g);
        
        //<0,0> -> <-10,-60>
        plotLine(0,0,-10,-60, g);
        
        //<0,0> -> <-60,10>
        plotLine(0,0,-60,10, g);
        
//      // Generador de números Random
//      // Se va a utilizar nextInt, que devuelve un entero.
//      Random r = new Random();
//
//      // Generar 1000 puntos
//      for (int i=0; i<1000; i++) {
//          int x = Math.abs(r.nextInt()) % w;
//          int y = Math.abs(r.nextInt()) % h;
//          // Así se pinta un punto
//          g2d.drawLine(x, y, x, y);
//      }
  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Points");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Points());
      // Asignarle tamaño
      frame.setSize(500, 500);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
  
  
}