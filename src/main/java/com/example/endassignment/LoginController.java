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
    private Button buttonLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private TextField fieldUserName;

    @FXML
    private Label lblWrongUserNamePassword;

    @FXML
    private GridPane gridPanel;

    LibraryDB libraryDB = new LibraryDB();


    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        for (User user: libraryDB.users) {
            if ((fieldUserName.getText().isEmpty() || fieldPassword.getText().isEmpty())) {
                lblWrongUserNamePassword.setText("Please fill all the fields");
            }
            else if(user.getUserName().equals(fieldUserName.getText()) && user.getPassword().equals(fieldPassword.getText())){
                MainController controller = new MainController(user);
                loadScene(controller,"main-view.fxml");
            }
            else if(!user.getUserName().equals(fieldUserName.getText()) && !user.getPassword().equals(fieldPassword.getText())){
                lblWrongUserNamePassword.setText("\u26A0"+"Invalid username/password combination");
            }
            }
        }

    public void loadScene(Object controller, String fxmlFileName){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource(fxmlFileName));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage loginStage = (Stage) buttonLogin.getScene().getWindow();
            loginStage.setTitle("Main View");
            loginStage.setScene(scene);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}






