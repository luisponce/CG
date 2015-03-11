

package CG;

import CG.Transform.Rotate;
import CG.Transform.Scale;
import CG.Transform.Translate;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 */
public class GameObject {
    
    Graphics2D g2d;
    
    int x, y;
    
    Vector2 up, right;
    
    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    
    
    public GameObject(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e,
            Graphics2D g2d){
        this.g2d = g2d;
        this.x = x;
        this.y = y;
        this.edges = e;
        this.vertices = v;
    }
    
    private void paintEdge(int pos){
        Integer[] edge = edges.get(pos);
        
        Punto2 v1 = vertices.get(edge[0]);
        Punto2 v2 = vertices.get(edge[1]);
        
        g2d.drawLine(Math.round((float) (v1.getX())),
                Math.round((float) (v1.getY())),
                Math.round((float) (v2.getX())),
                (int) Math.round(v2.getY()));
    }
    
    public void paintFigure(){
        for(int i = 0; i<edges.size(); i++){
            paintEdge(i);
        }
    }
    
    public void Move(int dx, int dy){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(dx, dy)))));
        }
        
        x += dx;
        y += dy;
    }
    
    public void Rotate(double angle){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Rotate(angle)))));
        }
        
        //TODO: rotar vectores up y right
    }
    
    public void Scale(double sx, double sy){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Scale(sx, sy)))));
        }
        
         
   }
    
}
