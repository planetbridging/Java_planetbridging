/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAccessing;

/**
 *
 * @author shadowlord101
 */
import Objects.FolderScanResults;
import Objects.Location;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author shannonsetter
 */


public class FolderScanner implements Callable<FolderScanResults>{

    private FolderScanResults FSR;
    
	
    public FolderScanner(String l , String s){
        this.FSR = new FolderScanResults(l,s);
    }
    

  public FolderScanResults GetResult() {
	return this.FSR;
  }

  
  
  private void StartScan()throws FileNotFoundException, IOException, NullPointerException{
      
  
        //try different directory and filename :)
	search(new File(FSR.GetLocation()));

	int count = FSR.GetFileLstSize();
	if(count ==0){
	    System.out.println("\nNo result found!");
	}else{
	    System.out.println("\nFound " + count + " result!\n");
	    /*for (String matched : this.getResult()){
		System.out.println("Found : " + matched);
	    }*/
	}
        FSR.SetRunningStatus(false);
        System.out.println("Search complete");
  }

  public void searchDirectory(File directory)throws FileNotFoundException, IOException, NullPointerException {

	
	    search(directory);


  }

  private void search(File file) throws FileNotFoundException, IOException, NullPointerException{

	if (file.isDirectory()) {
	 // System.out.println("Searching directory ... " + file.getAbsoluteFile());
		
            //do you have permission to read this directory?	
	    if (file.canRead()) {
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			 try{
                            search(temp);
                            } catch (IOException ex) {
            //System.out.println(ex);
                        }catch(NullPointerException ex){
                           // System.out.println(ex);
                        }
		    } else {
                        //getFileNameToSearch().contains(temp.getName().toLowerCase())
                        String WholePath = temp.getAbsolutePath();
                        int FileExtension = WholePath.lastIndexOf(".");
                        String FileName = temp.getName().toLowerCase();
                        Location filetemp = new Location(FileName,WholePath,"i",temp.length());
                        FSR.AppendFileObj(filetemp);
                        FSR.AppendFileSize(temp.length());
                        if(FileExtension>= 0){
                            //FileName = FileName + WholePath.substring(FileExtension).toLowerCase();
                        }
			//Prep.AddCopy(WholePath);
                        
                        

		}
	    }

	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }

  }

    
    public FolderScanResults call() {
        try {
            //System.out.println("Starting");
            StartScan();
        } catch (IOException ex) {
            //System.out.println(ex);
        } catch (NullPointerException ex) {
            //System.out.println(ex);
        }
        return this.FSR;
    }
    
}

