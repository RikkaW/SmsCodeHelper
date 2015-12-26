package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditSlideDurationActivity
  extends Activity
{
  private int mCurSlide;
  private Button mDone;
  private EditText mDur;
  private TextView mLabel;
  private final View.OnClickListener mOnDoneClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      editDone();
    }
  };
  private final View.OnKeyListener mOnKeyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if (paramAnonymousKeyEvent.getAction() != 0) {
        return false;
      }
      switch (paramAnonymousInt)
      {
      default: 
        return false;
      }
      editDone();
      return false;
    }
  };
  private Bundle mState;
  private int mTotal;
  
  private void notifyUser(int paramInt)
  {
    mDur.requestFocus();
    mDur.selectAll();
    Toast.makeText(this, paramInt, 0).show();
  }
  
  protected void editDone()
  {
    String str = mDur.getText().toString();
    try
    {
      int i = Integer.valueOf(str).intValue();
      if (i <= 0)
      {
        notifyUser(2131361887);
        return;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      notifyUser(2131361886);
      return;
    }
    setResult(-1, new Intent(mDur.getText().toString()));
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968604);
    MessageUtils.setWindowLayoutParams(this);
    if (paramBundle == null)
    {
      paramBundle = getIntent();
      mCurSlide = paramBundle.getIntExtra("slide_index", 1);
      mTotal = paramBundle.getIntExtra("slide_total", 1);
    }
    for (int i = paramBundle.getIntExtra("dur", 8);; i = mState.getInt("dur", 8))
    {
      mLabel = ((TextView)findViewById(2131820628));
      mLabel.setText(getString(2131361883) + " " + (mCurSlide + 1) + "/" + mTotal);
      mDur = ((EditText)findViewById(2131820629));
      mDur.setText(String.valueOf(i));
      mDur.setOnKeyListener(mOnKeyListener);
      mDone = ((Button)findViewById(2131820630));
      mDone.setOnClickListener(mOnDoneClickListener);
      return;
      mState = paramBundle.getBundle("state");
      mCurSlide = mState.getInt("slide_index", 1);
      mTotal = mState.getInt("slide_total", 1);
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    mState = new Bundle();
    mState.putInt("slide_index", mCurSlide);
    mState.putInt("slide_total", mTotal);
    try
    {
      i = Integer.parseInt(mDur.getText().toString());
      mState.putInt("dur", i);
      paramBundle.putBundle("state", mState);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        int i = 5;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.EditSlideDurationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */