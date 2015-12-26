package com.android.mms.ui;

import android.text.ClipboardManager;
import android.widget.Toast;

class NewMessageActivity$14$3
  implements Runnable
{
  NewMessageActivity$14$3(NewMessageActivity.14 param14) {}
  
  public void run()
  {
    ((ClipboardManager)this$1.this$0.getSystemService("clipboard")).setText(this$1.val$origNumber);
    Toast.makeText(this$1.this$0, 2131362081, 0).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.14.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */