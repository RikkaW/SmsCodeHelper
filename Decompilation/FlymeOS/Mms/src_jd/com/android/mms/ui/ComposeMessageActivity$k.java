package com.android.mms.ui;

import android.content.Intent;
import android.net.Uri;
import hb;
import tr;
import ts;
import tt;
import tu;

public class ComposeMessageActivity$k
  implements Runnable
{
  private final Intent b;
  
  ComposeMessageActivity$k(ComposeMessageActivity paramComposeMessageActivity, Intent paramIntent)
  {
    b = paramIntent;
  }
  
  public void run()
  {
    a.p = false;
    Uri localUri = ComposeMessageActivity.a(a, b);
    if (a.isFinishing())
    {
      a.runOnUiThread(new tr(this));
      if (localUri != null) {
        new Thread(new ts(this, localUri), "WorkingMessage.asyncDelete").start();
      }
    }
    do
    {
      return;
      a.c = hb.a(a, localUri, a.c, false);
      if (!a.isFinishing()) {
        break;
      }
      a.runOnUiThread(new tt(this));
    } while (a.c == null);
    a.c.p();
    return;
    if (a.c != null) {
      a.c.a(a.a);
    }
    a.p = true;
    a.runOnUiThread(new tu(this));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */