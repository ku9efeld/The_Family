/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_family.controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import the_family.ConnectionLine;
import the_family.Person;
import the_family.Storage;
import the_family.Leaf;
/**
 * FXML Controller class
 *
 * @author kumme
 */
public class GraphicTreeController implements Initializable {
    @FXML
    Button btnRelation;
    @FXML
    Button btnScene2;
    @FXML
    private  Group Tree;
    @FXML
    Button snapshotBtn;

  /// Список всех соединений 
    public static ArrayList <ConnectionLine> relations;
    private static double mousePosX, mousePosY;
    private static boolean clickedOnElement;
    
    /// Инцилизация всех листочков из таблицы
    @FXML
    public  void initialize(URL url, ResourceBundle resourceBundle){
        btnScene2.toFront();
        btnRelation.toFront();
        int k = 0;
        for(int i=0; i < Storage.getPeopleArray().size(); i++){ 
            if(notSearchedLeaf(i)){
                // Добавление новых листков, если их не было до этого в сцене
                Leaf l = new Leaf(Storage.getPeopleArray().get(i),100,120+k);
                //

                //
                Storage.addLeaf(l);
                /// Обновляем персоне листок и листок в массиве 
                k = k + (int)(2*l.getText().getBoundsInLocal().getHeight());
                Storage.getPeopleArray().get(i).setLeaf(l);
                Storage.getLeaves().set(i,l);
            }
            addLeaf(Storage.getPeopleArray().get(i).getLeaf());
            }  
        for (int i=0; i < Storage.getConnectionLineArray().size();i++){
           Tree.getChildren().add(Storage.getConnectionLineArray().get(i).getLine());
           Storage.getConnectionLineArray().get(i).getLine().toBack();
        }
    }

    // Nodes
    public  void addLeaf(Leaf leaf) {
           Tree.getChildren().add(leaf.getRoot()); 
    }
    // Поиск собственного листка
    public boolean notSearchedLeaf(int index){
        try{
        Leaf selectedLeaf = Storage.getPeopleArray().get(index).getLeaf();
        for(Leaf l:Storage.getLeaves()){
            if(selectedLeaf.eqvLeaf(l)){ // is equal method
                return false;
            }
        }
        return true;
        }
       catch(NullPointerException e) {
           return true;
       }
    }
    

    public static double getMousePosX() {
        return mousePosX;
    }

    public static double getMousePosY() {
        return mousePosY;
    }

    public static boolean getClickedOnElement() {
        return clickedOnElement;
    }
  
    public static ArrayList<ConnectionLine> getRelations() {
        return relations;
    }

    public static void addRelations(ConnectionLine relation) {
         relations.add(relation); 
    }

    public static void setClickedOnElement(boolean state) {
        clickedOnElement = state;
    }  
    
    // 2->1
        @FXML
        public void handleBtn2() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Database.fxml"));
        Stage window = (Stage)btnScene2.getScene().getWindow();
        window.setScene(new Scene(root,890,656));
    }
    // Создание времменого окошка для установления связи
        @FXML
        public void handleBtnRelation() throws Exception {
        ConnectionAddStage connectionAddStage = new ConnectionAddStage(Tree);
    }
        @FXML 
        public void createSnapshot() throws Exception{
                WarningStage warningStage = new WarningStage("Указать путь: ",Tree);
	}

 }
        
    

