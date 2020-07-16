/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author shannonsetter
 */

public class Location implements Serializable{
    private String Name;
    private String Location;
    private String Type;
    private Date Stamp;
    private long Size;
    private static final long serialVersionUID = 9238490832904L;
    public Location(String n, String t){
        this.Name = n; this.Type = t;
        this.Stamp = new Date();
    }
    
    public Location(String n, String l, String t,long s){
        this.Name = n;
        this.Location = l;
        this.Type = t;
        this.Size = s;
    }
    
    public Location(String n, String l, String t){
        this.Name = n;
        this.Location = l;
        this.Type = t;
    }
    
    public void SetName(String n){this.Name = n;}
    public void SetLocation(String l){this.Location = l;}
    public void SetType(String t){this.Type = t;}
    public void SetSize(long s){this.Size = s;}
    
    public String GetName(){return this.Name;}
    public String GetLocation(){return this.Location;}
    public String GetType(){return this.Type;}
    public String GetStamp(){return this.Stamp.toString();}
    public long GetSize(){return this.Size;}
}
