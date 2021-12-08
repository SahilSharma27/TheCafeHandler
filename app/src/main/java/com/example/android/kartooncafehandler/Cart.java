package com.example.android.kartooncafehandler;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {


    private int cartItemId;

    private String cartItemName;

    private int quantity;

    private double price;

    private String custom;

    private double customPrice;

    private int cartItemCategory;//veg , non veg , egg

public Cart(){}
    public Cart(String cartItemName, int quantity, double price) {
        this.cartItemName = cartItemName;
        this.quantity = quantity;
        this.price = price;
    }


    protected Cart(Parcel in) {
        cartItemId = in.readInt();
        cartItemName = in.readString();
        quantity = in.readInt();
        price = in.readDouble();
        custom = in.readString();
        customPrice = in.readDouble();
        cartItemCategory = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public void setCartItemName(String cartItemName) {
        this.cartItemName = cartItemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public double getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(double customPrice) {
        this.customPrice = customPrice;
    }

    public int getCartItemCategory() {
        return cartItemCategory;
    }

    public void setCartItemCategory(int cartItemCategory) {
        this.cartItemCategory = cartItemCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cartItemId);
        parcel.writeString(cartItemName);
        parcel.writeInt(quantity);
        parcel.writeDouble(price);
        parcel.writeString(custom);
        parcel.writeDouble(customPrice);
        parcel.writeInt(cartItemCategory);
    }
}
