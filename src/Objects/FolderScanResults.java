/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author shadowlord101
 */
import java.util.ArrayList;

/**
 *
 * @author shannon
 */
public class FolderScanResults {
    
    private String Location;
    private String Searching;
    private ArrayList<Location> FileLst;
    private Boolean Running;
    private long TotalSize;
    
    public FolderScanResults(String l,String s){
        this.Searching = s;
        this.Location = l;
        this.TotalSize = 0;
        this.FileLst = new ArrayList<Location>();
        this.Running = true;
    }
    
    public int GetFileLstSize(){return this.FileLst.size();}
    public String GetLocation(){return this.Location;}
    public ArrayList<Location> GetFileLst(){return this.FileLst;}
    public boolean GetRunningStatus(){return this.Running;}
    public long GetTotalSize(){return this.TotalSize;}
    public String GetSearched(){return this.Searching;}
    
    public void SetRunningStatus(boolean r){this.Running = r;}
    
    public void AppendFileObj(Location tem){this.FileLst.add(tem);}
    public void AppendFileSize(double ts){this.TotalSize += ts;}
    
}

