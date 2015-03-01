
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
        str += getX() + ",";
        str += getY() + ")";
        
        return str;
    }
    
    //operacion premultiplicar por matriz
    public static Matriz2 preTimes(Vector2 v2, Matriz2 m2){
        double[][] res = new double [1][3];
        Matriz2 aux = new Matriz2();
        aux.setValue(v2.getX(), 0, 0);
        aux.setValue(v2.getY(), 0, 1);
        aux.setValue(v2.getComps()[2], 0, 2);
        for(int i = 0; i < 3; i++){
            try {
                res[0][i] = multiplyArrays(aux.getRow(0), m2.getCol(i));
            } catch (Exception ex) {
                Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Matriz2(res);
    }
    
    //TODO: implementar main
}
