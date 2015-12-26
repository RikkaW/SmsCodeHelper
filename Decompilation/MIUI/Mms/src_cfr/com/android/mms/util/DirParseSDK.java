/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.os.IBinder
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.android.fileexplorer.service.IDirParse;
import com.android.mms.MmsApp;

public class DirParseSDK {
    private static DirParseSDK mInstance;
    private final String TAG = DirParseSDK.class.getSimpleName();
    private ServiceConnection mConnection;
    private Context mContext;
    private IDirParse mService;

    private DirParseSDK(Context context) {
        this.mConnection = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d((String)DirParseSDK.this.TAG, (String)"dirparse service connected");
                DirParseSDK.this.mService = IDirParse.Stub.asInterface(iBinder);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.d((String)DirParseSDK.this.TAG, (String)"dirparse service disconnected");
                DirParseSDK.this.mService = null;
            }
        };
        this.mContext = context;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static DirParseSDK getInstance() {
        if (mInstance == null) {
            synchronized (DirParseSDK.class) {
                if (mInstance == null) {
                    mInstance = new DirParseSDK((Context)MmsApp.getApp());
                }
            }
        }
        return mInstance;
    }

    public void close() {
        this.mContext.unbindService(this.mConnection);
        this.mService = null;
    }

    public IDirParse getService() {
        return this.mService;
    }

    public void init() {
        Intent intent = new Intent("com.android.fileexplorer.DirParseService").setPackage("com.android.fileexplorer");
        this.mContext.bindService(intent, this.mConnection, 1);
    }

}

