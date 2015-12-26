package com.android.mms.ui;

import android.os.Bundle;
import android.view.Window;

public class ComposeMessageActivityNoLockScreen
  extends ComposeMessageActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    getWindow().addFlags(4718592);
    super.onCreate(paramBundle);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivityNoLockScreen
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */