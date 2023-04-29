/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_family;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 *
 * @author kumme
 */
public class ConnectionLine {
    private final Line line;
    private final Person firstPerson, secondPerson;
    private String connectionType;

    public ConnectionLine(Person firstPerson, Person secondPerson, String connectionType) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.connectionType = connectionType;
        line = new Line();
        //handleMousePressed();
        createLine();
       
    }

//    private void handleMousePressed() {
//        line.setOnMouseClicked(mouseEvent -> {
//            CanvasUtil.setSelectedLine(this);
//
//            try {
//                showEditConnectionWindow();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//

    private void createLine() {
        line.setStartX(firstPerson.getLeaf().getPosX());
        line.setStartY(firstPerson.getLeaf().getPosY());
        line.setEndX(secondPerson.getLeaf().getPosX());
        line.setEndY(secondPerson.getLeaf().getPosY());
        line.setStroke(randomColor());
        line.setStrokeWidth(2.5);
    }
//
//    /**
//     * Function generates random colors
//     *
//     * @return Generated random color
//     */
    private Paint randomColor() {
        Random random = new Random();
        double r = random.nextDouble();
        double g = random.nextDouble();
        double b = random.nextDouble();
        return Color.color(r, g, b);
    }
//
//    /**
//     * Function checks whether a line is connected to a person or not
//     *
//     * @param person Person which will be compared to the people connected by the line
//     * @return True if this line is connected to a person and false otherwise
//     */
    /// Добавить eql персон
    public boolean isConnectedTo(Person person) {
        return person == firstPerson || person == secondPerson;
    }
//
    public Line getLine() {
        return line;
    }
//
    public Person getFirstPerson() {
        return firstPerson;
    }
//
    public Person getSecondPerson() {
        return secondPerson;
    }
//
    public String getConnectionType() {
        return connectionType;
    }
//
//    public void setConnectionType(String connectionType) {
//        this.connectionType = connectionType;
//    }
}
