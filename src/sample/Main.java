package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private static final int PADDING = 5;
    protected Button[][] buttons;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Font defaultFont = new Font(Font.getDefault().getName(), 28);
        final Insets defaultInsets = new Insets(PADDING);

        primaryStage.setTitle("Jeopardy");
        BorderPane root = new BorderPane();
        GridPane main = new GridPane();
        main.setPadding(defaultInsets);
        main.setHgap(PADDING);
        main.setVgap(PADDING);
        root.setCenter(main);


        BorderPane bottom = new BorderPane();
        bottom.setPadding(defaultInsets);
        Label team1 = new Label("Team 1");
        team1.setFont(defaultFont);
        Label team2 = new Label("Team 2");
        team2.setFont(defaultFont);
        bottom.setLeft(team1);
        bottom.setRight(team2);
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


    public static void main(String[] args) {
        launch(args);
    }
}
