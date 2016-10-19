package sample;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by BrandonRoehl on 10/15/16.
 */
public class JeopardyQuestionLaunch implements EventHandler<ActionEvent> {
    private Stage stage;
    private TextArea textArea;
    private BorderPane bottom;

    private final String question;
    private final String answer;
    private final int x;
    private final int y;
    private final Main main;

    public JeopardyQuestionLaunch(Main main, int x, int y) {
        this.x = x;
        this.y = y;
        this.main = main;

        question = main.getQuestion(x, y);
        answer = main.getAnswer(x, y);

        BorderPane root = new BorderPane();

        textArea = new TextArea("Question:\n" + question);
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        textArea.setWrapText(true);
        root.setCenter(textArea);

        bottom = new BorderPane();
        Button show = new Button("Revel Answer");
        show.setOnAction(new Revel());
        show.setPrefWidth(Integer.MAX_VALUE);
        bottom.setCenter(show);

        root.setBottom(bottom);

        Scene scene = new Scene(root, 300, 300);
        stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @Override
    public void handle(ActionEvent event) {
        stage.show();
        stage.centerOnScreen();
    }


    private class Revel implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            textArea.setText("Question:\n" + question + "\n\nAnswer:\n" + answer);

            Button neither = new Button("Neither");
            neither.setOnAction(event1 -> {
                main.disableButton(x, y);
                stage.hide();
            });
            bottom.setCenter(neither);

            Button[] teamButtons = new Button[]{new Button(main.getTeamName(0)), new Button(main.getTeamName(1))};
            for (int i = 0; i < 2; i++) {
                final int num = i;
                teamButtons[num].setOnAction(event1 -> {
                    main.addPoints(((y + 1) * 100), num);
                    main.disableButton(x, y);
                    stage.hide();
                });
            }

            bottom.setLeft(teamButtons[0]);
            bottom.setRight(teamButtons[1]);
        }
    }
}
