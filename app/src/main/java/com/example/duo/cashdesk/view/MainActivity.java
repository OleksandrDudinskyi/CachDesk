package com.example.duo.cashdesk.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.duo.cashdesk.R;
import com.example.duo.cashdesk.controller.CashDeskController;

/**
 * @author o.dudinskyi(dudinskyj@gmail.com)
 */
public class MainActivity extends AppCompatActivity {
    private InventoryAdapter mInventoryAdapter;
    private CashDeskController cashDeskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.inventory_list);
        cashDeskController = new CashDeskController();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mInventoryAdapter = new InventoryAdapter(cashDeskController.getDefaultInventory(getResources()));
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

    public void onUserSelectValue(int selectedValue) {
        if (cashDeskController.canBeWithdrawn(mInventoryAdapter.getHolderList(), selectedValue)) {
            mInventoryAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
        }
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
            mInventoryAdapter.setHolderList(cashDeskController.getDefaultInventory(getResources()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
