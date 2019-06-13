package golfapp.UI;

import golfapp.App;
import golfapp.Result;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    private static Stage stage;

        public static void loadWindow(Stage primaryStage) {
        stage = primaryStage;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setPrefSize(800, 600);
        gridPane.setMinSize(800, 600);

        Image image = new Image("file:res/image.png", 300, 800, true, true);
        ImageView imageView = new ImageView(image);
        Tooltip.install(imageView, new Tooltip("Se on frisbeegolfkori, idiootti!"));

        gridPane.add(imageView, 0, 0, 1, 2);

        Button lisaaTulosButton = new Button();
        lisaaTulosButton.setText("LISÄÄ TULOS");
        lisaaTulosButton.setPrefSize(200, 100);
        lisaaTulosButton.setTooltip(new Tooltip("Lisää uuden kierroksen tulos."));
        lisaaTulosButton.setOnAction(event -> lisaaTulosButtonPressed());

        Button lisaaRataButton = new Button();
        lisaaRataButton.setText("LISÄÄ RATA");
        lisaaRataButton.setPrefSize(200, 100);
        lisaaRataButton.setTooltip(new Tooltip("Lisää uusi golfrata."));
        lisaaRataButton.setOnAction(event -> lisaaRataButtonPressed());

        GridPane buttonPane = new GridPane();
        buttonPane.setHgap(20);
        buttonPane.setVgap(20);
        buttonPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(lisaaTulosButton, HPos.LEFT);
        GridPane.setHalignment(lisaaRataButton, HPos.RIGHT);
        buttonPane.setMinSize(500, 300);
        buttonPane.add(lisaaTulosButton, 0, 0);
        buttonPane.add(lisaaRataButton, 1, 0);

        gridPane.add(buttonPane, 1, 0);

        GridPane tuloksetPane = new GridPane();
        tuloksetPane.setHgap(20);
        tuloksetPane.setVgap(20);
        tuloksetPane.setMinSize(500, 300);

        Label edellisetLabel = new Label("EDELLISET TULOKSET:");
        edellisetLabel.setAlignment(Pos.CENTER);

        TableColumn pvmColumn = new TableColumn();
        pvmColumn.setMinWidth(50);
        pvmColumn.setText("PVM");
        pvmColumn.setSortable(false);
        pvmColumn.setMinWidth(100);
        pvmColumn.setCellValueFactory(
                new PropertyValueFactory<Result, String>("date")
        );

        TableColumn rataColumn = new TableColumn();
        rataColumn.setMinWidth(50);
        rataColumn.setText("Rata");
        rataColumn.setSortable(false);
        rataColumn.setMinWidth(250);
        rataColumn.setCellValueFactory(
                new PropertyValueFactory<Result, String>("course")
        );

        TableColumn tulosColumn = new TableColumn();
        tulosColumn.setMinWidth(50);
        tulosColumn.setText("Tulos");
        tulosColumn.setSortable(false);
        tulosColumn.setMinWidth(100);
        tulosColumn.setCellValueFactory(
                new PropertyValueFactory<Result, Integer>("result")
        );

        TableView<Result> edellisetTulokset = new TableView<>();
        edellisetTulokset.setItems(App.getResults());
        edellisetTulokset.setMinWidth(450);
        edellisetTulokset.setMaxHeight(150);
        edellisetTulokset.getColumns().addAll(pvmColumn, rataColumn, tulosColumn);
        edellisetTulokset.setEditable(false);

        tuloksetPane.add(edellisetTulokset, 0, 1);

        Button enemmanButton = new Button();
        enemmanButton.setText("Enemmän");
        enemmanButton.setPrefSize(200, 50);
        enemmanButton.setTooltip(new Tooltip("Näytä enemmän tuloksia."));
        enemmanButton.setOnAction(event -> enemmanButtonPressed());

        tuloksetPane.add(edellisetLabel, 0, 0, 2, 1);
        tuloksetPane.add(enemmanButton, 0, 2);

        gridPane.add(tuloksetPane, 1, 1);

        VBox root = new VBox();
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void enemmanButtonPressed() {
        ResultsWindow.loadWindow(stage);
    }

        private static void lisaaTulosButtonPressed() {
        System.out.println("lisaaTulosButtonPressed");
        AddResultWindow.loadWindow(stage);
    }

        private static void lisaaRataButtonPressed() {
        System.out.println("lisaaTulosButtonPressed");
        AddCourseWindow.loadWindow(stage);
    }
}
