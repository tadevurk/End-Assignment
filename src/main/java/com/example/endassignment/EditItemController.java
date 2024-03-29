package com.example.endassignment;

import Model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditItemController implements Initializable {

    private Item item;

    private MainController mainController;

    @FXML
    private Button btnEditCancelItem;

    @FXML
    private TextField txtFieldAuthor;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private Label lblEditItem;

    public EditItemController(Item item, MainController mainController) {
        this.item = item;
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFieldTitle.setText(item.getTitle());
        txtFieldAuthor.setText(item.getAuthor());
    }

    @FXML
    void onButtonEditItem(ActionEvent event) {

        if (txtFieldTitle.getText().isEmpty() || txtFieldAuthor.getText().isEmpty()) {
            lblEditItem.setText("Please fill all fields");
            return;
        }
        item.setTitle(txtFieldTitle.getText());
        item.setAuthor(txtFieldAuthor.getText());

        mainController.setItemsToItemTableView();

        Stage close = (Stage) btnEditCancelItem.getScene().getWindow();
        close.close();
    }

    @FXML
    void onButtonCancelEditItem(ActionEvent event) {
        Stage cancel = (Stage) btnEditCancelItem.getScene().getWindow();
        cancel.close();
    }

}
