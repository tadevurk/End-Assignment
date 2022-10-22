package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
    public int itemCode;
    public LocalDate lendDateOfItem;

    public String author;

    public String title;

    public IsItemAvailable available;

    public IsItemAvailable getAvailable() {
        return available;
    }

    public void setAvailable(IsItemAvailable available) {
        this.available = available;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    // if false, the item can be lent
    // if true, the item can NOT be lent
    public boolean itemStatus;

    public Item(int itemCode, LocalDate lendDateOfItem, IsItemAvailable available, String author, String title) {
        this.itemCode = itemCode;
        this.lendDateOfItem = lendDateOfItem;
        this.available = available;
        this.title = title;
        this.author = author;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public LocalDate getLendDateOfItem() {
        return lendDateOfItem;
    }

    public void setLendDateOfItem(LocalDate lendDateOfItem) {
        this.lendDateOfItem = lendDateOfItem;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }
}
