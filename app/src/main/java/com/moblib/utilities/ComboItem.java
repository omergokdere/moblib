package com.moblib.utilities;

/**
 * Created by omer.gokdere on 28.11.2015.
 */
public class ComboItem {
    private String adi;

    private String itemText;

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    private Object itemValue;

    public Object getItemValue() {
        return itemValue;
    }

    public void setItemValue(Object itemValue) {
        this.itemValue = itemValue;
    }

    public ComboItem(String itemText, Object itemValue) {
        this.itemText = itemText;
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        return itemText;
    }
}
