package com.android.mms.ui;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

class NewMessagePopupActivity$4
  implements TextView.OnEditorActionListener
{
  NewMessagePopupActivity$4(NewMessagePopupActivity paramNewMessagePopupActivity) {}
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (TextUtils.isEmpty(NewMessagePopupActivity.access$300(this$0).getText())) {
        break label30;
      }
      NewMessagePopupActivity.access$400(this$0);
    }
    for (;;)
    {
      return false;
      label30:
      Toast.makeText(this$0, 2131361865, 1).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */