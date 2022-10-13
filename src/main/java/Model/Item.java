package Model;

import java.time.LocalDate;

public class Item {
    public int itemCode;
    public LocalDate lendDateOfItem;

    // if false, the item can be lent
    public boolean itemStatus;

    public Item(int itemCode, LocalDate lendDateOfItem, boolean itemStatus) {
        this.itemCode = itemCode;
        this.lendDateOfItem = lendDateOfItem;
        this.itemStatus = itemStatus;
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
