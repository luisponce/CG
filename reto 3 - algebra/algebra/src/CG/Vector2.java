

package CG;

/**
 *
 */
public class Vector2 {
    double[] comps = new double[3];

    /**
     * Constructor por dos puntos, inicial y final.
     * @param p1 punto inicial
     * @param p2 punto final
     */
    public Vector2 (Punto2 p1, Punto2 p2){
        comps[0] = p2.getX() - p1.getX();
        comps[1] = p2.getY() - p1.getY();

        comps[2] = 1;
    }

    /**
     * Constructor por componentes
     * @param x componente en x
     * @param y componente en y
     */
    public Vector2(double x, double y) {
        comps[0] = x;
        comps[1] = y;
        
        comps[2] = 1;
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
    
    public void setX(double x){
        comps[0] = x;
    }
    
    public void setY(double y){
        comps[1] = y;
    }
    
    public double magnitude(){
        //sqrt(x^2 + y^2)
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }
   
    public Vector2 normalized(){
        double x = getX()/magnitude();
        double y = getY()/magnitude();
        
        return new Vector2(x, y);
    }
    
    @Override
    public String toString(){
        String str = "<";
        str += getX() + ",";
        str += getY() + ">";
        
        return str;
    }
    
    //STATIC stuff-------------------------
    public static Vector2 add(Vector2 v1, Vector2 v2){
        Vector2 res = new Vector2(null, null);
        res.setX(v1.getX() + v2.getX());
        res.setY(v1.getY() + v2.getY());
        return res;
    }
    
    public static Vector2 sub(Vector2 v1, Vector2 v2){
        Vector2 res = new Vector2(null, null);
        res.setX(v1.getX() - v2.getX());
        res.setY(v1.getY() - v2.getY());
        return res;
    }
    
    public static double dot(Vector2 v1, Vector2 v2){
        double x = (v1.getX() * v2.getX());
        double y = (v1.getY() * v2.getY());
        return x+y;
    }
    
    //El resultado da en 3D, se implementa cuando este
    //Vector3
    
    //public static Vector2 cross(Vector2 v1, Vector2 v2){
    //    Vector2 res = new Vector2(null, null);
    //    
    //}
}
