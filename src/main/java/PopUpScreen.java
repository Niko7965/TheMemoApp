import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopUpScreen extends Application {
    @Override
    public void start(Stage popUpStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("popUpSample.fxml"));
        popUpStage.setTitle("Hello World");
        popUpStage.setScene(new Scene(root, 400, 128));
        popUpStage.show();
    }

    public void setupPopUp(String[] args) {
        launch(args);
    }
}
