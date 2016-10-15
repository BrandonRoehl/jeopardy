package sample;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by BrandonRoehl on 10/15/16.
 */
public class JeopardyQuestionLaunch implements EventHandler<ActionEvent> {
    private Stage stage;

    public JeopardyQuestionLaunch(Main main, int x, int y) {
        BorderPane root = new BorderPane();









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
}
