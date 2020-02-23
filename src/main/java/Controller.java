import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class Controller {
    // object to hold new recording
    private MinimSound sound = new MinimSound();

    ////////////////////////////
    // All GUI objects in use //
    ////////////////////////////
    @FXML
    private AnchorPane backgroundAnchorPane;

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
    private GridPane dialogGridPane;
    private Object BackgroundImage;

    // backgrounds made by by icon images
    private int iconSize = 320;
    private BackgroundImage musicIconBackgroundImage = new BackgroundImage(
            new Image("music_icon.PNG",iconSize,iconSize,true,true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
    private BackgroundImage playIconBackgroundImage = new BackgroundImage(
            new Image("play_icon.PNG",iconSize,iconSize,true,true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
    private BackgroundImage pauseIconBackgroundImage = new BackgroundImage(
            new Image("pause_icon.PNG",iconSize,iconSize,true,true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
    private BackgroundImage stopIconBackgroundImage = new BackgroundImage(
            new Image("stop_icon.PNG",iconSize,iconSize,true,true),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);

    private Background musicBackground = new Background(musicIconBackgroundImage);
    private Background playBackground = new Background(playIconBackgroundImage);
    public Background pauseBackground = new Background(pauseIconBackgroundImage);
    private Background stopBackground = new Background(stopIconBackgroundImage);




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
        sound.record(); //make recording
        backgroundAnchorPane.setBackground(playBackground); //show recording-icon
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
        backgroundAnchorPane.setBackground(stopBackground); //show stopped-icon
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
        // open dialog to get user input for filename
        setDialogVisibility(true);
    }

    @FXML
    // when "Save" button in the pop-up dialog is pressed
    // save the user inputted file name from pop-up dialog
    void saveDialogInput(MouseEvent event){
        if(dialogTextField.getLength() > 0) {
            // Screen.setTempFileName(dialogTextField.getText());
            sound.newFile(dialogTextField.getText()); //name sound file
            setDialogVisibility(false); //close dialog
            dialogTextField.setText(""); //reset text field
            backgroundAnchorPane.setBackground(musicBackground); //show standard-icon
        } else if(dialogTextField.getLength() == 0) {
            // If no input is detected change label
            dialogLabel.setText("You must input file name to save your recording");
        } else {
            // error code
            System.out.println("Fail occurred during request of file name by user input!");
        }
    }

    @FXML
    // when clicking on label, but this isn't used yet
    void chooseFile(MouseEvent event){
        // play or mark the saved file from the file-menu
    }


    ////////////////////////////////////
    // Functions to manipulate Labels //
    ////////////////////////////////////
    @FXML
    void newFIleLabel(MinimSound thisSound) {
        // there should be a way of making new Labels to represent files
        // method missing. Should be made if drop down file chooser menu should be incorporated
    }

    ////////////////////////
    // Internal functions //
    ////////////////////////
    // shows dialog box if input is true and hides it when input is false
    // dialog is also enabled and disabled accordingly
    private void setDialogVisibility(boolean inputBool){ // dialogVisible = input
        dialogGridPane.setVisible(inputBool);
        dialogGridPane.setDisable(!inputBool);
        System.out.println("Dialog visibility changed to" + inputBool);
    }

}

