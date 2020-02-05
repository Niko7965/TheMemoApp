import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import ddf.minim.ugens.FilePlayer;

import java.io.InputStream;

public class MinimSound {
    AudioInput in;
    private static AudioRecorder recorder;
    boolean recorded;
    //Playback
    AudioOutput out;
    FilePlayer player;
    Minim minim;
    //Record

    public String sketchPath( String fileName ){
        return Main.main.sketchPath(fileName);
    }

    public InputStream createInput(String fileName ){
        return Main.main.createInput(fileName);
    }
    void minimSetup(){
        minim = new Minim(this);

        in = minim.getLineIn(Minim.STEREO);
        recorder = minim.createRecorder(in,"Record1.wav");
        out = minim.getLineOut(Minim.STEREO);

    }
    void newFile(String path){
        recorder = minim.createRecorder(in,path);
    }


    public static void record(){
        if(!recorder.isRecording()){
            recorder.beginRecord();
            System.out.println("Recording started");
        }
    }

    public static void stoprecord(){
        if(recorder.isRecording()){
            recorder.endRecord();
            System.out.println("Recording stopped");
        }


    }



}
