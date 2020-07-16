/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author shannonsetter
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Computer implements Serializable{
    private static final long serialVersionUID = 5950169519310163575L;
    private String UserSerial;
    private String Nickname;
    private String Location;
    private Date Stamp;
    private ArrayList<Drive> Drives;
    private ArrayList<Location> Directory;
    private ArrayList<Location> Search;
    private ArrayList<String> Block;
    private ArrayList<String> Friends;
    
    public Computer(String us,String nn){
        this.UserSerial = us;
        this.Nickname = nn;
        this.Location = "";
        this.Drives = new ArrayList<Drive>();
        this.Search = new ArrayList<Location>();
        this.Directory = new ArrayList<Location>();
        this.Stamp = new Date();
    }

    
    
    public void AddSearch(Location l){this.Search.add(l);}
    public void ClearSearch(){this.Search.clear();}
    public void AddDrive(Drive d){this.Drives.add(d);}
    public void AddDrive(ArrayList<Drive> dr){this.Drives.clear();this.Drives.addAll(dr);}
    public void ClearDrives(){this.Drives.clear();}
    public void SetLocationSearch(){this.Location = "Search";this.UpdateDirectory(this.Search);}
    public void SetDrive(ArrayList<Drive> dos){this.Drives = dos;}
    public void SetLocationLst(ArrayList<Location> fobs){this.Directory = fobs;}
    
    /*private void FilterLocations(ArrayList<String> Filter, String t){
        ArrayList<Location> TmpNew = new ArrayList<Location>();
        for(String s: Filter){
            int NameStart = 0;
             if(s.contains("\\")){
                NameStart = s.lastIndexOf("\\") +1;
            }else if(s.contains("/")){
                NameStart = s.lastIndexOf("/") + 1;
            }
            String Name = s.substring(NameStart, s.length());
            TmpNew.add(new Location(Name, s, t));
        }
        this.UpdateDirectory(TmpNew);
    }*/
    
    public String GetUserSerial(){return this.UserSerial;}
    public String GetNickname(){return this.Nickname;}
    public String GetLocation(){return this.Location;}
    public String GetStamp(){return this.Stamp.toString();}
    
    public void SetLocation(String loc){this.Location = loc;}
    
    public ArrayList<Drive> GetDrives(){return this.Drives;}
    public ArrayList<Location> GetDirectory(){return this.Directory;}
    public ArrayList<Location> GetSearch(){return this.Search;}
    
    public void UpdateDirectory(ArrayList<Location> i){this.Directory.clear();this.Directory.addAll(i);}
    public void ClearDirectory(){this.Directory.clear();}
    
    
    public void RemoveDrive(int i){if(i<= Drives.size()){Drives.remove(i);}}
}
