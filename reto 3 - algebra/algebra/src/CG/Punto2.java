
package CG;

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
    
    //TODO: implementar operacion premultiplicar por matriz
    
    //TODO: implementar main
}
