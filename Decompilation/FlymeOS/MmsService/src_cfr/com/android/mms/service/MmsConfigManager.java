/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.telephony.SubscriptionInfo
 *  android.telephony.SubscriptionManager
 *  android.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 *  android.util.ArrayMap
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 */
package com.android.mms.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.ArrayMap;
import android.util.Log;
import com.android.mms.service.MmsConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MmsConfigManager {
    private static volatile MmsConfigManager sInstance = new MmsConfigManager();
    private Context mContext;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener;
    private final BroadcastReceiver mReceiver;
    private final Map<Integer, MmsConfig> mSubIdConfigMap = new ArrayMap();
    private SubscriptionManager mSubscriptionManager;

    public MmsConfigManager() {
        this.mReceiver = new BroadcastReceiver(){

            public void onReceive(Context object, Intent intent) {
                object = intent.getAction();
                Log.i((String)"MmsService", (String)("mReceiver action: " + (String)object));
                if (object.equals((Object)"LOADED")) {
                    MmsConfigManager.this.loadInBackground();
                }
            }
        };
        this.mOnSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                MmsConfigManager.this.loadInBackground();
            }
        };
    }

    public static MmsConfigManager getInstance() {
        return sInstance;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void load(Context object) {
        Object object2 = this.mSubscriptionManager.getActiveSubscriptionInfoList();
        if (object2 == null || object2.size() < 1) {
            Log.e((String)"MmsService", (String)"MmsConfigManager.load -- empty getActiveSubInfoList");
            return;
        }
        Log.d((String)"MmsService", (String)("MmsConfigManager.load -- subs.size()" + object2.size()));
        ArrayMap arrayMap = new ArrayMap();
        ArrayList arrayList = new ArrayList();
        synchronized (object2) {
            arrayList.addAll(object2);
        }
        object2 = arrayList.iterator();
        do {
            if (!object2.hasNext()) {
                object = this.mSubIdConfigMap;
                synchronized (object) {
                    this.mSubIdConfigMap.clear();
                    this.mSubIdConfigMap.putAll((Map<Integer, MmsConfig>)arrayMap);
                    return;
                }
            }
            arrayList = (SubscriptionInfo)object2.next();
            if (arrayList == null) continue;
            Configuration configuration = new Configuration();
            if (arrayList.getMcc() == 0 && arrayList.getMnc() == 0) {
                Configuration configuration2 = this.mContext.getResources().getConfiguration();
                configuration.mcc = configuration2.mcc;
                configuration.mnc = configuration2.mnc;
                Log.i((String)"MmsService", (String)("MmsConfigManager.load -- no mcc/mnc for sub: " + (Object)arrayList + " using mcc/mnc from main context: " + configuration.mcc + "/" + configuration.mnc));
            } else {
                Log.i((String)"MmsService", (String)("MmsConfigManager.load -- mcc/mnc for sub: " + (Object)arrayList));
                configuration.mcc = arrayList.getMcc();
                configuration.mnc = arrayList.getMnc();
            }
            configuration = object.createConfigurationContext(configuration);
            int n = arrayList.getSubscriptionId();
            arrayMap.put(n, new MmsConfig((Context)configuration, n));
        } while (true);
    }

    private void loadInBackground() {
        new Thread(){

            public void run() {
                Configuration configuration = MmsConfigManager.this.mContext.getResources().getConfiguration();
                Log.i((String)"MmsService", (String)("MmsConfigManager.loadInBackground(): mcc/mnc: " + configuration.mcc + "/" + configuration.mnc));
                MmsConfigManager.this.load(MmsConfigManager.this.mContext);
            }
        }.start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public MmsConfig getMmsConfigBySubId(int n) {
        MmsConfig mmsConfig;
        Map<Integer, MmsConfig> map = this.mSubIdConfigMap;
        synchronized (map) {
            mmsConfig = this.mSubIdConfigMap.get(n);
        }
        Log.i((String)"MmsService", (String)("getMmsConfigBySubId -- for sub: " + n + " mmsConfig: " + mmsConfig));
        return mmsConfig;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mSubscriptionManager = SubscriptionManager.from((Context)context);
        IntentFilter intentFilter = new IntentFilter("LOADED");
        context.registerReceiver(this.mReceiver, intentFilter);
        SubscriptionManager.from((Context)this.mContext).addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
    }

}

