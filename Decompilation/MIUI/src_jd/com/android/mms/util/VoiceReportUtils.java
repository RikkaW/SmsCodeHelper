package com.android.mms.util;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings.System;
import com.android.mms.data.Contact;
import com.android.mms.transaction.SmsReportService;

public class VoiceReportUtils
{
  public static boolean checkVoiceReport(Context paramContext)
  {
    int i = Settings.System.getInt(paramContext.getContentResolver(), "voiceassist_report_method", 2);
    int j = Settings.System.getInt(paramContext.getContentResolver(), "voiceassist_sms_report", 1);
    if ((i == 2) || (j <= 0)) {}
    do
    {
      return false;
      if (i != 0) {
        break;
      }
      paramContext = (AudioManager)paramContext.getSystemService("audio");
    } while ((!paramContext.isWiredHeadsetOn()) && (!paramContext.isBluetoothScoOn()));
    return true;
  }
  
  public static void voiceReport(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    Contact localContact = Contact.get(paramString1).load(true, true);
    char c2 = '0';
    char c1 = c2;
    if (localContact != null)
    {
      c1 = c2;
      if (localContact.getRealName() != null)
      {
        c1 = c2;
        if (!localContact.getRealName().trim().equals("")) {
          c1 = '1';
        }
      }
    }
    if (!checkVoiceReport(paramContext)) {
      return;
    }
    Intent localIntent = new Intent(paramContext, SmsReportService.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("sms_address", paramString1);
    localBundle.putString("sms_body", paramString2);
    if (c1 == '1') {
      localBundle.putString("sms_name", localContact.getName());
    }
    localBundle.putChar("sms_contact", c1);
    localBundle.putInt(MSimUtils.SLOT_ID, paramInt);
    localIntent.putExtras(localBundle);
    paramContext.startService(localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.VoiceReportUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */