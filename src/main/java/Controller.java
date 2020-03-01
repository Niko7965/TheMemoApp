import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import oracle.jrockit.jfr.Recording;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Controller {
    // variables to show duration of recording
    private Timer recordTimer;
    private boolean soundStarted = false;
    private boolean recording = false;
    private long startTime;
    // object to hold new recording
    private MinimSound sound = new MinimSound();

    ////////////////////////////
    // All GUI objects in use //
    ////////////////////////////
    @FXML
    private Label timeLabel;

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

    @FXML
    private Object BackgroundImage;

    // backgrounds made by by icon images
    private int iconSize = 320;
    private BackgroundImage micIconBackgroundImage = new BackgroundImage(
            new Image("mic_icon.PNG",iconSize,iconSize,true,true),
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

    private Background micBackground = new Background(micIconBackgroundImage);
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
        if(!soundStarted) {
            sound.minimSetup();
        }

        recordButton.setDisable(!recordButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        stopButton.setDisable(!stopButton.isDisabled()); // -||-

        if(!saveButton.isDisabled()){
            saveButton.setDisable(true);
        }
        sound.newTemp();
        sound.record(); //make recording
        recording = true;

        try {
            startTimer();
        } catch (InterruptedException e) {
            System.out.println("Timer Error");
            e.printStackTrace();
        }

        backgroundAnchorPane.setBackground(playBackground); //show recording-icon
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
        saveButton.setDisable(!saveButton.isDisabled()); // -||-
        recordButton.setDisable(!recordButton.isDisabled()); // -||-
        recording = false;
        sound.stopRecord();
        backgroundAnchorPane.setBackground(stopBackground); //show stopped-icon
    }

    @FXML
    // when "Save" button is pressed
    // save recording and make itself, "Save", unavailable
    // also initiate the user input dialog to receive file name
    void saveRecording(MouseEvent event) {
        saveButton.setDisable(!saveButton.isDisabled()); //B=¬A therefore ¬B=A too. (negation of disable-value)
        if(recordButton.isDisabled()){
            recordButton.setDisable(false); //A should always show after file is saved
        }
        // open dialog to get user input for filename
        setDialogVisibility(true);
    }

    @FXML
    // when "Save" button in the pop-up dialog is pressed
    // save the user inputted file name from pop-up dialog
    void saveDialogInput(MouseEvent event)  {
        if(dialogTextField.getLength() > 0) {
            try {
                sound.saveAs(dialogTextField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("FilenameChange");
            //sound.setOutputPath(dialogTextField.getText()); //name sound file
            timeLabel.setText("00:00");
            setDialogVisibility(false); //close dialog
            dialogTextField.setText(""); //reset text field
            backgroundAnchorPane.setBackground(micBackground); //show standard-icon

        } else if(dialogTextField.getLength() == 0) {
            // If no input is detected change label
            dialogLabel.setText("You must input file name to save your recording");
        } else {
            // error code
            System.out.println("Fail occurred during request of file name by user input!");
        }
    }

    @FXML
    // when clicking on label; NOT IN USE
    void chooseFile(MouseEvent event){
        // play or mark the saved file from the file-menu
    }


    ////////////////////////////////////
    // Functions to manipulate Labels //
    ////////////////////////////////////
    @FXML
    void newFileLabel(MinimSound thisSound) {
        // there should be a way of making new Labels to represent files
        // method missing. Should be made if drop down file chooser menu should be incorporated
    }

    ////////////////////////
    // Internal functions //
    ////////////////////////
    // shows dialog box if input is true and hides it when input is false
    // dialog is also enabled and disabled accordingly
    private void setDialogVisibility(boolean inputBool){ // visibility = input
        dialogGridPane.setVisible(inputBool);
        dialogGridPane.setDisable(!inputBool);
        System.out.println("Dialog visibility changed to " + inputBool);
    }


    //A lambda, that can be called repeatedly from a timer. Is used to update the timerLabel.
    private TimerTask updateTimer = new TimerTask() {
        //The code run by the timer task
        @Override
        public void run() {
            //Only update the timer if the user is recording.
            if(recording){
                System.out.println("Time is " + getTime());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeLabel.setText(getTime());
                    }
                });
            } else {
                recordTimer.cancel();
            }
        }
    };

    //Time Functions
    private String getTime(){
        //Calculates time difference
        long recordTime = System.currentTimeMillis() - startTime;
        long recordedSecs = recordTime / 1000;
        long recordedMins = recordedSecs / 60;
        String displayMins;
        String displaySecs;

        //The rest below is formatting
        if(recordedSecs % 60 < 10){
            displaySecs = "0"+recordedSecs % 60;
        }
        else{
            displaySecs = String.valueOf(recordedSecs % 60);
        }

        if(recordedMins< 10){
            displayMins = "0"+recordedMins;
        }
        else{
            displayMins = String.valueOf(recordedMins);
        }

        return (displayMins + ":" + displaySecs);
    }

    private void startTimer() throws InterruptedException {
        startTime = System.currentTimeMillis();
        recordTimer = new Timer("newRecordTimer");
        recordTimer.scheduleAtFixedRate(updateTimer, 1000, 1000);

    }

}
