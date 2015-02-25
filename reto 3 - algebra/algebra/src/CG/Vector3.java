/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CG;

/**
 *
 * @author jonathaneidelman
 */
public class Vector3 {
    double[] comps = new double[4];

    /**
     * Constructor por dos puntos, inicial y final.
     * @param p1 punto inicial
     * @param p2 punto final
     */
    public Vector3 (Punto3 p1, Punto3 p2){
        comps[0] = p2.getX() - p1.getX();
        comps[1] = p2.getY() - p1.getY();
        comps[2] = p2.getZ() - p1.getZ();
        
        comps[3] = 1;
    }

    /**
     * Constructor por componentes
     * @param x componente en x
     * @param y componente en y
     * @param z componente en z
     */
    public Vector3(double x, double y, double z) {
        comps[0] = x;
        comps[1] = y;
        comps[2] = z;
        
        comps[3] = 1;
    }

    public double[] getComps() {
        return comps;
    }
    
    public double getX(){
        return comps[0];
    }
    
    public double getY(){
        return comps[1];
    }
    
    public double getZ(){
        return comps[2];
    }
    
    public void setX(double x){
        comps[0] = x;
    }
    
    public void setY(double y){
        comps[1] = y;
    }
    
    public void setZ(double z){
        comps[2] = z;
    }
    
    public double magnitude(){
        //sqrt(x^2 + y^2 + z^2)
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) +
                Math.sqrt(Math.pow(getZ(), 2)));
    }
   
    public Vector3 normalized(){
        double x = getX()/magnitude();
        double y = getY()/magnitude();
        double z = getZ()/magnitude();
        
        return new Vector3(x, y, z);
    }
    
    @Override
    public String toString(){
        String str = "<";
        str += getX() + ",";
        str += getY() + ",";
        str += getZ() + ">";
        
        return str;
    }
    
    //STATIC stuff-------------------------
    public static Vector3 add(Vector3 v1, Vector3 v2){
        Vector3 res = new Vector3(0.0, 0.0, 0.0);
        res.setX(v1.getX() + v2.getX());
        res.setY(v1.getY() + v2.getY());
        res.setZ(v1.getZ() + v2.getZ());
        return res;
    }
    
    public static Vector3 sub(Vector3 v1, Vector3 v2){
       Vector3 res = new Vector3(0.0, 0.0, 0.0);
        res.setX(v1.getX() - v2.getX());
        res.setY(v1.getY() - v2.getY());
        res.setZ(v1.getZ() - v2.getZ());
        return res; 
    }
    
    public static double dot(Vector3 v1, Vector3 v2){
        double x = (v1.getX() * v2.getX());
        double y = (v1.getY() * v2.getY());
        double z = (v1.getZ() * v2.getZ());
        return x+y+z;
    }
    
    public static Vector3 cross(Vector3 v1, Vector3 v2){
        Vector3 res = new Vector3(0.0, 0.0, 0.0);
        
        res.setX(v1.getY()*v2.getZ() - v1.getZ()*v2.getY());
        res.setY(-(v1.getX()*v2.getZ() - v1.getZ()*v2.getX()));
        res.setZ(v1.getX()*v2.getY() - v1.getY()*v2.getX());
        
        return res;
    }
}
