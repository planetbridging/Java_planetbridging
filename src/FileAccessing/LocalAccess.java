/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAccessing;

import Objects.Drive;
import Objects.Location;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author shannonsetter
 */
public class LocalAccess {
    
    public ArrayList<Drive> LocalDrives(){
        //LinuxDrives();
        ArrayList<Drive> f = new ArrayList();
        
        
        if(GetOs().startsWith("Linux")){
            f.addAll(LinuxDrives());
        }
        
        if(GetOs().startsWith("Mac")){
            f.addAll(MacDrives());
        }
        
        if(!GetOs().startsWith("Mac")){
            File[] roots = File.listRoots();
            for(File r : roots){
                String n = FileSystemView.getFileSystemView().getSystemDisplayName (r);
                f.add(new Drive(r.getPath(),n,true,false,
                r.canRead(),r.canWrite(),r.getTotalSpace(),r.getUsableSpace()));
            }
        }
        
        return f;
    }
    
     private ArrayList<Drive> LinuxDrives(){
        ArrayList<Drive> f = new ArrayList();
        try{
            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("mount");
            InputStream in = ps.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while( (line = reader.readLine()) != null){
               if(line.contains("/media/")){
                   int StartCatch = line.indexOf("on") + 3;
                   int EndCatch = line.indexOf(" type fuseblk");
                   String Catch = line.substring(StartCatch, EndCatch);
                   File d = new File(Catch);
                   String n = FileSystemView.getFileSystemView().getSystemDisplayName (d);
                   Drive TmpD = new Drive(Catch,n,true,false,
            d.canRead(),d.canWrite(),d.getTotalSpace(),d.getUsableSpace());
                   f.add(TmpD);
               }
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return f;
    }

     private ArrayList<Drive> MacDrives(){
        ArrayList<Drive> f = new ArrayList();
        try{
            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("mount");
            InputStream in = ps.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while( (line = reader.readLine()) != null){
                
                if(line.contains("/dev/")){
                    int Start = line.indexOf("on ");
                    int Finish = line.lastIndexOf(" (");
                    String Location = line.substring(Start+3, Finish);
                    File d = new File(Location);
                    String n = FileSystemView.getFileSystemView().getSystemDisplayName (d);
                    Drive TmpD = new Drive(Location,n,true,false,
            d.canRead(),d.canWrite(),d.getTotalSpace(),d.getUsableSpace());
                   f.add(TmpD);
                }
               /*if(line.contains("/media/")){
                   int StartCatch = line.indexOf("on") + 3;
                   int EndCatch = line.indexOf(" type fuseblk");
                   String Catch = line.substring(StartCatch, EndCatch);
                   File d = new File(Catch);
                   String n = FileSystemView.getFileSystemView().getSystemDisplayName (d);
                   Drive TmpD = new Drive(Catch,n,true,false,
            d.canRead(),d.canWrite(),d.getTotalSpace(),d.getUsableSpace());
                   f.add(TmpD);
               }*/
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return f;
    }
     
     public ArrayList<Location> GetLocation(String loc){
        ArrayList<Location> Tmp = new ArrayList<Location>();

            if(CheckExists(loc)){
                try {
            
                File folder = new File(loc);
                File[] listOfFiles = folder.listFiles();
                for (File file : listOfFiles) {
                    if(!file.getName().startsWith(".") && !file.isHidden()){
                        if (file.isFile()) {
                            Tmp.add(new Location(file.getName(),file.getAbsolutePath(),"i",file.length()));
                        }else{
                            Tmp.add(new Location(file.getName(),"o"));
                        }
                    }
                }

                
                   
                } catch (NullPointerException ex) {
                    System.out.println("Error: 101 "+ex);
                }
            }
        
        
        if(Tmp.isEmpty()){
            Tmp.add(new Location(loc,"b"));
        }
        return Tmp;
    }
     
    public boolean CheckExists(String Loc){
        File NewLocation = new File(Loc);
        if(NewLocation.exists()){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList<String> Setlsts(String location){
        boolean LocExists = CheckExists(location);
        ArrayList<String> Tmp;
        if(LocExists){
            Tmp = ReadFile(location);
        }else{
            Tmp = new ArrayList<String>();
            WriteFile(location, "");        
        }
        return Tmp;
    }  
    
    public void WriteFile(String loc, String contents){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream(loc), "utf-8"));
            writer.write(contents);
        } catch (IOException ex) {
          // report
        } finally {
           try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
    
    public ArrayList<String> ReadFile(String Filename){
        ArrayList<String> Results = null;
        BufferedReader in = null ;
        String line;
            try {
                Results = new ArrayList<String>();
                in = new BufferedReader(new FileReader(Filename));
                while((line = in.readLine()) != null){
                    Results.add(line);
                }
                in.close();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return Results;
    }
    
    public void AppendFile(String loc, String Contents){   
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(loc, true));
            bw.write(Contents);
            bw.newLine();
            bw.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {                       
                if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
            }
        }
    }
    
    public boolean CreateFolder(String Current, String NewFolder){
        boolean success = false;
        try{
            
            Current = Current + File.separator + NewFolder;
            // Create one directory
            


            // Create multiple directories
            success = (new File(Current)).mkdirs();
            if (success) {
              System.out.println("Directories: " + Current + " created");
            }

            }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
            } 
        return success;
    }
    
    
    public String GetOs(){return System.getProperty("os.name");}
    public String GetComputerName(){return System.getenv("COMPUTERNAME");}
}
