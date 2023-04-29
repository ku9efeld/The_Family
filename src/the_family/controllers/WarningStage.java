/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_family.controllers;

import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author kumme
 */
public class WarningStage extends Stage{
    private Button screenBtn;
     private VBox  Vroot;
     private HBox  Hroot;
     private Text type;
     private TextField input;
     private String fName;
    public WarningStage(String typeError){
        this.type = new Text("Errors/"+typeError);
        this.type.setFill(Color.RED);
        this.type.setFont(Font.font("Helvetica", FontWeight.BOLD,16));
        Vroot = new VBox(this.type);
        Vroot.setAlignment(Pos.CENTER);
        this.setScene(new Scene(Vroot,200,200));
        start();
        

    }
     public WarningStage(String typeError,Group Tree){
         screenBtn = new Button ("Сохранить");
         this.type = new Text(typeError);
         input = new TextField();
         input.setPrefWidth(250);
         Hroot = new HBox(this.type,input,screenBtn);
         Hroot.setAlignment(Pos.CENTER);
         this.setScene(new Scene(Hroot,400,200));
         start();
         screenBtn.setOnAction((me)->screen(Tree));
     }
     
     private void start(){
        this.setTitle(this.type.getText());
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
     }
     
     
     
     private void screen(Group Tree){
                fName = input.getText();
         	try {
                WritableImage image = Tree.snapshot(new SnapshotParameters(), null);
		File file = new File(fName + "\\Tree.png");
	        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                     } catch (IOException e) {
                    WarningStage warningStage2 = new WarningStage("Системе не удается найти путь");
	    }
	}
     }

