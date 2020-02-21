import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controller {
    private MinimSound sound = new MinimSound();

    @FXML
    private Circle circle;

    @FXML
    private Button recordButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button saveButton;

    @FXML
    private DialogPane textInputDialog;

    @FXML
    private Button dialogButton;

    @FXML
    private Label dialogLabel;

    @FXML
    private TextField dialogTextField;

    @FXML
    void recordSound(MouseEvent event) {
        sound.minimSetup();
        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        stopButton.setDisable(!stopButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        //recordButton.setText("Pause");
        if(!saveButton.isDisabled()){
            saveButton.setDisable(true);
        }
        sound.record();
        /* unused feature where button has two functionalities may be implemented later
         * recordButton.setText("Pause");
         */
    }

    @FXML
    void rotate(MouseEvent event) {

    }

    @FXML
    void saveRecording(MouseEvent event) {
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        //recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)

        // PopUpScreen.showNameInput(true);

        if(recordButton.isDisabled()){
            recordButton.setDisable(false); //A should always show after file is saved
        }
        // text input for filename
        textInputDialog.setVisible(true);
        textInputDialog.setDisable(false);
    }

    @FXML
    void stopRecordingSound(MouseEvent event) {
        stopButton.setDisable(!stopButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        sound.stoprecord();
    }

    @FXML
    void saveDialogInput(MouseEvent event){
        if(dialogTextField.getLength() > 0) {
            // Screen.setTempFileName(dialogTextField.getText());
            sound.newFile(dialogTextField.getText());
            textInputDialog.setVisible(false);
            textInputDialog.setDisable(true);
        } else if(dialogTextField.getLength() == 0) {
            // If no input is detected
            dialogLabel.setText("You must input file name to save your recording");
        } else {
            System.out.println("Fail occurred during request of file name by user input!");
        }
    }

    @FXML
    void chooseFile(MouseEvent event){
        // play or mark the saved file from the file-menu
    }

}

