package sample;

import com.sun.jdi.connect.Connector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private static final int PADDING = 5;

    private String[] teamName;
    private Button[][] buttons;
    private int[] team;
    private Label[] teamLabel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        getNames();

        final Font defaultFont = new Font(Font.getDefault().getName(), 28);
        final Insets defaultInsets = new Insets(PADDING);
        team = new int[]{0, 0};

        primaryStage.setTitle("Jeopardy");
        BorderPane root = new BorderPane();
        GridPane main = new GridPane();
        main.setPadding(defaultInsets);
        main.setHgap(PADDING);
        main.setVgap(PADDING);
        root.setCenter(main);


        BorderPane bottom = new BorderPane();
        bottom.setPadding(defaultInsets);

        teamLabel = new Label[]{new Label("Team 1"), new Label("Team 2")};
        for (Label label : teamLabel) { label.setFont(defaultFont); }

        bottom.setLeft(teamLabel[0]);
        bottom.setRight(teamLabel[1]);
        root.setBottom(bottom);

        int rows = 5;
        int columns = 5;
        buttons = new Button[rows][columns];
        String[] category = {"First", "Second", "Third", "Forth", "Fifth"};

        for (int x = 0; x < columns; x++) {
            Label label = new Label(category[x]);
            label.setFont(defaultFont);
            main.add(label, x, 0);
            for (int y = 0; y < rows; y++) {
                buttons[x][y] = new Button();
                buttons[x][y].setText((x + 1) + "00");
                buttons[x][y].setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                buttons[x][y].setFont(defaultFont);
                buttons[x][y].setOnAction(new JeopardyQuestionLaunch(this, x, y));
                main.add(buttons[x][y], x, y + 1);
            }
        }

        Scene scene = new Scene(root, 750, 500);
        primaryStage.setScene(scene);

        // Set the stage to be unified
        root.setStyle("-fx-background-color:transparent;");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNIFIED);

        primaryStage.show();
    }

    public void addPoints(int points, int team) {
        this.teamLabel[team].setText(teamName[team] + ": " + (this.team[team] += points));
    }

    public void disableButton(int x, int y) {
        buttons[x][y].setDisable(true);
    }

    private void getNames() {
        Stage stage = new Stage();
        BorderPane main = new BorderPane();

        main.setTop(new Label("Enter team names"));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(new Label("Team 1"), 0, 0);
        gridPane.add(new Label("Team 2"), 0, 1);

        TextField[] team = new TextField[]{new TextField(), new TextField()};
        for (int i = 0; i < 2; i ++) {
            team[i].setPromptText("Name");
            gridPane.add(team[i], 1, i);
        }

        main.setCenter(gridPane);
        Scene scene = new Scene(main, 300, 300);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();

        System.out.println(team[0].getText());
        System.out.println(team[1].getText());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
