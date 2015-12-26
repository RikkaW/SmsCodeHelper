package com.android.mms.update;

import android.content.Context;

final class Push$1
  implements Runnable
{
  Push$1(Context paramContext) {}
  
  public void run()
  {
    Push.subscribeDefaultTopics(val$context);
    Push.setAliasByUserId(val$context, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.Push.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */