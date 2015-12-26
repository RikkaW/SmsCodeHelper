package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

class NewMessageActivity$4
  implements View.OnClickListener
{
  NewMessageActivity$4(NewMessageActivity paramNewMessageActivity) {}
  
  public void onClick(View paramView)
  {
    NewMessageActivity.access$200(this$0).setVisibility(8);
    NewMessageActivity.access$300(this$0).setVisibility(0);
    NewMessageActivity.access$400(this$0).requestFocus();
    NewMessageActivity.access$300(this$0).scrollTo(0, NewMessageActivity.access$500(this$0).getMeasuredHeight());
    this$0.showSoftKeyboard();
    NewMessageActivity.access$600(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */