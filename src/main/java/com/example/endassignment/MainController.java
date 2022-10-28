package com.example.endassignment;

import Data.LibraryDB;
import Model.IsItemAvailable;
import Model.Item;
import Model.Member;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // ITEM
    @FXML
    public TableView<Item> tableViewItems;

    @FXML
    private TableColumn<Item, Integer> itemCode;

    @FXML
    private TableColumn<Item, IsItemAvailable> available;

    @FXML
    private TableColumn<Item, String> title;

    @FXML
    private TableColumn<Item, String> author;

    // ---------------------------------------------------------------


    // MEMBER
    @FXML
    public TableView<Member> tableViewMembers;

    @FXML
    private TableColumn<Member, Integer> memberId;

    @FXML
    private TableColumn<Member, LocalDate> memberBirthDate;

    @FXML
    private TableColumn<Member, String> memberName;

    @FXML
    private TableColumn<Member, String> memberSurname;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnEditMember;

    @FXML
    private Button btnDeleteMember;

    @FXML
    private TextField txtFieldSearchMember;
    // -----------------------------------------------------------------------

    @FXML
    private Button btnAddItem;

    @FXML
    private Label lblUserFullName;

    @FXML
    private TextField txtLendingItemCode;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtReceivingItemCode;

    @FXML
    private Label lblLendMessage;

    @FXML
    private Label lblReceiveMessage;

    @FXML
    private Label lblReceiveMessageLate;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnEditItem;

    @FXML
    private TextField txtFieldSearch;

    User loggedUser;

    LibraryDB libraryDB;

    private ObservableList<Item> items;

    private ObservableList<Member> members;

    public MainController(User loggedUser, LibraryDB libraryDB) {
        this.loggedUser = loggedUser;
        this.libraryDB = libraryDB;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUserFullName.setText("WELCOME " + loggedUser.toString());
        setItemsToItemTableView();
        setMembersToTableView();
    }

    @FXML
    void onButtonReceiveItem(ActionEvent event) {
        int itemCode = Integer.parseInt(txtReceivingItemCode.getText());

        for (Item item : libraryDB.getItems()) {
            if (item.getItemCode() == itemCode) {
                if (txtMemberID.getText().isEmpty()) {
                    lblReceiveMessage.setText("Please enter the member ID");
                    return;
                }
                if (item.getAvailable() == IsItemAvailable.No) {
                    Member member = checkMemberExistsInTheDataBase(Integer.parseInt(txtMemberID.getText()));
                    if (member == null) {
                        lblLendMessage.setText("There is no member with this ID");
                        return;
                    }
                    item.setAvailable(IsItemAvailable.Yes);
                    lblReceiveMessage.setText("\u2705" + " Item is successfully received!");
                    lblLendMessage.setText(""); // Clean lend message
                    long weeks = ChronoUnit.WEEKS.between(item.getLendDateOfItem(), LocalDate.now());
                    if (weeks > 3) {
                        long daysBetween = ChronoUnit.DAYS.between(item.getLendDateOfItem(), LocalDate.now());
                        lblReceiveMessageLate.setText("Don't be too late for next time, please" + "\n" + "\u26A0" + " The item is " + daysBetween + " day(s) late");
                    }
                    item.setLendDateOfItem(null);

                } else {
                    lblReceiveMessage.setText("You did not lend this item");
                }
                txtReceivingItemCode.setText("");
                break;
            }
            lblReceiveMessage.setText("There is no item with this ID");
        }
        setItemsToItemTableView();
    }

    @FXML
    void onButtonLendItem(ActionEvent event) {
        try {
            int itemCode = Integer.parseInt(txtLendingItemCode.getText());
            Member member = checkMemberExistsInTheDataBase(Integer.parseInt(txtMemberID.getText()));
            Item item = checkItemExistsInTheDataBase(itemCode);
            if (member == null) {
                lblLendMessage.setText("There is no member with this ID");
                return;
            }
            if (item == null) {
                lblLendMessage.setText("There is no item with this ID");
                return;
            }
            if (item.getAvailable() == IsItemAvailable.No) {
                lblLendMessage.setText("Item is already lent!");
            } else {
                lblLendMessage.setText("\u2705 Item is successfully lent!");
                lblReceiveMessage.setText(""); // Clean receive message
                item.setAvailable(IsItemAvailable.No); // change the status of the item as lent
                item.setLendDateOfItem(LocalDate.now());
            }
            txtLendingItemCode.setText("");
            txtMemberID.setText("");
            setItemsToItemTableView();
        } catch (NumberFormatException formatException) {
            lblLendMessage.setText("Enter correct format!");
        }

    }


    // Check if the member exists in the DB
    private Member checkMemberExistsInTheDataBase(int memberId) {
        for (Member member : libraryDB.getMembers()) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private Item checkItemExistsInTheDataBase(int itemID) {
        for (Item item : libraryDB.getItems()) {
            if (item.getItemCode() == itemID) {
                return item;
            }
        }
        return null;
    }

    public void setItemsToItemTableView() {
        tableViewItems.getItems().clear();
        items = FXCollections.observableArrayList(libraryDB.getItems());

        itemCode.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemCode"));
        available.setCellValueFactory(new PropertyValueFactory<Item, IsItemAvailable>("available"));
        title.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Item, String>("author"));

        tableViewItems.setItems(items);
    }

    public void setMembersToTableView() {
        tableViewMembers.getItems().clear();
        members = FXCollections.observableArrayList(libraryDB.getMembers());

        memberId.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberId"));
        memberName.setCellValueFactory(new PropertyValueFactory<Member, String>("memberName"));
        memberSurname.setCellValueFactory(new PropertyValueFactory<Member, String>("memberSurname"));
        memberBirthDate.setCellValueFactory(new PropertyValueFactory<Member, LocalDate>("memberBirthDate"));

        tableViewMembers.setItems(members);
    }

    @FXML
    void onButtonAddItem(ActionEvent event) {
        AddItemController addItemController = new AddItemController(libraryDB, this);
        loadScene(addItemController, "add-item-view.fxml", "Add Item");
    }

    @FXML
    void onButtonDeleteItem(ActionEvent event) {
        libraryDB.getItems().remove(tableViewItems.getSelectionModel().getSelectedItem());
        setItemsToItemTableView();
    }

    @FXML
    void onButtonEditItem(ActionEvent event) {
        EditItemController editItemController = new EditItemController(tableViewItems.getSelectionModel().getSelectedItem(), this);
        loadScene(editItemController, "edit-item-view.fxml", "Edit Item");
    }

    @FXML
    void onButtonAddMember(ActionEvent event) {
        AddMemberController addMemberController = new AddMemberController(libraryDB, this);
        loadScene(addMemberController, "add-member-view.fxml", "Add Member");
    }

    @FXML
    void onButtonEditMember(ActionEvent event) {
        EditMemberController editMemberController = new EditMemberController(tableViewMembers.getSelectionModel().getSelectedItem(), this);
        loadScene(editMemberController, "edit-member-view.fxml", "Edit Member");
    }

    @FXML
    void onButtonDeleteMember(ActionEvent event) {
        libraryDB.getMembers().remove(tableViewMembers.getSelectionModel().getSelectedItem());
        setMembersToTableView();
    }

    //TODO: text field clear! not ("").



    private void loadScene(Object controller, String fxmlFileName, String title) {
        // TODO: duplication of code over here
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource(fxmlFileName));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL); // Block other windows to do anything
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
