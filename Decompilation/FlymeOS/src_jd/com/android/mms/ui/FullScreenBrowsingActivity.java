package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import un;

public class FullScreenBrowsingActivity
  extends Activity
{
  private TextView a;
  
  public void finish()
  {
    super.finish();
    overridePendingTransition(2131034127, 2131034128);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968670);
    a = ((TextView)findViewById(2131886468));
    a.setText(getIntent().getStringExtra("fullScreenBrowsingText"));
    a.setOnClickListener(new un(this));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1) {
      finish();
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FullScreenBrowsingActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */