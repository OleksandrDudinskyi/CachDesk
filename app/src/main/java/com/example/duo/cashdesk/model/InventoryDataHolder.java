package com.example.duo.cashdesk.model;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class InventoryDataHolder {
    private int mDenomination;
    private int mInventory;

    public InventoryDataHolder(int denomination, int inventory) {

        mDenomination = denomination;
        mInventory = inventory;
    }

    public int getDenomination() {
        return mDenomination;
    }

    public void setDenomination(int denomination) {
        mDenomination = denomination;
    }

    public int getInventory() {
        return mInventory;
    }

    public void setInventory(int inventory) {
        mInventory = inventory;
    }
}
