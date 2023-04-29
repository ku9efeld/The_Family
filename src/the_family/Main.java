/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package the_family;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kumme
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("controllers/fxml/Database.fxml"));
        ///
        primaryStage.setTitle("Family Builder");
        primaryStage.setScene(new Scene(root,890,656));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        
    }
    
}
