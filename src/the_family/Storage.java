/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package the_family;

import java.util.ArrayList;


/**
 *
 * @author kumme
 */
public class Storage {

private static final ArrayList<Person> people = new ArrayList<>();
private static final ArrayList<ConnectionLine> lines = new ArrayList<>();
private static final ArrayList<Leaf> leaves = new ArrayList<>();




public static void addPerson(Person person) {
        people.add(person);
    }

    public static void removePerson(Person person) {
        people.remove(person);
    }
    public static void addLine(ConnectionLine line) {
        lines.add(line);
        
    }
    public static void addLeaf(Leaf leaf) {
        leaves.add(leaf);
    }
      public static int getSizeLeaves() {
        return leaves.size();
    }
    
    public static void removeLine(ConnectionLine line) {
        lines.remove(line);
    }
    // Getters
     public static ArrayList<Leaf> getLeaves() {
        return leaves;
    }
    public static ArrayList<Person> getPeopleArray() {
        return people;
    }
 
    
     public static ArrayList<ConnectionLine> getConnectionLineArray() {
        return lines;
    }
       public static void clearData() {
        people.clear();
        lines.clear();
        leaves.clear();
    }
       
}
