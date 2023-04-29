/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_family.controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import the_family.Person;

/**
 *
 * @author kumme
 */
public class PersonalStage extends Stage {
    Person person;
    VBox root;
   public PersonalStage(Person p){
        person = p;
       Text t1 = new Text("Имя: " + person.getFirstName());
       Text t2 = new Text("Фамилия: " + person.getLastName());
       Text t3 = new Text("Год рождения: " + person.getBirthYear());
       Text t4 = new Text("Год смерти: " + person.getDeathYear());
       Text t5 = new Text( "Место рождения: " + person.getBirthPlace());
        root = new VBox(t1,t2,t3,t4,t5);
        root.setAlignment(Pos.CENTER);
        this.setScene(new Scene(root,200,200));
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
    }
    
}
