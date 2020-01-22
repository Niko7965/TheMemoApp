import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Circle circle;

    @FXML
    private Button recordButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button saveButton;

    @FXML
    void recordSound(MouseEvent event) {
        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        stopButton.setDisable(!stopButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        //recordButton.setText("Pause");
        if(!saveButton.isDisabled()){
            saveButton.setDisable(true);
        }
    }

    @FXML
    void rotate(MouseEvent event) {

    }

    @FXML
    void saveRecording(MouseEvent event) {
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        //recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
    }

    @FXML
    void stopRecordingSound(MouseEvent event) {
        stopButton.setDisable(!stopButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
    }


}
