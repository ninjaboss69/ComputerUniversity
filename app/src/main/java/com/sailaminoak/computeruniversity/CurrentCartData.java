package com.sailaminoak.computeruniversity;

public class CurrentCartData {
    public CurrentCartData(){

    }
    String itemID,itemCount,itemName,itemSize,itemTotalPrice,itemImage;

    public CurrentCartData(String itemID, String itemCount, String itemName,String itemSize, String itemTotalPrice, String itemImage) {
        this.itemID = itemID;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemTotalPrice = itemTotalPrice;
        this.itemImage = itemImage;
        this.itemSize=itemSize;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(String itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
