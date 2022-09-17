package com.sailaminoak.computeruniversity;

public class PendingOrderData {
    public PendingOrderData(){

    }
    String itemName,itemPrice,itemCount,itemImage,shippingAddress,pendingOrNot;

    public PendingOrderData(String itemName, String itemPrice, String itemCount,String itemImage,String shippingAddress,String pendingOrNot) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemImage=itemImage;
        this.shippingAddress=shippingAddress;
        this.pendingOrNot=pendingOrNot;

    }

    public String getPendingOrNot() {
        return pendingOrNot;
    }

    public void setPendingOrNot(String pendingOrNot) {
        this.pendingOrNot = pendingOrNot;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }


    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
