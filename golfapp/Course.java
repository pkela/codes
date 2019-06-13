package golfapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Course {

    private SimpleIntegerProperty numberOfHoles;
    private ArrayList<Hole> holes;
    private SimpleStringProperty name;

    public Course(ArrayList<Hole> holes, String name) {
        this.numberOfHoles = new SimpleIntegerProperty(holes.size());
        this.holes = holes;
        this.name = new SimpleStringProperty(name);
    }

    public int getSumOfPars() {
        int sum = 0;
        for (Hole hole : holes) {
            sum += hole.getPar().get();
        }
        return sum;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public SimpleIntegerProperty getNumberOfHoles() {
        return numberOfHoles;
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
}
