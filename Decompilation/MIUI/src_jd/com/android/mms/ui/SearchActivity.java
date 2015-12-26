package com.android.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import miui.app.Activity;

public class SearchActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if ("android.intent.action.VIEW".equals(paramBundle.getAction())) {
      ComposeMessageRouterActivity.route(this, paramBundle);
    }
    finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */