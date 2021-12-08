package com.example.android.kartooncafehandler;

import java.util.ArrayList;

public class MenuItem {
    private String Title;
    private String Backdrop;
    private ArrayList<Item> list;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBackdrop() {
        return Backdrop;
    }

    public void setBackdrop(String backdrop) {
        Backdrop = backdrop;
    }

    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }
}
