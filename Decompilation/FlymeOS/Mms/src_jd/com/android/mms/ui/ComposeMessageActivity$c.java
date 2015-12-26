package com.android.mms.ui;

import gk.c;

class ComposeMessageActivity$c
  implements gk.c
{
  private ComposeMessageActivity$c(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(int paramInt)
  {
    if (ComposeMessageActivity.t(a) != paramInt)
    {
      ComposeMessageActivity.i(a, paramInt);
      if (ComposeMessageActivity.t(a) != -10) {
        break label56;
      }
      ComposeMessageActivity.j(a, 1);
    }
    for (;;)
    {
      ComposeMessageActivity.s(a);
      ComposeMessageActivity.ag(a);
      return;
      label56:
      ComposeMessageActivity.j(a, 0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */