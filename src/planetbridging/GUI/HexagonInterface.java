/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbridging.GUI;

import CoreClasses.PlayPop;
import Objects.HexAnimation;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import planetbridging.Planetbridging;

/**
 *
 * @author the-s
 */

import Objects.HexAnimation;
import CoreClasses.*;
import java.awt.*;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.SWT;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.xml.bind.Marshaller.Listener;
import javafx.animation.FadeTransition;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class HexagonInterface {
    

    
    JFrame frame;
    TrayIcon trayIcon;
    SystemTray tray;
    PopupMenu popup;
    Planetbridging Engine;
    
    public HexagonInterface(Planetbridging e) {
        Engine = e;
        frame = new JFrame();
        frame.getContentPane().add(new ImagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/planet_100.png")));
        SetupTray();
    }

   /* public static void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HexStartup();
            }
        });
    }*/
    
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    public static void TopleftWindow(Window frame){
        frame.setLocation(0, 0);
    }
    
    public static void ToprightWindow(Window frame){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dimension.getWidth() - frame.getWidth();
        frame.setLocation(x, 0);
    }
    
    public static void BottomleftWindow(Window frame){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int) dimension.getHeight() - frame.getHeight();
        frame.setLocation(0, y);
    }
    
    public static void BottomrightWindow(Window frame){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dimension.getWidth() - frame.getWidth();
        int y = (int) dimension.getHeight() - frame.getHeight();
        frame.setLocation(x, y);
    }
    
    private void SetupTray(){
        if(SystemTray.isSupported()){
            System.out.println("system tray supported");
            tray=SystemTray.getSystemTray();

            Image image=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/planet_100.png"));
            
            popup=new PopupMenu();
            AddTrayMenuItems();
            
            trayIcon=new TrayIcon(image, "Planet Bridging", popup);
            trayIcon.setImageAutoSize(true);
        }else{
            System.out.println("system tray not supported");
        }
    }
    
    private void AddTrayMenuItems(){
        MenuItem defaultItem=new MenuItem("Exit");
        ActionListener exitListener=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting....");
                    System.exit(0);
                }
        };
        defaultItem.addActionListener(exitListener);
        popup.add(defaultItem);
        
        
        MenuItem OpenCenter=new MenuItem("Open Center");
            OpenCenter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowFrame();
                    centreWindow(frame);
                }
            });
        popup.add(OpenCenter);
        
        MenuItem OpenTopLeft=new MenuItem("Open Top Left");
            OpenTopLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowFrame();
                    TopleftWindow(frame);
                }
            });
        popup.add(OpenTopLeft);
        
        MenuItem OpenTopRight=new MenuItem("Open Top Right");
            OpenTopRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowFrame();
                    ToprightWindow(frame);
                }
            });
        popup.add(OpenTopRight);
        
        MenuItem OpenBottomLeft=new MenuItem("Open Bottom Left");
            OpenBottomLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowFrame();
                    BottomleftWindow(frame);
                }
            });
        popup.add(OpenBottomLeft);
        
        MenuItem OpenBottomRight=new MenuItem("Open Bottom Right");
            OpenBottomRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowFrame();
                    BottomrightWindow(frame);
                }
            });
        popup.add(OpenBottomRight);
    }
    
    private void ShowFrame(){
        frame.setVisible(true);
                    frame.setExtendedState(JFrame.NORMAL);
                    tray.remove(trayIcon);    
    }

    @SuppressWarnings("serial")
    public class ImagePanel extends JPanel{

        BufferedImage pb;
        BufferedImage pbtitles;
        
        boolean MouseInOut = false;
        
        BufferedImage BtnScan;
        BufferedImage BtnItemFinder;
        BufferedImage BtnItemExplorer;
        BufferedImage BtnServer;
        BufferedImage BtnSettings;
        BufferedImage BtnTransferList;
        
        JLabel LblScan = new JLabel();
        JLabel LblExplorer= new JLabel();
        JLabel LblFinder = new JLabel();
        JLabel LblSettings = new JLabel();
        JLabel LblTransferList = new JLabel();
        final JLabel LblMainHub = new JLabel();
        JLabel LblServers = new JLabel();
        JLabel LblTitles = new JLabel();
        JLabel LblBidgeware = new JLabel("@ Copyright Bridgeware 2019");
        
        int BtnSize = 100;
        int HalfScreenWidth;
        int HalfScreenHeight;
        
         boolean inDrag = false;
            int startX = -1, startY = -1;
        
        ArrayList<HexAnimation> HexFades;

        public ImagePanel() {
            HexFades = new ArrayList<HexAnimation>();
            setOpaque(false);
            setLayout(null);
            setSize(300, 250);
            try {
                pb = ImageIO.read(getClass().getResource("/imgs/planet_100.png"));
                pbtitles = ImageIO.read(getClass().getResource("/imgs/hexside100.png"));
                BtnScan = ImageIO.read(getClass().getResource("/imgs/minus.png"));
                BtnItemExplorer = ImageIO.read(getClass().getResource("/imgs/hexside100itemexplorer.png"));
                BtnItemFinder = ImageIO.read(getClass().getResource("/imgs/hexside100itemfinder.png"));
                BtnServer = ImageIO.read(getClass().getResource("/imgs/hexside100servers.png"));
                BtnSettings = ImageIO.read(getClass().getResource("/imgs/hexside100settings.png"));
                BtnTransferList = ImageIO.read(getClass().getResource("/imgs/hexside100transferlist.png"));
                
                //pb = new ImageIcon(getClass().getResource("/imgs/ic_monitor-small.png"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            //addMouseListener(this);
            HalfScreenWidth = this.getWidth()/2-BtnSize/2;
            HalfScreenHeight = getHeight()/2-BtnSize/2;
            HexFades.add(new HexAnimation(HalfScreenWidth, HalfScreenHeight));
            HexFades.add(new HexAnimation(HalfScreenWidth -BtnSize, HalfScreenHeight));
            HexFades.add(new HexAnimation(HalfScreenWidth - BtnSize/2, HalfScreenHeight + BtnSize -(BtnSize/4)));
            HexFades.add(new HexAnimation(HalfScreenWidth + BtnSize/2, HalfScreenHeight + BtnSize -(BtnSize/4)));
            HexFades.add(new HexAnimation(HalfScreenWidth + BtnSize, HalfScreenHeight));
            HexFades.add(new HexAnimation(HalfScreenWidth + BtnSize/2, HalfScreenHeight - BtnSize +(BtnSize/4)));
            HexFades.add(new HexAnimation(HalfScreenWidth - BtnSize/2, HalfScreenHeight - BtnSize +(BtnSize/4)));
            AddHexagons();
            AddListeners();
            TimerManager();
        }
        @Override
        public void paintComponent(Graphics g){
            
            
            if(inDrag){
                PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            System.out.println(x + "," + y);
                frame.setLocation(x - 150, y - 100);
            }
            
            
            
             
            Graphics2D g2d = (Graphics2D) g;
            
            if(!HexFades.isEmpty()){
                for(int i = 0;i<=HexFades.size()-1;i++){
                    float tmp = HexFades.get(i).GetAlpha();
                    float[] scales = { 1f, 1f, 1f, tmp };
                    float[] offsets = new float[4];
                    RescaleOp rop = new RescaleOp(scales, offsets, null);

                    g2d.drawImage(pbtitles, rop, HexFades.get(i).GetX(), HexFades.get(i).GetY());
                }
            }
            
            //
        }
        
        private void TimerManager(){
        
        java.util.Timer t = new java.util.Timer();
        t.schedule(new TimerTask() {
            float f1 = 0.01f;
            float f2 = 0.01f;
            float f3 = 0.01f;
            float f4 = 0.01f;
            float f5 = 0.01f;
            float f6 = 0.01f;
            float f7 = 0.01f;
            int count = 1;
            @Override
            public void run() {

                if(count >=1 && count <= 15){
                    f1 += 0.05;
                    SetFadeInHex(f1,0);
                }
                
                if(count >=10 && count <= 15){
                    f2 += 0.05;
                    SetFadeInHex(f2,1);
                    LblMainHub.setVisible(true);
                    if(count == 10){
                        Pop();
                    }
                }
                
                if(count >=15 && count <= 20){
                    f3 += 0.05;
                    SetFadeInHex(f3,2);
                    LblScan.setVisible(true);
                    if(count == 15){
                        Pop();
                    }
                }
                
                if(count >=20 && count <= 25){
                    f4 += 0.05;
                    SetFadeInHex(f4,3);
                    LblServers.setVisible(true);
                    if(count == 20){
                        Pop();
                    }
                }
                
                if(count >=25 && count <= 30){
                    f5 += 0.05;
                    SetFadeInHex(f5,4);
                    LblSettings.setVisible(true);
                    if(count == 25){
                        Pop();
                    }
                }
                
                if(count >=30 && count <= 35){
                    f6 += 0.05;
                    SetFadeInHex(f6,5);
                    LblExplorer.setVisible(true);
                    if(count == 30){
                        Pop();
                    }
                }
                
                if(count >=35 && count <= 40){
                    f7 += 0.05;
                    SetFadeInHex(f7,6);
                    LblFinder.setVisible(true);
                    if(count == 35){
                        Pop();
                    }
                }
                
                if(count >= 40 && count <= 45){
                    LblTransferList.setVisible(true);
                    if(count == 40){
                        Pop();
                    }
                }
                
                if(count >= 50){
                    t.cancel();
                    HexFades.clear();
                    LblBidgeware.setVisible(true);
                    PopSwipe();
                }
                
                
                repaint();
                count ++;
            }
        }, 0, 100);
    }
        
        private void SetFadeInHex(float f,int hexnum){
            float tmp = HexFades.get(hexnum).GetAlpha();
            tmp += f;
            HexFades.get(hexnum).SetAlpha(tmp);
        }
        

        private void AddHexagons(){
            
            LblTitles.setBounds(HalfScreenWidth+20, HalfScreenHeight+BtnSize/4, 100, 50);
            LblTitles.setForeground(Color.white);
            add(LblTitles);
            
            
            LblBidgeware.setBounds(75, 235, 250,50);
            LblBidgeware.setForeground(Color.white);
            LblBidgeware.setVisible(false);
            add(LblBidgeware);
            
            LblMainHub.setIcon((new ImageIcon(pb)));
            LblMainHub.setBounds(HalfScreenWidth, HalfScreenHeight, BtnSize, BtnSize);
            LblMainHub.setVisible(false);
            add(LblMainHub);
            
            LblScan.setIcon((new ImageIcon(BtnScan)));
            LblScan.setBounds(HalfScreenWidth -BtnSize, HalfScreenHeight, BtnSize, BtnSize);
            LblScan.setVisible(false);
            add(LblScan);
            
            LblExplorer.setIcon((new ImageIcon(BtnItemExplorer)));
            LblExplorer.setBounds(HalfScreenWidth + BtnSize, HalfScreenHeight, BtnSize, BtnSize);
            LblExplorer.setVisible(false);
            add(LblExplorer);
            
            LblFinder.setIcon((new ImageIcon(BtnItemFinder)));
            LblFinder.setBounds(HalfScreenWidth + BtnSize/2, HalfScreenHeight - BtnSize +(BtnSize/4), BtnSize, BtnSize);
            LblFinder.setVisible(false);
            add(LblFinder);
            
            LblSettings.setIcon((new ImageIcon(BtnSettings)));
            LblSettings.setBounds(HalfScreenWidth + BtnSize/2, HalfScreenHeight + BtnSize -(BtnSize/4), BtnSize, BtnSize);
            LblSettings.setVisible(false);
            add(LblSettings);
            
            LblTransferList.setIcon((new ImageIcon(BtnTransferList)));
            LblTransferList.setBounds(HalfScreenWidth - BtnSize/2, HalfScreenHeight - BtnSize +(BtnSize/4), BtnSize, BtnSize);
            LblTransferList.setVisible(false);
            add(LblTransferList);
            
            LblServers.setIcon((new ImageIcon(BtnServer)));
            LblServers.setBounds(HalfScreenWidth - BtnSize/2, HalfScreenHeight + BtnSize -(BtnSize/4) , BtnSize, BtnSize);
            LblServers.setVisible(false);
            add(LblServers);
        }
        
        private void AddListeners(){
            LblScan.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("Minimize");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    //Engine.ShowScanner();
                    HideToTry();
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    
                }
            });
            
            LblExplorer.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("File Explorer");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    
                }
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    Engine.ShowMainFrame();
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    
                }
            });
            
            LblFinder.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("Find File");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    //Engine.ShowSearch();
                }
                @Override
                public void mouseReleased(MouseEvent evt){
                    HideTitle();
                }
            });
            
            LblSettings.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("Settings");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt){
                    //Engine.ShowSettings();
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    
                }
            });
            
            LblTransferList.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("Transfer List");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    //Engine.ShowTransferFrame();
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    
                }
            });
            
            LblServers.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){
                    ShowTitle("Servers");
                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    
                }
            });
            
            LblMainHub.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent evt){

                }
                @Override
                public void mouseExited(MouseEvent evt){
                    HideTitle();
                }
                @Override
                public void mousePressed(MouseEvent evt){
                    inDrag = true;
                    
                    
                }
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    inDrag = false;
                }
                @Override
                public void mouseDragged(MouseEvent evt)
                {
                    int x = evt.getXOnScreen();
                    int y = evt.getYOnScreen();
                   // frame.setLocation(x, y);
                    System.out.println(x + "," + y);
                }
            });
            
            
        }
        
        
        
  
  
        private void ShowTitle(String title){
            LblMainHub.setIcon(new ImageIcon(pbtitles));
            LblTitles.setText(title);
        }
        
        private void HideTitle(){
            LblMainHub.setIcon(new ImageIcon(pb));
            LblTitles.setText("");
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }
        
    }
    
    private void HideToSystemTray(){
        System.out.println("creating instance");
        try{
            System.out.println("setting look and feel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println("Unable to set LookAndFeel");
        }
        
        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState()==ICONIFIED){
                    try {
                        tray.add(trayIcon);
                        frame.setVisible(false);
                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
                        System.out.println("unable to add to tray");
                    }
                }
        if(e.getNewState()==7){
                    try{
            tray.add(trayIcon);
            frame.setVisible(false);
            System.out.println("added to SystemTray");
            }catch(AWTException ex){
            System.out.println("unable to add to system tray");
        }
            }
        if(e.getNewState()==MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                    System.out.println("Tray icon removed");
                }
                if(e.getNewState()==NORMAL){
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                    System.out.println("Tray icon removed");
                }
            }
        });
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/planet_100.png")));

        frame.setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void HideToTry(){
         try {
                        tray.add(trayIcon);
                        frame.setVisible(false);
                    } catch (AWTException ex) {
                        System.out.println("unable to add to tray");
                    }
    }
    
    private void Pop(){
        PlayPop pp = new PlayPop();
        pp.Play("bubble.mp3");
    }
    
    private void PopSwipe(){
        PlayPop pp = new PlayPop();
        pp.Play("swip.mp3");
    }
}


