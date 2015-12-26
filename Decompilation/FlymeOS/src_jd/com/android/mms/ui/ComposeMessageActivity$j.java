package com.android.mms.ui;

import vv;

class ComposeMessageActivity$j
  implements Comparable<j>
{
  public long a;
  public long b;
  public vv c;
  
  public ComposeMessageActivity$j(ComposeMessageActivity paramComposeMessageActivity, long paramLong, vv paramvv)
  {
    a = paramLong;
    c = paramvv;
    if (c != null)
    {
      b = c.N;
      return;
    }
    b = 0L;
  }
  
  public int a(j paramj)
  {
    if (c == null) {
      return -1;
    }
    if (c == null) {
      return 1;
    }
    return (int)(b - b);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */