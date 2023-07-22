package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

public class MyBroadcastReceiver extends Activity {
    public final static String TAG = "MyBroadcastReceiver";


    public final static String B_ACTION_NEW_OUTGOING_CALL = Intent.ACTION_NEW_OUTGOING_CALL;

    private TeleBroadcastReceiver  mBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.my_broadcast_receiver);
    }





    //按钮1-注册广播
    public void registerIt(View v) {
        Log.i(TAG, "registerIt");
        mBroadcastReceiver = new TeleBroadcastReceiver();
//        String callForwardString = "**21*5556#";
//        Intent intentCallForward = new Intent(Intent.ACTION_CALL);
//        Uri uri2 = Uri.fromParts("tel", callForwardString, "#");
//        intentCallForward.setData(uri2);
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
