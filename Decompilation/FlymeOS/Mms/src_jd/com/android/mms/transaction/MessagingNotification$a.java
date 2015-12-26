package com.android.mms.transaction;

import android.content.Context;

final class MessagingNotification$a
{
  public CharSequence a;
  public long b;
  
  public MessagingNotification$a(CharSequence paramCharSequence, long paramLong)
  {
    a = paramCharSequence;
    b = paramLong;
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    MessagingNotification.a(paramContext, paramBoolean, a, b);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */