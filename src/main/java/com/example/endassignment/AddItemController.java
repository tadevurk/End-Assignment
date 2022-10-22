package com.example.endassignment;

import Data.LibraryDB;
import Model.IsItemAvailable;
import Model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController{

    LibraryDB libraryDB;

    MainController mainController;

    @FXML
    private Button btnAddCancelItem;

    @FXML
    private TextField txtFieldAuthor;

    @FXML
    private TextField txtFieldTitle;


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
        int newItemCode;
        if (libraryDB.items.size() == 0){
            newItemCode = 1;
        }
        else {
            newItemCode = (libraryDB.items.get(libraryDB.items.size() -1).itemCode + 1); // The last item's code + 1 is a new item code
        }
        libraryDB.items.add(new Item(newItemCode,null, IsItemAvailable.Yes,txtFieldAuthor.getText(),txtFieldTitle.getText()));
        mainController.setItemsToItemTableView();

        txtFieldAuthor.setText("");
        txtFieldTitle.setText("");
    }
}
