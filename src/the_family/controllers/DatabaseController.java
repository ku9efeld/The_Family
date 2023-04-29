/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package the_family.controllers;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import the_family.ConnectionLine;
import the_family.Person;
import the_family.Storage;

/**
 * FXML Controller class
 *
 * @author kumme
 */
public class DatabaseController implements Initializable { // Инциализация контроллера
    private Parent root;
    @FXML 
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name,place_birthday;
    @FXML
    private TableColumn<Person, String> lastname;
    @FXML
    private TableColumn<Person, Integer> year_birthday,year_death;
    @FXML
    private TextField NAME,LASTNAME,YEAR_DEATH,YEAR_BIRTHDAY,PLACE_BIRTHDAY; // Поля, которые заполняет пользователь о родственнике 
    @FXML
    private Button btnScene1;
    @FXML
    private Button clearDataBtn;
    
    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle){
       
        /// Инцилизация таблицы при создании сцены
        name = new TableColumn<>("First Name");
        name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        //
        lastname = new TableColumn<>("Last Name");
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        //
        //
        year_birthday = new TableColumn<>("Year birth");
        year_birthday.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        ///
        year_death = new TableColumn<>("Year death");
        year_death.setCellValueFactory(new PropertyValueFactory<>("deathYear"));
        //
        place_birthday = new TableColumn<>("Place birth");
        place_birthday.setCellValueFactory(new PropertyValueFactory<Person,String>("birthPlace"));
        //
        year_birthday.setPrefWidth(125);
        year_death.setPrefWidth(125);
        lastname.setPrefWidth(120);
        name.setPrefWidth(160);
        place_birthday.setPrefWidth(180);
        //
        table.getColumns().add(name);
        table.getColumns().add(lastname);
        table.getColumns().add(year_birthday);
        table.getColumns().add(year_death);
        table.getColumns().add(place_birthday);
        // Обновление таблицы
        updateTable(Storage.getPeopleArray());
    }
    
    private void textFieldsClear(){
        NAME.clear();
        LASTNAME.clear();
        YEAR_DEATH.clear();
        YEAR_BIRTHDAY.clear();
        PLACE_BIRTHDAY.clear();
    }
    
     @FXML
    //[1] Действия для 1ой сцены (База данных)
     // [1.1.1] Занесение человека в табличку [1.1.2] Предупреждение системы о пустых полях 
        public void addPerson(ActionEvent me) throws Exception{
        if (textFieldsEmpty() == false){
        Person p0 = new Person(NAME.getText(),LASTNAME.getText(),YEAR_DEATH.getText(),YEAR_BIRTHDAY.getText(),PLACE_BIRTHDAY.getText());
        if(p0.getBirthYear() <= p0.getDeathYear() || (p0.getBirthYear()> 0 && p0.getDeathYear() == 0)){
        Storage.addPerson(p0);
        updateTable(Storage.getPeopleArray());
        }
        else{
            String typeError = "Неверные даты";
            WarningStage warningStage = new WarningStage(typeError);
        }
        }
        else{
           String typeError = "Пустые поля";
           // Предупреждение о пустых полях
           WarningStage warningStage = new WarningStage(typeError);
           // Заполнение всех Field - пустотой
        }
        textFieldsClear();
        
        }
    // 1.2 Обновление таблицы
     private void updateTable(ArrayList<Person> list) {
            table.getItems().clear();
             for(Person person : list) {
            table.getItems().add(person);
        }
    }
    // 1.3 Удалить строку из таблицы и хранилища данных
    @FXML
    public void buttonRemove(ActionEvent me){
        Person p = Storage.getPeopleArray().get(table.getSelectionModel().getSelectedIndex());
        // Удаляем все линии, с кем соединина данная персона
        for (int i = 0 ; i < Storage.getConnectionLineArray().size(); i++){
            if(Storage.getConnectionLineArray().get(i).isConnectedTo(p)) {
                Storage.getConnectionLineArray().remove(i);
                i--;
            }
            }
        // После удаления всех связей - удаляем и самого человека из персон
        Storage.getPeopleArray().remove(table.getSelectionModel().getSelectedIndex());
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }
    
    
    // 1->2 Перейти на сцену с графом
    @FXML
    public void handleBtn1(ActionEvent me) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GraphicTree.fxml"));
        root = loader.load() ;
        Stage window = (Stage)btnScene1.getScene().getWindow();
        window.setScene(new Scene(root,900,800));
    }
    @FXML
    public void clearData(ActionEvent me) throws Exception{
        Storage.clearData();
        updateTable(Storage.getPeopleArray());
    }
    
    // Обработка пустых полей на вводе (чтобы исключить пустых листьев)  TODO - Добавить оповещение об изменении
        private boolean textFieldsEmpty() {
        return NAME.getText().isEmpty()
                || LASTNAME.getText().isEmpty()
                || YEAR_DEATH.getText().isEmpty()
                || YEAR_BIRTHDAY.getText().isEmpty()
                || PLACE_BIRTHDAY.getText().isEmpty();
    }
    
}
