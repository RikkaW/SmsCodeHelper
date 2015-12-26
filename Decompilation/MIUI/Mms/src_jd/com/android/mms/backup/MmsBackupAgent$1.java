package com.android.mms.backup;

import java.util.Comparator;

class MmsBackupAgent$1
  implements Comparator<SmsProtos.Sms>
{
  MmsBackupAgent$1(MmsBackupAgent paramMmsBackupAgent) {}
  
  public int compare(SmsProtos.Sms paramSms1, SmsProtos.Sms paramSms2)
  {
    return paramSms1.getAddress().compareTo(paramSms2.getAddress());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsBackupAgent.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */