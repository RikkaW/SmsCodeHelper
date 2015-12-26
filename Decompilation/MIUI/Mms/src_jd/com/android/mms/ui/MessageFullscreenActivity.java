package com.android.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import miui.app.Activity;

public class MessageFullscreenActivity
  extends Activity
{
  private void finishWithAnimation()
  {
    finish();
    overridePendingTransition(0, 2131034117);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    overridePendingTransition(0, 2131034117);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968639);
    paramBundle = getIntent().getStringExtra("body");
    ((TextView)findViewById(2131820578)).setText(paramBundle);
    ((RelativeLayout)findViewById(2131820715)).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
          MessageFullscreenActivity.this.finishWithAnimation();
        }
        return true;
      }
    });
    ((LinearLayout)findViewById(2131820717)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageFullscreenActivity.this.finishWithAnimation();
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageFullscreenActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */