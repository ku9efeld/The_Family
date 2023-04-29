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
public class Person {
    ArrayList<Person> relations;
    private String firstName, lastName;
    private String birthPlace;
    private int birthYear;
    private int deathYear;
    private Leaf leaf;
    public Person(String firstName, String lastName, String deathYear, String birthYear, String birthPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        try { // Если в году окажутся буковы
           this.birthYear = Integer.parseInt(birthYear);
           //if(this.birthYear < 0) this.birthYear=Integer.MIN_VALUE;
        }
        catch (NumberFormatException ex){
            this.birthYear = Integer.MIN_VALUE;
        }
         try { // Если в году окажутся буковы
           this.deathYear = Integer.parseInt(deathYear);
          // if(this.deathYear < 0) this.birthYear=Integer.MAX_VALUE;
        }
        catch (NumberFormatException ex){
            this.deathYear = Integer.MAX_VALUE;
        }
        this.birthPlace = birthPlace;
        this.relations = new ArrayList<>();
    }
 public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
 
    public void addRelation(Person person) {
        relations.add(person);
    }
    public void removeRelation(Person person) {
        relations.remove(person);
    }
     public ArrayList<Person> getRelations() {
        return relations;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public String getBirthPlace() {
        return birthPlace;
    }
     public int getDeathYear(){
        return this.deathYear;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
    public void setLeaf(Leaf l){
    this.leaf = l;
    }
    public Leaf getLeaf() {
        return leaf;
    }
}

