package View;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import trackList.Track;
import trackList.TrackList;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import  org.xml.sax.*;
import  org.xml.sax.helpers.*; 






public class ViewClass implements View {

  /*  public void printTrackList(TrackList trackList) {
        for (int i = 0; i < trackList.getGenres().size(); i++) {
            System.out.println(trackList.getGenres().get(i) + ":");
            for (int j = 0; j < trackList.getTracks().size(); j++)
                if (trackList.getTrack(j).getGenre().equals(trackList.getGenres().get(i)))
                    System.out.println("Исполнитель: " + trackList.getTrack(j).getBand() + "  Название трека: " + trackList.getTrack(j).getName() + "  Длина трека: " + trackList.getTrack(j).getDuration());
        }
    }*/

    public void printTracksByNameOfType(String type, TrackList trackList, String typeName) {
        switch (type) {
            case "Genre":
                System.out.println("Жанр: " + typeName);
                for (int i = 0; i < trackList.getTracks().size(); i++)
                    if (trackList.getTrack(i).getGenre().equals(typeName))
                        System.out.println("Альбом: " + trackList.getTrack(i).getAlbum() + " Исполнитель: " + trackList.getTrack(i).getBand() + "  Название трека: " + trackList.getTrack(i).getName() + "  Длина трека: " + trackList.getTrack(i).getDuration());
                break;
            case "Album":
                System.out.println("Альбом: " + typeName);
                for (int i = 0; i < trackList.getTracks().size(); i++)
                    if (trackList.getTrack(i).getGenre().equals(typeName))
                        System.out.println("Жанр: " + trackList.getTrack(i).getGenre() + " Исполнитель: " + trackList.getTrack(i).getBand() + "  Название трека: " + trackList.getTrack(i).getName() + "  Длина трека: " + trackList.getTrack(i).getDuration());
                break;
            case "Band":
                System.out.println("Исполнитель: " + typeName);
                for (int i = 0; i < trackList.getTracks().size(); i++)
                    if (trackList.getTrack(i).getGenre().equals(typeName))
                        System.out.println("Жанр: " + trackList.getTrack(i).getGenre() + " Альбом: " + trackList.getTrack(i).getAlbum() + "  Название трека: " + trackList.getTrack(i).getName() + "  Длина трека: " + trackList.getTrack(i).getDuration());
                break;
        }
        
    }
   
    
    public TrackList scanTrackList(){
        Scanner in=new Scanner(System.in);
        TrackList tracks=new TrackList();
        int index=0;
        String nameGenre="",nameTrack="",album="",band="",duration="";
        String str="yes";
        while(str.equals("yes")){
        System.out.print("Введите жанр трэка:");
        nameGenre=in.nextLine();
        System.out.print("Введите название трэка:");
        nameTrack=in.nextLine();
        System.out.print("Введите название альбома:");
        album=in.nextLine();
        System.out.print("Введите исполнителя:");
        band=in.nextLine();
        System.out.print("Введите продолжительность трэка:");
        duration=in.nextLine();
        Track track=new Track(nameTrack,album,band,duration,nameGenre);
        tracks.addTrack(index, track);
        index++;
        System.out.print("Желаете продалжить ввод? 'yes' or 'no'");
        str=in.nextLine();
        System.out.println();
    }    
        return tracks;
    }
     public String Comands(){
        
        System.out.println("Введите  командy из списка:");
        System.out.println("read console, print console, read txt, write txt, write XML");
        Scanner in=new Scanner(System.in);
        String comand=in.nextLine();
        return comand;
    }
     public void WriterXmlTrackList(TrackList tracList, String str) throws FileNotFoundException{
         FileOutputStream out = new FileOutputStream(str+".xml"); 
          XMLEncoder xml = new XMLEncoder(out);
          xml.writeObject(tracList);
          for(int i=0;i<tracList.getTracks().size();i++)
           xml.writeObject(" Жанр: "+tracList.getTrack(i).getGenre()+" Альбом "+tracList.getTrack(i).getAlbum()+" Исполнитель: "+tracList.getTrack(i).getBand()+" Название: "+tracList.getTrack(i).getName()+" Продолжительность: "+tracList.getTrack(i).getDuration());
       
          xml.close();
        
    }
     public void WriterTxtTrackList(TrackList tracList, String str) throws FileNotFoundException, IOException{
       PrintWriter writ=new PrintWriter(new BufferedWriter(new FileWriter(str+".txt")));
       for(int i=0;i<tracList.getTracks().size();i++)
           writ.print(" Жанр: "+tracList.getTrack(i).getGenre()+" Альбом "+tracList.getTrack(i).getAlbum()+" Исполнитель: "+tracList.getTrack(i).getBand()+" Название: "+tracList.getTrack(i).getName()+" Продолжительность: "+tracList.getTrack(i).getDuration());
       writ.close();
     }

    @Override
    public void printTrackList(TrackList tracList) {
       for(int i=0;i<tracList.getTracks().size();i++)
           System.out.println(" Жанр: "+tracList.getTrack(i).getGenre()+" Альбом "+tracList.getTrack(i).getAlbum()+" Исполнитель: "+tracList.getTrack(i).getBand()+" Название: "+tracList.getTrack(i).getName()+" Продолжительность: "+tracList.getTrack(i).getDuration());
    }
    public TrackList ReadTxtTrackList( String str) throws FileNotFoundException, IOException{
        TrackList trackList=new TrackList();
        BufferedReader read = new BufferedReader(new FileReader(str+".txt"));
        String tracks;
        int index=0;
        while((tracks=read.readLine())!=null){
            String [] tr=tracks.split(" ");
            Track track=new Track(tr[0],tr[1],tr[2],tr[3],tr[4]);
            trackList.addTrack(index, track);
            index++;
        }
        read.close();
        return trackList;
    }
    public String NameTxt(){
        System.out.println("Введите путь и имя файла через слеш (без расширения xml) :");
        Scanner in=new Scanner(System.in);
        String str=in.nextLine();
        return str;
    }
}

