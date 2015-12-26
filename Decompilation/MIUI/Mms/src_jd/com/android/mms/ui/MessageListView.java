package com.android.mms.ui;

import android.content.Context;
import android.text.ClipboardManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.android.mms.util.EditableListView;

public class MessageListView
  extends EditableListView
{
  private boolean mAllowTranscriptionOnResize = true;
  
  public MessageListView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MessageListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    Object localObject;
    do
    {
      do
      {
        return super.onKeyShortcut(paramInt, paramKeyEvent);
        localObject = (MessageListItem)getSelectedView();
      } while (localObject == null);
      localObject = ((MessageListItem)localObject).getMessageItem();
    } while ((localObject == null) || (!((MessageItem)localObject).isSms()));
    ((ClipboardManager)getContext().getSystemService("clipboard")).setText(((MessageItem)localObject).getBody());
    return true;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (mAllowTranscriptionOnResize) {
      moveToEnd();
    }
  }
  
  public void setAllowTranscriptOnResize(boolean paramBoolean)
  {
    mAllowTranscriptionOnResize = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */