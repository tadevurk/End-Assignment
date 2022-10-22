package com.example.endassignment;

import Data.LibraryDB;
import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AddMemberController {
    LibraryDB libraryDB;

    MainController mainController;

    @FXML
    private Button btnAddMember;

    @FXML
    private DatePicker datePickerMemberBD;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    private String pattern = "dd-MM-yyyy";


    // TODO: Pass the item as parameter
    public AddMemberController(LibraryDB libraryDB, MainController mainController) {
        this.libraryDB = libraryDB;
        this.mainController = mainController;
    }



    @FXML
    void onButtonAddMember(ActionEvent event) {

        int newMemberID;
        if (libraryDB.members.size() == 0){
            newMemberID = 1;
        }
        else {
            newMemberID = (libraryDB.members.get(libraryDB.members.size() -1).getMemberId() + 1); // The last item's code + 1 is a new item code
        }

        libraryDB.members.add(new Member(newMemberID,txtFieldFirstName.getText(),txtFieldLastName.getText(),datePickerMemberBD.getValue()));
        mainController.setMembersToTableView();

        txtFieldFirstName.setText("");
        txtFieldLastName.setText("");
        datePickerMemberBD.setValue(null);
    }


    @FXML
    void onButtonCancelAddMember(ActionEvent event) {
        Stage close = (Stage) btnAddMember.getScene().getWindow();
        close.close();
    }
}
