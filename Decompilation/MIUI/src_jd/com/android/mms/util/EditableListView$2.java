package com.android.mms.util;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class EditableListView$2
  implements AdapterView.OnItemClickListener
{
  EditableListView$2(EditableListView paramEditableListView) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    EditableListView.access$002(this$0, paramAdapterView);
    EditableListView.access$102(this$0, paramView);
    EditableListView.access$202(this$0, paramInt);
    EditableListView.access$302(this$0, paramLong);
    if (!EditableListView.access$500(this$0))
    {
      EditableListView.access$602(this$0, paramInt);
      EditableListView.access$502(this$0, true);
      EditableListView.access$702(this$0, EditableListView.access$800(this$0).obtainMessage());
      EditableListView.access$800(this$0).removeMessages(1);
      access$700this$0).what = 1;
      EditableListView.access$800(this$0).sendMessageDelayed(EditableListView.access$700(this$0), EditableListView.access$900());
      return;
    }
    if (EditableListView.access$600(this$0) == paramInt)
    {
      EditableListView.access$800(this$0).removeMessages(1);
      EditableListView.access$202(this$0, paramInt);
      EditableListView.access$702(this$0, EditableListView.access$800(this$0).obtainMessage());
      access$700this$0).what = 2;
      EditableListView.access$800(this$0).sendMessageAtFrontOfQueue(EditableListView.access$700(this$0));
      EditableListView.access$502(this$0, false);
      return;
    }
    EditableListView.access$702(this$0, EditableListView.access$800(this$0).obtainMessage());
    EditableListView.access$800(this$0).removeMessages(1);
    EditableListView.access$502(this$0, true);
    access$700this$0).what = 1;
    EditableListView.access$602(this$0, paramInt);
    EditableListView.access$800(this$0).sendMessageDelayed(EditableListView.access$700(this$0), EditableListView.access$900());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */