

package CG;

/**
 *
 */
public class Vector2 {
    private double[] comps = new double[3];

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
        str += getX() + ", ";
        str += getY() + ">";
        
        return str;
    }
    
    //STATIC stuff-------------------------
    public static Vector2 add(Vector2 v1, Vector2 v2){
        double x = v1.getX() + v2.getX();
        double y = v1.getY() + v2.getY();
        return new Vector2(x, y);
    }
    
    public static Vector2 sub(Vector2 v1, Vector2 v2){
        double x = v1.getX() - v2.getX();
        double y = v1.getY() - v2.getY();
        return new Vector2(x, y);
    }
    
    public static double dot(Vector2 v1, Vector2 v2){
        double x = (v1.getX() * v2.getX());
        double y = (v1.getY() * v2.getY());
        return x+y;
    }
    
    //trata el producto cruz como si fuera entre vector3
    public static Vector3 cross(Vector2 v1, Vector2 v2){
        Vector3 aux1 = new Vector3(v1.getX(), v1.getY(), 0);
        Vector3 aux2 = new Vector3(v2.getX(), v2.getY(), 0);
        
        Vector3 res = Vector3.cross(aux1, aux2);
        
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de Vector2:");
        
        Vector2 v1 = new Vector2(10, 15.5);
        System.out.println("V1 = " + v1.toString());
        
        Vector2 v2 = new Vector2(5.3, 5);
        System.out.println("V2 = " + v2.toString());
        
        Vector2 suma = Vector2.add(v1, v2);
        System.out.println("V1+V2 = "+suma.toString());
        
        Vector2 resta = Vector2.sub(v1, v2);
        System.out.println("V1-V2 = "+resta.toString());
        
        Vector3 cruz = Vector2.cross(v1, v2);
        System.out.println("V1xV2 = "+cruz.toString());
        
        double punto = Vector2.dot(v1, v2);
        System.out.println("V1*V2 = "+punto);
    }
}
