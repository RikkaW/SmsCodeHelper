/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.AccountManager
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.HashSet
 */
package com.xiaomi.mms.mx.fw;

import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.mms.mx.fw.HmsChannelService;
import com.xiaomi.mms.mx.fw.XmppIQHandler;
import com.xiaomi.mms.mx.fw.XmppMessageHandler;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HmsPushBroadcastReceiver
extends BroadcastReceiver {
    private static boolean mChannelConnected;
    private static XmppIQHandler mIQHandler;
    private static volatile boolean mIsRefreshingTokens;
    private static Set<ChannelConnListener> mListeners;
    private static XmppMessageHandler mMsgHandler;
    private static int mTokenInvalidCount;

    static {
        mListeners = Collections.synchronizedSet((Set)new HashSet());
        mChannelConnected = false;
        mIsRefreshingTokens = false;
        mTokenInvalidCount = 0;
    }

    public static boolean isChannelConnected() {
        return mChannelConnected;
    }

    private ExtendedAuthToken loadTokens(Context context) {
        synchronized (this) {
            Log.d("HmsPushBroadcastReceiver", "start invalidating auth token .....");
            AccountManager.get((Context)context).invalidateAuthToken("com.xiaomi", XiaoMiJIDUtils.getAuthtokenString(context, "mipub"));
            context = XiaoMiJIDUtils.getAuthtoken(context, "mipub");
            return context;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void notifyConnectionEvent(boolean bl, int n) {
        Set<ChannelConnListener> set = mListeners;
        synchronized (set) {
            Iterator<ChannelConnListener> iterator = mListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onChannelConnChanged(bl, n);
            }
            return;
        }
    }

    private void refreshTokens(final Context context) {
        synchronized (this) {
            if (!mIsRefreshingTokens) {
                mIsRefreshingTokens = true;
                new AsyncTask<Void, Void, ExtendedAuthToken>(){

                    protected /* varargs */ ExtendedAuthToken doInBackground(Void ... extendedAuthToken) {
                        try {
                            extendedAuthToken = HmsPushBroadcastReceiver.this.loadTokens(context);
                            return extendedAuthToken;
                        }
                        catch (Exception var1_2) {
                            Log.d("HmsPushBroadcastReceiver", var1_2.toString());
                            return null;
                        }
                    }

                    protected void onPostExecute(ExtendedAuthToken extendedAuthToken) {
                        super.onPostExecute((Object)extendedAuthToken);
                        if (extendedAuthToken != null) {
                            Log.d("HmsPushBroadcastReceiver", "the new auth token ..... token : " + extendedAuthToken.authToken + "  security :" + extendedAuthToken.security);
                            Intent intent = new Intent("com.xiaomi.mms.action_open_channel");
                            intent.putExtra("auth_token", extendedAuthToken.authToken);
                            intent.putExtra("auth_security", extendedAuthToken.security);
                            intent.setPackage(context.getPackageName());
                            context.startService(intent);
                        }
                        mIsRefreshingTokens = false;
                    }
                }.execute((Object[])new Void[0]);
            }
            return;
        }
    }

    private void reopenChannel(Context context) {
        Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver , close channel and open the channel, because receive 'wait' or 'not-bind' message");
        HmsChannelService.tryCloseChannel(context);
        HmsChannelService.tryOpenChannel(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(Context object, Intent object2) {
        String string2 = object2.getAction();
        if ("com.xiaomi.push.service_started".equalsIgnoreCase(string2)) {
            HmsChannelService.tryOpenChannel((Context)object);
            return;
        }
        if (!TextUtils.equals((CharSequence)object2.getStringExtra("ext_chid"), (CharSequence)"8")) return;
        if (mMsgHandler == null) {
            mMsgHandler = XmppMessageHandler.getInstance();
        }
        if (mIQHandler == null) {
            mIQHandler = XmppIQHandler.getInstance(object.getApplicationContext());
        }
        if ("com.xiaomi.push.channel_closed".equals((Object)string2)) {
            mChannelConnected = false;
            int n = object2.getIntExtra("ext_reason", 0);
            Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver channel disconnected! " + n);
            this.notifyConnectionEvent(false, n);
            return;
        }
        if ("com.xiaomi.push.new_iq".equals((Object)string2)) {
            object = new IQ(object2.getBundleExtra("ext_packet"));
            Log.v("HmsPushBroadcastReceiver", "RECV:" + object.toXML());
            mIQHandler.handle((Packet)object);
            return;
        }
        if ("com.xiaomi.push.new_msg".equals((Object)string2)) {
            object = new Message(object2.getBundleExtra("ext_packet"));
            Log.v("HmsPushBroadcastReceiver", "RECV:" + object.toXML());
            mMsgHandler.handle((Packet)object);
            return;
        }
        if ("com.xiaomi.push.new_pres".equals((Object)string2)) {
            object = new Presence(object2.getBundleExtra("ext_packet"));
            Log.v("HmsPushBroadcastReceiver", "RECV:" + object.toXML());
            mMsgHandler.handle((Packet)object);
            return;
        }
        if ("com.xiaomi.push.kicked".equals((Object)string2)) {
            string2 = object2.getStringExtra("ext_kick_type");
            object2 = object2.getStringExtra("ext_kick_reason");
            Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver kick by server, type: " + string2 + ", reason: " + (String)object2);
            boolean bl = true;
            if ("cancel".equals((Object)string2)) {
                if ("multi-login".equals(object2)) {
                    object2 = new Intent("com.xiaomi.mms.action_close_channel");
                    object2.setPackage(object.getPackageName());
                    object.startService((Intent)object2);
                    this.notifyConnectionEvent(false, 6);
                } else {
                    bl = false;
                }
            } else if ("auth".equals((Object)string2)) {
                if ("token-expired".equals(object2) || "invalid-token".equals(object2)) {
                    this.refreshTokens((Context)object);
                } else if ("not-bind".equals(object2)) {
                    this.reopenChannel((Context)object);
                } else {
                    bl = false;
                }
            } else if ("modify".equals((Object)string2)) {
                if ("invalid-pid".equals(object2)) {
                    this.refreshTokens((Context)object);
                } else {
                    bl = false;
                }
            } else if (!"wait".equals((Object)string2)) {
                bl = false;
            }
            if (bl) return;
            {
                Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver kick by server, but not be disposed........");
                return;
            }
        }
        if (!"com.xiaomi.push.channel_opened".equals((Object)string2)) return;
        mChannelConnected = object2.getBooleanExtra("ext_succeeded", false);
        if (mChannelConnected) {
            Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver channel connected!");
            mTokenInvalidCount = 0;
            this.notifyConnectionEvent(true, 0);
            return;
        }
        string2 = object2.getStringExtra("ext_reason_msg");
        Log.d("HmsPushBroadcastReceiver", "channel connect failed, reason=" + string2);
        this.notifyConnectionEvent(false, object2.getIntExtra("ext_reason", 0));
        if (!"invalid-sig".equals((Object)string2) && !"invalid-token".equals((Object)string2) && !"token-expired".equals((Object)string2)) return;
        {
            if (++mTokenInvalidCount < 3) {
                this.refreshTokens((Context)object);
                return;
            }
        }
        mTokenInvalidCount = 0;
        Log.w("HmsPushBroadcastReceiver", "max token refresh time reaches.");
    }

    public static interface ChannelConnListener {
        public void onChannelConnChanged(boolean var1, int var2);
    }

}

