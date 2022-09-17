package com.sailaminoak.computeruniversity;

public class shop_item_data {
    shop_item_data(){

    }
    String itemId,itemPrice,itemImage,itemExtraData;

    public shop_item_data(String itemId, String itemPrice, String itemImage,String itemExtraData) {
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.itemExtraData=itemExtraData;
    }

    public String getItemExtraData() {
        return itemExtraData;
    }

    public void setItemExtraData(String itemExtraData) {
        this.itemExtraData = itemExtraData;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
