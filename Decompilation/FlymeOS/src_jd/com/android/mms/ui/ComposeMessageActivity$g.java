package com.android.mms.ui;

import android.database.ContentObserver;
import android.os.Handler;
import gr;
import hb;
import tn;
import zt;

public class ComposeMessageActivity$g
  extends ContentObserver
{
  public ComposeMessageActivity$g(ComposeMessageActivity paramComposeMessageActivity, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    super.onChange(paramBoolean);
    if (a.a.i())
    {
      ComposeMessageActivity.av(a);
      a.c.a(a.a);
    }
    while (!zt.c().b()) {
      return;
    }
    new Handler().postDelayed(new tn(this), 200L);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */