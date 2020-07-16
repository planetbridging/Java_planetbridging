/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

/**
 *
 * @author shadowlord101
 */
import Objects.FolderScanResults;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Objects.Location;
import java.util.concurrent.Callable;
import planetbridging.Planetbridging;

public class SearchFile implements Callable<FolderScanResults>{

    private Planetbridging Engine;
    private String fileNameToSearch;
    private String Location;
    private FolderScanResults result;
    private boolean Search;
    
    
    public SearchFile(Planetbridging e, String l , String s) throws FileNotFoundException, IOException, NullPointerException{
        this.fileNameToSearch = s;
        this.Location = l;
        this.Engine = e;
        this.Search = true;
        this.result = new FolderScanResults(l,s);
    }
	
    public SearchFile(Planetbridging e, String l ) throws FileNotFoundException, IOException, NullPointerException{
        this.fileNameToSearch = "";
        this.Location = l;
        this.Engine = e;
        this.Search = false;
        this.result = new FolderScanResults(l,"");
    }
    
  public String getFileNameToSearch() {
	return fileNameToSearch;
  }

  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }  
  
  private void StartScan()throws FileNotFoundException, IOException, NullPointerException{
      
  
        //try different directory and filename :)
	searchDirectory(new File(Location), fileNameToSearch);

	int count = this.result.GetFileLstSize();
	if(count ==0){
	    System.out.println("\nNo result found!");
	}else{
	    System.out.println("\nFound " + count + " result!\n");
	    /*for (String matched : this.getResult()){
		System.out.println("Found : " + matched);
	    }*/
	}
        System.out.println("Search complete");
  }

  public void searchDirectory(File directory, String fileNameToSearch)throws FileNotFoundException, IOException, NullPointerException {

	setFileNameToSearch(fileNameToSearch);

	if (directory.isDirectory()) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}

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
            System.out.println(ex);
                        }catch(NullPointerException ex){
                            System.out.println(ex);
                        }
		    } else {
                        //getFileNameToSearch().contains(temp.getName().toLowerCase())
                        String WholePath = temp.getPath();
                        int FileExtension = WholePath.lastIndexOf(".");
                        String FileName = temp.getName().toLowerCase();
                        if(FileExtension>= 0){
                            //FileName = FileName + WholePath.substring(FileExtension).toLowerCase();
                        }
			if (FileName.contains(getFileNameToSearch().toLowerCase()) && Search) {			
			    
                            Location FoundFile = new Location(FileName,WholePath,"i");
                            result.AppendFileObj(FoundFile);
                            //Engine.AddSearchGui(FoundFile);
                        }
                        
                        

		}
	    }

	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }

  }

    public void run() {
        try {
            System.out.println("Starting");
            StartScan();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public FolderScanResults call() throws Exception {
        try {
            //System.out.println("Starting");
            StartScan();
        } catch (IOException ex) {
            //System.out.println(ex);
        } catch (NullPointerException ex) {
            //System.out.println(ex);
        }
        return this.result;
    }

}

