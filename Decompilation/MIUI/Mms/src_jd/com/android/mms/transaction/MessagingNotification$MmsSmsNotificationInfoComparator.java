package com.android.mms.transaction;

import java.util.Comparator;

final class MessagingNotification$MmsSmsNotificationInfoComparator
  implements Comparator<MessagingNotification.MmsSmsNotificationInfo>
{
  public int compare(MessagingNotification.MmsSmsNotificationInfo paramMmsSmsNotificationInfo1, MessagingNotification.MmsSmsNotificationInfo paramMmsSmsNotificationInfo2)
  {
    return Long.signum(paramMmsSmsNotificationInfo2.getTime() - paramMmsSmsNotificationInfo1.getTime());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.MmsSmsNotificationInfoComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */