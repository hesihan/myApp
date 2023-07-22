package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public final static String TAG = "MyBroadcastReceiver";


    public final static String B_ACTION_NEW_OUTGOING_CALL = Intent.ACTION_NEW_OUTGOING_CALL;
    private static final int MY_PERMISSIONS_REQUEST = 10222;
    private TeleBroadcastReceiver  mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] pro = new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS
        ,Manifest.permission.WRITE_CONTACTS
                ,Manifest.permission.READ_EXTERNAL_STORAGE
                ,Manifest.permission.GET_TASKS
                ,Manifest.permission.USE_SIP
                ,Manifest.permission.MODIFY_AUDIO_SETTINGS
                ,Manifest.permission.ANSWER_PHONE_CALLS
                ,Manifest.permission.GET_ACCOUNTS
                ,Manifest.permission.WAKE_LOCK
                ,Manifest.permission.CHANGE_NETWORK_STATE
                ,Manifest.permission.MANAGE_OWN_CALLS
                ,Manifest.permission.RECEIVE_SMS
                ,Manifest.permission.FOREGROUND_SERVICE
                ,Manifest.permission.CALL_PHONE
                ,Manifest.permission.READ_CONTACTS
        };
        ActivityCompat.requestPermissions(this,  pro, MY_PERMISSIONS_REQUEST);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    //按钮1-注册广播
    public void registerIt(View v) {
        Log.i(TAG, "registerIt");
        mBroadcastReceiver = new TeleBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    //按钮2-撤销广播
    public void unregisterIt(View v) {
        Log.i(TAG, "unregisterIt");
        unregisterReceiver(mBroadcastReceiver);
    }
}