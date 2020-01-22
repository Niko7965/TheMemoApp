import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import processing.core.PApplet;
import processing.sound.*;



public class Main extends PApplet {

    public static void main(String[] args) {
        // write your code here

        PApplet.main("Main");
        System.out.println("HelloWorld");
        new Screen().start(args);

    }
    public void settings(){
        //Put stuff like size() here
        size(1500,800);


    }
    public void setup(){
        //Ordinary setup
        Sound s;
        SinOsc sin = new SinOsc(this);
        sin.play();
        sin.freq(440);
        getSurface().setVisible(false);
    }
    public void draw(){
        rect(0, 0, 20, 20);
    }
}
