package golfapp.UI;

import golfapp.App;
import golfapp.Course;
import golfapp.Hole;

import golfapp.Result;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddResultWindow {

    private static Stage stage;

    private static List<TextField> textFields;
    private static DatePicker datePicker;
    private static ChoiceBox<Course> courseChoiceBox;

    public static void loadWindow(Stage primaryStage) {
        stage = primaryStage;

        ListView<Integer> pituusListView = new ListView<>();
        ListView<Integer> parListView = new ListView<>();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setPrefSize(stage.getWidth(), stage.getHeight());
        gridPane.setMinSize(stage.getWidth(), stage.getHeight());


        Label titlelabel = new Label("TULOKSEN LISÄYS:");
        gridPane.add(titlelabel, 1, 0    );

        datePicker = new DatePicker();
        datePicker.setPrefWidth(265);
        datePicker.getEditor().setAlignment(Pos.CENTER_RIGHT);
        datePicker.getEditor().setOnKeyPressed(event -> {
            datePicker.getEditor().setEditable(false);
        });
        datePicker.setValue(LocalDate.now());
        gridPane.add(datePicker, 2, 0, 3, 1);

        courseChoiceBox = new ChoiceBox();
        courseChoiceBox.setItems(App.getCourses());
        courseChoiceBox.setValue(App.getCourses().isEmpty() ? null : App.getCourses().get(0));
        courseChoiceBox.setPrefWidth(400);
        courseChoiceBox.setMinWidth(400);
        courseChoiceBox.valueProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                parListView.setItems(FXCollections.observableArrayList(newValue.getHoles()
                        .stream()
                        .limit(9)
                        .map(Hole::getPar)
                        .map(SimpleIntegerProperty::getValue)
                        .collect(Collectors.toList())));

                pituusListView.setItems(FXCollections.observableArrayList(newValue.getHoles()
                        .stream()
                        .limit(9)
                        .map(Hole::getLength)
                        .map(SimpleIntegerProperty::getValue)
                        .collect(Collectors.toList())));
            }
        });

        gridPane.add(courseChoiceBox, 1, 1, 3,1);

        Label tulosLabel = new Label("Tulos:");
        gridPane.add(tulosLabel, 0, 4);
        Label parLabel = new Label("Par:");
        gridPane.add(parLabel, 0, 5);
        Label pituusLabel = new Label("Pituus(m):");
        gridPane.add(pituusLabel, 0, 6);

        Label holeNumbers = new Label("   1        2         3         4         5        6        7         8         9");
        holeNumbers.setPrefWidth(400);
        gridPane.add(holeNumbers, 1, 3 , 3, 1);



        ListView<Integer> tulosListView = new ListView<>();
        tulosListView.setOrientation(Orientation.HORIZONTAL);
        tulosListView.setPrefHeight(40);
        tulosListView.setFixedCellSize(40);
        tulosListView.setEditable(true);

        TableView<Integer> tulosTableView = new TableView<>();
        tulosTableView.setPrefSize(40, 40);
        tulosTableView.setFixedCellSize(40);
        tulosTableView.setEditable(true);

        GridPane textGrid = new GridPane();
        textGrid.setAlignment(Pos.CENTER);
        textGrid.setPrefWidth(400);

        textFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            TextField textField = new TextField();
            textField.setPrefWidth(44);
            textField.setOnKeyPressed(event -> {
                // If not positive number, clear
                if(!textField.getText().matches("\\d+")) {
                    textField.clear();
                }
                // If more than one char, remove all but the first
                if(textField.getText().length() > 1) {
                   textField.setText(textField.getText(0, 1));
                }
            });
            textFields.add(textField);
            textGrid.add(textField, i, 0);
        }

        gridPane.add(textGrid, 1, 4, 3, 1);

        parListView.setOrientation(Orientation.HORIZONTAL);
        parListView.setPrefHeight(50);
        parListView.setFixedCellSize(44);
        parListView.setEditable(false);
        parListView.setMouseTransparent(true);
        parListView.setFocusTraversable(false);
        parListView.setItems(FXCollections.observableArrayList(courseChoiceBox.getSelectionModel().getSelectedItem().getHoles()
                .stream()
                .limit(9)
                .map(Hole::getPar)
                .map(SimpleIntegerProperty::getValue)
                .collect(Collectors.toList())));

        gridPane.add(parListView, 1, 5, 3, 1);

        pituusListView.setOrientation(Orientation.HORIZONTAL);
        pituusListView.setPrefHeight(50);
        pituusListView.setFixedCellSize(44);
        pituusListView.setEditable(false);
        pituusListView.setMouseTransparent(true);
        pituusListView.setFocusTraversable(false);
        pituusListView.setItems(FXCollections.observableArrayList(courseChoiceBox.getSelectionModel().getSelectedItem().getHoles()
                .stream()
                .limit(9)
                .map(Hole::getLength)
                .map(SimpleIntegerProperty::getValue)
                .collect(Collectors.toList())));

        gridPane.add(pituusListView, 1, 6, 3, 1);

        Button palaaButton = new Button();
        palaaButton.setText("Palaa");
        palaaButton.setPrefWidth(100);
        palaaButton.setOnAction(event -> palaaButtonPressed());
        gridPane.add(palaaButton, 1, 7);

        Pane emptyPane = new Pane();
        emptyPane.setMinWidth(155);
        gridPane.add(emptyPane, 2, 7);

        Button lisaaButton = new Button();
        lisaaButton.setText("Lisää");
        lisaaButton.setPrefWidth(100);
        lisaaButton.setOnAction(event -> lisaaButtonPressed());
        gridPane.add(lisaaButton, 3, 7);

        VBox root = new VBox();
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void palaaButtonPressed() {
        MainWindow.loadWindow(stage);
    }

    private static void lisaaButtonPressed() {
        ArrayList<Integer> tulosList = new ArrayList<>();
        for(TextField textField : textFields) {
            if("".equals(textField.getText())) return;
            tulosList.add(Integer.parseInt(textField.getText()));
        }
        if(null == courseChoiceBox.getValue()) return;
        Result result = new Result(courseChoiceBox.getValue(), tulosList, datePicker.getValue());
        App.getResults().add(result);

        MainWindow.loadWindow(stage);
    }
}
