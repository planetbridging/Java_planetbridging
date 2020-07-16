/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbridging;

import FileAccessing.HelperClass;
import FileAccessing.LocalAccess;
import Objects.Computer;
import Objects.TempSerialGenerator;
import planetbridging.GUI.HexagonInterface;
import planetbridging.GUI.MainFrame;

/**
 *
 * @author the-s
 */
public class Planetbridging {
    public static void main(String[] args) {
        Planetbridging pb = new Planetbridging();
    }
   
    //----------------------------------------------------------splashframe
    private HexagonInterface GUI;
    
    //----------------------------------------------------------splashframe
    
    //----------------------------------------------------------Mainframe
    private MainFrame MainGUI;
    public void ShowMainFrame(){MainGUI.show();}
    
     //----------------------------------------------------------Mainframe
    
    //----------------------------------------------------------LocalComputer
    
    private Computer LocalComputer;
    private LocalAccess LA;
    
    public void LoadLocalComputer(){
        LA = new LocalAccess();
        LocalComputer = new Computer(TSGObj.RandomKey(32),LA.GetComputerName());
        LocalComputer.AddDrive(LA.LocalDrives());
        MainGUI.SetLocalComputer(LocalComputer);
    }
    
    //----------------------------------------------------------LocalComputer
    
    //----------------------------------------------------------Helpers
    private TempSerialGenerator TSGObj;
    private HelperClass Help;
    
    private void LoadHelpers(){
        TSGObj = new TempSerialGenerator();
        Help = new HelperClass();
    }
    
    public String ReturnHtmlSetup(Computer pc){
        String name = pc.GetNickname();
        int drivecount = pc.GetDrives().size();
        long totalbytes = 0;
        for(int i = 0;i<=pc.GetDrives().size()-1;i++){
            totalbytes+= pc.GetDrives().get(i).GetTotalSpace();
        }
        String totalsize  = Help.humanReadableByteCount(totalbytes,false);
        String htmlbuild = "<html>"+name+"<br>Drives: "+drivecount+"<br>"+totalsize+"</html>";
        return htmlbuild;
    }
    
    //----------------------------------------------------------Helpers
    
    public Planetbridging(){
        GUI = new HexagonInterface(this);
        MainGUI = new MainFrame(this);
        LoadHelpers();
        LoadLocalComputer();
    }
    
}
