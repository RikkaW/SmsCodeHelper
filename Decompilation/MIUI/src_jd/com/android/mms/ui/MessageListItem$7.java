package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.understand.UnderstandAction;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MessageListItem$7
  implements View.OnClickListener
{
  MessageListItem$7(MessageListItem paramMessageListItem, UnderstandMessage paramUnderstandMessage, int paramInt, MessageItem paramMessageItem) {}
  
  public void onClick(View paramView)
  {
    MiStatSdkHelper.recordUnderstandButtonClick(val$msg.mActionID, val$index);
    Object localObject = UnderstandFactory.getButtonActions(val$msg.mActionID, val$index);
    if (localObject != null)
    {
      paramView = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        UnderstandAction localUnderstandAction = UnderstandFactory.parseActionString((String)((Iterator)localObject).next());
        if (localUnderstandAction != null) {
          paramView.add(localUnderstandAction);
        }
      }
      int i = MSimUtils.getSlotIdBySimInfoId(val$msgItem.getSimId());
      UnderstandFactory.doAction(MessageListItem.access$1300(this$0), paramView, val$msg, i);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */