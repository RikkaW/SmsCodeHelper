package com.android.mms.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import com.android.mms.MmsApp;
import com.android.mms.view.MessageListItemBase;
import vv;
import zv;

public class MessageListView$e
  implements ActionMode.Callback
{
  private MessageListItemBase b;
  private int c = -1;
  private long d = 0L;
  
  public MessageListView$e(MessageListView paramMessageListView, MessageListItemBase paramMessageListItemBase, int paramInt, long paramLong)
  {
    b = paramMessageListItemBase;
    c = paramInt;
    d = paramLong;
  }
  
  public MessageListItemBase a()
  {
    return b;
  }
  
  public void a(MessageListItemBase paramMessageListItemBase, int paramInt, long paramLong)
  {
    b = paramMessageListItemBase;
    c = paramInt;
    d = paramLong;
  }
  
  public int b()
  {
    return c;
  }
  
  public long c()
  {
    return d;
  }
  
  public boolean d()
  {
    return (b == null) || (c == -1) || (d == 0L);
  }
  
  public void e()
  {
    b.setSelected(false);
    b = null;
    c = -1;
    d = 0L;
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    if (MessageListView.e(a) == null) {
      return false;
    }
    MessageListView.e(a).a(paramMenuItem.getItemId(), b);
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (MessageListView.d(a)) {
      paramMenu.add(4, 4, 1, 2131493358);
    }
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode) {}
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    paramActionMode = b.getMessageItem();
    if (paramActionMode != null)
    {
      Log.d("MessageListView", "msgItem.mMessageType = " + u + ", uri = " + paramActionMode.K() + ", TextUtils.isEmpty(msgItem.mBody) = " + TextUtils.isEmpty(o));
      if ((paramActionMode != null) && (!MessageListView.a(a, paramActionMode)) && (u != 130) && (M != vv.a) && (!paramActionMode.o()) && (!paramActionMode.F()) && (MessageListView.d(a))) {
        break label347;
      }
      paramMenu.removeItem(1);
      label133:
      if ((paramActionMode != null) && (u != 130) && (!paramActionMode.o()) && (!paramActionMode.F()) && (!TextUtils.isEmpty(o))) {
        break label362;
      }
      paramMenu.removeItem(2);
      paramMenu.removeItem(3);
      label185:
      if ((MmsApp.a) || (MmsApp.b)) {
        paramMenu.add(8, 8, 0, 2131493163);
      }
      if ((!MmsApp.d) && (MessageListView.d(a)) && (paramActionMode != null))
      {
        if (!paramActionMode.ad()) {
          break label396;
        }
        paramMenu.add(6, 6, 3, 2131493463);
      }
    }
    for (;;)
    {
      if ((MmsApp.a) && (paramActionMode.q()) && (zv.e >= 1)) {
        paramMenu.add(7, 7, 3, 2131493277);
      }
      if ((MmsApp.b) && (paramActionMode.q()) && (zv.e >= 1)) {
        paramMenu.add(7, 7, 3, 2131493703);
      }
      paramMenu.add(9, 9, 2, 2131493718);
      return true;
      Log.e("MessageListView", "null, null, null");
      break;
      label347:
      paramMenu.add(1, 1, 0, 2131493359);
      break label133;
      label362:
      paramMenu.add(2, 2, 0, 2131493357);
      if (paramActionMode.ak()) {
        break label185;
      }
      paramMenu.add(3, 3, 0, 2131493362);
      break label185;
      label396:
      if (u != 130) {
        paramMenu.add(5, 5, 3, 2131493455);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListView.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */