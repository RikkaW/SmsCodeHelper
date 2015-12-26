package com.android.mms.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class FlymeMessageActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    paramBundle.setComponent(new ComponentName(this, ComposeMessageActivity.class));
    paramBundle.putExtra("shareByFlymeMsg", true);
    startActivity(paramBundle);
    overridePendingTransition(0, 2131034154);
    finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FlymeMessageActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */