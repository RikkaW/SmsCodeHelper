package com.android.mms.ui;

import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.PopupMenu;
import pv;

public class AttachmentEditor$a
  implements View.OnLongClickListener
{
  private int b;
  private View c;
  
  public AttachmentEditor$a(AttachmentEditor paramAttachmentEditor, int paramInt, View paramView)
  {
    b = paramInt;
    c = paramView;
  }
  
  public boolean onLongClick(View paramView)
  {
    paramView = new PopupMenu(AttachmentEditor.a(a), c);
    paramView.getMenuInflater().inflate(2131951622, paramView.getMenu());
    paramView.setOnMenuItemClickListener(new pv(this));
    paramView.show();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentEditor.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */