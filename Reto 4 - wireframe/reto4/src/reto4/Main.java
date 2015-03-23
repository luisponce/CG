/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reto4;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author JeanPierre
 */
public class Main extends JFrame {
    private PnlTransform panelControl;
    private Projection panelProjections;
    
    private static Main instance = null;
    
    public static Main getInstance(){
        if(instance == null){
            instance = new Main();
        }
        return instance;
    }
    
   public Projection getPanelProjections(){
       return this.panelProjections;
   } 
    public Main() { 
        setTitle("Projections");
        setSize(920, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        panelControl = new PnlTransform(this);
        panelControl.setBounds(550, 5, 360, 410);
         
        panelProjections = new Projection();
        panelProjections.setBounds(5, 0, 540, 410);
         
        add(panelControl);
        add(panelProjections);
        
        panelProjections.init();
        panelControl.setVisible(true);
        panelProjections.setVisible(true);
    }
    
    public static void main (String args[]) { 
        Main ventana = getInstance();
        
        //ventana.panelProjections.init(ventana.getGraphics());
        ventana.setVisible(true);
    } 
}
