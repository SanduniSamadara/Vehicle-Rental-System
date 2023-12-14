import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws  IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("lk/ijse/FinalProject/view/MainForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image("lk"));
        primaryStage.setTitle("WelcomeForm");
        primaryStage.show();
    }
}



