package com.android.mms.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;

class DownloadManager$1
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  DownloadManager$1(DownloadManager paramDownloadManager) {}
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    int j = DownloadManager.access$000(this$0).length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        long l = MSimUtils.getSimIdBySlotId(i);
        if (l >= 0L)
        {
          ??? = MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval");
          String str = MSimUtils.createKeyWithSimId(l, "pref_key_mms_retrieval_during_roaming");
          if (((!TextUtils.isEmpty((CharSequence)???)) && (((String)???).equals(paramString))) || ((!TextUtils.isEmpty(str)) && (str.equals(paramString)))) {
            synchronized (DownloadManager.access$100())
            {
              DownloadManager.access$000(this$0)[i] = DownloadManager.access$200(this$0, paramSharedPreferences, l);
            }
          }
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DownloadManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */