package com.meizu.update.display;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import anf;

public class KeyguardHelperActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    anf.c("KeyguardHelperActivity create");
    super.onCreate(paramBundle);
    paramBundle = getWindow();
    paramBundle.addFlags(4194304);
    paramBundle.addFlags(524288);
    paramBundle.addFlags(2097152);
    finish();
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.display.KeyguardHelperActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */