package com.example.endassignment;

import Data.LibraryDB;
import Model.IsItemAvailable;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController{

    private LibraryDB libraryDB;

    private MainController mainController;

    @FXML
    private Button btnAddCancelItem;

    @FXML
    private TextField txtFieldAuthor;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private Label lblAddItem;


    public AddItemController(LibraryDB libraryDB, MainController mainController) {
        this.mainController = mainController;
        this.libraryDB = libraryDB;
    }

    @FXML
    void onButtonAddItem(ActionEvent event) {
        addItem();
    }

    @FXML
    void onButtonCancelItem(ActionEvent event) {
        Stage cancel = (Stage) btnAddCancelItem.getScene().getWindow();
        cancel.close();
    }


    private void addItem(){
        if (txtFieldTitle.getText().isEmpty() || txtFieldAuthor.getText().isEmpty()){
            lblAddItem.setText("Please fill all fields");
            return;
        }
        int newItemCode;
        // If item list is empty, initialize as 1;
        if (libraryDB.getItems().isEmpty()){
            newItemCode = 1;
        }
        else {
            newItemCode = (libraryDB.getItems().get(libraryDB.getItems().size() -1).getItemCode() + 1); // The last item's code + 1 is a new item code
        }
        libraryDB.getItems().add(new Item(newItemCode,null, IsItemAvailable.Yes,txtFieldAuthor.getText(),txtFieldTitle.getText()));
        mainController.setItemsToItemTableView();

        txtFieldAuthor.setText("");
        txtFieldTitle.setText("");

        Stage cancel = (Stage) btnAddCancelItem.getScene().getWindow();
        cancel.close();

    }
}
