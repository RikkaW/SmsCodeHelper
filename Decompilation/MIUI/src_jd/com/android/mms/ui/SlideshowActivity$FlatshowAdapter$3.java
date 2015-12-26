package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnLongClickListener;
import miui.view.menu.ContextMenuDialog;

class SlideshowActivity$FlatshowAdapter$3
  implements View.OnLongClickListener
{
  SlideshowActivity$FlatshowAdapter$3(SlideshowActivity.FlatshowAdapter paramFlatshowAdapter, SimplePduPart paramSimplePduPart) {}
  
  public boolean onLongClick(View paramView)
  {
    paramView = new ContextMenuDialog(this$1.this$0);
    paramView.addMenuItem(2131362012, new Runnable()
    {
      public void run()
      {
        String str = MessageUtils.saveMmsPartToDisk(this$1.this$0, val$part, SlideshowActivity.FlatshowAdapter.access$100(this$1));
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$1.this$0);
        localBuilder.setTitle(2131362012);
        localBuilder.setIconAttribute(16843605);
        if (str != null) {
          localBuilder.setMessage(this$1.this$0.getString(2131362013, new Object[] { str }));
        }
        for (;;)
        {
          localBuilder.setNeutralButton(17039370, null);
          localBuilder.show();
          return;
          localBuilder.setMessage(2131362014);
        }
      }
    });
    paramView.show();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.FlatshowAdapter.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */