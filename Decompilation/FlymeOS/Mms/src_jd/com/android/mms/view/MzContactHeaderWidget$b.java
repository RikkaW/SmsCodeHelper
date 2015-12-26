package com.android.mms.view;

import android.os.AsyncTask;
import gm;
import gq;

class MzContactHeaderWidget$b
  extends AsyncTask<Integer, Void, Void>
{
  private String[] b;
  
  public MzContactHeaderWidget$b(MzContactHeaderWidget paramMzContactHeaderWidget, String[] paramArrayOfString)
  {
    b = paramArrayOfString;
  }
  
  protected Void a(Integer... paramVarArgs)
  {
    int i = 0;
    while (i < paramVarArgs[0].intValue())
    {
      b[i] = ((gm)a.b.get(i)).g();
      i += 1;
    }
    return null;
  }
  
  protected void a(Void paramVoid)
  {
    MzContactHeaderWidget.i(a);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MzContactHeaderWidget.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */