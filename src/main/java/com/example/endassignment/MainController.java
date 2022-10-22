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
    private TableColumn<Member, String> memberBirthDate;

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

    Member currentMember;

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

        // TODO: Ask teacher about that, why it doesn't work when I run it...
        //searchItem();
    }

    @FXML
    void onButtonReceiveItem(ActionEvent event) {
        int itemCode = Integer.parseInt(txtReceivingItemCode.getText());


        for (Item item : libraryDB.items) {
            if (item.getItemCode() == itemCode) {
                if (item.available == IsItemAvailable.No) {
                    item.setAvailable(IsItemAvailable.Yes);
                    currentMember.getLentItemsByMember().remove(item);
                    currentMember.getReceivedItemsByMember().add(item);
                    lblReceiveMessage.setText("\u2705" + " Item is successfully received!");
                    long weeks = ChronoUnit.WEEKS.between(item.getLendDateOfItem(), LocalDate.now());
                    if (weeks > 3) {
                        long daysBetween = ChronoUnit.DAYS.between(item.lendDateOfItem, LocalDate.now());
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
        int itemCode = Integer.parseInt(txtLendingItemCode.getText());

        int memberId = Integer.parseInt(txtMemberID.getText());
        currentMember = null;

        currentMember = checkMemberExist(memberId, currentMember);
        checkItem(itemCode, currentMember);
        setItemsToItemTableView();
    }


    // Check if the member exists in the DB
    private Member checkMemberExist(int memberId, Member currentMember) {
        for (Member member : libraryDB.members) {
            if (member.getMemberId() == memberId) {
                currentMember = member; // Assign the member as current member
            } else {
                lblLendMessage.setText("There is no membership with this ID");
            }
        }
        return currentMember;
    }


    // Check if the item exists in the DB
    private void checkItem(int itemCode, Member currentMember) {
        if (currentMember != null) {
            for (Item item : libraryDB.getItemsList()) {
                if (item.getItemCode() == itemCode) {
                    //TODO: booelan should be switched to enum! DON'T FORGET THAT!!
                    if (item.available == IsItemAvailable.Yes) {
                        lblLendMessage.setText("\u2705 Item is successfully lent!");
                        item.setAvailable(IsItemAvailable.No); // change the status of the item as lent
                        currentMember.getLentItemsByMember().add(item);
                        item.setLendDateOfItem(LocalDate.now());
                        txtLendingItemCode.setText("");
                        txtMemberID.setText("");
                    } else {
                        lblLendMessage.setText("Item is already lent!");
                    }
                    break;
                } else {
                    lblLendMessage.setText("There is no item with this ID");
                }
            }
        }
    }

    public void setItemsToItemTableView(){
        tableViewItems.getItems().clear();
        items = FXCollections.observableArrayList(libraryDB.items);

        itemCode.setCellValueFactory(new PropertyValueFactory<Item,Integer>("itemCode"));
        available.setCellValueFactory(new PropertyValueFactory<Item,IsItemAvailable>("available"));
        title.setCellValueFactory(new PropertyValueFactory<Item,String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Item,String>("author"));

        tableViewItems.setItems(items);
    }

    public void setMembersToTableView(){
        tableViewMembers.getItems().clear();
        members = FXCollections.observableArrayList(libraryDB.members);

        memberId.setCellValueFactory(new PropertyValueFactory<Member,Integer>("memberId"));
        memberName.setCellValueFactory(new PropertyValueFactory<Member,String>("memberName"));
        memberSurname.setCellValueFactory(new PropertyValueFactory<Member,String>("memberSurname"));
        memberBirthDate.setCellValueFactory(new PropertyValueFactory<Member,String>("memberBirthDate"));

        tableViewMembers.setItems(members);
    }

    @FXML
    void onButtonAddItem(ActionEvent event) {
        AddItemController addItemController = new AddItemController(libraryDB,this);
        loadScene(addItemController,"add-item-view.fxml",btnAddItem,"Add Item");
    }

    @FXML
    void onButtonDeleteItem(ActionEvent event) {
        libraryDB.items.remove(tableViewItems.getSelectionModel().getSelectedItem());
        setItemsToItemTableView();
    }

    @FXML
    void onButtonEditItem(ActionEvent event) {
        EditItemController editItemController = new EditItemController(tableViewItems.getSelectionModel().getSelectedItem(),this);
        loadScene(editItemController,"edit-item-view.fxml",btnEditItem,"Edit Item");
    }

    @FXML
    void onButtonAddMember(ActionEvent event) {
        AddMemberController addMemberController = new AddMemberController(libraryDB,this);
        loadScene(addMemberController,"add-member-view.fxml",btnAddMember,"Add Member");
    }

    @FXML
    void onButtonEditMember(ActionEvent event) {
        EditMemberController editMemberController = new EditMemberController(tableViewMembers.getSelectionModel().getSelectedItem(),this);
        loadScene(editMemberController,"edit-member-view.fxml",btnEditItem,"Edit Member");
    }

    @FXML
    void onButtonDeleteMember(ActionEvent event) {
        libraryDB.members.remove(tableViewMembers.getSelectionModel().getSelectedItem());
        setMembersToTableView();
    }


    private void searchItem(){

        // Wrap the Observable list in a filteredList (initially display all)
        FilteredList<Item> filteredData = new FilteredList<>(items,b->true);

        txtFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(item -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    // Compare item code, title and author of every item filter tex
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(item.getItemCode()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    }else if (item.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (item.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else {
                        return false; // Doesn't match
                    }
                });
        });

        // Wrap the Filtered list in a sorted list
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        // Bind the sortedList comparator to the table view comparator
        //      Otherwise, sorting the TableView would have no effect
        sortedData.comparatorProperty().bind(tableViewItems.comparatorProperty());

        // Add sorted (and filtered) data to the table
        tableViewItems.setItems(sortedData);
    }

    private void loadScene(Object controller, String fxmlFileName, Button button, String title){
        // TODO: duplication of code over here
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource(fxmlFileName));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL); // Block other windows to do anything
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
