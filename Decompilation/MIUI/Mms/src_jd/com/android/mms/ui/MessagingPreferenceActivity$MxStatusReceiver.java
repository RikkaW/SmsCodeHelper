package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.CheckBoxPreference;

class MessagingPreferenceActivity$MxStatusReceiver
  extends BroadcastReceiver
{
  private MessagingPreferenceActivity$MxStatusReceiver(MessagingPreferenceActivity paramMessagingPreferenceActivity) {}
  
  private void updateMxPrefStatusSummary(int paramInt1, int paramInt2)
  {
    if ((MessagingPreferenceActivity.access$200(this$0)) && (paramInt1 != 0))
    {
      if (MessagingPreferenceActivity.access$300(this$0) == null) {
        break label36;
      }
      MessagingPreferenceActivity.access$300(this$0).setSummary(paramInt1);
    }
    label36:
    do
    {
      return;
      if ((MessagingPreferenceActivity.access$400(this$0) != null) && (paramInt2 == 0))
      {
        MessagingPreferenceActivity.access$400(this$0).setSummary(paramInt1);
        return;
      }
    } while ((MessagingPreferenceActivity.access$500(this$0) == null) || (1 != paramInt2));
    MessagingPreferenceActivity.access$500(this$0).setSummary(paramInt1);
  }
  
  int getDsptResId(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 2: 
    case 5: 
    case 8: 
    case 14: 
    case 16: 
    case 18: 
    case 20: 
    case 22: 
    case 23: 
    case 25: 
      return 2131362389;
    case 3: 
    case 15: 
      return 2131362393;
    case 4: 
    case 6: 
    case 7: 
    case 11: 
    case 12: 
    case 13: 
    case 17: 
    case 19: 
    case 21: 
    case 24: 
      return 2131362388;
    case 9: 
      return 2131362392;
    }
    return 2131362387;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("message", 1);
    if ("miui.intent.action.MX_STATUS_SLOT_1_DETAIL".equals(paramIntent.getAction())) {
      updateMxPrefStatusSummary(getDsptResId(i), 0);
    }
    for (;;)
    {
      MessagingPreferenceActivity.access$100(this$0);
      return;
      if ("miui.intent.action.MX_STATUS_SLOT_2_DETAIL".equals(paramIntent.getAction())) {
        updateMxPrefStatusSummary(getDsptResId(i), 1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity.MxStatusReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */