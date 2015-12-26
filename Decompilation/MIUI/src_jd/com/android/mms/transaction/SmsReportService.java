package com.android.mms.transaction;

import android.app.Application;
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
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import com.android.mms.MmsApp;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SmsReportService
  extends Service
  implements TextToSpeech.OnInitListener
{
  private BroadcastReceiver mPlugReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((Settings.System.getInt(getContentResolver(), "voiceassist_report_method", 2) == 0) && (!((AudioManager)paramAnonymousContext.getSystemService("audio")).isWiredHeadsetOn()) && (mTts != null)) {}
      try
      {
        mTts.stop();
        return;
      }
      catch (IllegalArgumentException paramAnonymousContext)
      {
        MyLog.e("SmsReportService", "Fail to stop tts", paramAnonymousContext);
      }
    }
  };
  private BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (("android.intent.action.KEYCODE_MUTE".equals(paramAnonymousIntent.getAction())) && (mTts != null)) {}
      try
      {
        mTts.stop();
        mUtteranceIds.clear();
        return;
      }
      catch (IllegalArgumentException paramAnonymousContext)
      {
        for (;;)
        {
          MyLog.e("SmsReportService", "Fail to stop tts", paramAnonymousContext);
        }
      }
    }
  };
  private TextToSpeech mTts;
  private ContentObserver mTtsObserver = new ContentObserver(null)
  {
    public void onChange(boolean paramAnonymousBoolean)
    {
      super.onChange(paramAnonymousBoolean);
      if (mTts != null)
      {
        mTts.shutdown();
        SmsReportService.access$002(SmsReportService.this, null);
      }
      SmsReportService.this.initTts();
    }
  };
  private List<String> mUtteranceIds;
  
  private static void gainFocus()
  {
    ((AudioManager)MmsApp.getApp().getSystemService("audio")).requestAudioFocus(null, 3, 2);
  }
  
  private void initTts()
  {
    if ((mTts == null) || (mTts.getCurrentEngine() == null))
    {
      if (mTts != null)
      {
        mTts.shutdown();
        mTts = null;
      }
      mTts = new TextToSpeech(getApplicationContext(), this);
    }
  }
  
  public static void releaseFocus()
  {
    ((AudioManager)MmsApp.getApp().getSystemService("audio")).abandonAudioFocus(null);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    mUtteranceIds = new ArrayList();
    Object localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.KEYCODE_MUTE");
    registerReceiver(mReceiver, (IntentFilter)localObject);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.HEADSET_PLUG");
    registerReceiver(mPlugReceiver, (IntentFilter)localObject);
    localObject = Settings.Secure.getUriFor("tts_default_synth");
    getContentResolver().registerContentObserver((Uri)localObject, false, mTtsObserver);
  }
  
  public void onDestroy()
  {
    if (mTts != null) {
      mTts.shutdown();
    }
    unregisterReceiver(mReceiver);
    unregisterReceiver(mPlugReceiver);
    getContentResolver().unregisterContentObserver(mTtsObserver);
    super.onDestroy();
  }
  
  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      if (mTts != null)
      {
        paramInt = mTts.setLanguage(Locale.CHINA);
        if ((paramInt == -1) || (paramInt == -2)) {
          MyLog.e("SmsReportService", "Language is not available.");
        }
      }
      else
      {
        return;
      }
      MyLog.v("SmsReportService", "init success");
      mTts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener()
      {
        public void onUtteranceCompleted(String paramAnonymousString) {}
      });
      return;
    }
    MyLog.e("SmsReportService", "Could not initialize TextToSpeech.");
    try
    {
      if (mTts != null) {
        mTts.shutdown();
      }
      mTts = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        MyLog.e("SmsReportService", "Fail to shutdown tts", localIllegalArgumentException);
      }
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    initTts();
    if (paramIntent == null) {
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    Object localObject = paramIntent.getExtras();
    if (localObject == null) {
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    if (!MSimUtils.isCallStateIdle()) {
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    String str = "[n1]" + ((Bundle)localObject).getString("sms_address");
    char c = ((Bundle)localObject).getChar("sms_contact");
    if (c == '1') {}
    for (str = getString(2131362210) + "," + ((Bundle)localObject).getString("sms_name");; str = getString(2131362210) + "," + getString(2131362209) + " " + str)
    {
      str = str + ". " + ((Bundle)localObject).getString("sms_body");
      HashMap localHashMap = new HashMap();
      localHashMap.put("com.miui.voiceassist.priority", "800");
      int i = MSimUtils.getSlotIdFromBundle((Bundle)localObject);
      localObject = i + "__" + ((Bundle)localObject).getString("sms_address") + "__" + c + "__" + paramInt2;
      mUtteranceIds.add(localObject);
      localHashMap.put("utteranceId", localObject);
      localHashMap.put("streamType", "3");
      gainFocus();
      if (mTts.speak(str, 1, localHashMap) == -1) {
        initTts();
      }
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReportService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */