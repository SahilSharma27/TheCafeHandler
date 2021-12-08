package com.example.android.kartooncafehandler;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class TableReservation implements Parcelable {
    private String reservationKey;
    private String reservationId;
    private String reservationForDate;
    private String reservationForTime;
    private String numberOfPpl;
    private String userName;
    private String userEmail;
    private String userContact;
    private String specialOccassion;
    private String specialInstruction;
    private ArrayList<Cart> reservationOrderAheadList;
    private double orderTotal;
    private String reservationStatus;


    public TableReservation() {
    }


    protected TableReservation(Parcel in) {
        reservationKey = in.readString();
        reservationId = in.readString();
        reservationForDate = in.readString();
        reservationForTime = in.readString();
        numberOfPpl = in.readString();
        userName = in.readString();
        userEmail = in.readString();
        userContact = in.readString();
        specialOccassion = in.readString();
        specialInstruction = in.readString();
        reservationOrderAheadList = in.createTypedArrayList(Cart.CREATOR);
        orderTotal = in.readDouble();
        reservationStatus = in.readString();
    }

    public static final Creator<TableReservation> CREATOR = new Creator<TableReservation>() {
        @Override
        public TableReservation createFromParcel(Parcel in) {
            return new TableReservation(in);
        }

        @Override
        public TableReservation[] newArray(int size) {
            return new TableReservation[size];
        }
    };

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


    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public String getNumberOfPpl() {
        return numberOfPpl;
    }

    public void setNumberOfPpl(String numberOfPpl) {
        this.numberOfPpl = numberOfPpl;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public ArrayList<Cart> getReservationOrderAheadList() {
        return reservationOrderAheadList;
    }

    public void setReservationOrderAheadList(ArrayList<Cart> reservationOrderAheadList) {
        this.reservationOrderAheadList = reservationOrderAheadList;
    }

    public String getReservationForDate() {
        return reservationForDate;
    }

    public void setReservationForDate(String reservationForDate) {
        this.reservationForDate = reservationForDate;
    }

    public String getReservationForTime() {
        return reservationForTime;
    }

    public void setReservationForTime(String reservationForTime) {
        this.reservationForTime = reservationForTime;
    }


    public String getReservationKey() {
        return reservationKey;
    }

    public void setReservationKey(String reservationKey) {
        this.reservationKey = reservationKey;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getSpecialOccassion() {
        return specialOccassion;
    }

    public void setSpecialOccassion(String specialOccassion) {
        this.specialOccassion = specialOccassion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reservationKey);
        parcel.writeString(reservationId);
        parcel.writeString(reservationForDate);
        parcel.writeString(reservationForTime);
        parcel.writeString(numberOfPpl);
        parcel.writeString(userName);
        parcel.writeString(userEmail);
        parcel.writeString(userContact);
        parcel.writeString(specialOccassion);
        parcel.writeString(specialInstruction);
        parcel.writeTypedList(reservationOrderAheadList);
        parcel.writeDouble(orderTotal);
        parcel.writeString(reservationStatus);
    }
}



