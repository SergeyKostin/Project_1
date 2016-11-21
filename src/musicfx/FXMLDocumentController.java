/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicfx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.RepositoryClass;
import model.Track;
import model.TrackList;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
   
    private TrackList model=new TrackList();
    private String location;
    
    @FXML
    private void ActionReadTxt(ActionEvent event) {
       Stage stage = new Stage();
       StackPane root = new StackPane();
       stage.setTitle("Введите путь к файлу");
       TextArea textArea = new TextArea();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       Scene scene = new Scene(root,250,70);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=this.ReadTxtTrackList(location);
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
       
    }
    
    public TrackList ReadTxtTrackList(String str) throws FileNotFoundException, IOException{
        TrackList trackList=new TrackList();
        BufferedReader read = new BufferedReader(new FileReader(str+".txt"));
        String tracks;
        int index=0;
        while((tracks=read.readLine())!=null){
            String [] tr=tracks.split(";");
            Track track=new Track(tr[0],tr[1],tr[2],tr[3],tr[4]);
            trackList.addTrack(index, track);
            index++;
        }
        read.close();
        return trackList;
    }
    
    @FXML
    private void ActionReadXml(ActionEvent event) {
         Stage stage = new Stage();
       StackPane root = new StackPane();
       TextArea textArea = new TextArea();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,250,70);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=RepositoryClass.ReadXmlTrackList(location);
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
    }
    @FXML
    private void ActionReadMp3(ActionEvent event) {
         Stage stage = new Stage();
       StackPane root = new StackPane();
       TextArea textArea = new TextArea();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,250,70);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=RepositoryClass.scanForTrack(location);
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
    }
    @FXML
    private void ActionWriteConsole(ActionEvent event) {
        for (int i = 0; i < model.getGenres().size(); i++) {
            System.out.println(model.getGenres().get(i) + ":");
            for (int j = 0; j < model.getTracks().size(); j++)
                if (model.getTrack(j).getGenre().equals(model.getGenres().get(i)))
                    System.out.println("Исполнитель: " + model.getTrack(j).getBand() + "  Название трека: " + model.getTrack(j).getName() + "  Длина трека: " + model.getTrack(j).getDuration()+" Альбом: "+model.getTrack(j).getAlbum());
               }
       
    }
                 
    @FXML
    private void ActionWriteTxt(ActionEvent event) {
         Stage stage = new Stage();
       StackPane root = new StackPane();
       TextArea textArea = new TextArea();
       Button but=new Button("write tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,250,70);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.WriterTxtTrackList(model, location);
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
    }
    
     public void WriterTxtTrackList(TrackList tracList, String str) throws FileNotFoundException, IOException{
       PrintWriter writ=new PrintWriter(new BufferedWriter(new FileWriter(str+".txt")));
       for(int i=0;i<tracList.getTracks().size();i++){
           writ.write(tracList.getTrack(i).getName()+";"+tracList.getTrack(i).getAlbum()+";"+tracList.getTrack(i).getBand()+";"+tracList.getTrack(i).getDuration()+";"+tracList.getTrack(i).getGenre()+";");
           writ.append("\n");
       }
       writ.close();
     }
    @FXML
    private void ActionWriteXml(ActionEvent event) {
         Stage stage = new Stage();
       StackPane root = new StackPane();
       TextArea textArea = new TextArea();
       Button but=new Button("write tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,250,70);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               RepositoryClass.WriterXmlTrackList(model, location);
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
    }
    @FXML
    private void ActionWievTracks(ActionEvent event) {
       Stage stage = new Stage();
       stage.setTitle("Tracks:");
        TableView<Track> tableTracs=new TableView<Track>();
        ObservableList<Track> tracksData = FXCollections.observableArrayList(model.getTracks());
       
        
        TableColumn<Track,String> nameOfTrackCol = new TableColumn<Track,String>("nameOfTrack");
        nameOfTrackCol.setCellValueFactory(new PropertyValueFactory<Track, String>("nameOfTrack"));
        
        TableColumn<Track,String> albumCol = new TableColumn<Track,String>("album");
        albumCol.setCellValueFactory(new PropertyValueFactory<Track, String>("album"));
        
        TableColumn<Track,String> bandCol = new TableColumn<Track,String>("band");
        bandCol.setCellValueFactory(new PropertyValueFactory<Track, String>("band"));
        
        TableColumn<Track,String> durationCol = new TableColumn<Track,String>("duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<Track, String>("duration"));
        
        TableColumn<Track,String> genreCol = new TableColumn<Track,String>("genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<Track, String>("genre"));
        
        tableTracs.getColumns().setAll(nameOfTrackCol,albumCol,bandCol,durationCol,genreCol);
        
        tableTracs.setItems(tracksData);
        Scene scene = new Scene(tableTracs,320,400);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
