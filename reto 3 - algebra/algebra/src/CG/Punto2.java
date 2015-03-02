
package CG;

import static CG.Matriz2.multiplyArrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Punto2 {
    private double[] coords = new double[3]; //x,y,w
    
    public Punto2(double x, double y){
        coords[0] = x;
        coords[1] = y;
        
        coords[2] = 1;
    }
    
    public Punto2(double[] coords) {
        this.coords = coords;
    }

    public double[] getCoords() {
        return coords;
    }
    
    public double getX(){
        return coords[0];
    }
    
    public double getY(){
        return coords[1];
    }
    
    public void setX(double x){
        coords[0] = x;
    }
    
    public void setY(double y){
        coords[1] = y;
    }
    
    @Override
    public String toString(){
        String str = "(";
        str += getX() + ", ";
        str += getY() + ")";
        
        return str;
    }
    
    //operacion premultiplicar por matriz
    public static Punto2 preTimes(Punto2 p2, Matriz2 m2){
        double[] res = new double [3];
        Matriz2 aux = new Matriz2();
        aux.setValue(p2.getX(), 0, 0);
        aux.setValue(p2.getY(), 0, 1);
        aux.setValue(p2.getCoords()[2], 0, 2);
        for(int i = 0; i < 3; i++){
            try {
                res[i] = multiplyArrays(aux.getRow(0), m2.getCol(i));
            } catch (Exception ex) {
                Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Punto2(res);
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de punto2:");
        
        Punto2 p1 = new Punto2(3, 5);
        System.out.println("P1 = " + p1.toString());
        
        //test de preTimes
        double[][] md1 = {{1,2,1}, {0,3,2}, {1,1,1}};
        Matriz2 m1 = new Matriz2(md1);
        System.out.println("M1 = \n"+m1.toString());
        
        Punto2 pre = Punto2.preTimes(p1, m1);
        System.out.println("P1*M1 = "+pre.toString());
        
    }
    
}
