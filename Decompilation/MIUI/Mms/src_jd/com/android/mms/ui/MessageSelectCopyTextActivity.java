package com.android.mms.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import miui.app.Activity;

public class MessageSelectCopyTextActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968664);
    paramBundle = getIntent();
    Object localObject1 = (TextView)findViewById(2131820780);
    Object localObject2 = paramBundle.getStringExtra("extra_contact");
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      ((TextView)localObject1).setText(getResources().getString(2131362372) + (String)localObject2);
      localObject1 = paramBundle.getStringExtra("extra_number");
      localObject2 = (TextView)findViewById(2131820781);
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        break label154;
      }
      ((TextView)localObject2).setText(getResources().getString(2131362373) + (String)localObject1);
    }
    for (;;)
    {
      paramBundle = paramBundle.getStringExtra("extra_body");
      ((TextView)findViewById(2131820783)).setText(paramBundle);
      return;
      ((TextView)localObject1).setVisibility(8);
      break;
      label154:
      ((TextView)localObject2).setVisibility(8);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageSelectCopyTextActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */