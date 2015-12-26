package com.android.mms.util;

import android.os.Handler;
import android.os.Message;

class EditableListView$1
  extends Handler
{
  EditableListView$1(EditableListView paramEditableListView) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    case 1: 
      EditableListView.access$400(this$0).onSingleClick(EditableListView.access$000(this$0), EditableListView.access$100(this$0), EditableListView.access$200(this$0), EditableListView.access$300(this$0));
      EditableListView.access$502(this$0, false);
      return;
    }
    EditableListView.access$400(this$0).onDoubleClick(EditableListView.access$000(this$0), EditableListView.access$100(this$0), EditableListView.access$200(this$0), EditableListView.access$300(this$0));
    EditableListView.access$502(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */