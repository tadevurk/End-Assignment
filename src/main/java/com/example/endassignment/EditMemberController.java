package com.example.endassignment;

import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMemberController implements Initializable {

    private Member member;

    private MainController mainController;

    @FXML
    private Button btnCancelEditMember;

    @FXML
    private Button btnEditMember;

    @FXML
    private DatePicker datePickerMemberBD;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    public EditMemberController(Member member, MainController mainController) {
        this.member = member;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            txtFieldFirstName.setText(member.getMemberName());
            txtFieldLastName.setText(member.getMemberSurname());
            datePickerMemberBD.setValue(member.getMemberBirthDate());
    }

    @FXML
    void onButtonCancelEditMember(ActionEvent event) {
        Stage close = (Stage) btnCancelEditMember.getScene().getWindow();
        close.close();
    }

    @FXML
    void onButtonEditMember(ActionEvent event) {
        member.setMemberName(txtFieldFirstName.getText());
        member.setMemberSurname(txtFieldLastName.getText());
        member.setMemberBirthDate(datePickerMemberBD.getValue());

        mainController.setMembersToTableView();
        Stage close = (Stage) btnCancelEditMember.getScene().getWindow();
        close.close();
    }
}
