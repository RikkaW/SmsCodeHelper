package com.android.mms.transaction;

import java.util.Comparator;

final class MessagingNotification$c
  implements Comparator<MessagingNotification.b>
{
  public int a(MessagingNotification.b paramb1, MessagingNotification.b paramb2)
  {
    return Long.signum(paramb2.a() - paramb1.a());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */