import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopUpController {

    @FXML
    void Save(MouseEvent event) throws Exception {
        Stage stage = (Stage) PopUpScreen.getStage();
        stage.close();
        // MouseEvent
    }

}
