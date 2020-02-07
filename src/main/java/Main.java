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


public class Main extends PApplet {


    public static Main main;

    public static String[] argString;

    public static void main(String[] args) {
        // write your code here

        PApplet.main("Main");
        System.out.println("Hello World");
        new Screen().start(args);
        new PopUpScreen().setupPopUp(args);

    }
    public void settings(){
        //Put stuff like size() here
        size(1500,800);
        main=this;

    }
    public void setup(){
        getSurface().setVisible(false);
        //Ordinary setup
        //MinimSound sc = new MinimSound();
        //sc.minimSetup();
        /*
        sc.record();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sc.stoprecord();
        */
    }



    public void draw(){
        rect(0, 0, 20, 20);
    }

}
