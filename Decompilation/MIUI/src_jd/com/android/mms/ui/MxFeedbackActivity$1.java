package com.android.mms.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.mms.transaction.MxFeedbackService;

class MxFeedbackActivity$1
  implements View.OnClickListener
{
  MxFeedbackActivity$1(MxFeedbackActivity paramMxFeedbackActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = MxFeedbackActivity.access$000(this$0).getText().toString();
    if (TextUtils.isEmpty(paramView))
    {
      Toast.makeText(this$0, 2131362286, 0).show();
      return;
    }
    MxFeedbackService.startMxFeedback(this$0, paramView);
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MxFeedbackActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */