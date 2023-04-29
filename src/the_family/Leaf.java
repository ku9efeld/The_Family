package the_family;


import java.awt.geom.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import the_family.controllers.PersonalStage;


public class Leaf {
    @FXML
    public final Text text;
    @FXML
    public final Ellipse ellipse;
    private Person person;
    private double posX, posY;
   private final Point2D.Double iconGrabPosition = new Point2D.Double(0.0D, 0.0D);
   private final Point2D.Double mouseGrabPosition = new Point2D.Double(0.0D, 0.0D);
   private final Point2D.Double mouseReleasePosition = new Point2D.Double(0.0D, 0.0D);
   
    Group root;
    // Конструктор
    public Leaf(Person person, double posX, double posY) {
        this.person = person;
        this.posX = posX;
        this.posY = posY;
        text = new Text();
        ellipse = new Ellipse();
        root = new Group();
        root.getChildren().addAll(ellipse,text);
        drawingLeaf();
        mouseOnLeaf();
    }
    // Рисует листочек
    private void drawingLeaf() {
        text.setText(person.getFirstName() + " " + person.getLastName().charAt(0) + ".");
        double textWidth = text.getBoundsInLocal().getWidth();
        double textHeight = text.getBoundsInLocal().getHeight();
        text.setLayoutX(posX - textWidth / 2.0);
        text.setLayoutY(posY - textHeight / 4.0);
        text.setFill(Color.WHITE);
        ellipse.setLayoutX(posX);
        ellipse.setLayoutY(posY - textHeight / 2.0);
        //
        //
        ellipse.setRadiusX(textWidth * 0.8);
        ellipse.setRadiusY(textHeight);
        ellipse.setFill(Color.GREEN);
    }
    
   // Передвижение листочка при нажатии на текст/лепесток 
    private void mouseOnLeaf() {
        text.setOnMouseDragged((me) -> {
         this.mouseReleasePosition.x = me.getSceneX();
         this.mouseReleasePosition.y = me.getSceneY();
         this.posX = (int)(me.getSceneX() - this.mouseGrabPosition.x + this.iconGrabPosition.x);
         this.posY = (int)(me.getSceneY() - this.mouseGrabPosition.y + this.iconGrabPosition.y);
         drawingLeaf();
         drawingRelations();
      });
       text.setOnMousePressed((me) -> {
           if(me.getButton() == MouseButton.SECONDARY){
                PersonalStage personalStage = new PersonalStage(person);
            }else{
         this.mouseGrabPosition.x = me.getSceneX();
         this.mouseGrabPosition.y = me.getSceneY();
         this.iconGrabPosition.x = posX;
         this.iconGrabPosition.y = posY;
           }
      }); 
      ellipse.setOnMousePressed((me) -> {
            if(me.getButton() == MouseButton.SECONDARY){
                PersonalStage personalStage = new PersonalStage(person);
            }else{
         this.mouseGrabPosition.x = me.getSceneX();
         this.mouseGrabPosition.y = me.getSceneY();
         this.iconGrabPosition.x = posX;
         this.iconGrabPosition.y = posY;
            }
         //checkRelations();
      });
        ellipse.setOnMouseDragged((me) -> {
         this.mouseReleasePosition.x = me.getSceneX();
         this.mouseReleasePosition.y = me.getSceneY();
         this.posX = (int)(me.getSceneX() - this.mouseGrabPosition.x + this.iconGrabPosition.x);
         this.posY = (int)(me.getSceneY() - this.mouseGrabPosition.y + this.iconGrabPosition.y);
         drawingLeaf();
         drawingRelations();
      });
//        ellipse.setOnMouseClicked((me)->{
//            if(me.getButton() == MouseButton.SECONDARY){
//                PersonalStage personalStage = new PersonalStage(person);
//                System.out.println("fsdfsdfsd");
//            }
//        });
        
        
    }

//    public void updateInformation(Person person) { // Реализиовать или удалить
//        this.person = person;
//        drawingLeaf();
//    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public Text getText() {
        return text;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
    public Group getRoot(){
        return root;
    }
    private void drawingRelations(){
        for(ConnectionLine line:Storage.getConnectionLineArray()){
            // Если эта линия имеет связь для персоны, те персона (1 или 2)
           if(line.isConnectedTo(this.person)){
               if (line.getFirstPerson() == person){
                   line.getLine().setStartX(posX);
                   line.getLine().setStartY(posY);
               }
               else{
                   line.getLine().setEndX(posX);
                   line.getLine().setEndY(posY);
               }
           }

        }
        
    }
    public Person getPerson(){
        return this.person;
    }
    
    public boolean eqvLeaf(Leaf l){
        return (this.ellipse.getCenterX() == l.getEllipse().getCenterX() &&
                this.ellipse.getCenterY() == l.getEllipse().getCenterY() &&
                this.person.getFirstName().equals(l.getPerson().getFirstName()) && this.person.getLastName().equals(l.getPerson().getLastName()));
    }
}