package com.example.endassignment;

import Data.LibraryDB;
import Model.Item;
import Model.Member;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button buttonLendItem;

    @FXML
    private Button buttonReceiveItem;

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

    Member currentMember;

    User loggedUser;

    LibraryDB libraryDB;

    public MainController(User loggedUser) {
        this.loggedUser = loggedUser;
        libraryDB = new LibraryDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUserFullName.setText("WELCOME " + loggedUser.toString());
    }

    @FXML
    void onButtonReceiveItem(ActionEvent event) {
        int itemCode = Integer.parseInt(txtReceivingItemCode.getText());

        for (Item item : libraryDB.items) {
            if (item.getItemCode() == itemCode) {
                if (item.itemStatus) {
                    item.setItemStatus(false);
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
            lblLendMessage.setText("There is no item with this ID");
        }
    }

    @FXML
    void onButtonLendItem(ActionEvent event) {
        int itemCode = Integer.parseInt(txtLendingItemCode.getText());

        int memberId = Integer.parseInt(txtMemberID.getText());
        currentMember = null;

        currentMember = checkMemberExist(memberId, currentMember);
        checkItem(itemCode, currentMember);
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
                    if (!item.isItemStatus()) {
                        lblLendMessage.setText("\u2705 Item is successfully lent!");
                        item.setItemStatus(true); // change the status of the item as lent
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
}
