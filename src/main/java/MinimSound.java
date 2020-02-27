import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import ddf.minim.ugens.FilePlayer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*MinimSound is a class that is used to handle sound
  MinimSound uses the "Minim" library from processing
  MinimSound uses a few Java libraries (io, nio, util) to save sound files on the device */

public class MinimSound {
    //Record
    //Recording variables.
    private String folder;
    private AudioInput in;
    private AudioRecorder recorder;
    private Minim minim;
    private String path;

    //Playback, unused currently, but important for future development
    private AudioOutput out;
    private FilePlayer player;
    private List recordings;

    //A function that returns the absolute path from a sketchpath (C:/Users/... from Java/Resources/...
    //Is required for the Minim Library to work
    public String sketchPath(String fileName){
        return Main.main.sketchPath(fileName);
    }

    //Creates a new sound input
    //Is required for the Minim Library to work
    public InputStream createInput(String fileName ){
        return Main.main.createInput(fileName);
    }

    //The setup function for MinimSound
    //Defines inputs, outputs and directories for the Minim library
   void minimSetup(){
        minim = new Minim(this);
        in = minim.getLineIn(Minim.STEREO);
        recorder = minim.createRecorder(in,"/Recordings/Temp/temp.wav");
        path = "/Recordings/Temp/temp.wav";
        folder = "./Recordings";
        out = minim.getLineOut(Minim.STEREO);
    }

    //Changes the output path for the Minim recorder
    void newTemp(){
        String pathName = "./Recordings/Temp/" +System.currentTimeMillis()+ ".wav";
        path = pathName;
        recorder = minim.createRecorder(in,pathName);
    }

    //Starts recording if not already recording
    void record(){
        if(!recorder.isRecording()){
            recorder.beginRecord();
            System.out.println("Recording started");
        }
    }

    //Stops the recording if recording
    void stopRecord(){
        if(recorder.isRecording()){
            recorder.endRecord();
            recorder.save();
            System.out.println("Recording stopped");

        }

    }

    void deleteFile(String filename){
        File f = new File(sketchPath(filename));
        f.delete();
    }



    void saveAs(String newName) throws IOException {
        String destination = "Recordings/"+newName+".wav";
        FileUtils.copyFile(FileUtils.getFile(path),FileUtils.getFile(destination));

    }

    //Takes a file path, and return a list of contents of the directory
    //It is currently not used, but was made for possible future development of the app,
    //Allowing the user to play previously recorded files
    public List<String> getAllRecordings(){
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {
            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //Prints the contents of a directory
    //It is currently not used, but was made for possible future development of the app,
    //Allowing the user to play previously recorded files
    private void printDir(String path){
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
