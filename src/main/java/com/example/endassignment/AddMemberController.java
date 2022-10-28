package com.example.endassignment;

import Data.LibraryDB;
import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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



    public AddMemberController(LibraryDB libraryDB, MainController mainController) {
        this.libraryDB = libraryDB;
        this.mainController = mainController;
    }



    @FXML
    void onButtonAddMember(ActionEvent event) {

        int newMemberID;
        if (libraryDB.getMembers().isEmpty()){
            newMemberID = 1;
        }
        else {
            newMemberID = (libraryDB.getMembers().get(libraryDB.getMembers().size() -1).getMemberId() + 1); // The last item's code + 1 is a new item code
        }

        libraryDB.getMembers().add(new Member(newMemberID,txtFieldFirstName.getText(),txtFieldLastName.getText(),datePickerMemberBD.getValue() == null
                ? LocalDate.parse(datePickerMemberBD.getEditor().getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                :datePickerMemberBD.getValue()));
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
