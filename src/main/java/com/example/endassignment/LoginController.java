package com.example.endassignment;

import Data.LibraryDB;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    Button buttonLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private TextField fieldUserName;

    @FXML
    private Label lblWrongUserNamePassword;

    @FXML
    private GridPane gridPanel;

    //TODO: redundant new library thing
    LibraryDB libraryDB = new LibraryDB();

    public LoginController(LibraryDB libraryDB) {
        this.libraryDB = libraryDB;
    }


    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        for (User user : libraryDB.getUsers()) {
            if ((fieldUserName.getText().isEmpty() || fieldPassword.getText().isEmpty())) {
                lblWrongUserNamePassword.setText("Please fill all the fields");
            } else if (user.getUserName().equals(fieldUserName.getText()) && user.getPassword().equals(fieldPassword.getText())) {
                loadScene("main-view.fxml", new MainController(user, libraryDB));
            } else if (!user.getUserName().equals(fieldUserName.getText()) || !user.getPassword().equals(fieldPassword.getText())) {
                lblWrongUserNamePassword.setText("\u26A0" + "Invalid username/password combination");
            }
        }
    }

    private void loadScene(String name, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource(name));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = (Stage) buttonLogin.getScene().getWindow();
            window.setTitle("Main View");
            window.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






