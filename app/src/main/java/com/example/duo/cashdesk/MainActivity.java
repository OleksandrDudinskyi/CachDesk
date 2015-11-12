package com.example.duo.cashdesk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class MainActivity extends AppCompatActivity {
    private static final int DEFAULT_INVENTORY_VALUE = 0;
    private InventoryAdapter mInventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.inventory_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mInventoryAdapter = new InventoryAdapter(getData());
        recyclerView.setAdapter(mInventoryAdapter);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(new WithdrawDialog(), WithdrawDialog.class.getCanonicalName()).commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<InventoryDataHolder> getData() {
        final int[] denominationArray = getResources().getIntArray(R.array.denomination_array);
        final int[] inventoryArray = getResources().getIntArray(R.array.inventory_array);
        final int denominationSize = denominationArray.length;
        final int inventorySize = denominationArray.length;
        List<InventoryDataHolder> inventoryDataHolders = new ArrayList<>();
        for (int i = 0; i < denominationSize; i++) {
            inventoryDataHolders.add(new InventoryDataHolder(denominationArray[i], i < inventorySize ? inventoryArray[i] : DEFAULT_INVENTORY_VALUE));
        }
        return inventoryDataHolders;
    }

    public void onUserSelectValue(String selectedValue) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.refresh_item) {
            mInventoryAdapter.setHolderList(getData());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
