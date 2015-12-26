/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.database.ContentObserver
 *  android.media.AudioManager
 *  android.media.AudioManager$OnAudioFocusChangeListener
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.provider.Settings$System
 *  android.speech.tts.TextToSpeech
 *  android.speech.tts.TextToSpeech$OnInitListener
 *  android.speech.tts.TextToSpeech$OnUtteranceCompletedListener
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Locale
 */
package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import com.android.mms.MmsApp;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SmsReportService
extends Service
implements TextToSpeech.OnInitListener {
    private BroadcastReceiver mPlugReceiver;
    private BroadcastReceiver mReceiver;
    private TextToSpeech mTts;
    private ContentObserver mTtsObserver;
    private List<String> mUtteranceIds;

    public SmsReportService() {
        this.mReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onReceive(Context context, Intent intent) {
                if ("android.intent.action.KEYCODE_MUTE".equals((Object)intent.getAction()) && SmsReportService.this.mTts != null) {
                    try {
                        SmsReportService.this.mTts.stop();
                    }
                    catch (IllegalArgumentException var1_2) {
                        MyLog.e("SmsReportService", "Fail to stop tts", var1_2);
                    }
                    SmsReportService.this.mUtteranceIds.clear();
                }
            }
        };
        this.mPlugReceiver = new BroadcastReceiver(){

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public void onReceive(Context context, Intent intent) {
                if (Settings.System.getInt((ContentResolver)SmsReportService.this.getContentResolver(), (String)"voiceassist_report_method", (int)2) != 0 || ((AudioManager)context.getSystemService("audio")).isWiredHeadsetOn() || SmsReportService.this.mTts == null) return;
                try {
                    SmsReportService.this.mTts.stop();
                    return;
                }
                catch (IllegalArgumentException var1_2) {
                    MyLog.e("SmsReportService", "Fail to stop tts", var1_2);
                    return;
                }
            }
        };
        this.mTtsObserver = new ContentObserver(null){

            public void onChange(boolean bl) {
                super.onChange(bl);
                if (SmsReportService.this.mTts != null) {
                    SmsReportService.this.mTts.shutdown();
                    SmsReportService.this.mTts = null;
                }
                SmsReportService.this.initTts();
            }
        };
    }

    private static void gainFocus() {
        ((AudioManager)MmsApp.getApp().getSystemService("audio")).requestAudioFocus(null, 3, 2);
    }

    private void initTts() {
        if (this.mTts == null || this.mTts.getCurrentEngine() == null) {
            if (this.mTts != null) {
                this.mTts.shutdown();
                this.mTts = null;
            }
            this.mTts = new TextToSpeech(this.getApplicationContext(), (TextToSpeech.OnInitListener)this);
        }
    }

    public static void releaseFocus() {
        ((AudioManager)MmsApp.getApp().getSystemService("audio")).abandonAudioFocus(null);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mUtteranceIds = new ArrayList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.KEYCODE_MUTE");
        this.registerReceiver(this.mReceiver, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        this.registerReceiver(this.mPlugReceiver, intentFilter);
        intentFilter = Settings.Secure.getUriFor((String)"tts_default_synth");
        this.getContentResolver().registerContentObserver((Uri)intentFilter, false, this.mTtsObserver);
    }

    public void onDestroy() {
        if (this.mTts != null) {
            this.mTts.shutdown();
        }
        this.unregisterReceiver(this.mReceiver);
        this.unregisterReceiver(this.mPlugReceiver);
        this.getContentResolver().unregisterContentObserver(this.mTtsObserver);
        super.onDestroy();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onInit(int n) {
        if (n == 0) {
            if (this.mTts != null) {
                n = this.mTts.setLanguage(Locale.CHINA);
                if (n != -1 && n != -2) {
                    MyLog.v("SmsReportService", "init success");
                    this.mTts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener(){

                        public void onUtteranceCompleted(String string) {
                            SmsReportService.releaseFocus();
                        }
                    });
                    return;
                }
                MyLog.e("SmsReportService", "Language is not available.");
            }
            return;
        }
        MyLog.e("SmsReportService", "Could not initialize TextToSpeech.");
        try {
            if (this.mTts != null) {
                this.mTts.shutdown();
            }
        }
        catch (IllegalArgumentException var2_2) {
            MyLog.e("SmsReportService", "Fail to shutdown tts", var2_2);
        }
        this.mTts = null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int onStartCommand(Intent intent, int n, int n2) {
        this.initTts();
        if (intent == null) {
            return super.onStartCommand(intent, n, n2);
        }
        Object object = intent.getExtras();
        if (object == null) {
            return super.onStartCommand(intent, n, n2);
        }
        if (!MSimUtils.isCallStateIdle()) {
            return super.onStartCommand(intent, n, n2);
        }
        String string = "[n1]" + object.getString("sms_address");
        char c2 = object.getChar("sms_contact");
        string = c2 == '1' ? this.getString(2131362210) + "," + object.getString("sms_name") : this.getString(2131362210) + "," + this.getString(2131362209) + " " + string;
        string = string + ". " + object.getString("sms_body");
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"com.miui.voiceassist.priority", (Object)"800");
        int n3 = MSimUtils.getSlotIdFromBundle((Bundle)object);
        object = "" + n3 + "__" + object.getString("sms_address") + "__" + c2 + "__" + n2;
        this.mUtteranceIds.add((String)object);
        hashMap.put((Object)"utteranceId", object);
        hashMap.put((Object)"streamType", (Object)"3");
        SmsReportService.gainFocus();
        if (this.mTts.speak(string, 1, hashMap) == -1) {
            this.initTts();
        }
        return super.onStartCommand(intent, n, n2);
    }

}

