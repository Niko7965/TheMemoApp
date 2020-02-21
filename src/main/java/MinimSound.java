import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import ddf.minim.ugens.FilePlayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinimSound {
    private String folder;
    private AudioInput in;
    private AudioRecorder recorder;
    boolean recorded;
    //Playback
    private AudioOutput out;
    FilePlayer player;
    private Minim minim;
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
        recorder = minim.createRecorder(in,"/Recordings/Record1.wav");
        folder = "./Recordings";
        out = minim.getLineOut(Minim.STEREO);

    }
    public void newFile(String path){
        String pathName = "./Recordings/" + path + ".wav";
        recorder = minim.createRecorder(in,pathName);
    }


    void record(){
        newFile("Test");

        if(!recorder.isRecording()){
            recorder.beginRecord();
            System.out.println("Recording started");
        }
    }

    void stoprecord(){
        if(recorder.isRecording()){
            recorder.endRecord();
            System.out.println("Recording stopped");
        }
        printDir(folder);


    }

    public void printDir(String path){
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        }


        /*
        System.out.println("Files in folder:");
        String[] pathnames;
        File f = new File(path);
        pathnames = f.list();
        System.out.println(pathnames);

        if (pathnames != null) {
            for (String pathname : pathnames){
                System.out.println(pathname);
            }
        }

        */

}
