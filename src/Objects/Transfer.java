/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.ArrayList;

/**
 *
 * @author R3DN3T
 */
public class Transfer {
    private String FromComputer;
    private String ToComputer;
    private ArrayList<Location> CopyFromLst;
    private String PasteFolderLocation;
    private String CopyFolderLocation;
    public Transfer(String fpc,String topc,ArrayList<Location> clip,String from,String paste){
        this.FromComputer = fpc;
        this.ToComputer = topc;
        this.CopyFromLst = clip;
        this.PasteFolderLocation = paste;
        this.CopyFolderLocation = from;
    }
    
    public void PopFromLst(){this.CopyFromLst.remove(0);}
    
    public String GetFromComputer(){return this.FromComputer;}
    public String GetToComputer(){return this.ToComputer;}
    public ArrayList<Location> GetCopyLst(){return this.CopyFromLst;}
    public String GetPasteLocation(){return this.PasteFolderLocation;}
    public String GetCopyLocation(){return this.CopyFolderLocation;}
    public int GetLstSize(){return this.CopyFromLst.size();}
}
