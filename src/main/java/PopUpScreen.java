import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class PopUpScreen extends Application {
    private static Scene popUpScreen;
    private static Stage PopUpScreen;


    @Override
    public void start(Stage popUpStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("popUpSample.fxml"));
        popUpStage.setTitle("");
        popUpStage.setScene(new Scene(root, 400, 128));
        popUpStage.show();
        //thisStage = popUpStage;
    }

    public void setupPopUp(String[] args) {
        launch(args);
    }

    public static void showNameInput(boolean showMe){
        if(showMe){/*this.toString().show()*/; } System.out.println("Name input box showing");
            if(!showMe){ PopUpScreen.hide(); } System.out.println("Name input box hidden");
    }

    public static Scene getScene() {
        return(popUpScreen);
    }
    public static Stage getStage() {
        return(PopUpScreen);
    }

}
