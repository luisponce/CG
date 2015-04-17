/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto7;

import CG.Punto2;
import java.util.ArrayList;

/**
 *
 * @author jonathaneidelman
 */
public class Bezier {
    public ArrayList<Punto2> p = new ArrayList<>();
    ArrayList<Punto2> paint = new ArrayList<>();
    public Bezier(){
        Punto2 a = new Punto2(20,30);
        Punto2 b = new Punto2(100,105);
        Punto2 c = new Punto2(130,-150);
        Punto2 d = new Punto2(230,160);
        
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        
        //en este caso n seria 3 (pues hay 4 puntos de control)
        //que es simplemente quien itera desde 0 hasta n+1
    }
    
    public int coefficient(int n, int k){
        return (fact(n)/(fact(k)*(fact(n - k))));
    }
    
    public double bez(int n, int k, double u){
        return (coefficient(n,k)*Math.pow(u, k)*Math.pow((1-u), (n-k)));
    }
    
    public void points(int n, double u){
        double sumX = 0;
        double sumY = 0;
        for(int k = 0; k <= n; ++k){
            sumX += p.get(k).getX()*bez(n,k,u);
            sumY += p.get(k).getY()*bez(n,k,u);
        }
        paint.add(new Punto2(sumX,sumY));
    }
    
    public void curve(){
        
        for(double u = 0; u <= 1; u += 0.0001){
           points(3,u);
        }
    }
    
    public int fact(int n){
        if (n == 0) return 1;
        return n * fact(n-1);
    }
}
