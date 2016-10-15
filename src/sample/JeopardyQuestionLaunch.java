package sample;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by BrandonRoehl on 10/15/16.
 */
public class JeopardyQuestionLaunch implements EventHandler<ActionEvent> {

    public JeopardyQuestionLaunch(Main main, int x, int y) {

    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Fill stage with content
        stage.showAndWait();
        System.out.println("This worked");
    }
}
