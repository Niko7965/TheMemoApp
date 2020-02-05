import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PopUpController {

    @FXML
    void Save(MouseEvent event) throws Exception {
        PopUpScreen.showNameInput(false);

    }

}
