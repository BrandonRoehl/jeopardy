package sample;

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
        this.team = new int[]{0, 0};

        primaryStage.setTitle("Jeopardy");
        BorderPane root = new BorderPane();
        GridPane main = new GridPane();
        main.setPadding(defaultInsets);
        main.setHgap(PADDING);
        main.setVgap(PADDING);
        root.setCenter(main);


        BorderPane bottom = new BorderPane();
        bottom.setPadding(defaultInsets);

        this.teamLabel = new Label[]{new Label(this.teamName[0]), new Label(this.teamName[1])};
        for (Label label : this.teamLabel) { label.setFont(defaultFont); }

        bottom.setLeft(this.teamLabel[0]);
        bottom.setRight(this.teamLabel[1]);
        root.setBottom(bottom);

        int rows = 5;
        int columns = 5;
        this.buttons = new Button[rows][columns];
        String[] category = {"First", "Second", "Third", "Forth", "Fifth"};

        for (int x = 0; x < columns; x++) {
            Label label = new Label(category[x]);
            label.setFont(defaultFont);
            main.add(label, x, 0);
            for (int y = 0; y < rows; y++) {
                this.buttons[x][y] = new Button();
                this.buttons[x][y].setText((y + 1) + "00");
                this.buttons[x][y].setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                this.buttons[x][y].setFont(defaultFont);
                this.buttons[x][y].setOnAction(new JeopardyQuestionLaunch(this, x, y));
                main.add(this.buttons[x][y], x, y + 1);
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
        this.teamLabel[team].setText(this.teamName[team] + ": " + (this.team[team] += points));
    }

    public void disableButton(int x, int y) {
        this.buttons[x][y].setDisable(true);
    }

    public String getTeamName(int i) {
        return this.teamName[i];
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

        TextField[] team = new TextField[]{ new TextField(), new TextField() };
        for (int i = 0; i < 2; i ++) {
            team[i].setPromptText("Name");
            gridPane.add(team[i], 1, i);
        }

        Button button = new Button("Submit");
        button.setOnAction(event -> stage.close());
        button.setPrefWidth(Integer.MAX_VALUE);
        gridPane.add(button, 0, 2, 2, 1);

        main.setCenter(gridPane);
        Scene scene = new Scene(main, 300, 200);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.showAndWait();

        this.teamName = new String[2];
        for (int i = 0; i < 2; i ++) {
            if (team[i].getText().isEmpty()){
                this.teamName[i] = "Team " + (i + 1);
            } else {
                this.teamName[i] = team[i].getText();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
