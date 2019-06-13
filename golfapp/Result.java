package golfapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;

public class Result {

    private Course course;
    private ObservableList<Pair<Hole, Integer>> holeResults;
    private int result;
    private LocalDate date;

    public Result(Course course, List<Integer> results, LocalDate date) {
        this.course = course;
        this.date = date;
        holeResults = FXCollections.observableArrayList();
        for (int i = 0; i < course.getNumberOfHoles().get(); i++) {
            Pair<Hole, Integer> pair = new Pair<>(course.getHoles().get(i), results.get(i));
            holeResults.add(pair);
        }
        this.result = getResult();
    }

    @Override
    public String toString() {
        return App.getDateFormat().format(date) + ", \t\t" + course.getName() + ", \t\t" + (getResult() >= 0 ? "+" : "") + getResult();
    }

    public int getResult() {
        int numOfThrows = 0;
        for (Pair<Hole, Integer> holeResult : holeResults) {
            numOfThrows += holeResult.getValue();
        }
        return numOfThrows - course.getSumOfPars();
    }

    public ObservableList<Pair<Hole, Integer>> getHoleResults() {
        return holeResults;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
