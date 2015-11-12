package com.example.duo.cashdesk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryHolder> {

    List<InventoryDataHolder> mHolderList = new ArrayList<>();

    public InventoryAdapter(List<InventoryDataHolder> holderList) {
        mHolderList.addAll(holderList);
    }

    @Override
    public InventoryAdapter.InventoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InventoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(InventoryAdapter.InventoryHolder holder, int position) {
        holder.setData(mHolderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHolderList.size();
    }

    public void setHolderList(List<InventoryDataHolder> holderList) {
        mHolderList.clear();
        mHolderList.addAll(holderList);
        notifyDataSetChanged();

    }

    class InventoryHolder extends RecyclerView.ViewHolder {
        private TextView mDenomination;
        private TextView mInventory;

        public InventoryHolder(View itemView) {
            super(itemView);
            mDenomination = (TextView) itemView.findViewById(R.id.denomination);
            mInventory = (TextView) itemView.findViewById(R.id.inventory);
        }

        private void setData(InventoryDataHolder holder) {
            mDenomination.setText(itemView.getContext().getString(R.string.denomination_pattern, holder.getDenomination()));
            final String inventory = Integer.toString(holder.getInventory());
            mInventory.setText(inventory);
        }
    }
}
