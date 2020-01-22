import processing.core.PApplet;

import java.util.concurrent.TimeUnit;


public class Main extends PApplet {


    public static Main main;

    public static void main(String[] args) {
        // write your code here

        PApplet.main("Main");
        System.out.println("HelloWorld");
        new Screen().start(args);


    }
    public void settings(){
        //Put stuff like size() here
        size(1500,800);
        main=this;

    }
    public void setup(){
        //Ordinary setup
        MinimSound sc = new MinimSound();
        sc.minimSetup();
        sc.record();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sc.stoprecord();
        getSurface().setVisible(false);
    }
    public void draw(){
        rect(0, 0, 20, 20);
    }
}
