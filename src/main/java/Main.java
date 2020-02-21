import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import processing.core.PApplet;
import processing.sound.*;

import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

import java.io.File;
import java.util.concurrent.TimeUnit;



/* "Main" is the main class of MemoApp. All other classes are initialized through main
*/

public class Main extends PApplet {


    public static Main main;

    public static String[] argString;

    //The first code run,
    //Sets a main class for processing to use
    //Starts the screen class

    public static void main(String[] args) {
        PApplet.main("Main");
        new Screen().start(args);
        new PopUpScreen().setupPopUp(args);

    }

    //Settings for processing, MemoApp uses this to set the size of the processing window to something very small,
    //before it is removed by setup
    public void settings(){
        //Put stuff like size() here
        size(1,1);
        main=this;

    }

    //The "Main" of the processing library.
    //Since processing is only used for handling sound,
    //the only function called here, is one hiding the processing graphics window
    public void setup(){
        getSurface().setVisible(false);

    }


    //A function that is called constantly by processing, it is not used by MemoApp
    public void draw(){

    }

}
