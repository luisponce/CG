
package CG;

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
    
    //TODO: implementar main
    
    @Override
    public String toString(){
        String str = "(";
        str += getX() + ",";
        str += getY() + ",";
        str += getZ() + ")";
        
        return str;
    }
}
