/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fko2.Graficos;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Alvar
 */
public class Marcos {
    File cat;
    
    public static void main(String [] args){
    
    MiMarco MM = new MiMarco();
   // MM.JfileChose();
    }
    
}

class MiMarco extends JFrame{
        public MiMarco(){
              
               setVisible(true);
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
               
               Toolkit Screen = Toolkit.getDefaultToolkit();
               Dimension SZ = Screen.getScreenSize();
               int WT = SZ.width/2;
               int HT = SZ.height/2; 
               setSize(WT,HT);
               setLocation((WT/2),(HT/2));
               
               setTitle("Seleccion de Archivo");
               Image MI =Screen.getImage("C:\\Users\\Alvar\\Desktop\\thanksgiving_43-128.gif");
               setIconImage(MI);
               
              JButton BTSelect = new JButton();
              BTSelect.setBorderPainted(false);
              BTSelect.setContentAreaFilled(false);
              
               
               
              
               
            
            
            
        }
        
        public String JfileChose(){
        
        String gh="";
        
        JFileChooser  MF = new JFileChooser();
            MF.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         
            String Label="";
            int Resp = MF.showOpenDialog(MiMarco.this);
            if(Resp == JFileChooser.APPROVE_OPTION){
                Label =MF.getSelectedFile().toString();
                
            }else{
                Label = "The operation was cancelled";
            }
            JOptionPane.showMessageDialog(null,Label);
        
        return gh;
        }
}
