package com.android.mms.ui;

import android.os.AsyncTask;

class ManageSimMessages$a
  extends AsyncTask<Integer, Void, Integer>
{
  private long[] b;
  private int c;
  
  public ManageSimMessages$a(ManageSimMessages paramManageSimMessages, long[] paramArrayOfLong, int paramInt)
  {
    b = paramArrayOfLong;
    c = paramInt;
  }
  
  protected Integer a(Integer... paramVarArgs)
  {
    int i = -1;
    switch (c)
    {
    }
    for (;;)
    {
      return Integer.valueOf(i);
      ManageSimMessages.a(a, b);
      continue;
      i = ManageSimMessages.b(a, b);
    }
  }
  
  protected void a(Integer paramInteger)
  {
    b = null;
    if (paramInteger.intValue() < 1)
    {
      ManageSimMessages.h(a);
      return;
    }
    ManageSimMessages.a(a, false);
  }
  
  protected void onPreExecute()
  {
    ManageSimMessages.a(a, c);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */