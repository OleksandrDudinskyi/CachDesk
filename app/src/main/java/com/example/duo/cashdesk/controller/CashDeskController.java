package com.example.duo.cashdesk.controller;

import android.content.res.Resources;

import com.example.duo.cashdesk.R;
import com.example.duo.cashdesk.model.InventoryDataHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class CashDeskController {

    private static final int DEFAULT_INVENTORY_VALUE = 0;

    public List<InventoryDataHolder> getDefaultInventory(Resources resources) {
        final int[] denominationArray = resources.getIntArray(R.array.denomination_array);
        final int[] inventoryArray = resources.getIntArray(R.array.inventory_array);
        final int denominationSize = denominationArray.length;
        final int inventorySize = denominationArray.length;
        List<InventoryDataHolder> defaultInventory = new ArrayList<>();
        for (int i = 0; i < denominationSize; i++) {
            defaultInventory.add(new InventoryDataHolder(denominationArray[i], i < inventorySize ? inventoryArray[i] : DEFAULT_INVENTORY_VALUE));
        }
        return defaultInventory;
    }

    public boolean canBeWithdrawn(List<InventoryDataHolder> inventoryList, int amountWithdraw) {
        final int size = inventoryList.size();
        List<InventoryDataHolder> resultList = new ArrayList<>();
        resultList.addAll(inventoryList);
        for (int i = size - 1; i >= 0; i--) {
            InventoryDataHolder data = resultList.get(i);
            int wholePart = amountWithdraw / data.getDenomination();
            wholePart = data.getInventory() - wholePart < 0 ? data.getInventory() : wholePart;
            amountWithdraw = amountWithdraw - data.getDenomination() * wholePart;
            data.setInventory(data.getInventory() - wholePart);
            if (amountWithdraw == 0) {
                break;
            }
        }
        if (amountWithdraw != 0) {
            return false;
        } else {
            inventoryList.clear();
            inventoryList.addAll(resultList);
        }
        return true;
    }
}
