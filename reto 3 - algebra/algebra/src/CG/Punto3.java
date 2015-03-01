
package CG;

import static CG.Matriz3.multiplyArrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Punto3 {
    private double[] coords = new double[4]; //x,y,z,w
    
    public Punto3(double x, double y, double z){
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
        
        coords[3] = 1;
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
    
    public double getZ(){
        return coords[2];
    }
    
    public void setX(double x){
        coords[0] = x;
    }
    
    public void setY(double y){
        coords[1] = y;
    }
    
    public void setZ(double z){
        coords[2] = z;
    }
    
    //TODO: implementar operacion premultiplicar por matriz
    public static Matriz3 preTimes(Vector3 v3, Matriz3 m2){
        double[][] res = new double [1][3];
        Matriz3 aux = new Matriz3();
        aux.setValue(v3.getX(), 0, 0);
        aux.setValue(v3.getY(), 0, 1);
        aux.setValue(v3.getZ(), 0, 2);
        aux.setValue(v3.comps[3], 0, 3);
        for(int i = 0; i < 4; i++){
            try {
                res[0][i] = multiplyArrays(aux.getRow(0), m2.getCol(i));
            } catch (Exception ex) {
                Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Matriz3(res);
    }
    
    //TODO: implementar main
    
    @Override
    public String toString(){
        String str = "(";
        str += getX() + ", ";
        str += getY() + ", ";
        str += getZ() + ")";
        
        return str;
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de punto2:");
        
        Punto3 p1 = new Punto3(4, 4, 1);
        System.out.println("P1 = " + p1.toString());
        
        //TODO: test de preTimes
        
        
    }
}
