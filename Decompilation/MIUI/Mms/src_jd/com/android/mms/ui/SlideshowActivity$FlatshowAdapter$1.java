package com.android.mms.ui;

import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.HashSet;
import miui.view.menu.ContextMenuDialog;

class SlideshowActivity$FlatshowAdapter$1
  implements View.OnClickListener
{
  SlideshowActivity$FlatshowAdapter$1(SlideshowActivity.FlatshowAdapter paramFlatshowAdapter) {}
  
  public void onClick(View paramView)
  {
    ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(this$1.this$0);
    paramView = ((TextView)paramView).getUrls();
    HashSet localHashSet = new HashSet();
    if (paramView.length == 1)
    {
      paramView = MessageUtils.getUriInfo(paramView[0].getURL());
      MessageUtils.performUriOperation(this$1.this$0, paramView, null);
      return;
    }
    int i = 0;
    while (i < paramView.length)
    {
      final Object localObject = paramView[i].getURL();
      if (!localHashSet.contains(localObject))
      {
        localHashSet.add(localObject);
        localObject = MessageUtils.getUriInfo((String)localObject);
        localContextMenuDialog.addMenuItem(title, new Runnable()
        {
          public void run()
          {
            MessageUtils.performUriOperation(this$1.this$0, localObject, null);
          }
        });
      }
      i += 1;
    }
    localContextMenuDialog.setTitle(2131362018);
    localContextMenuDialog.show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.FlatshowAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */