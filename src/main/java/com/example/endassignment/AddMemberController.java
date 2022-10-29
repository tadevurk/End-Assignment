package com.example.endassignment;

import Data.LibraryDB;
import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddMemberController {
    private LibraryDB libraryDB;

    private MainController mainController;

    @FXML
    private Button btnAddMember;

    @FXML
    private DatePicker datePickerMemberBD;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private Label lblAddMember;

    public AddMemberController(LibraryDB libraryDB, MainController mainController) {
        this.libraryDB = libraryDB;
        this.mainController = mainController;
    }

    @FXML
    void onButtonAddMember(ActionEvent event) {
        try {
            if (txtFieldFirstName.getText().isEmpty() || txtFieldLastName.getText().isEmpty()){
                lblAddMember.setText("Please fill all fields");
                return;
            }
            int newMemberID;
            // If member list is empty, initialize as 1;
            if (libraryDB.getMembers().isEmpty()) {
                newMemberID = 1;
            } else {
                newMemberID = (libraryDB.getMembers().get(libraryDB.getMembers().size() - 1).getMemberId() + 1); // The last member's id + 1 is a new item code
            }

            libraryDB.getMembers().add(new Member(newMemberID, txtFieldFirstName.getText(), txtFieldLastName.getText(), datePickerMemberBD.getValue() == null
                    ? LocalDate.parse(datePickerMemberBD.getEditor().getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    : datePickerMemberBD.getValue()));
            mainController.setMembersToTableView();

            txtFieldFirstName.setText("");
            txtFieldLastName.setText("");
            datePickerMemberBD.getEditor().clear();
            datePickerMemberBD.setValue(null);

            Stage close = (Stage) btnAddMember.getScene().getWindow();
            close.close();
        }catch (DateTimeParseException dtParseException){
                lblAddMember.setText("Please enter valid date format");
        }
    }

    @FXML
    void onButtonCancelAddMember(ActionEvent event) {
        Stage close = (Stage) btnAddMember.getScene().getWindow();
        close.close();
    }
}
