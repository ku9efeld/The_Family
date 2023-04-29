/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_family.controllers;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import the_family.ConnectionLine;
import the_family.Person;
import the_family.Storage;

/**
 *
 * @author kumme
 */



public class ConnectionAddStage extends Stage {
    Group Tree;
    VBox Menu = new VBox();
    private final ObservableList <String> options = FXCollections.observableArrayList(
                "Супруг", "Отец", "Мать", "Сын/дочь", "Бабушка/дедушка", "Брат/Сестра",  "Другое");
    private final ChoiceBox<String> connectionType = new ChoiceBox(options);
    private ComboBox<Person> firstPersonComboBox, secondPersonComboBox;
    private Button createConnectionButton;
    private ArrayList<Person> list;

   
   
   public ConnectionAddStage(Group Tree){ 
     this.Tree = Tree;
     list = Storage.getPeopleArray();
     firstPersonComboBox =  new ComboBox<>();
     labelBox("Первая персона",firstPersonComboBox);
     secondPersonComboBox = new ComboBox<>();
     labelBox("Вторая персона",secondPersonComboBox);
     createConnectionButton = new Button("Создать");
     labelBox("Первый приходится второму...",connectionType);
    adressToName(firstPersonComboBox);
    adressToName(secondPersonComboBox);
    addPeopleToFirstComboBox();
    secondPersonComboBox.setDisable(true);
    secondPersonComboBox.setPrefWidth(125);
    connectionType.setDisable(true);
    Menu.getChildren().addAll(connectionType,createConnectionButton);
    Menu.setAlignment(Pos.CENTER);
    this.setScene(new Scene(Menu, 400, 250));
    createConnectionButton.setDisable(true);
    this.setTitle("Создать связь");
    this.show();
    this.interactions();
   }
   
   private void addPeopleToFirstComboBox() {
        for(Person person : list) {
            firstPersonComboBox.getItems().add(person);
        }
    }
   
   
   void interactions(){
       // Если выбор в первой строке сделан, то установить список людей для второй строки
       firstPersonComboBox.setOnAction((event) -> {
        secondPersonComboBox.getSelectionModel().clearSelection();
        secondPersonComboBox.getItems().clear();
        connectionType.setDisable(true);
        createConnectionButton.setDisable(true);
        Person selectedPerson = firstPersonComboBox.getSelectionModel().getSelectedItem();
        addPeopleToSecondComboBox(selectedPerson);
        secondPersonComboBox.setDisable(false);
    });
       /// Если выбор во второй строке сделан, то разблок. кнопку с типом связи
       secondPersonComboBox.setOnAction((event) -> {
        connectionType.getSelectionModel().clearSelection();
        createConnectionButton.setDisable(true);
        connectionType.setDisable(false);
    });
       connectionType.setOnAction((event) -> createConnectionButton.setDisable(false)); // Если выбрана связь, дать возможность создать связь
       //
       createConnectionButton.setOnAction((event) -> {
               //
        Person firstPerson = firstPersonComboBox.getSelectionModel().getSelectedItem();
        Person secondPerson = secondPersonComboBox.getSelectionModel().getSelectedItem();
        String connection = connectionType.getValue();
        firstPerson.addRelation(secondPerson);
        secondPerson.addRelation(firstPerson);
        ConnectionLine connectionLine = new ConnectionLine(firstPerson, secondPerson, connection);
        Storage.addLine(connectionLine);
        Tree.getChildren().add(connectionLine.getLine());
        connectionLine.getLine().toBack();
   
    });   
   }
   
 
   
   private void addPeopleToSecondComboBox(Person selectedPerson) { // TODO сделать связь двунаправленной
        ArrayList<Person> connections = selectedPerson.getRelations();
        for(Person person : list) {
            if(person != selectedPerson && (connections == null || !connections.contains(person))) {
                secondPersonComboBox.getItems().add(person);
            }
        }
    }
   
   
   
   
// В выпадающем списке показывает имя и фамилию из объекта Person,а не адрес объекта, как было без этого
   static void adressToName (ComboBox<Person> cm){
    cm.setConverter(new StringConverter<Person>() {
    @Override
    public String toString(Person object) {
        return object.getFirstName() + " " + object.getLastName(); 
    }
    @Override
    public Person fromString(String string) {
        return null;
    }
    });
    }

      /// ComboBox
      private void labelBox(String label,ComboBox<Person> box){
       Label l = new Label(label);
       VBox row = new VBox(l,box);
       row.setAlignment(Pos.CENTER);
       Menu.getChildren().add(row);
   }
      /// ChoiceBox
        private void labelBox(String label,ChoiceBox<String> box){
       Label l = new Label(label);
       VBox row = new VBox(l,box);
       row.setAlignment(Pos.CENTER);
       Menu.getChildren().add(row);
   }
 }
    
    


