package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import tv;
import tw;
import tx;

public class ConfirmRateLimitActivity
  extends Activity
{
  private long a;
  private Handler b;
  private Runnable c;
  
  private void a(boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.android.mms.RATE_LIMIT_CONFIRMED");
    localIntent.putExtra("answer", paramBoolean);
    sendBroadcast(localIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130968615);
    ((Button)findViewById(2131886239)).setOnClickListener(new tv(this));
    ((Button)findViewById(2131886240)).setOnClickListener(new tw(this));
    b = new Handler();
    c = new tx(this);
    a = System.currentTimeMillis();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
      a(false);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (b != null) {
      b.removeCallbacks(c);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    long l = a - System.currentTimeMillis() + 19500L;
    if (l <= 0L) {
      a(false);
    }
    while (b == null) {
      return;
    }
    b.postDelayed(c, l);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConfirmRateLimitActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */