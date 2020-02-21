import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controller {
    // object to hold new recording
    private MinimSound sound = new MinimSound();

    ////////////////////////////
    // All GUI objects in use //
    ////////////////////////////
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


    //////////////////////////////////////////////////////
    // FUNCTIONS run when interacting with GUI elements //
    //////////////////////////////////////////////////////
    @FXML
    // when "Record" button is pressed
    // record sound and make "stop recording" available
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
    // not used
    void rotate(MouseEvent event) {

    }

    @FXML
    // when "Stop" button is pressed
    // stop recording and make "Record" available again and also make "Save" available
    void stopRecordingSound(MouseEvent event) {
        stopButton.setDisable(!stopButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        sound.stoprecord();
    }

    @FXML
    // when "Save" button is pressed
    // save recording and make itself, "Save", unavailable
    // also initiate the user input dialog to receive file name
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
    // when "Save" button in the pop-up dialog is pressed
    // save the user inputted file name from pop-up dialog
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
    // when clicking on label, but this isn't used yet
    void chooseFile(MouseEvent event){
        // play or mark the saved file from the file-menu
    }

    @FXML
    // there should be a way of making new Labels to represent files
    // method missing. Should be made if drop down file chooser menu should be incorporated
    void newFIleLabel(MinimSound thisSound) {

    }

}

