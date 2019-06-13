package golfapp.UI;

import java.util.ArrayList;

import golfapp.App;
import golfapp.Course;
import golfapp.Hole;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddCourseWindow {

    private static Stage stage;

    private static ArrayList<TextField> parFields = new ArrayList();
    private static ArrayList<TextField> pituusFields = new ArrayList();
    private static TextField rataTextField = new TextField();


    public static void loadWindow(Stage primaryStage) {
        stage = primaryStage;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setPrefSize(stage.getWidth(), stage.getHeight());
        gridPane.setMinSize(stage.getWidth(), stage.getHeight());


        Label titlelabel = new Label("Radan lisäys:");
        gridPane.add(titlelabel, 0, 0    );

        rataTextField.setPromptText("Radan nimi");
        rataTextField.setPrefWidth(400);
        rataTextField.setMaxWidth(400);
        rataTextField.setAlignment(Pos.CENTER);
        gridPane.add(rataTextField, 1, 1, 2, 1);

        Label title2label = new Label("Väylien lkm.:");
        gridPane.add(title2label, 0, 2    );

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("9");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        gridPane.add(rb1, 1, 2);

        RadioButton rb2 = new RadioButton("18");
        rb2.setToggleGroup(group);
        rb2.setDisable(true);
        gridPane.add(rb2, 2, 2);

        Label parLabel = new Label("Par:");
        gridPane.add(parLabel, 0, 5);
        Label pituusLabel = new Label("Pituus(m):");
        gridPane.add(pituusLabel, 0, 6);

        GridPane parPane = new GridPane();

        for(int i = 0; i < 9; i++ ){
            TextField par1TextField = new TextField();
            par1TextField.setPrefWidth(44);
            par1TextField.setMaxWidth(44);
            par1TextField.setAlignment(Pos.CENTER);
            parFields.add(par1TextField);
            parPane.add(par1TextField, i, 1);
            par1TextField.setOnKeyPressed(event -> {
                // If not positive number, clear
                if(!par1TextField.getText().matches("\\d+")) {
                    par1TextField.clear();
                }
                // If more than one char, remove all but the first
                if(par1TextField.getText().length() > 1) {
                    par1TextField.setText(par1TextField.getText(0, 1));
                }
            });
        }

        gridPane.add(parPane, 1, 5, 2, 4);

        GridPane lenghtPane = new GridPane();

        for(int i = 0; i < 9; i++ ){
            TextField lenghtTextField = new TextField();
            lenghtTextField.setPrefWidth(44);
            lenghtTextField.setMaxWidth(44);
            lenghtTextField.setAlignment(Pos.CENTER);
            pituusFields.add(lenghtTextField);
            lenghtTextField.setOnKeyPressed(event -> {
                // If not positive number, clear
                if(!lenghtTextField.getText().matches("\\d+")) {
                    lenghtTextField.clear();
                }
                // If more than one char, remove all but the first
                if(lenghtTextField.getText().length() > 3) {
                    lenghtTextField.setText(lenghtTextField.getText(0, 3));
                }
            });
            lenghtPane.add(lenghtTextField, i, 0);
        }

        gridPane.add(lenghtPane, 1, 6, 2, 5);


        rb1.setUserData("9");
        rb2.setUserData("18");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {

                    GridPane par1Pane = new GridPane();
                    GridPane lenght1Pane = new GridPane();

                    gridPane.getChildren().remove(parPane);
                    gridPane.getChildren().remove(lenghtPane);
                    gridPane.getChildren().remove(par1Pane);
                    gridPane.getChildren().remove(lenght1Pane);



                    String select = group.getSelectedToggle().getUserData().toString();

                    System.out.println(select);
                    int j = Integer.parseInt(select);
                    System.out.println(j);


                    for(int i = 0; i < j; i++ ){
                        TextField par1TextField = new TextField();
                        par1TextField.setPrefWidth(30);
                        par1TextField.setMaxWidth(30);
                        par1TextField.setAlignment(Pos.CENTER);
                        par1Pane.add(par1TextField, i, 1);
                    }

                    gridPane.add(par1Pane, 1, 5, 2, 4);


                    for(int i = 0; i < j; i++ ){
                        TextField lenghtTextField = new TextField();
                        lenghtTextField.setPrefWidth(30);
                        lenghtTextField.setMaxWidth(30);
                        lenghtTextField.setAlignment(Pos.CENTER);
                        lenght1Pane.add(lenghtTextField, i, 1);
                    }

                    gridPane.add(lenght1Pane, 1, 6, 2, 5);

                }

            }
        });

        Button kuvaButton = new Button();
        kuvaButton.setText("Lisää kuva");
        kuvaButton.setOnAction(event -> kuvaButtonPressed());
        gridPane.add(kuvaButton, 1, 7);


        Button palaaButton = new Button();
        palaaButton.setText("Palaa");
        palaaButton.setOnAction(event -> palaaButtonPressed());
        gridPane.add(palaaButton, 1, 8);

        Button lisaaButton = new Button();
        lisaaButton.setText("Lisää");
        lisaaButton.setOnAction(event -> lisaaButtonPressed());
        gridPane.add(lisaaButton, 2, 8);


        VBox root = new VBox();
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static void palaaButtonPressed() {
        System.out.println("PalaaButtonPressed");
        MainWindow.loadWindow(stage);
    }

    private static void lisaaButtonPressed() {

        ArrayList<Hole> holes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if("".equals(parFields.get(i).getText())) return;
            if("".equals(pituusFields.get(i).getText())) return;
            Hole hole = new Hole(Integer.parseInt(parFields.get(i).getText()), Integer.parseInt(pituusFields.get(i).getText()));
            holes.add(hole);
        }
        if("".equals(rataTextField.getText())) return;

        Course course = new Course(holes, rataTextField.getText());
        App.getCourses().add(course);

        MainWindow.loadWindow(stage);

    }

    private static void kuvaButtonPressed() {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Lisää kuva");
        filechooser.showOpenDialog(stage);

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
}