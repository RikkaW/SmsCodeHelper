package com.android.mms.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class AttachmentProcessor$4
  implements AdapterView.OnItemClickListener
{
  AttachmentProcessor$4(AttachmentProcessor paramAttachmentProcessor) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (String)AttachmentProcessor.access$600(this$0).getItem(paramInt);
    AttachmentProcessor.access$100(this$0).insertPhraseSms(paramAdapterView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */