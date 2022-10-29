package Model;

import java.io.Serializable;
import java.time.LocalDate;


public class Item implements Serializable {
    private int itemCode;
    private LocalDate lendDateOfItem;

    private String author;

    private String title;

    private IsItemAvailable available;

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
}
