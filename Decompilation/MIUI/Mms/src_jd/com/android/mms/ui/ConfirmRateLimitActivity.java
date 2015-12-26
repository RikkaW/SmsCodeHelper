package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfirmRateLimitActivity
  extends Activity
{
  private long mCreateTime;
  private Handler mHandler;
  private Runnable mRunnable;
  
  private void doAnswer(boolean paramBoolean)
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
    setContentView(2130968591);
    ((Button)findViewById(2131820576)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConfirmRateLimitActivity.this.doAnswer(true);
      }
    });
    ((Button)findViewById(2131820577)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConfirmRateLimitActivity.this.doAnswer(false);
      }
    });
    mHandler = new Handler();
    mRunnable = new Runnable()
    {
      public void run()
      {
        ConfirmRateLimitActivity.this.doAnswer(false);
      }
    };
    mCreateTime = System.currentTimeMillis();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
      doAnswer(false);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (mHandler != null) {
      mHandler.removeCallbacks(mRunnable);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    long l = mCreateTime - System.currentTimeMillis() + 19500L;
    if (l <= 0L) {
      doAnswer(false);
    }
    while (mHandler == null) {
      return;
    }
    mHandler.postDelayed(mRunnable, l);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConfirmRateLimitActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */