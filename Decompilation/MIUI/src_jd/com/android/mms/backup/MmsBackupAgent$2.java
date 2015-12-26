package com.android.mms.backup;

import android.text.TextUtils;
import java.util.Comparator;

class MmsBackupAgent$2
  implements Comparator<MmsProtos.Pdu>
{
  MmsBackupAgent$2(MmsBackupAgent paramMmsBackupAgent) {}
  
  public int compare(MmsProtos.Pdu paramPdu1, MmsProtos.Pdu paramPdu2)
  {
    return TextUtils.join(",", paramPdu1.getAddrsList()).compareTo(TextUtils.join(",", paramPdu2.getAddrsList()));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsBackupAgent.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */