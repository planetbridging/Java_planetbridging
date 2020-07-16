/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;

/**
 *
 * @author shannonsetter
 */
public class Drive implements Serializable{
    private String Location;
    private String DisplayName;
    private boolean IsDrive;
    private boolean IsCD;
    private boolean CanRead;
    private boolean CanWrite;
    private long TotalSpace;
    private long UsuableSpace;
    private static final long serialVersionUID = 5234234323575L;
    
    public Drive(String l, String dn, 
            boolean isd, 
            boolean iscd,
            boolean cr, 
            boolean cw,
            long ts, long us){
        this.Location = l; this.DisplayName = dn;
        this.IsDrive = isd;
        this.IsCD = iscd;
        this.CanRead = cr;
        this.CanWrite = cw;
        this.TotalSpace = ts;
        this.UsuableSpace = us;
    }
    
    public Drive(String l, String dn, 
            long ts, long us){
        this.Location = l; this.DisplayName = dn;
        this.IsDrive = true;
        this.IsCD = false;
        this.CanRead = true;
        this.CanWrite = true;
        this.TotalSpace = ts;
        this.UsuableSpace = us;
    }
    
    public String GetLocation(){return this.Location;}
    public String GetDisplayName(){return this.DisplayName;}
    public boolean GetDrive(){return this.IsDrive;}
    public boolean GetCd(){return this.IsCD;}
    public boolean GetRead(){return this.CanRead;}
    public boolean GetWrite(){return this.CanWrite;}
    public long GetTotalSpace(){return this.TotalSpace;}
    public long GetUsuableSpace(){return this.UsuableSpace;}
    
    public void SetUsuableSpace(long us){this.UsuableSpace = us;}
    public void SetDisplayName(String dn){this.DisplayName = dn;}
    public void SetLocation(String l){this.Location = l;}
    public void SetWrite(boolean w){this.CanWrite = w;}
    public void SetRead(boolean r){this.CanRead = r;}
    
}
