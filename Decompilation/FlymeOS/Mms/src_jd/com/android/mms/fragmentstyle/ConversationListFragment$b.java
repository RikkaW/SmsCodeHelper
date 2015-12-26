package com.android.mms.fragmentstyle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import gr;
import gr.b;
import java.util.Collection;
import zt;

public class ConversationListFragment$b
  implements DialogInterface.OnClickListener
{
  private final Collection<Long> a;
  private final gr.b b;
  private final Context c;
  private boolean d;
  private Runnable e;
  
  public ConversationListFragment$b(Collection<Long> paramCollection, gr.b paramb, Context paramContext)
  {
    a = paramCollection;
    b = paramb;
    c = paramContext;
  }
  
  public void a()
  {
    if (a == null)
    {
      gr.a(b, 1801, d);
      zt.c().a();
    }
    for (;;)
    {
      if (e != null) {
        e.run();
      }
      return;
      gr.a(b, 1801, d, a);
      ConversationListFragment.a(a, false);
    }
  }
  
  public void a(Runnable paramRunnable)
  {
    e = paramRunnable;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    a();
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */