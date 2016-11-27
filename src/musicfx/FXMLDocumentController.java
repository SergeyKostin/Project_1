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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.RepositoryClass;
import model.Track;
import model.TrackList;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button save_nameTrackList;
    @FXML
    private TextField textNameTrackList;
    @FXML
    private TableView<Track> tableTracs=new TableView<Track>();
    @FXML
    private TableColumn<Track,String> nameOfTrackCol = new TableColumn<Track,String>("nameOfTrack");
    @FXML
    private TableColumn<Track,String> albumCol = new TableColumn<Track,String>("album");
    @FXML
    private TableColumn<Track,String> bandCol = new TableColumn<Track,String>("band");
    @FXML
    private TableColumn<Track,String> durationCol = new TableColumn<Track,String>("duration");
    @FXML  
    private TableColumn<Track,String> genreCol = new TableColumn<Track,String>("genre");
   
    private TrackList model=new TrackList();
    private String location;
    private ObservableList<Track> tracksData;
    

    @FXML
    private void ActionReadTxt(ActionEvent event) {
       Stage stage = new Stage();
       StackPane root = new StackPane();
       stage.setTitle("Введите путь к файлу");
       TextField textArea = new TextField();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       Scene scene = new Scene(root,350,70);
       root.setAlignment(Pos.CENTER_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=this.ReadTxtTrackList(location);
               this.tracksData=FXCollections.observableArrayList(model.getTracks());
               this.initialize();
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
       TextField textArea = new TextField();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,350,70);
       root.setAlignment(Pos.CENTER_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=RepositoryClass.ReadXmlTrackList(location);
               this.tracksData=FXCollections.observableArrayList(model.getTracks());
               this.initialize();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
    }
    @FXML
    private void ActionReadMp3(ActionEvent event) {
         Stage stage = new Stage();
       StackPane root = new StackPane();
       TextField textArea = new TextField();
       Button but=new Button("read tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,350,70);
       root.setAlignment(Pos.CENTER_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e -> {
            this.location=textArea.getText();
            stage.close();
           try {
               this.model=RepositoryClass.scanForTrack(location);
               this.tracksData=FXCollections.observableArrayList(model.getTracks());
               this.initialize();
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
       TextField textArea = new TextField();
       Button but=new Button("write tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,350,70);
       root.setAlignment(Pos.CENTER_RIGHT);
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
       TextField textArea = new TextField();
       Button but=new Button("write tracks");
       root.getChildren().addAll(textArea, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,350,70);
       root.setAlignment(Pos.CENTER_RIGHT);
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
    private void ActionWriteTrackXml(ActionEvent event) throws IOException {
      
        Stage stage = new Stage();
        GridPane grid = new GridPane();
          grid.setAlignment(Pos.CENTER);
          grid.setHgap(10);
          grid.setVgap(10);
          grid.setPadding(new Insets(25, 25, 25, 25));

       StackPane root = new StackPane();
       TextField TextNameTrack = new TextField();
       grid.add(TextNameTrack, 2, 2);
       TextField loc = new TextField();
       grid.add(loc, 2, 3);
       Button but=new Button("read track");
       Label nameTrac=new Label("name of Track:");
       grid.add(nameTrac, 1, 2);
       Label locfile=new Label("Lacation file:");
       grid.add(locfile, 1, 3);
       root.getChildren().addAll(grid, but);
       stage.setTitle("Введите путь к файлу");
       Scene scene = new Scene(root,350,150);
       root.setAlignment(Pos.BOTTOM_RIGHT);
       stage.setScene(scene);
       stage.show();
         but.setOnAction(e ->{
         Track track=new Track();
         this.location=loc.getText();
         track=this.model.getTrack(TextNameTrack.getText());
         stage.close();
            try {
                RepositoryClass.WriterXmlTrack(track, location);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
    }
    
    public void initialize() {
        this.textNameTrackList.setText(model.getNameTrackList());
        save_nameTrackList.setOnAction(e->{
            this.model.setNameTrackList(textNameTrackList.getText());
            });
        
        tracksData = FXCollections.observableArrayList(model.getTracks());
       
        nameOfTrackCol.setCellValueFactory(new PropertyValueFactory<Track, String>("name"));
        nameOfTrackCol.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        nameOfTrackCol.setOnEditCommit((CellEditEvent<Track, String> event) -> {
            TablePosition<Track, String> pos = event.getTablePosition();
            String newNameOfTrack = event.getNewValue();
            int row = pos.getRow();
            Track trac = event.getTableView().getItems().get(row);

            model.getTrack(row).setName(newNameOfTrack);
        });


        albumCol.setCellValueFactory(new PropertyValueFactory<Track, String>("album"));
        albumCol.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        albumCol.setOnEditCommit((CellEditEvent<Track, String> event) -> {
            TablePosition<Track, String> pos = event.getTablePosition();
            String album = event.getNewValue();
            int row = pos.getRow();
            Track trac = event.getTableView().getItems().get(row);
            model.getTrack(row).setAlbum(album);
        });
        bandCol.setCellValueFactory(new PropertyValueFactory<Track, String>("band"));
        bandCol.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        bandCol.setOnEditCommit((CellEditEvent<Track, String> event) -> {
            TablePosition<Track, String> pos = event.getTablePosition();
            String band = event.getNewValue();
            int row = pos.getRow();
            Track trac = event.getTableView().getItems().get(row);
            model.getTrack(row).setBand(band);
        });
        durationCol.setCellValueFactory(new PropertyValueFactory<Track, String>("duration"));
        durationCol.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        durationCol.setOnEditCommit((CellEditEvent<Track, String> event) -> {
            TablePosition<Track, String> pos = event.getTablePosition();
            String duration = event.getNewValue();
            int row = pos.getRow();
            Track trac = event.getTableView().getItems().get(row);
            model.getTrack(row).setDuration(duration);
        });
        genreCol.setCellValueFactory(new PropertyValueFactory<Track, String>("genre"));
        genreCol.setCellFactory(TextFieldTableCell.<Track> forTableColumn());
        genreCol.setOnEditCommit((CellEditEvent<Track, String> event) -> {
            TablePosition<Track, String> pos = event.getTablePosition();
            String genre = event.getNewValue();
            int row = pos.getRow();
            Track trac = event.getTableView().getItems().get(row);
            model.getTrack(row).setGenre(genre);
        });
        tableTracs.setItems(tracksData);
        
        
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
}
