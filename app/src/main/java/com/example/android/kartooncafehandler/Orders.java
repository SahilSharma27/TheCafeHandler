package com.example.android.kartooncafehandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Orders implements Parcelable{
    private String orderKey;
    private String orderId;
    private String orderTime;
    private String userName;
    private String userEmail;
    private String userContact;
    private String userAddress;
    private String specialInstruction;
    private ArrayList<Cart> orderList;
    private double orderTotal;
    private String paymentMethod;
    private String orderStatus;
    private String paymentStatus;


    public Orders() {
    }


    protected Orders(Parcel in) {
        orderKey = in.readString();
        orderId = in.readString();
        orderTime = in.readString();
        userName = in.readString();
        userEmail = in.readString();
        userContact = in.readString();
        userAddress = in.readString();
        specialInstruction = in.readString();
        orderList = in.createTypedArrayList(Cart.CREATOR);
        orderTotal = in.readDouble();
        paymentMethod = in.readString();
        orderStatus = in.readString();
        paymentStatus = in.readString();
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime(){
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public ArrayList<Cart> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Cart> orderList) {
        this.orderList = orderList;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderKey);
        parcel.writeString(orderId);
        parcel.writeString(orderTime);
        parcel.writeString(userName);
        parcel.writeString(userEmail);
        parcel.writeString(userContact);
        parcel.writeString(userAddress);
        parcel.writeString(specialInstruction);
        parcel.writeTypedList(orderList);
        parcel.writeDouble(orderTotal);
        parcel.writeString(paymentMethod);
        parcel.writeString(orderStatus);
        parcel.writeString(paymentStatus);
    }
}
