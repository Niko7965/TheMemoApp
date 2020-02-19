import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




//
public class Screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Memo App");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }

    public void start(String[] args) {
        launch(args);
    }

}
