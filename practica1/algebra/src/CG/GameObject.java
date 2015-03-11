

package CG;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 */
public class GameObject {
    
    Graphics2D g2d;
    
    int x, y;
    
    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    
    public GameObject(int x, int y, Graphics2D g2d){
        this.g2d = g2d;
        this.x = x;
        this.y = y;
        
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
}
