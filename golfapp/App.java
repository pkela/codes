package golfapp;

import golfapp.UI.MainWindow;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class App extends Application{

    private static final String APP_NAME = "Ultimate Pro Disc Golf Score Tracking App for Professionals - v0.1";

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static Random random = new Random();

    private static ObservableList<Course> courses;
    private static ObservableList<Result> results;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        courses = FXCollections.observableArrayList();
        results = FXCollections.observableArrayList();

        ArrayList<Hole> holes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Hole hole = new Hole(random.nextInt(150) + 50, random.nextInt(5) + 2);
            holes.add(hole);
        }
        Course course1 = new Course(holes, "Toppila Frisbeegolf Park");

        courses.add(course1);

        holes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Hole hole = new Hole(random.nextInt(150) + 50, random.nextInt(5) + 2);
            holes.add(hole);
        }
        Course course2 = new Course(holes, "Toinen frisbeegolfrata");

        courses.add(course2);

        ArrayList<Integer> numberOfThrowsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Integer throwCount = random.nextInt(6) + 1;
            numberOfThrowsList.add(throwCount);
        }
        LocalDate date1 = LocalDate.now();
        Result result1 = new Result(course1, numberOfThrowsList, date1);
        results.add(result1);

        numberOfThrowsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Integer throwCount = random.nextInt(6) + 1;
            numberOfThrowsList.add(throwCount);
        }

        LocalDate date2 = LocalDate.now().minusMonths(6);
        Result result2 = new Result(course2, numberOfThrowsList, date2);
        results.add(result2);

        primaryStage.setTitle(APP_NAME);
        MainWindow.loadWindow(primaryStage);
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static ObservableList<Course> getCourses() {
        return courses;
    }

    public static ObservableList<Result> getResults() {
        return results;
    }

}
