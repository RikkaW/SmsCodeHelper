package com.android.mms.ui;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.android.mms.MmsApp;

public class AttachmentTypeListAdapter$AttachmentTypeItem
{
  public Drawable iconRes;
  public boolean jump;
  public int nameRes;
  
  public AttachmentTypeListAdapter$AttachmentTypeItem(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    nameRes = paramInt1;
    iconRes = MmsApp.getApp().getResources().getDrawable(paramInt2);
    jump = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentTypeListAdapter.AttachmentTypeItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */