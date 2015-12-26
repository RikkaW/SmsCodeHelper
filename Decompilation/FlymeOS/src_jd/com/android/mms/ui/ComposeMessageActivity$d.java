package com.android.mms.ui;

import gk;
import vx;
import xv;
import zv.a;

class ComposeMessageActivity$d
  implements zv.a
{
  private ComposeMessageActivity$d(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(int paramInt1, int paramInt2)
  {
    if (ComposeMessageActivity.q(a)) {}
    do
    {
      return;
      ComposeMessageActivity.p(a);
      if (ComposeMessageActivity.u(a) != null)
      {
        ComposeMessageActivity.u(a).a();
        ComposeMessageActivity.al(a);
        ComposeMessageActivity.u(a).b(ComposeMessageActivity.t(a));
      }
      a.invalidateOptionsMenu();
      if (ComposeMessageActivity.am(a) != null) {
        ComposeMessageActivity.am(a).a();
      }
    } while (!ComposeMessageActivity.b(a, paramInt1, paramInt2));
    a.b.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */