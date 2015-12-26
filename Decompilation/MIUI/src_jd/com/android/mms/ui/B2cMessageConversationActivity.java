package com.android.mms.ui;

import android.app.Application;
import android.content.res.Resources;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import com.android.mms.MmsApp;

public class B2cMessageConversationActivity
  extends SingleRecipientConversationActivity
{
  private static int sEditContainerMarginLeft = MmsApp.getApp().getResources().getDimensionPixelSize(2131427487);
  private View mSimButtonLeftView;
  
  protected void handleShowSecurityAlert(Cursor paramCursor) {}
  
  protected void initResourceRefs()
  {
    super.initResourceRefs();
    mSwitchBtn.setVisibility(8);
    mEditorContainer.getLayoutParams()).leftMargin = sEditContainerMarginLeft;
    if (mSimButtonContainer != null)
    {
      mSimButtonLeftView = findViewById(2131820697);
      mSimButtonLeftView.setVisibility(8);
      mSendButton1.getLayoutParams()).leftMargin = sEditContainerMarginLeft;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.B2cMessageConversationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */