
package Controller;

import View.ViewClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import trackList.Track;
import trackList.TrackList;

public class ControllerClass implements Controller {

    private ViewClass view;
    private TrackList model;
    
    public ControllerClass(){
        this.model=new TrackList();
        this.view=new ViewClass();
    }
    @Override
    public void createTrack() {
        
    }

    @Override
    public void createPlayList(ArrayList<Track> tracks) {
        this.model=new TrackList((ArrayList<Track>) tracks);
    }

    @Override
    public Track getTrackByName(String name) {
        return this.model.getTrackByNameOfTrack(name);
    }

    @Override
    public ArrayList<Track> getTracksByPlayList() {
       return this.model.getTracks();
    }

    @Override
    public List<Track> scanForTracks() {
        return null;
    }
    
    
    
    
     public void Comand() throws IOException{
        Scanner in=new Scanner(System.in);
        String str="yes";
        while(str.equals("yes")){
       String comand=this.view.Comands();
       switch (comand){
           case "read console":
           {   
               this.model=this.view.scanTrackList();
               break;
           }
           case "print console":
           {  
              this.view.printTrackList(model);
               break;
           }
           case "read txt" :
           {
               String nameTxt=this.view.NameTxt();
               this.model=this.view.ReadTxtTrackList(nameTxt);
           }
           case "write txt" :
           {
               String nameTxt=this.view.NameTxt();
               this.view.WriterTxtTrackList(model, nameTxt);
           }
           case "write XML":
           {
               String nameXML=this.view.NameTxt();
               this.view.WriterXmlTrackList(model, nameXML);
           }
           
       }
       System.out.println("Желаете ввести еще команду? 'yes' or 'no'");
       str=in.nextLine();
    }
   
}
}
