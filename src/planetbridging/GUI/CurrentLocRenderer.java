/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbridging.GUI;

import FileAccessing.HelperClass;
import Objects.Drive;
import Objects.Location;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author the-s
 */

import Objects.Drive;
import Objects.Location;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class CurrentLocRenderer extends JLabel implements ListCellRenderer  {
    
    Icon DriveIcon = new ImageIcon(getClass().getResource("/imgs/ic_drive-web-small.png"));
    private final Icon FolderIcon = new ImageIcon(getClass().getResource("/imgs/folder-5.png"));
    private final Icon FileIcon = new ImageIcon(getClass().getResource("/imgs/file.png"));
    private final Icon BackIcon = new ImageIcon(getClass().getResource("/imgs/back.png"));
    private boolean ShowLstView = false;
    private boolean ShowLocation = false;
    private final HelperClass Help = new HelperClass();
    
    public CurrentLocRenderer(boolean lv,boolean l){
        this.ShowLocation = l;
        this.ShowLstView = lv;
    }
    
    @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            /*JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);*/
            
            
            setHorizontalTextPosition(JLabel.RIGHT);
            setOpaque(isSelected);
            if (value instanceof Drive) {
                setIcon(DriveIcon);
                Drive d = (Drive)value;
                setText("<html><center>"+d.GetLocation()+"<br>"+Help.humanReadableByteCount(d.GetTotalSpace(),false)+"</center></html>");
            }else if(value instanceof Location){
                Location l = (Location)value;
   
                if(l.GetType().startsWith("i")){
                    setIcon(FileIcon);
                    String size = Help.humanReadableByteCount(l.GetSize(),false);
                    String name = l.GetName();
                    if(l.GetName().length() > 8 && !ShowLocation){
                        name = l.GetName().substring(0, 8) + "...";
                    }else{
                        name = l.GetLocation() + "\\" + l.GetName();
                    }
                    setText("<html><center>"+name +"<br>" +size+"</center></html>");
                }else if(l.GetType().startsWith("o")){
                    setIcon(FolderIcon);
                    setText("<html><center>"+l.GetName() + "</center></html>");
                }else if(l.GetType().startsWith("b")){
                    setIcon(BackIcon);
                    setText("<html><center>Location Doesn't exist</center></html>");
                }
                
                
            }
            
            if(index==0){
                setBackground(Color.LIGHT_GRAY);
            }
            
            if (isSelected) 
         {
              setBackground(Color.lightGray);
               setForeground(Color.BLACK);
          }
         else 
         {

               setBackground(Color.lightGray);
               setForeground(Color.WHITE);
          }
            if(ShowLstView){
                setHorizontalTextPosition(JLabel.RIGHT);
                setVerticalTextPosition(JLabel.CENTER);
                setHorizontalAlignment(JLabel.LEFT);
            }else{
                setHorizontalTextPosition(JLabel.CENTER);
                setVerticalTextPosition(JLabel.BOTTOM);
                setHorizontalAlignment(JLabel.CENTER);
            }
            return this;
        }
    
}
