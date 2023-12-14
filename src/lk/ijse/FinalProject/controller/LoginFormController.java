package lk.ijse.FinalProject.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginFormController {
    public ImageView lblCloseEye;
    public ImageView lblOpenEye;
    public Label lblTime;
    public TextField txtUserName;
    public PasswordField txtHiddenPassword;
    public TextField txtShowPassword;
    String password;

    public void initialize() {
        txtShowPassword.setVisible(false);
        lblOpenEye.setVisible(false);
        RunningTime();
    }
    public void HiddenPasswordOnAction(ActionEvent keyEvent) {
        password =txtHiddenPassword.getText();
        txtShowPassword.setText(password);

    }
    public void ShowPasswordOnAction(ActionEvent keyEvent) {
        password =txtShowPassword.getText();
        txtHiddenPassword.setText(password);
    }
    public void Close_Eye_ClickOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        lblOpenEye.setVisible(true);
        lblCloseEye.setVisible(false);
        txtHiddenPassword.setVisible(false);
    }
    public void Open_Eye_ClickOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        lblOpenEye.setVisible(false);
        lblCloseEye.setVisible(true);
        txtHiddenPassword.setVisible(true);
    }
    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("") && txtHiddenPassword.getText().equalsIgnoreCase("")) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("DashBoard");
            stage.centerOnScreen();
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Logging Failed,Incorrect Password or User Name").show();
        }
    }
    private void RunningTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }


}
