package com.android.mms.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.mms.transaction.MxFeedbackService;
import miui.app.Activity;

public class MxFeedbackActivity
  extends Activity
{
  private EditText mDsptEditView;
  private Button mSubmitBtn;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968673);
    mDsptEditView = ((EditText)findViewById(2131820804));
    mSubmitBtn = ((Button)findViewById(2131820805));
    mSubmitBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = mDsptEditView.getText().toString();
        if (TextUtils.isEmpty(paramAnonymousView))
        {
          Toast.makeText(MxFeedbackActivity.this, 2131362286, 0).show();
          return;
        }
        MxFeedbackService.startMxFeedback(MxFeedbackActivity.this, paramAnonymousView);
        finish();
      }
    });
  }
  
  protected void onResume()
  {
    super.onResume();
    mDsptEditView.requestFocus();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MxFeedbackActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */