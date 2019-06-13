package golfapp.UI;

import golfapp.App;
import golfapp.Hole;
import golfapp.Result;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.stream.Collectors;

public class ResultsWindow {

    private static Stage stage;

    private static TableView<Result> edellisetTulokset = new TableView<>();

    public static void loadWindow(Stage primaryStage) {
        stage = primaryStage;
        ListView parListView = new ListView();
        ListView pituusListView = new ListView();
        ListView tulosListView = new ListView();

        VBox root = new VBox();

        TableColumn pvmColumn = new TableColumn();
        pvmColumn.setText("PVM");
        pvmColumn.setSortable(false);
        pvmColumn.setMinWidth(65);
        pvmColumn.setCellValueFactory(
                new PropertyValueFactory<Result, String>("date")
        );

        TableColumn rataColumn = new TableColumn();
        rataColumn.setText("Rata");
        rataColumn.setSortable(false);
        rataColumn.setMinWidth(140);
        rataColumn.setCellValueFactory(
                new PropertyValueFactory<Result, String>("course")
        );

        TableColumn tulosColumn = new TableColumn();
        tulosColumn.setText("Tulos");
        tulosColumn.setSortable(false);
        tulosColumn.setMinWidth(75);
        tulosColumn.setCellValueFactory(
                new PropertyValueFactory<Result, Integer>("result")
        );

        edellisetTulokset.setItems(App.getResults());
        edellisetTulokset.setMaxWidth(300);
        edellisetTulokset.setPrefHeight(600);
        edellisetTulokset.getColumns().addAll(pvmColumn, rataColumn, tulosColumn);
        edellisetTulokset.setEditable(false);
        edellisetTulokset.getSelectionModel().selectFirst();


        edellisetTulokset.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue != null) {
                    parListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getCourse().getHoles()
                            .stream()
                            .limit(9)
                            .map(Hole::getPar)
                            .map(SimpleIntegerProperty::getValue)
                            .collect(Collectors.toList())));

                    pituusListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getCourse().getHoles()
                            .stream()
                            .limit(9)
                            .map(Hole::getLength)
                            .map(SimpleIntegerProperty::getValue)
                            .collect(Collectors.toList())));

                    tulosListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getHoleResults()
                            .stream()
                            .limit(9)
                            .map(Pair::getValue)
                            .collect(Collectors.toList())));

                } else {
                    parListView.setItems(null);
                    pituusListView.setItems(null);
                    tulosListView.setItems(null);
                }
            }
        });

        GridPane rightGrid = new GridPane();
        rightGrid.setAlignment(Pos.CENTER);
        rightGrid.setPrefWidth(440);
        rightGrid.setHgap(3);

        Label tulosLabel = new Label("Tulos");
        Label parLabel = new Label("Par");
        Label pituusLabel = new Label("Pituus");

        rightGrid.add(tulosLabel, 0 ,0);
        rightGrid.add(parLabel, 0, 1);
        rightGrid.add(pituusLabel, 0, 2);

        tulosListView.setOrientation(Orientation.HORIZONTAL);
        tulosListView.setPrefWidth(400);
        tulosListView.setPrefHeight(50);
        tulosListView.setFixedCellSize(44);
        tulosListView.setEditable(false);
        tulosListView.setMouseTransparent(true);
        tulosListView.setFocusTraversable(false);
        if(null != edellisetTulokset.getSelectionModel().getSelectedItem()) {
            tulosListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getHoleResults()
                    .stream()
                    .limit(9)
                    .map(Pair::getValue)
                    .collect(Collectors.toList())));
        }

        rightGrid.add(tulosListView, 1, 0, 2, 1);

        parListView.setOrientation(Orientation.HORIZONTAL);
        parListView.setPrefWidth(400);
        parListView.setPrefHeight(50);
        parListView.setFixedCellSize(44);
        parListView.setEditable(false);
        parListView.setMouseTransparent(true);
        parListView.setFocusTraversable(false);
        if(null != edellisetTulokset.getSelectionModel().getSelectedItem()) {
            parListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getCourse().getHoles()
                    .stream()
                    .limit(9)
                    .map(Hole::getPar)
                    .map(SimpleIntegerProperty::getValue)
                    .collect(Collectors.toList())));
        }

        rightGrid.add(parListView, 1, 1, 2, 1);

        pituusListView.setOrientation(Orientation.HORIZONTAL);
        pituusListView.setPrefWidth(400);
        pituusListView.setPrefHeight(50);
        pituusListView.setFixedCellSize(44);
        pituusListView.setEditable(false);
        pituusListView.setMouseTransparent(true);
        pituusListView.setFocusTraversable(false);
        if(null != edellisetTulokset.getSelectionModel().getSelectedItem()) {
            pituusListView.setItems(FXCollections.observableArrayList(edellisetTulokset.getSelectionModel().getSelectedItem().getCourse().getHoles()
                    .stream()
                    .limit(9)
                    .map(Hole::getLength)
                    .map(SimpleIntegerProperty::getValue)
                    .collect(Collectors.toList())));
        }


        rightGrid.add(pituusListView, 1, 2, 2, 1);

        Pane emptyPane1 = new Pane();
        emptyPane1.setMinWidth(250);

        Pane emptyPane2 = new Pane();
        emptyPane2.setMinWidth(250);

        Pane emptyPane3 = new Pane();
        emptyPane3.setMinHeight(50);

        Button poistaTulosButton = new Button("Poista tulos");
        poistaTulosButton.setPrefWidth(150);
        poistaTulosButton.setOnAction(event -> poistaTulosButtonPressed());

        Button palaaButton = new Button("Palaa");
        palaaButton.setPrefWidth(150);
        palaaButton.setOnAction(event -> palaaButtonPressed());

        rightGrid.add(emptyPane1, 1, 4);
        rightGrid.add(poistaTulosButton, 2, 4);
        rightGrid.add(emptyPane3, 0, 5);
        rightGrid.add(emptyPane2, 1, 6);
        rightGrid.add(palaaButton, 2, 6);

        SplitPane splitPane = new SplitPane();
        splitPane.setPrefSize(800, 600);
        splitPane.getItems().addAll(edellisetTulokset, rightGrid);

        root.getChildren().add(splitPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void palaaButtonPressed() {
        MainWindow.loadWindow(stage);
    }

    private static void poistaTulosButtonPressed() {
        if(null != edellisetTulokset.getSelectionModel().getSelectedItem()){
            App.getResults().remove(edellisetTulokset.getSelectionModel().getSelectedItem());
        }

    }
}
