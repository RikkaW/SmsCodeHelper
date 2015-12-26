package com.android.mms;

import miui.external.ApplicationDelegate;

public class Application
  extends miui.external.Application
{
  public ApplicationDelegate onCreateApplicationDelegate()
  {
    return new MmsApp();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.Application
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */